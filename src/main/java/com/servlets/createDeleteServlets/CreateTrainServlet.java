package com.servlets.createDeleteServlets;

import com.classes.DAO.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/CreateTrainServlet")
public class CreateTrainServlet extends HttpServlet {
    DBWorker dbWorker;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        int trainNumber = Integer.parseInt(request.getParameter("trainNumber"));
        String initialStation = request.getParameter("initialStation");
        String endStation = request.getParameter("endStation");
        double cost = Double.parseDouble(request.getParameter("cost"));
        String departureDate = request.getParameter("departureDate");
        String departureTime = request.getParameter("departureTime");
        String arrivalDate = request.getParameter("arrivalDate");
        String arrivalTime = request.getParameter("arrivalTime");
        String query="INSERT INTO trains " +
                "(train_num,initial_station,end_station,cost,departure_date,departure_time,arrival_date,arrival_time)"
                + " VALUES "+"('"+trainNumber+"','"+initialStation+"',"+"'"+endStation+"',"+"'"+cost+"',"
                +"'"+departureDate+"',"+"'"+departureTime+"',"+"'"+arrivalDate+"',"+"'"+arrivalTime+"')";
        try{

            dbWorker=new DBWorker();
            dbWorker.connectDb();
            dbWorker.getStatement().executeUpdate(query);
            System.out.print("Train added");
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
