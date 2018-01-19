package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.command.impl.adminImpl.AdminVacancyViewCommand;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;

public class CandidateVacancyViewCommand implements Command {
    private VacancyService vacancyService;
    private EmployerService employerService;

    public CandidateVacancyViewCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Command command = new AdminVacancyViewCommand(vacancyService, employerService);
        Router router = command.execute(request);
        router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE_VACANCY);
        return router;
    }
}
