package net.sanstech.persistence;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public TrackDTO getTrack(int id) {
        TrackDTO foundTrack = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id=?");
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundTrack = new TrackDTO();
                foundTrack.setId(id);
                foundTrack.setAlbum(resultSet.getString("album"));
                foundTrack.setDescription(resultSet.getString("description"));
                foundTrack.setPerformer(resultSet.getString("performer"));
                foundTrack.setTitle(resultSet.getString("title"));
                foundTrack.setPublicationDate(resultSet.getString("publicationDate"));
                foundTrack.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                foundTrack.setDuration(resultSet.getInt("duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundTrack;
    }
}
