package com.servlets;

import com.classes.DAO.DBWorker;
import com.classes.passanger.Passanger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        PrintWriter out = response.getWriter();
        passanger = passangersList.get(passangersList.size()-1);
        System.out.println("Passanger_id:" + passanger.getPassangerId());
        System.out.println("Train_id: " + passanger.getTrainId());
        try {
            String query = "INSERT INTO orders (train_id,passanger_id) VALUES (" + passanger.getTrainId() + "," + passanger.getPassangerId() + ")";
            dbWorker.getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        out.print("Payed, ");
        out.print("Order created");
    }
}
