package net.sanstech.persistence.impl;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.util.SqlConnector;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class TokenDAOImpl implements TokenDAO {
    private static final String INSERT_INTO_TOKENS_TOKEN_USER_VALUES = "INSERT INTO tokens (token, user) VALUES (?,?)";
    private static final String SELECT_FROM_TOKENS_WHERE_USER = "SELECT * FROM tokens WHERE user=?";
    private static final String SELECT_FROM_TOKENS_WHERE_TOKEN = "SELECT * FROM tokens WHERE token=?";

    @Inject
    private SqlConnector sqlConnector;

    @Override
    public TokenDTO getToken(final UserDTO user) {
        try (
                final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement(SELECT_FROM_TOKENS_WHERE_USER)
        ) {
            preparedStatement.setString(1, user.getUser());
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final TokenDTO tokenDTO = new TokenDTO();

                if (resultSet.next()) {
                    tokenDTO.setUser(user.getName());
                    tokenDTO.setToken(resultSet.getString("token"));
                }

                return tokenDTO;
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public TokenDTO getToken(final String token) {
        try (
                final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement(SELECT_FROM_TOKENS_WHERE_TOKEN)
        ) {
            preparedStatement.setString(1, token);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final TokenDTO tokenDTO = new TokenDTO();

                if (resultSet.next()) {
                    tokenDTO.setUser(resultSet.getString("user"));
                    tokenDTO.setToken(resultSet.getString("token"));
                }

                return tokenDTO;
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public TokenDTO insertToken(final String token, final UserDTO user) {
        try (
                final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement(INSERT_INTO_TOKENS_TOKEN_USER_VALUES)
        ) {
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, user.getUser());
            preparedStatement.execute();

            return new TokenDTO(token, user.getName());
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
