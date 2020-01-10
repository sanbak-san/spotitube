package net.sanstech.persistence.impl;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final TrackDAO trackDAO = new TrackDAO();

    @Override
    public PlaylistDTO getPlaylist(final int id) {
        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists WHERE id=?")
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    final PlaylistDTO foundPlaylist = new PlaylistDTO();
                    foundPlaylist.setId(id);
                    foundPlaylist.setName(resultSet.getString("name"));
                    foundPlaylist.setOwner(true);
                    foundPlaylist.setTrack(trackDAO.getTrack(1));
                    return foundPlaylist;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    @Override
    public PlaylistSummaryDTO getAllPlaylists(final TokenDTO tokenDTO) {

        try (
                final Connection connection = connectionFactory.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists")
        ) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                final PlaylistSummaryDTO playlists = new PlaylistSummaryDTO();
                while (resultSet.next()) {
                    playlists.getPlaylists().add(
                            new PlaylistDTO(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    tokenDTO.getUser().equals(resultSet.getString("owner")),
                                    new ArrayList<>()));
                }
                return playlists;
            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }

    }

    @Override
    public void deletePlaylist(final int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlists WHERE id=?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPlaylist(final PlaylistDTO playlistDTO) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playlists (name, owner) VALUES (?,?)");
        ) {
            preparedStatement.setString(1, playlistDTO.getName());
            preparedStatement.setString(2, String.valueOf(playlistDTO.getOwner()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
