package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Interview;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.InterviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AdminInterviewEditCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminInterviewEditCommand.class);

    private InterviewService service;

    public AdminInterviewEditCommand(InterviewService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_INTERVIEW_EDIT, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_ADMIN_INTERVIEW_EDIT);
        int interviewId = 0;
        try {
            interviewId = Integer.parseInt(stringInterviewId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value interviewId to integer! Detail: " +
                    e.getMessage());
        }

        Interview interview = service.findById(interviewId);
        if (interview != null) {
            request.setAttribute("interview", interview);
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_INTERVIEW);
            String language = request.getParameter(ParameterConstant.PARAM_LANGUAGE);
            String message = MessageManager.getMessage(language,
                    MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
