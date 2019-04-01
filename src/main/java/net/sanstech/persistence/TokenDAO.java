package net.sanstech.persistence;

import net.sanstech.dto.TokenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TokenDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

//    public TokenDTO getToken(String username) {
//        TokenDTO foundToken = null;
//
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tokens WHERE user=?");
//        ) {
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                foundToken = new TokenDTO();
//                foundToken.setUser(username);
//                foundToken.setToken(resultSet.getString("token"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return foundToken;
//    }
//
//    public TokenDTO insertToken(String token, String username) {
//        TokenDTO tokenDTO = null;
//
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tokens (token, user, expiry) VALUES (?,?,?)");
//        ) {
//            preparedStatement.setString(1, token);
//            preparedStatement.setString(2, username);
//            preparedStatement.setString(3, String.valueOf(LocalDateTime.now().plusDays(2)));
//            preparedStatement.execute();
//
//            tokenDTO.setToken(token);
//            tokenDTO.setUser(username);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tokenDTO;
//    }
}
