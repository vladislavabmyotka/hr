package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.service.EmployerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Implementation of the "admin_employer_view" command.
 *
 * <p>
 *     Retrieves from the database information about all the employers and puts in a request attribute to display
 *     on the page to view information about the employers.
 * </p>
 *
 * @see Command#execute(HttpServletRequest)
 */
public class AdminEmployerViewCommand implements Command {
    private EmployerService service;

    /**
     *  Constructs and initialize commands type of 'admin_employer_view'
     *
     * @param service - instance of the service type of "Employer" to access the database table "employer"
     */
    public AdminEmployerViewCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER, Router.RouteType.FORWARD);

        List<Employer> employers = service.takeAll();
        request.setAttribute("employerList", employers);

        return router;
    }
}
