package com.classes.DAO;

import com.classes.order.Order;
import com.classes.passanger.Passanger;
import com.classes.train.Train;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBWorker {
    Logger lg=Logger.getLogger(DBWorker.class);
    private String url = "jdbc:mysql://localhost/trainproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "root";
    private Statement st;
    private Connection myConn;
    private Train train;
    private Passanger passanger;
    private Order order;
    private List<Train> trainsList = new LinkedList<>();
    private List<Passanger> passangersList = new LinkedList<>();
    private List<Order> ordersList = new LinkedList<>();

    public List<Train> getTrainsList() {
        return trainsList;
    }

    public List<Passanger> getPassangersList() {
        return passangersList;
    }
    public List<Order> getOrdersList(){
        return ordersList;
    }
    public void initPassengersList() {
        String query = "SELECT *FROM passangers";
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                passanger = new Passanger(rs.getInt("passanger_id")
                        , rs.getString("passanger_firstName")
                        , rs.getString("passanger_lastName")
                        , rs.getInt("train_identificationNumber"));
                passangersList.add(passanger);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void initTrainsList() {
        String query = "SELECT *FROM trains";
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                train = new Train(rs.getInt("train_id")
                        , rs.getInt("train_num")
                        , rs.getString("initial_station")
                        , rs.getString("end_station")
                        , rs.getDouble("cost")
                        , rs.getString("departure_date")
                        , rs.getString("departure_time")
                        , rs.getString("arrival_date")
                        , rs.getString("arrival_time"));
                System.out.println(train);
                trainsList.add(train);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    public void initOrdersList() {
        String query = "SELECT *FROM orders";
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                order = new Order(rs.getInt("order_id")
                        ,rs.getInt("train_id")
                        ,rs.getInt("passanger_id"));
                System.out.println(order);
                ordersList.add(order);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void connectDb() {
        try {
            System.out.println("Obtaining connection...");
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, user, password);// Connection;
            st = myConn.createStatement();
            System.out.println("connected");
        } catch (Exception ex) {
            //lg.warn("");
        }
    }

    public void closeConnection() {
        try {
            myConn.close();
            System.out.println("Connection Closed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public Statement getStatement() {
        return st;
    }
}
