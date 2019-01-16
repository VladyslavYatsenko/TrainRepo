package com.servlets.ticketsServlets;

import com.classes.DAO.DBWorker;
import com.classes.train.Train;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/TicketInitializationServlet")
public class TicketInitializationServlet extends HttpServlet {
    private DBWorker dbWorker;
    private List<Train> fullTrainsList = new LinkedList<Train>();
    private List<Train> usersTrainsList = new LinkedList<>();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbWorker = new DBWorker();
        usersTrainsList=new LinkedList<>();
        dbWorker.connectDb();
        dbWorker.initTrainsList();
        fullTrainsList = dbWorker.getTrainsList();
        System.out.println("Trains List" + fullTrainsList);
        String initialStation = request.getParameter("initialStation");
        String endStation = request.getParameter("endStation");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        LocalTime userTime= LocalTime.parse(time);

        System.out.println(time);
        for (Train train : fullTrainsList) {
            System.out.println("Train->" + train);
            LocalTime trainTime=LocalTime.parse(train.getDepartureTime());
            if (initialStation.equals(train.getInitialStation())
                    && endStation.equals(train.getEndStation())
                    && date.equals(train.getDepartureDate())
                    && trainTime.isAfter(userTime)) {//СДЕЛАТЬ Departure ТАЙМ
                usersTrainsList.add(train);
                System.out.println(usersTrainsList);
            }
        }
        if (usersTrainsList.size() == 0) {
            String info="No available trains on your parameters";
            System.out.println(info);
            RequestDispatcher rd = request.getRequestDispatcher("/chooseTrainPage.jsp");
            rd.forward(request, response);
        }
//            String query = "SELECT *FROM trains WHERE initial_station = " + "'" + initialStation + "'"
////                    + " AND end_station= " + "'" + endStation + "'"
////                    + " AND departure_date = " + "'" + date + "'"
////                    + " AND departure_time >= " + "'" + time + "'";
////            System.out.println(query);
////            System.out.println(request.getParameter("date"));
////            System.out.println(request.getParameter("time"));
////            System.out.println(request.getParameter("initialStation"));
////            System.out.println(request.getParameter("endStation"));
////            ResultSet rs = dbWorker.getStatement().executeQuery(query);
////            if (rs.next() == false) {
////                System.out.println("No availible trains");
////
////                RequestDispatcher rd = request.getRequestDispatcher("/chooseTrainPage.jsp");
////                rd.forward(request, response);
////            } else {
////                do {
////                    System.out.println("Train_id:" + rs.getInt("train_id")
////                            + " Train_number:" + rs.getInt("train_num")
////                            + " Initial_station:" + rs.getString("initial_station")
////                            + " End_station:" + rs.getString("end_station")
////                            + " Cost:" + rs.getDouble("cost")
////                            + " Departure_date:" + rs.getDate("departure_date")
////                            + " Departure_time:" + rs.getTime("departure_time")
////                            + " Arrival_date:" + rs.getDate("arrival_date")
////                            + " Arrival_time:" + rs.getTime("arrival_time"));
////                    train = new Train(rs.getInt("train_id")
////                            , rs.getInt("train_num")
////                            , rs.getString("initial_station")
////                            , rs.getString("end_station")
////                            , rs.getDouble("cost")
////                            , rs.getString("departure_date")
////                            , rs.getString("departure_time")
////                            , rs.getString("arrival_date")
////                            , rs.getString("arrival_time"));
////                    if (!trainsList.contains(train)) {
////                        trainsList.add(train);
////                    }
////
////                    System.out.println(trainsList);
////                } while (rs.next());
////            }
        request.setAttribute("trainsList", usersTrainsList);
        RequestDispatcher rd = request.getRequestDispatcher("/registrationPage.jsp");
        rd.forward(request, response);
    }
}
