package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
