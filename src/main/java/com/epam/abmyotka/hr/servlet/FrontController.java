package com.epam.abmyotka.hr.servlet;

import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.ADMIN_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.EMPLOYER_ATTACHMENT;

@WebServlet(urlPatterns = { "/"})
public class FrontController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        PrintWriter out = response.getWriter();

        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        out.print(username + "\n" + password);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*String path = request.getRequestURI();
        PrintWriter out = response.getWriter();

        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        out.print(username + "\n" + password);*/
        /*response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        Account user = (Account) session.getAttribute("role");

        if(user == null){
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            user = new Account(username, password);
        }

        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();

        Account potentialAdmin = new Account(user.getLogin(), user.getPassword(), ADMIN_ATTACHMENT);

        if (admin.equals(potentialAdmin)) {
            session.setAttribute("role", potentialAdmin);
            dispatch(request, response, "/adminHome");
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/wrongLoginOrPassword.jsp").include(request, response);*/
            /*AccountService accountService = new AccountService();
            Account role = accountService.findAccount(user);
            if (role != null) {
                session.setAttribute("role", role);
                if (role.getAttachment().equals(CANDIDATE_ATTACHMENT))  {
                    dispatch(request, response, "/candidateHome");
                } else if (role.getAttachment().equals(EMPLOYER_ATTACHMENT)) {
                    dispatch(request, response, "/employerHome");
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/wrongLoginOrPassword.jsp").include(request, response);
            }*/
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws  javax.servlet.ServletException,java.io.IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
