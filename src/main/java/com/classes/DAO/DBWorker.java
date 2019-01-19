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
import java.util.ArrayList;
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
    private List<String> fullInfo=new ArrayList<>();
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
    public String getDepartureDate(int trainId){
        String departureDate=null;
        String query="SELECT departure_date FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                departureDate=rs.getString("departure_date");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return departureDate;
    }
    public String getArrivalDate(int trainId){
        String arrivalDate=null;
        String query="SELECT arrival_date FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                arrivalDate=rs.getString("arrival_date");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return arrivalDate;
    }
//    public List<String> getFullInfo(){
//        String query="select order_id,trains.train_id,trains.train_num,trains.initial_station," +
//                "trains.end_station,trains.cost,trains.departure_date,trains.departure_time," +
//                "trains.arrival_date,trains.arrival_time,passangers.passanger_firstName," +
//                "passangers.passanger_lastName FROM orders" +
//                " INNER JOIN trains ON trains.train_id=orders.train_id " +
//                "INNER JOIN passangers ON passangers.passanger_id=orders.order_id";
//        try{
//            ResultSet rs=st.executeQuery(query);
//            while (rs.next()){
//                fullInfo.add(rs.getInt("order_id")+" "+
//                        rs.getInt("trains.train_id")+" "+
//                        rs.getInt("trains.train_num")+" "+
//                        rs.getString("trains.initial_station")+" "+
//                        rs.getString("trains.end_station")+" "+
//                        rs.getDouble("trains.cost")+" "+
//                        rs.getString("trains.departure_date")+" "+
//                        rs.getString("trains.departure_time")+" "+
//                        rs.getString("trains.arrival_date")+" "+
//                        rs.getString("trains.arrival_time")+" "+
//                        rs.getString("passangers.passanger_firstName")+" "+
//                        rs.getString("passangers.passanger_lastName"));
//            }
//        }catch (SQLException ex){
//            ex.getMessage();
//        }
//        return fullInfo;
//    }
    public String getDepartureTime(int trainId){
        String departureTime=null;
        String query="SELECT departure_time FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                departureTime=rs.getString("departure_time");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return departureTime;
    }
    public double getCost(int trainId){
        double cost=0;
        String query="SELECT cost FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                cost=rs.getDouble("cost");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return cost;
    }
    public String getArrivalTime(int trainId){
        String arrivalTime=null;
        String query="SELECT arrival_time FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                arrivalTime=rs.getString("arrival_time");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return arrivalTime;
    }
    public String getEndStation(int trainId){
        String endStation=null;
        String query="SELECT end_station FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                endStation=rs.getString("end_station");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return endStation;
    }
    public String getPassangerFirstName(int passangerId){
        String query="SELECT passanger_firstName FROM passangers WHERE passanger_id="+passangerId;
        String passangerFirstName=null;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                passangerFirstName= rs.getString("passanger_firstName");
            }

        }catch (SQLException ex){
            ex.getMessage();
        }
        return passangerFirstName;
    }
    public int getTrainNumber(int trainId){
        int trainNumber=0;
        String query="SELECT train_num FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                trainNumber=rs.getInt("train_num");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return trainNumber;
    }
    public String getInitialStation(int trainId){
        String initialStation=null;
        String query="SELECT initial_station FROM trains WHERE train_id="+trainId;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                initialStation=rs.getString("initial_station");
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return initialStation;
    }
    public String getPassangerLastName(int passangerId){
        String query="SELECT passanger_lastName FROM passangers WHERE passanger_id="+passangerId;
        String passangerLastName=null;
        try{
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                passangerLastName= rs.getString("passanger_lastName");
            }

        }catch (SQLException ex){
            ex.getMessage();
        }
        return passangerLastName;
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
