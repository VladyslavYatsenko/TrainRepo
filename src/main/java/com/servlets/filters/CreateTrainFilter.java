package com.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

@WebFilter("/CreateTrainServlet")
public class CreateTrainFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int trainNumber = Integer.parseInt(request.getParameter("trainNumber"));
        String initialStation = request.getParameter("initialStation");
        String endStation = request.getParameter("endStation");
        double cost = Double.parseDouble(request.getParameter("cost"));
        LocalDate arrivalDate = LocalDate.parse(request.getParameter("arrivalDate"));
        LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"));
        LocalTime arrivalTime = LocalTime.parse(request.getParameter("arrivalTime"));
        LocalTime departureTime = LocalTime.parse(request.getParameter("departureTime"));
        System.out.println("Train Num: " + trainNumber);
        System.out.println("Init Station: " + initialStation);
        System.out.println("End Station: " + endStation);
        System.out.println("Cost:" + cost);
        System.out.println("Arrival Date: " + arrivalDate);
        System.out.println("Departure Date: " + departureDate);
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Departure Time: " + departureTime);
        if (trainNumber < 0 || trainNumber > 1000) {
            out.print("error trainNumber");
        } else if (cost < 0) {
            out.print("error cost");
        } else if (initialStation.equals(endStation)) {
            out.print("choose different stations");
        } else if (departureDate.isAfter(arrivalDate)) {
            out.print("error date (Departure Date is after Arrival Date");
        } else if (initialStation.length() == 0 || !initialStation.matches("^\\D*$")) {
            out.print("Error initial station");
        } else if (endStation.length() == 0 || !endStation.matches("^\\D*$")) {
            out.print("Error end station");
        }else {
        chain.doFilter(request, response);
    }
}

    public void init(FilterConfig config) throws ServletException {

    }

}
