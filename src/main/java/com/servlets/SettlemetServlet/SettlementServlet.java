package com.servlets.SettlemetServlet;

import com.classes.DAO.DBWorker;
import com.classes.passanger.Passanger;
import com.classes.train.Train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/SettlementServlet")
public class SettlementServlet extends HttpServlet {
    DBWorker dbWorker;
    List<Passanger> passangersList = new LinkedList<>();
    Passanger passanger;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbWorker = new DBWorker();
        dbWorker.connectDb();
        dbWorker.initPassengersList();
        System.out.println(dbWorker.getPassangersList());
        passangersList = dbWorker.getPassangersList();
        System.out.println("Train id" + request.getParameter("trainId"));
        System.out.println(passangersList);
        passanger = passangersList.get(passangersList.size()-1);
        System.out.println("Passanger_id:" + passanger.getPassangerId());
        System.out.println("Train_id: " + passanger.getTrainId());
        try {
            String query = "INSERT INTO orders (train_id,passanger_id) VALUES (" + passanger.getTrainId() + "," + passanger.getPassangerId() + ")";
            dbWorker.getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String passangerFirstName=dbWorker.getPassangerFirstName(passanger.getPassangerId());
        String passangerLastName=dbWorker.getPassangerLastName(passanger.getPassangerId());
        int trainNumber=dbWorker.getTrainNumber(passanger.getTrainId());
        String initialStation=dbWorker.getInitialStation(passanger.getTrainId());
        String endStation=dbWorker.getEndStation(passanger.getTrainId());
        String departureDate=dbWorker.getDepartureDate(passanger.getTrainId());
        String departureTime=dbWorker.getDepartureTime(passanger.getTrainId());
        String arrivalDate=dbWorker.getArrivalDate(passanger.getTrainId());
        String arrivalTime=dbWorker.getArrivalTime(passanger.getTrainId());
        double cost=dbWorker.getCost(passanger.getTrainId());
        request.setAttribute("passangerFirstName",passangerFirstName);
        request.setAttribute("passangerLastName",passangerLastName);
        request.setAttribute("trainNumber",trainNumber);
        request.setAttribute("initialStation",initialStation);
        request.setAttribute("endStation",endStation);
        request.setAttribute("departureDate",departureDate);
        request.setAttribute("departureTime",departureTime);
        request.setAttribute("arrivalDate",arrivalDate);
        request.setAttribute("arrivalTime",arrivalTime);
        request.setAttribute("cost",cost);
        dbWorker.closeConnection();
        getServletContext().getRequestDispatcher("/billPage.jsp").forward(request, response);






    }
}
