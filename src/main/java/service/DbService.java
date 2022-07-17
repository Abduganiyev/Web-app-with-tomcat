package service;


import model.Response;
import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static config.DbConfig.connection;

public class DbService{
    public Response register(User user) throws SQLException {
        try {
            String INSERT_INTO_USER = "INSERT INTO users(firstname, lastname, email, password) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USER);
            statement.setString(1,user.getFirstname());
            statement.setString(2,user.getLastname());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            int i = statement.executeUpdate();
            if (i == 1) {
                return new Response(true,"Successfully Registered");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Response(false,"Something went wrong");
    }
}
