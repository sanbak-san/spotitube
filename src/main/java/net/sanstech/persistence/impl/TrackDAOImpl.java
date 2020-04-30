package net.sanstech.persistence.impl;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.TrackDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAOImpl implements TrackDAO {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    @Override
    public TrackSummaryDTO getAllTracksFromPlaylist(final int playlistId) {
        final TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();

        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)");
        ) {
            getTracksFromResultSet(playlistId, trackSummaryDTO, preparedStatement);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }

        return trackSummaryDTO;
    }

    @Override
    public TrackSummaryDTO getAllTracksForPlaylist(final int playlistId) {
        final TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();

        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE id NOT IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)");
        ) {
            getTracksFromResultSet(playlistId, trackSummaryDTO, preparedStatement);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }

        return trackSummaryDTO;
    }

    @Override
    public void addTrackToPlaylist(final int playlistId, final TrackDTO trackDTO) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tracks_in_playlists (track_id, playlist_id, offline_available) VALUES (?,?,?)")
        ) {
            preparedStatement.setInt(1, trackDTO.getId());
            preparedStatement.setInt(2, playlistId);
            preparedStatement.setBoolean(3, trackDTO.isOfflineAvailable());
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(final int playlistId, final int trackId) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tracks_in_playlists WHERE track_id=? AND playlist_id=?")
        ) {
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    private void getTracksFromResultSet(final int playlistId, final TrackSummaryDTO trackSummaryDTO, final PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, String.valueOf(playlistId));
        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            final TrackDTO foundTrack = new TrackDTO();
            foundTrack.setId(resultSet.getInt("id"));
            foundTrack.setAlbum(resultSet.getString("album"));
            foundTrack.setDescription(resultSet.getString("description"));
            foundTrack.setPerformer(resultSet.getString("performer"));
            foundTrack.setTitle(resultSet.getString("title"));
            foundTrack.setPublicationDate(resultSet.getString("publicationDate"));
            foundTrack.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
            foundTrack.setDuration(resultSet.getInt("duration"));
            trackSummaryDTO.getTracks().add(foundTrack);
        }
    }
}
