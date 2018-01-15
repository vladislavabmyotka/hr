package com.epam.abmyotka.hr.command.impl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.service.EmployerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.epam.abmyotka.hr.validator.CandidateEmployerValidator.*;

public class AdminEmployerEditSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AdminEmployerEditSaveCommand.class);

    private EmployerService service;

    public AdminEmployerEditSaveCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_EMPLOYER, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_ID);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);

        if(checkID(stringEmployerId) && checkNames(surname) && checkNames(name) && checkLastname(lastname) &&
                checkPhone(phone) && checkEmail(email)) {
            Employer employer = new Employer(Integer.parseInt(stringEmployerId), surname, name, lastname, address,
                    phone, email, company);
            if (service.update(employer)) {
                List<Employer> employers = service.takeAll();
                request.setAttribute("employerList", employers);
            } else {
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        } else {
            request.setAttribute("errorMessage", MessageConstant.INCORRECT_DATA);
            router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER_EDIT);
            int employerId = 0;
            try {
                employerId = Integer.parseInt(stringEmployerId);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.ERROR, "Error while parsing string value employerId to integer! Detail: " +
                        e.getMessage());
            }
            Employer employer = service.findById(employerId);
            if (employer != null) {
                request.setAttribute("employer", employer);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_ADMIN_EMPLOYER);
                request.setAttribute("errorMessage", MessageConstant.ERROR_ON_WEBSITE);
            }
        }

        return router;
    }
}
