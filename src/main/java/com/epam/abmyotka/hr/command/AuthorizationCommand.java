package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements Command {
    private AccountService receiver;

    public AuthorizationCommand(AccountService receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String loginValue = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String passwordValue = request.getParameter(ParameterConstant.PARAM_PASSWORD);



        return null;
    }
}
