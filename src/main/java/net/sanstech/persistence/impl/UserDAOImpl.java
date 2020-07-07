package net.sanstech.persistence.impl;

import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.UserDAO;
import net.sanstech.util.ResultSetMapper;
import net.sanstech.util.SqlConnector;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlConnector sqlConnector;

    @Override
    public UserDTO getUser(final String username, final String password) {
        try (
                final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("SELECT * FROM account WHERE user=? AND password=?");
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            return ResultSetMapper.getUserFromResultSet(preparedStatement.executeQuery());
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
