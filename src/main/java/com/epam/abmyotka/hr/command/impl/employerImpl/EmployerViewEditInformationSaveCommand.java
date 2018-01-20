package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.EmployerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkEmail;
import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkNames;
import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.checkPhone;

public class EmployerViewEditInformationSaveCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EmployerViewEditInformationSaveCommand.class);

    private EmployerService service;

    public EmployerViewEditInformationSaveCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER, Router.RouteType.FORWARD);

        String stringEmployerId = request.getParameter(ParameterConstant.PARAM_EMPLOYER_ID);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);

        int employerId = 0;
        try {
            employerId = Integer.parseInt(stringEmployerId);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, "Error while parsing string value employerId to integer! Detail: " +
                    e.getMessage());
        }
        Employer employer = service.findById(employerId);
        if (employer != null) {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);

            if (surname != null) {
                if(checkNames(surname)) {
                    employer.setSurname(surname);
                } else {
                    request.setAttribute("employer", employer);
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_VIEW_EDIT_INFORMATION);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (name != null) {
                if (checkNames(name)) {
                    employer.setName(name);
                } else {
                    request.setAttribute("employer", employer);
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_VIEW_EDIT_INFORMATION);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (lastname != null) {
                employer.setLastname(lastname);
            }

            if (address != null) {
                employer.setAddress(address);
            }

            if (phone != null) {
                if (checkPhone(phone)) {
                    employer.setPhone(phone);
                } else {
                    request.setAttribute("employer", employer);
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_VIEW_EDIT_INFORMATION);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (email != null) {
                if (checkEmail(email)) {
                    employer.setEmail(email);
                } else {
                    request.setAttribute("employer", employer);
                    router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_VIEW_EDIT_INFORMATION);
                    request.setAttribute("errorMessage", message);
                    return router;
                }
            }

            if (company != null) {
                employer.setCompany(company);
            }

            if (!service.update(employer)) {
                language = request.getSession(true).getAttribute("language");
                message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
