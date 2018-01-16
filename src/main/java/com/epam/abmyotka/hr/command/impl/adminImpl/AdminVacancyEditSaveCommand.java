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
import java.math.BigDecimal;
import java.util.List;

import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkExperience;
import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkID;
import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkSalary;

public class AdminVacancyEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminVacancyEditSaveCommand.class);

    private VacancyService vacancyService;
    private EmployerService employerService;

    public AdminVacancyEditSaveCommand(VacancyService vacancyService, EmployerService employerService) {
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_VACANCY, Router.RouteType.FORWARD);

        String stringVacancyId = request.getParameter(ParameterConstant.PARAM_VACANCY_ID);
        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        String salary = request.getParameter(ParameterConstant.PARAM_SALARY);
        String location = request.getParameter(ParameterConstant.PARAM_LOCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String conditionVacancy = request.getParameter(ParameterConstant.PARAM_CONDITION_VACANCY);

        if(checkID(stringVacancyId) && checkSalary(salary) && checkExperience(experience)) {
            Vacancy vacancy = new Vacancy(Integer.parseInt(stringVacancyId), post, company, new BigDecimal(salary),
                    location, Integer.parseInt(experience), english, text, conditionVacancy);
            if (vacancyService.update(vacancy)) {
                List<Vacancy> vacancies = vacancyService.takeAll();

                for(Vacancy eachVacancy : vacancies) {
                    int employerId = eachVacancy.getEmployerId();
                    Employer employer = employerService.findById(employerId);
                    String surname = employer.getSurname();
                    String name = employer.getName();
                    String lastname = employer.getLastname();
                    String email = employer.getEmail();
                    lastname = lastname != null ? lastname : "";
                    eachVacancy.setEmployerInfo(surname + " " + name + " " + lastname  + "\n" + email);
                }

                request.setAttribute("vacancyList", vacancies);
            } else {
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        } else {
            request.setAttribute("errorMessage", MessageConstant.INCORRECT_DATA);
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_VACANCY_EDIT);
            int vacancyId = 0;
            try {
                vacancyId = Integer.parseInt(stringVacancyId);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Error while parsing string value vacancyId to integer! Detail: " +
                        e.getMessage());
            }
            Vacancy vacancy = vacancyService.findById(vacancyId);
            if (vacancy != null) {
                request.setAttribute("vacancy", vacancy);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_VACANCY);
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        }

        return router;
    }
}
