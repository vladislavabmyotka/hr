package com.epam.abmyotka.hr.servlet;

import com.epam.abmyotka.hr.entity.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FrontController extends HttpServlet{

    public FrontController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession(true);
        Account userBean = (Account) sesion.getAttribute("userbean");

        if(userBean == null){

            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            userBean = new Account(username, password);
        }
        String nexPage;
        String user = "log";
        String pass = "123";
        boolean result = false;

        if(user.equals(userBean.getLogin()) && pass.equals(userBean.getPassword())){

            nexPage = "/Welcom.jsp";
        } else {

            nexPage = "/Error.jsp";
        }
        dispatch(request, response, nexPage);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws  javax.servlet.ServletException,java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
