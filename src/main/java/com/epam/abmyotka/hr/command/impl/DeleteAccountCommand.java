package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class DeleteAccountCommand implements Command {
    private AccountService service;

    public DeleteAccountCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
