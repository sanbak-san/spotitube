package net.sanstech.persistence.impl;

import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.UserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    @Override
    public UserDTO getUser(final String username, final String password) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE user=? AND password=?");
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final UserDTO foundUser = new UserDTO();
                foundUser.setUser(username);
                foundUser.setPassword(password);
                foundUser.setName(resultSet.getString("name"));
                return foundUser;
            }
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
        return null;
    }
}
