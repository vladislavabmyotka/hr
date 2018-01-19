package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminCandidateDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminCandidateDeleteCommand.class);

    private CandidateService service;

    public AdminCandidateDeleteCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_ADMIN_CANDIDATE_DELETE);

        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidate id to integer! Detail: " +
                    e.getMessage());
        }

        if (!service.delete(candidateId)) {
            String language = request.getParameter(ParameterConstant.PARAM_LANGUAGE);
            String message = MessageManager.getMessage(language,
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        Command command = new AdminCandidateViewCommand(service);
        command.execute(request);

        return router;
    }
}
