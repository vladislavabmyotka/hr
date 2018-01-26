package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.CompanyEmailConstant;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.mail.MailThread;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.validator.AccountValidator;
import com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class ForgotPasswordCommand implements Command {
    private AccountService service;

    public ForgotPasswordCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_MAIN, Router.RouteType.FORWARD);

        String login = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        if (AccountValidator.checkLogin(login) && CandidateEmployerVacancyValidator.checkEmail(email)) {
            String password = service.findPasswordByLogin(login);
            if (password != null) {
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.user.name", CompanyEmailConstant.SEND_FROM_EMAIL);
                properties.setProperty("mail.user.password", CompanyEmailConstant.SEND_FROM_EMAIL_PASSWORD);

                Object language = request.getSession(true).getAttribute("language");
                String theme = MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_THEME_MESSAGE);
                String emailMessage = MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART1);
                emailMessage += "\n\n" + MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART2) + " " + password + ".\n\n" +
                        MessageManager.getMessage(language.toString(), MessageConstant.FORGOT_PASSWORD_MESSAGE_PART3) +
                        "\n" + MessageManager.getMessage(language.toString(),
                        MessageConstant.FORGOT_PASSWORD_MESSAGE_PART4);

                MailThread mailOperator = new MailThread(email, CompanyEmailConstant.SEND_FROM_EMAIL, theme,
                        emailMessage, properties);
                mailOperator.start();

                String successfullySent = MessageManager.getMessage(language.toString(),
                        MessageConstant.SUCCESSFULLY_FORGOT_PASSWORD_MESSAGE);
                request.setAttribute("notificationMessage", successfullySent);
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.NON_REGISTERED_MESSAGE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_LOGIN_MESSAGE);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_FORGOT_PASSWORD);
        }

        return router;
    }
}
