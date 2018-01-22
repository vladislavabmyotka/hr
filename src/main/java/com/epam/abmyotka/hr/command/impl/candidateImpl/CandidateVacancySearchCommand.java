package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CandidateVacancySearchCommand implements Command {
    private VacancyService vacancyService;
    private EmployerService employerService;

    public CandidateVacancySearchCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VACANCY, Router.RouteType.FORWARD);

        String search = request.getParameter(ParameterConstant.PARAM_SEARCH);
        if (search != null) {
            List<Vacancy> vacancies = vacancyService.findAllByKeyword(search);

            for (Vacancy vacancy : vacancies) {
                int employerId = vacancy.getEmployerId();
                if (employerId != 0) {
                    Employer employer = employerService.findById(employerId);
                    vacancy.setEmployerInfo(employer.getMainInformation());
                } else {
                    vacancy.setEmployerInfo(MessageConstant.EMPLOYER_NOT_ASSIGNED);
                }
            }

            request.setAttribute("vacancyList", vacancies);
        } else {
            Command command = new CandidateVacancyViewCommand(vacancyService, employerService);
            router = command.execute(request);
        }

        return router;
    }
}
