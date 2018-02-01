package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Implementation of the "admin_vacancy_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the vacancies, and brief information about the HR for each
 *     interview and puts in a request attribute to display on the page to view information about the interviews.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminVacancyViewCommand implements Command {
    private VacancyService vacancyService;
    private EmployerService employerService;

    /**
     *  Constructs and initialize commands type of 'admin_vacancy_view'
     *
     * @param vacancyService - instance of the service type of "Vacancy" to access the database table "vacancy"
     * @param employerService - instance of the service type of "Employer" to access the database table "employer"
     */
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
            if (employerId != 0) {
                Employer employer = employerService.findById(employerId);
                String mainEmployerInformation = employer.getMainInformation();
                vacancy.setEmployerInfo(mainEmployerInformation);
            } else {
                vacancy.setEmployerInfo(MessageConstant.EMPLOYER_NOT_ASSIGNED);
            }
        }

        request.setAttribute("vacancyList", vacancies);

        return router;
    }
}
