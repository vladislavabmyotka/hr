package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private AccountService service;

    public RegisterCommand(AccountService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {

        //TODO: заглушка!!!!
        return PathConstant.PATH_PAGE_CANDIDATE;
    }
}
