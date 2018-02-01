package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Implementation of the "admin_candidate_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the candidates and puts in a request attribute to display
 *     on the page to view information about the candidates.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminCandidateViewCommand implements Command{
    private CandidateService service;

    /**
     *  Constructs and initialize commands type of 'admin_candidate_view'
     *
     * @param service - instance of the service type of "Candidate" to access the database table "candidate"
     */
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
