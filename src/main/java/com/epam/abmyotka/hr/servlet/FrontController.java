package com.epam.abmyotka.hr.servlet;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontController extends HttpServlet{

    public FrontController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        Account user = (Account) session.getAttribute("role");

        if(user == null){
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            user = new Account(username, password);
        }

        String message;
        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();

        if (admin.equals(user)) {
            session.setAttribute("role", admin);
            message = "ADMIN";
        } else {
            AccountService accountService = new AccountService();
            Account role = accountService.findAccount(user);
            if (role != null) {
                session.setAttribute("role", role);
                message = role.getAttachment();
            } else {
                message = "NULL";
            }
        }

        PrintWriter out = response.getWriter();
        out.print(message);
        request.getRequestDispatcher("/index.jsp").include(request, response);
        //dispatch(request, response, nexPage);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws  javax.servlet.ServletException,java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
