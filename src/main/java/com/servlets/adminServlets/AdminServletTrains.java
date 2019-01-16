package com.servlets.adminServlets;

import com.classes.DAO.DBWorker;
import com.classes.order.Order;
import com.classes.passanger.Passanger;
import com.classes.train.Train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/AdminServletTrains")
public class AdminServletTrains extends HttpServlet {
    List<Passanger> passangersList=new ArrayList<>();
    List<Train> trainsList=new LinkedList<>();
    List<Order> ordersList=new LinkedList<>();
    DBWorker dbWorker;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/createTrainPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbWorker=new DBWorker();
        dbWorker.connectDb();
        dbWorker.initTrainsList();
        dbWorker.initPassengersList();
        dbWorker.initOrdersList();
        passangersList=dbWorker.getPassangersList();
        trainsList=dbWorker.getTrainsList();
        ordersList=dbWorker.getOrdersList();
        System.out.println("Passangers List->"+passangersList);
        System.out.println("Trains List->"+trainsList);
        System.out.println("Orders List->"+ordersList);
        request.setAttribute("fullTrainsList",trainsList);
        request.setAttribute("fullPassangersList",passangersList);
        request.setAttribute("fullOrdersList",ordersList);
        getServletContext().getRequestDispatcher("/adminPage.jsp").forward(request, response);
    }
}
