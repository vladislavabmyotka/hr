package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmployerAddVacancyCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;
    private VacancyService vacancyService;

    public EmployerAddVacancyCommand(AccountService accountService, EmployerService employerService,
                                     VacancyService vacancyService) {
        this.accountService = accountService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_ADD_VACANCY, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);
        if(accountId != 0) {
            if(employerService.isExistEmployerByAccountId(accountId)) {
                session.setAttribute("employerAccountId", accountId);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.NON_EMPLOYER_INFORMATION);
                request.setAttribute("errorMessage", message);
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
