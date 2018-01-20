package com.epam.abmyotka.hr.command.impl.employerImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.EmployerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.epam.abmyotka.hr.validator.CandidateEmployerVacancyValidator.*;

public class EmployerAddInformationSaveCommand implements Command {
    private EmployerService service;

    public EmployerAddInformationSaveCommand(EmployerService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_EMPLOYER, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);

        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String lastname = request.getParameter(ParameterConstant.PARAM_LASTNAME);
        String address = request.getParameter(ParameterConstant.PARAM_ADDRESS);
        String phone = request.getParameter(ParameterConstant.PARAM_PHONE);
        String email = request.getParameter(ParameterConstant.PARAM_EMAIL);
        String company = request.getParameter(ParameterConstant.PARAM_COMPANY);
        int accountId = (int)session.getAttribute("employerAccountId");

        if(checkNames(surname) && checkNames(name) && checkLastname(lastname) && checkPhone(phone) &&
                checkEmail(email)) {
            Employer employer = new Employer(surname, name, lastname, address, phone, email, company, accountId);
            if (!service.add(employer)) {
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
                request.setAttribute("errorMessage", message);
            }
        } else {
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.INCORRECT_DATA);
            request.setAttribute("errorMessage", message);
            router.setPagePath(PathConstant.PATH_PAGE_EMPLOYER_ADD_INFORMATION);
        }

        return router;
    }
}
