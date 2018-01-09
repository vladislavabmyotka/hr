package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.command.impl.AuthorizationCommand;
import com.epam.abmyotka.hr.command.impl.DeleteAccountCommand;
import com.epam.abmyotka.hr.command.impl.EditAccountDataCommand;
import com.epam.abmyotka.hr.command.impl.RegisterCommand;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new EditAccountDataCommand(new AccountService())),
    DELETE_ACCOUNT(new DeleteAccountCommand(new AccountService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
