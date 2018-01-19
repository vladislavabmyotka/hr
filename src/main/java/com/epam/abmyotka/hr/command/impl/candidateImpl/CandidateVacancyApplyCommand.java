package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.entity.Interview;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CandidateVacancyApplyCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CandidateVacancyApplyCommand.class);

    private AccountService accountService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;
    private InterviewService interviewService;

    public CandidateVacancyApplyCommand(AccountService accountService, CandidateService candidateService,
                                        VacancyService vacancyService, EmployerService employerService,
                                        InterviewService interviewService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
        this.interviewService = interviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account candidateAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

        if(accountId != 0) {
            Candidate candidate = candidateService.findByAccountId(accountId);
            if (candidate != null) {
                String stringVacancyId = request.getParameter(ParameterConstant.PARAM_CANDIDATE_VACANCY_APPLY);
                int vacancyId = 0;
                try {
                    vacancyId = Integer.parseInt(stringVacancyId);
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                            e.getMessage());
                }

                Interview interview = new Interview(candidate.getCandidateId(), vacancyId);
                if (!interviewService.checkForExist(interview)) {
                    if (interviewService.add(interview)) {
                        Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                        router = command.execute(request);
                        Object object = request.getSession(true).getAttribute("language");
                        String message = MessageManager.getMessage(object.toString(),
                                MessageConstant.CANDIDATE_SUCCESSFULLY_RESPONDED);
                        request.setAttribute("notificationMessage", message);
                    } else {
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                        Object object = request.getSession(true).getAttribute("language");
                        String message = MessageManager.getMessage(object.toString(),
                                MessageConstant.ERROR_ON_WEBSITE);
                        request.setAttribute("errorMessage", message);
                    }
                } else {
                    Object object = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(object.toString(),
                            MessageConstant.CANDIDATE_ALREADY_RESPONDED_TO_VACANCY);
                    request.setAttribute("errorMessage", message);
                    Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                    router = command.execute(request);
                }
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object object = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(object.toString(),
                        MessageConstant.NON_EXIST_CV);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
            Object object = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(object.toString(),
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
