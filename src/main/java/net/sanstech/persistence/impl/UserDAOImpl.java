package net.sanstech.persistence.impl;

import net.sanstech.dto.UserDTO;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.SpotitubePersistenceException;
import net.sanstech.persistence.UserDAO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    @Override
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
                foundUser.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
        return foundUser;
    }
}
