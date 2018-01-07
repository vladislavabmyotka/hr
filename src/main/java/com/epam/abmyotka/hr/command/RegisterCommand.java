package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.constant.AccountAttachmentConstant;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    private AccountService service;

    RegisterCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);
        String repeatPassword = request.getParameter(ParameterConstant.PARAM_REPEAT_PASSWORD);
        String attachment = request.getParameter(ParameterConstant.PARAM_ATTACHMENT);

        HttpSession session = request.getSession(true);

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(password) &&
                AccountValidator.checkAttachment(attachment) && password.equals(repeatPassword)) {
            Account user = new Account(login, password, attachment);
            if (!service.checkCoincidenceByLogin(login)) {
                if(service.addAccount(user)) {
                    session.setAttribute("role", user);
                    if (attachment.equals(AccountAttachmentConstant.CANDIDATE_ATTACHMENT)) {
                        page = PathConstant.PATH_PAGE_CANDIDATE;
                    } else {
                        page = PathConstant.PATH_PAGE_EMPLOYER;
                    }
                }
            } else {
                request.setAttribute("errorLoginPassMessage", MessageConstant.USED_LOGIN_MESSAGE);
                page = PathConstant.PATH_PAGE_REGISTER;
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            page = PathConstant.PATH_PAGE_REGISTER;
        }

        return page;
    }
}
