package com.epam.abmyotka.hr.command;

import com.epam.abmyotka.hr.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The root interface in the command hierarchy.
 */
public interface Command {
    Router execute(HttpServletRequest request);
}
