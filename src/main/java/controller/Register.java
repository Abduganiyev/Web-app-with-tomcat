package controller;

import model.Response;
import model.User;
import service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Register extends HttpServlet {
    static DbService dbService = new DbService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String retype_password = req.getParameter("retype_password");

        if (!password.equalsIgnoreCase(retype_password)) {
            printWriter.write("<h1 style= 'color:red' >Password is not Equals</h1>");
            return;
        }

        try {
            Response response = dbService.register(new User(
                    firstname,lastname,email,password
            ));

            if (response.isStatus()) {
                printWriter.write("<h1 style= 'color:green' >" +response.getMessage() + "</h1>");
            }
            else {
                printWriter.write("<h1 style= 'color:red' >" +response.getMessage() + "</h1>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
