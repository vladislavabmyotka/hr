package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.EmployerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of the "employer_add_information" command.
 *
 * <p>
 *     Extracts from the session object of type "Account" and it finds a match in the database and returns
 *     account id which is checked if there is already a information of this HR in the database and, if successful,
 *     throws to the page adding a information about HR, otherwise gives an error message that the information
 *     already exists.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class EmployerAddInformationCommand implements Command {
    private AccountService accountService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'employer_add_information'
     *
     * @param accountService - instance of the service type of "Account" to access the database table "account"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
    public EmployerAddInformationCommand(AccountService accountService, EmployerService employerService) {
        this.accountService = accountService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER_ADD_INFORMATION, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account employerAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(employerAccount);
        if(accountId != 0) {
            if(!employerService.isExistEmployerByAccountId(accountId)) {
                session.setAttribute("employerAccountId", accountId);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(),
                        MessageConstant.EMPLOYER_ALREADY_EXIST);
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
