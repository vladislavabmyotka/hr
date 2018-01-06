package com.epam.abmyotka.hr.servlet;

import com.epam.abmyotka.hr.command.ActionFactory;
import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.command.EmptyCommand;
import com.epam.abmyotka.hr.creator.AdminCreator;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.service.AccountService;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
        Command command = commandOptional.orElse(new EmptyCommand());
        String page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    private void processReq(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession(true);
        Account user = (Account) session.getAttribute("role");

        if(user == null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user = new Account(username, password);
        }

        AdminCreator creator = new AdminCreator();
        Account admin = creator.createAdmin();

        Account potentialAdmin = new Account(user.getLogin(), user.getPassword(), ADMIN_ATTACHMENT);

        if (admin.equals(potentialAdmin)) {
            session.setAttribute("role", potentialAdmin);
            dispatch(request, response, "/adminHome");
        } else {
            AccountService accountService = new AccountService();
            Account role = accountService.findAccount(user);
            if (role != null) {
                session.setAttribute("role", role);
                if (role.getAttachment().equals(CANDIDATE_ATTACHMENT))  {
                    dispatch(request, response, "/candidateHome");
                } else if (role.getAttachment().equals(EMPLOYER_ATTACHMENT)) {
                    dispatch(request, response, "/employerHome");
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println(user.getLogin() + "\n" + user.getPassword());
            }
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws  javax.servlet.ServletException,java.io.IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
