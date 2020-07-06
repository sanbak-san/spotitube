package net.sanstech.persistence.impl;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.util.PlaylistMapper;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {
    private static final String DELETE_FROM_PLAYLISTS_WHERE_ID = "DELETE FROM playlists WHERE id=?";
    private static final String INSERT_INTO_PLAYLISTS_NAME_OWNER_VALUES = "INSERT INTO playlists (name, owner) VALUES (?,?)";

    @Inject
    private ConnectionFactory connectionFactory;

    @Inject
    private TrackDAO trackDAO;

    @Override
    public PlaylistSummaryDTO getAllPlaylists(final TokenDTO tokenDTO) {
        try (
                // TODO: Try with resources lostrekken voor Single Responsability
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists")
        ) {
            // TODO: Klasse PlaylistMapper maken en daar onderstaande methode
            return PlaylistMapper.getPlaylistsFromResultSet(preparedStatement.executeQuery(), tokenDTO, trackDAO);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void deletePlaylist(final int id) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PLAYLISTS_WHERE_ID)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void addPlaylist(final TokenDTO tokenDTO, final PlaylistDTO playlistDTO) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PLAYLISTS_NAME_OWNER_VALUES)
        ) {
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, tokenDTO.getUser());
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public void editPlaylist(final PlaylistDTO playlistDTO) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlists SET name =? WHERE id =?")
        ) {
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, String.valueOf(playlistDTO.getId()));
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
