package com.servlets.loginOut;

import com.classes.DAO.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private DBWorker dbWorker;
    final String login = "Admin";
    final String password = "6991";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("login");
        String userPassword = request.getParameter("password");
        System.out.println(userName);
        System.out.println(userPassword);
        if (userName.equals(login) && userPassword.equals(password)) {
            dbWorker = new DBWorker();
            dbWorker.connectDb();
            HttpSession session=request.getSession();
            session.setAttribute("username",userName);

            response.sendRedirect("/adminPage.jsp");

        } else {
           response.sendRedirect("/index.jsp");
        }
    }
}

