package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EmployerVacancyDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerVacancyDeleteCommand.class);

    private AccountService accountService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    public EmployerVacancyDeleteCommand(AccountService accountService, EmployerService employerService,
                                        VacancyService vacancyService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_VIEW_VACANCY, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_VACANCY_DELETE);

        int vacancyId = 0;
        try {
            vacancyId = Integer.parseInt(stringVacancyId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                    e.getMessage());
        }

        if (!vacancyService.delete(vacancyId)) {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);

        if(accountId != 0) {
            Employer employer = employerService.findByAccountId(accountId);
            int employerId = employer.getEmployerId();
            List<Vacancy> vacancies = vacancyService.findAllByEmployerId(employerId);
            if (vacancies.size() != 0) {
                request.setAttribute("vacancyList", vacancies);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
