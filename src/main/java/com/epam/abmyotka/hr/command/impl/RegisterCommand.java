package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.AccountAttachmentConstant;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    private AccountService service;

    public RegisterCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_REGISTER, Router.RouteType.FORWARD);
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String repeatPassword = request.getParameter(ParameterConstant.PARAM_REPEAT_PASSWORD);
        String attachment = request.getParameter(ParameterConstant.PARAM_ATTACHMENT);

        HttpSession session = request.getSession(true);

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(password) &&
                AccountValidator.checkAttachment(attachment) && password.equals(repeatPassword)) {
            Account user = new Account(login, password, attachment);
            if (!service.checkCoincidenceByLogin(login)) {
                if(service.add(user)) {
                    session.setAttribute("role", user);
                    if (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT)) {
                        router.setRoute(Router.RouteType.REDIRECT);
                        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                    } else {
                        router.setRoute(Router.RouteType.REDIRECT);
                        router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER);
                    }
                } else {
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.USED_LOGIN_MESSAGE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(),
                    MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
