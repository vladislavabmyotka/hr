package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.AccountAttachmentConstant;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditAccountDataCommand implements Command {
    private AccountService service;

    public EditAccountDataCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EDIT_ACCOUNT);
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String oldPassword = request.getParameter(ParameterConstant.PARAM_OLD_PASSWORD);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String repeatPassword = request.getParameter(ParameterConstant.PARAM_REPEAT_PASSWORD);

        HttpSession session = request.getSession(true);
        String attachment = ((Account) session.getAttribute("role")).getAttachment();

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(oldPassword) &&
                AccountValidator.checkPassword(password) && password.equals(repeatPassword)) {
            Account user = new Account(login, password, attachment);
            int accountId = service.findAccountIdByPassword(oldPassword);
            if (accountId != 0) {
                if (service.update(user, accountId)) {
                    session.setAttribute("role", user);
                    attachment = ((Account) session.getAttribute("role")).getAttachment();
                    if (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT)) {
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                        router.setRoute(Router.RouteType.REDIRECT);
                    } else {
                        router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                        router.setRoute(Router.RouteType.REDIRECT);
                    }
                } else {
                    request.setAttribute("errorLoginPassMessage", MessageConstant.ERROR_ON_WEBSITE);
                }
            } else {
                request.setAttribute("errorLoginPassMessage", MessageConstant.INCORRECT_PASSWORD_MESSAGE);
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
        }
        return router;
    }
}