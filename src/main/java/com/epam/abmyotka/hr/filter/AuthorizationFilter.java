package com.epam.abmyotka.hr.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epam.abmyotka.hr.entity.Account;
import org.apache.commons.lang3.StringUtils;

//@WebFilter(servletNames = {"FrontController"})
public class AuthorizationFilter implements Filter {
    private static final String ADMIN_ATTACHMENT = "a";
    private static final String CANDIDATE_ATTACHMENT = "c";
    private static final String EMPLOYER_ATTACHMENT = "e";
    private List<String> pathFilters = Collections.singletonList("index.jsp");

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        HttpSession session = ((HttpServletRequest) request).getSession();

        Account user = (Account)session.getAttribute("role");

        if (user != null) {
            switch (user.getAttachment()) {
                case ADMIN_ATTACHMENT:
                    ((HttpServletResponse)response).sendRedirect("/adminHome.jsp");
                    break;
                case CANDIDATE_ATTACHMENT:
                    ((HttpServletResponse)response).sendRedirect("/candidateHome.jsp");
                    break;
                case EMPLOYER_ATTACHMENT:
                    ((HttpServletResponse)response).sendRedirect("/employerHome.jsp");
                    break;
            }
        } else {
            filterChain.doFilter(request, response);
        }

        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        //requestDispatcher.forward(request, response);
        //((HttpServletResponse) response).sendRedirect("/WEB-INF/jsp/register.jsp");
    }

    @Override
    public void destroy() {

    }
}
