package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.service.AccountService;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
