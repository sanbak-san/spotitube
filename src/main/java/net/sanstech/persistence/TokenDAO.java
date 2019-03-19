package net.sanstech.persistence;

import net.sanstech.dto.TokenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public TokenDTO getToken(String username) {
        TokenDTO foundToken = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tokens WHERE user=?");
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundToken = new TokenDTO();
                foundToken.setUser(username);
                foundToken.setToken(resultSet.getString("token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundToken;
    }
}
