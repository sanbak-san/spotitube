package net.sanstech.persistence.impl;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.TrackDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAOImpl implements TrackDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public TrackDTO getTrack(final int id) {
        TrackDTO foundTrack = null;

        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id=?");
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            final ResultSet resultSet = preparedStatement.executeQuery();

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
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return foundTrack;
    }

    @Override
    public TrackSummaryDTO getAllTracksForPlaylist(final int id) {
        final TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)");
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final TrackDTO foundTrack = new TrackDTO();
                foundTrack.setId(id);
                foundTrack.setAlbum(resultSet.getString("album"));
                foundTrack.setDescription(resultSet.getString("description"));
                foundTrack.setPerformer(resultSet.getString("performer"));
                foundTrack.setTitle(resultSet.getString("title"));
                foundTrack.setPublicationDate(resultSet.getString("publicationDate"));
                foundTrack.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                foundTrack.setDuration(resultSet.getInt("duration"));
                trackSummaryDTO.getTracks().add(foundTrack);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return trackSummaryDTO;
    }
}
