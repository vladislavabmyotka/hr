package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Interview;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.InterviewService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;

public class AdminInterviewEditSaveCommand implements Command {
    private InterviewService interviewService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;

    public AdminInterviewEditSaveCommand(InterviewService interviewService, CandidateService candidateService,
                                         VacancyService vacancyService, EmployerService employerService) {
        this.interviewService = interviewService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_INTERVIEW, Router.RouteType.FORWARD);

        String stringInterviewId = request.getParameter(ParameterConstant.PARAM_INTERVIEW_ID);
        String preResult = request.getParameter(ParameterConstant.PARAM_PRE_RESULT);
        String finalResult = request.getParameter(ParameterConstant.PARAM_FINAL_RESULT);

        Interview interview = new Interview(Integer.parseInt(stringInterviewId), preResult, finalResult);
        if (interviewService.update(interview)) {
            Command command = new AdminInterviewViewCommand(interviewService, candidateService, vacancyService,
                    employerService);
            command.execute(request);
        } else {
            request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
        }

        return router;
    }
}
