package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.CandidateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminCandidateEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminCandidateEditCommand.class);

    private CandidateService service;

    public AdminCandidateEditCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE_EDIT, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_ADMIN_CANDIDATE_EDIT);
        int candidateId = 0;
        try {
            candidateId = Integer.parseInt(stringCandidateId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value candidate id to integer! Detail: " +
                    e.getMessage());
        }

        Candidate candidate = service.findById(candidateId);
        if (candidate != null) {
            request.setAttribute("candidate", candidate);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE);
            String language = request.getParameter(ParameterConstant.PARAM_LANGUAGE);
            String message = MessageManager.getMessage(language,
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
