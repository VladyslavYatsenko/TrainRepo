package com.servlets.ticketsServlets;

import com.classes.DAO.DBWorker;
import com.classes.passanger.Passanger;
import com.classes.train.Train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/TicketRegistrationServlet")
public class TicketRegistrationServlet extends HttpServlet {
    private List<Passanger> passangerList=new LinkedList<>();
    private DBWorker dbWorker;
    Train userTrain;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.invalidate();
        response.sendRedirect("/chooseTrainPage.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int trainId=Integer.parseInt(request.getParameter("trainId"));
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        dbWorker=new DBWorker();
        dbWorker.connectDb();
        String query="INSERT INTO passangers " +
                "(passanger_firstName,passanger_lastName,train_identificationNumber) VALUES ("
                +"'"+firstName+"',"
                +"'"+lastName+"',"
                +"'"+trainId+"')";
        System.out.println("Passanger Added");
        try{
            dbWorker.getStatement().executeUpdate(query);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        dbWorker.initTrainsList();
        for(Train train:dbWorker.getTrainsList()){
            if(trainId==train.getTrainId()){
                userTrain=train;
            }
        }
        System.out.println("User train is:"+userTrain);
        request.setAttribute("userTrain",userTrain);
        getServletContext().getRequestDispatcher("/settlementPage.jsp").forward(request, response);
        dbWorker.closeConnection();
    }
}
