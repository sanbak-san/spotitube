package net.sanstech.persistence.impl;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.util.ResultSetMapper;
import net.sanstech.util.SqlConnector;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {
    private static final String DELETE_FROM_PLAYLISTS_WHERE_ID = "DELETE FROM playlists WHERE id=?";
    private static final String INSERT_INTO_PLAYLISTS_NAME_OWNER_VALUES = "INSERT INTO playlists (name, owner) VALUES (?,?)";

    @Inject
    private TrackDAO trackDAO;

    @Inject
    private SqlConnector sqlConnector;

    @Override
    public PlaylistSummaryDTO getAllPlaylists(final TokenDTO tokenDTO) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("SELECT * FROM playlists");
            return ResultSetMapper.getPlaylistsFromResultSet(preparedStatement.executeQuery(), tokenDTO, trackDAO);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void deletePlaylist(final int id) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement(DELETE_FROM_PLAYLISTS_WHERE_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void addPlaylist(final TokenDTO tokenDTO, final PlaylistDTO playlistDTO) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement(INSERT_INTO_PLAYLISTS_NAME_OWNER_VALUES);
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, tokenDTO.getUser());
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void editPlaylist(final PlaylistDTO playlistDTO) {
        try {
            final PreparedStatement preparedStatement = sqlConnector.getPreparedStatement("UPDATE playlists SET name =? WHERE id =?");
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, String.valueOf(playlistDTO.getId()));
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
