package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkExperience;
import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkSalary;

public class EmployerAddVacancySaveCommand implements Command {
    private EmployerService employerService;
    private VacancyService vacancyService;

    public EmployerAddVacancySaveCommand(EmployerService employerService, VacancyService vacancyService) {
        this.employerService = employerService;
        this.vacancyService = vacancyService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);

        String post = request.getParameter(ParameterConstant.PARAM_POST);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        String salary = request.getParameter(ParameterConstant.PARAM_SALARY);
        String location = request.getParameter(ParameterConstant.PARAM_LOCATION);
        String experience = request.getParameter(ParameterConstant.PARAM_EXPERIENCE);
        String english = request.getParameter(ParameterConstant.PARAM_ENGLISH);
        String text = request.getParameter(ParameterConstant.PARAM_TEXT);
        String conditionVacancy = request.getParameter(ParameterConstant.PARAM_CONDITION_VACANCY);
        int accountId = (int)session.getAttribute("employerAccountId");

        Employer employer = employerService.findByAccountId(accountId);
        if (employer != null) {
            if (checkSalary(salary) && checkExperience(experience)) {
                int employerId = employer.getEmployerId();
                Vacancy vacancy = new Vacancy(post, company, new BigDecimal(salary), location,
                        Integer.parseInt(experience), english, text, conditionVacancy, employerId);
                if (!vacancyService.add(vacancy)) {
                    Object language = request.getSession(true).getAttribute("language");
                    String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                    request.setAttribute("errorMessage", message);
                }
            } else {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
                request.setAttribute("errorMessage", message);
                router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_ADD_VACANCY);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
