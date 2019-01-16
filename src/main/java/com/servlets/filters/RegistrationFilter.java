package com.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/TicketRegistrationServlet")
public class RegistrationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int trainId = Integer.parseInt(request.getParameter("trainId"));
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        if (trainId < 1) {
            out.print("Error train id");
        } else if (firstName.length() == 0 || !firstName.matches("^\\D*$")) {
            out.print("Error first name");
        } else if (lastName.length() == 0 || !lastName.matches("^\\D*$")) {
            out.print("Error last name");
        } else {
            chain.doFilter(request, response);

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
