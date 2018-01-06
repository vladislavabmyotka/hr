package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.ADMIN_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.EMPLOYER_ATTACHMENT;

public class AuthorizationCommand implements Command {
    private AccountService service;

    public AuthorizationCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String password = request.getParameter(ParameterConstant.PARAM_PASSWORD);

        HttpSession session = request.getSession(true);

        if(AccountValidator.checkLogin(login) && AccountValidator.checkPassword(password)) {
            Account user = new Account(login, password);
            AdminCreator creator = new AdminCreator();
            Account admin = creator.createAdmin();

            Account potentialAdmin = new Account(user.getLogin(), user.getPassword(), ADMIN_ATTACHMENT);

            if (admin.equals(potentialAdmin)) {
                session.setAttribute("role", potentialAdmin);
                page = PathConstant.PATH_PAGE_ADMIN;
            } else {
                Account role = service.findAccount(user);
                if (role != null) {
                    session.setAttribute("role", role);
                    if (role.getAttachment().equals(CANDIDATE_ATTACHMENT))  {
                        page = PathConstant.PATH_PAGE_CANDIDATE;
                    } else if (role.getAttachment().equals(EMPLOYER_ATTACHMENT)) {
                        page = PathConstant.PATH_PAGE_EMPLOYER;
                    }
                } else {
                    request.setAttribute("errorLoginPassMessage", MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
                    page = PathConstant.PATH_PAGE_MAIN;
                }
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageConstant.INCORRECT_LOGIN_PASSWORD_MESSAGE);
            page = PathConstant.PATH_PAGE_MAIN;
        }

        return page;
    }
}
