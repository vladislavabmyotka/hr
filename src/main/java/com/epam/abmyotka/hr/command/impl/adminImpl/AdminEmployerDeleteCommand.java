package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.EmployerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminEmployerDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminEmployerDeleteCommand.class);

    private EmployerService service;

    public AdminEmployerDeleteCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_ADMIN_EMPLOYER_DELETE);

        int employerId = 0;
        try {
            employerId = Integer.parseInt(stringEmployerId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidate id to integer! Detail: " +
                    e.getMessage());
        }

        if (!service.delete(employerId)) {
            String message = MessageManager.getMessage(request.getParameter(ParameterConstant.PARAM_LANGUAGE),
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        Command command = new AdminEmployerViewCommand(service);
        command.execute(request);

        return router;
    }
}
