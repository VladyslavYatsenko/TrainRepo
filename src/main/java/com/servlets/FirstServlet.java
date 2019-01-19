package com.servlets;

        import com.classes.DAO.DBWorker;
        import com.classes.train.Train;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.*;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    DBWorker dbWorker;
    Set<String> trainsInitialStation;
    Set<String> trainsEndStation;
    List<Train> fullTrainsList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dbWorker=new DBWorker();
        dbWorker.connectDb();
        dbWorker.initTrainsList();
        trainsInitialStation=new TreeSet<>();
        trainsEndStation=new TreeSet<>();
        fullTrainsList=new ArrayList<>();
        fullTrainsList=dbWorker.getTrainsList();
        for(Train t:fullTrainsList){
            trainsInitialStation.add(t.getInitialStation());
            trainsEndStation.add(t.getEndStation());
        }
        System.out.println("Init train: "+trainsEndStation);
        System.out.println("Departyre" +trainsInitialStation);
        request.setAttribute("trainsInitialStation", trainsInitialStation);
        request.setAttribute("trainsEndStation", trainsEndStation);
        dbWorker.closeConnection();
        getServletContext().getRequestDispatcher("/chooseTrainPage.jsp").forward(request, response);

    }
}
