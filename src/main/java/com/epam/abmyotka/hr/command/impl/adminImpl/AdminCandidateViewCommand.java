package com.epam.abmyotka.hr.command.impl.adminImpl;

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
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_CANDIDATE, Router.RouteType.FORWARD);

        List<Candidate> candidates = service.takeAll();
        request.setAttribute("candidateList", candidates);

        return router;
    }
}
