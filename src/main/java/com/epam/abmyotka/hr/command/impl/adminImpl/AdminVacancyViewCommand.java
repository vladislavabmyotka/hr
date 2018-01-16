package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminVacancyViewCommand implements Command {
    private VacancyService vacancyService;
    private EmployerService employerService;

    public AdminVacancyViewCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_VACANCY, Router.RouteType.FORWARD);

        List<Vacancy> vacancies = vacancyService.takeAll();

        for(Vacancy vacancy : vacancies) {
            int employerId = vacancy.getEmployerId();
            Employer employer = employerService.findById(employerId);
            String surname = employer.getSurname();
            String name = employer.getName();
            String lastname = employer.getLastname();
            String email = employer.getEmail();
            vacancy.setEmployerInfo(surname + " " + name + " " + (lastname != null ? lastname : "")  + "\n" + email);
        }

        request.setAttribute("vacancyList", vacancies);

        return router;
    }
}
