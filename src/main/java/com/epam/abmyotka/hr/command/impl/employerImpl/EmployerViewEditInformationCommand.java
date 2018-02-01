package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.EmployerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "employer_view_edit_information" command.
 *
 * <p>
 *     It takes an object of the "Account" type from the session, finds the employer id from the database on
 *     it object and throws to the page for viewing and editing the employer information.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerViewEditInformationCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'employer_view_edit_information'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public EmployerViewEditInformationCommand(AccountService accountService, EmployerService employerService) {
        this.accountService = accountService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_VIEW_EDIT_INFORMATION, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);

        if(accountId != 0) {
            Employer employer = employerService.findByAccountId(accountId);
            if (employer != null) {
                request.setAttribute("employer", employer);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.EMPLOYER_NON_INFO_BEFORE_VIEW_EDIT_INFO);
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
