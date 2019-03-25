package net.sanstech.persistence;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public ArrayList<TrackDTO> getTracksByPlaylist(int playlist_id) {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        TrackDTO foundTrack = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM track_in_playlist WHERE playlist_id =?)");
        ) {
            preparedStatement.setString(1, String.valueOf(playlist_id));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundTrack = new TrackDTO();
                foundTrack.setId(resultSet.getInt("id"));
                foundTrack.setAlbum(resultSet.getString("album"));
                foundTrack.setDescription(resultSet.getString("description"));
                foundTrack.setPerformer(resultSet.getString("performer"));
                foundTrack.setTitle(resultSet.getString("title"));
                foundTrack.setPublicationDate(resultSet.getString("publicationDate"));
                foundTrack.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                foundTrack.setDuration(resultSet.getInt("duration"));
                tracks.add(foundTrack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }
}
