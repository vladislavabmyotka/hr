package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.command.impl.*;
import com.epam.abmyotka.hr.command.impl.accountImpl.AccountDeleteCommand;
import com.epam.abmyotka.hr.command.impl.accountImpl.AccountEditCommand;
import com.epam.abmyotka.hr.command.impl.adminImpl.*;
import com.epam.abmyotka.hr.command.impl.candidateImpl.CandidateAddCommand;
import com.epam.abmyotka.hr.command.impl.candidateImpl.CandidateAddSaveCommand;
import com.epam.abmyotka.hr.command.impl.candidateImpl.CandidateViewEditCommand;
import com.epam.abmyotka.hr.command.impl.candidateImpl.CandidateViewEditSaveCommand;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

public enum CommandType {
    AUTHORIZATION(new AuthorizationCommand(new AccountService())),
    REGISTER(new RegisterCommand(new AccountService())),
    EDIT_ACCOUNT_DATA(new AccountEditCommand(new AccountService())),
    DELETE_ACCOUNT(new AccountDeleteCommand(new AccountService())),
    ADMIN_CANDIDATE_VIEW(new AdminCandidateViewCommand(new CandidateService())),
    ADMIN_CANDIDATE_DELETE(new AdminCandidateDeleteCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT(new AdminCandidateEditCommand(new CandidateService())),
    ADMIN_CANDIDATE_EDIT_SAVE(new AdminCandidateEditSaveCommand(new CandidateService())),
    ADMIN_EMPLOYER_VIEW(new AdminEmployerViewCommand(new EmployerService())),
    ADMIN_EMPLOYER_DELETE(new AdminEmployerDeleteCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT(new AdminEmployerEditCommand(new EmployerService())),
    ADMIN_EMPLOYER_EDIT_SAVE(new AdminEmployerEditSaveCommand(new EmployerService())),
    ADMIN_VACANCY_VIEW(new AdminVacancyViewCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_DELETE(new AdminVacancyDeleteCommand(new VacancyService(), new EmployerService())),
    ADMIN_VACANCY_EDIT(new AdminVacancyEditCommand(new VacancyService())),
    ADMIN_VACANCY_EDIT_SAVE(new AdminVacancyEditSaveCommand(new VacancyService(), new EmployerService())),
    CANDIDATE_ADD(new CandidateAddCommand(new AccountService(), new CandidateService())),
    CANDIDATE_ADD_SAVE(new CandidateAddSaveCommand(new CandidateService())),
    CANDIDATE_VIEW_EDIT(new CandidateViewEditCommand(new AccountService(), new CandidateService())),
    CANDIDATE_VIEW_EDIT_SAVE(new CandidateViewEditSaveCommand(new CandidateService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
