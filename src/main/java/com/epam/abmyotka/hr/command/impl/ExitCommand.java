package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class ExitCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
