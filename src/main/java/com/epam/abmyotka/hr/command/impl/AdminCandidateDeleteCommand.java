package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCandidateDeleteCommand implements Command {
    private CandidateService service;

    public AdminCandidateDeleteCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE, Router.RouteType.FORWARD);

        String stringCandidateId = request.getParameter(ParameterConstant.PARAM_ADMIN_CANDIDATE_DELETE);

        int candidateId = Integer.parseInt(stringCandidateId);

        if (!service.delete(candidateId)) {
            request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
        }

        List<Candidate> candidates = service.takeAll();
        request.setAttribute("candidateList", candidates);

        return router;
    }
}
