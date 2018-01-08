package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ExitCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
