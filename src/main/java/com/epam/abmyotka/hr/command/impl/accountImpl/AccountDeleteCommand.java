package com.epam.abmyotka.hr.command.impl.accountImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountDeleteCommand implements Command {
    private AccountService accountService;

    public AccountDeleteCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_DELETE_ACCOUNT);
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("role");

        if (accountService.delete(account)) {
            router.setPagePath(PathConstant.PATH_PAGE_MAIN);
            router.setRoute(Router.RouteType.REDIRECT);
            session.invalidate();
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
