package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminVacancyDeleteCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminVacancyDeleteCommand.class);

    private VacancyService vacancyService;
    private EmployerService employerService;

    public AdminVacancyDeleteCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_VACANCY, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_ADMIN_VACANCY_DELETE);

        int vacancyId = 0;
        try {
            vacancyId = Integer.parseInt(stringVacancyId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                    e.getMessage());
        }

        if (!vacancyService.delete(vacancyId)) {
            request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
        }

        List<Vacancy> vacancies = vacancyService.takeAll();

        for(Vacancy vacancy : vacancies) {
            int employerId = vacancy.getEmployerId();
            Employer employer = employerService.findById(employerId);
            String surname = employer.getSurname();
            String name = employer.getName();
            String lastname = employer.getLastname();
            lastname = lastname != null ? lastname : "";
            String email = employer.getEmail();
            vacancy.setEmployerInfo(surname + " " + name + " " + lastname  + "\n" + email);
        }

        request.setAttribute("vacancyList", vacancies);

        return router;
    }
}
