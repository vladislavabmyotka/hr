package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.command.impl.*;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.CandidateService;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new EditAccountDataCommand(new AccountService())),
    DELETE_ACCOUNT(new DeleteAccountCommand(new AccountService())),
    ADMIN_CANDIDATE_VIEW(new AdminCandidateViewCommand(new CandidateService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
