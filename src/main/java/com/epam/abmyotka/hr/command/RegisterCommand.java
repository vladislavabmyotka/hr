package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private AccountService receiver;

    public RegisterCommand(AccountService receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
