package net.sanstech.persistence.impl;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.util.ResultSetMapper;
import net.sanstech.util.SqlConnector;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrackDAOImpl implements TrackDAO {

    @Inject
    private SqlConnector sqlConnector;

    @Override
    public TrackSummaryDTO getAllTracksFromPlaylist(final int playlistId) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)");
            preparedStatement.setString(1, String.valueOf(playlistId));

            return ResultSetMapper.getTracksFromResultSet(preparedStatement.executeQuery());
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public TrackSummaryDTO getAllTracksForPlaylist(final int playlistId) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id NOT IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)");
            preparedStatement.setString(1, String.valueOf(playlistId));

            return ResultSetMapper.getTracksFromResultSet(preparedStatement.executeQuery());
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void addTrackToPlaylist(final int playlistId, final TrackDTO trackDTO) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("INSERT INTO tracks_in_playlists (track_id, playlist_id, offline_available) VALUES (?,?,?)");
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
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("DELETE FROM tracks_in_playlists WHERE track_id=? AND playlist_id=?");
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

}
