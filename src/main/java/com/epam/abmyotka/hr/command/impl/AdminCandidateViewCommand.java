package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCandidateViewCommand implements Command{
    private CandidateService service;

    public AdminCandidateViewCommand(CandidateService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        List<Candidate> candidates = service.takeAll();
        request.setAttribute("candidateList", candidates);
        router.setPagePath(PathConstant.PATH_PAGE_ADMIN_CANDIDATE);
        router.setRoute(Router.RouteType.FORWARD);

        return router;
    }
}
