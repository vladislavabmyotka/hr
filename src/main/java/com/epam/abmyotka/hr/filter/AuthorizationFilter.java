package com.epam.abmyotka.hr.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.epam.abmyotka.hr.entity.Account;
import org.apache.commons.lang3.StringUtils;

@WebFilter(servletNames = {"FrontController"})
public class AuthorizationFilter implements Filter {
    private List<String> pathFilters = Arrays.asList("add", "remove", "update");

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        String path = StringUtils.substringAfterLast(uri, "/");

        if (pathFilters.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        Account user = (Account) session.getAttribute("role");

        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        requestDispatcher.forward(request, response);
        //((HttpServletResponse) response).sendRedirect("/WEB-INF/jsp/register.jsp");
    }

    @Override
    public void destroy() {

    }
}
