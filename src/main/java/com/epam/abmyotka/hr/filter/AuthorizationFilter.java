package com.epam.abmyotka.hr.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epam.abmyotka.hr.constant.AccountAttachmentConstant;
import com.epam.abmyotka.hr.entity.Account;
import org.apache.commons.lang3.StringUtils;

import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.ADMIN_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.CANDIDATE_ATTACHMENT;
import static com.epam.abmyotka.hr.constant.AccountAttachmentConstant.EMPLOYER_ATTACHMENT;

//@WebFilter(servletNames = {"FrontController"})
@WebFilter(filterName = "AuthFilter", urlPatterns = { "/*" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        Account user = (Account)session.getAttribute("role");

        if (user != null) {
            switch (user.getAttachment()) {
                case ADMIN_ATTACHMENT:
                    dispatch(request, response, "/adminHome");
                    break;
                case CANDIDATE_ATTACHMENT:
                    dispatch(request, response, "/candidateHome");
                    break;
                case EMPLOYER_ATTACHMENT:
                    dispatch(request, response, "/employerHome");
                    break;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void dispatch(ServletRequest request, ServletResponse response, String page)
            throws  ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
