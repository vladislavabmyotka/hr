package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.EmployerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminEmployerEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminEmployerEditCommand.class);

    private EmployerService service;

    public AdminEmployerEditCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER_EDIT, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_ADMIN_EMPLOYER_EDIT);
        int employerId = 0;
        try {
            employerId = Integer.parseInt(stringEmployerId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value employer id to integer! Detail: " +
                    e.getMessage());
        }

        Employer employer = service.findById(employerId);
        if (employer != null) {
            request.setAttribute("employer", employer);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
