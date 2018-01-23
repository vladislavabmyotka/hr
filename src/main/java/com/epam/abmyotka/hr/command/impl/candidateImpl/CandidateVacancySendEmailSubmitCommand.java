package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.mail.MailThread;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Properties;

public class CandidateVacancySendEmailSubmitCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    public CandidateVacancySendEmailSubmitCommand(AccountService accountService, CandidateService candidateService,
                                                EmployerService employerService, VacancyService vacancyService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY_SEND_EMAIL, Router.RouteType.FORWARD);

        String to = request.getParameter(ParameterConstant.PARAM_TO);
        String theme = request.getParameter(ParameterConstant.PARAM_THEME);
        String message = request.getParameter(ParameterConstant.PARAM_MESSAGE);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);

        if (!Objects.equals(to, "") & !Objects.equals(theme, "") & !Objects.equals(message, "")) {
            HttpSession session = request.getSession(true);
            Account candidateAccount = (Account) session.getAttribute("role");
            int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

            if (accountId != 0) {
                Candidate candidate = candidateService.findByAccountId(accountId);
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.user.name", candidate.getEmail());
                properties.setProperty("mail.user.password", password);

                MailThread mailOperator = new MailThread(to, candidate.getEmail(), theme, message, properties);
                mailOperator.start();

                Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
                router = command.execute(request);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object language = request.getSession(true).getAttribute("language");
                String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", errorMessage);
            }
        } else {
            Command command = new CandidateVacancySendEmailCommand(employerService);
            router = command.execute(request);
            Object language = request.getSession(true).getAttribute("language");
            String errorMessage = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", errorMessage);
        }

        return router;
    }
}
