package net.sanstech.persistence;

import net.sanstech.dto.UserDTO;
import net.sanstech.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public UserDTO getUser(String username, String password) {
        UserDTO foundUser = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE user=? AND password=?");
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundUser = new UserDTO();
                foundUser.setUser(username);
                foundUser.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUser;
    }
}
