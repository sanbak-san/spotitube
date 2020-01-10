package net.sanstech.persistence;

import net.sanstech.dto.PlaylistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final TrackDAO trackDAO = new TrackDAO();

    public PlaylistDTO getPlaylist(int id) {
        PlaylistDTO foundPlaylist = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists WHERE id=?");
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundPlaylist = new PlaylistDTO();
                foundPlaylist.setId(id);
                foundPlaylist.setName(resultSet.getString("name"));
                foundPlaylist.setOwner(true);
                foundPlaylist.setTrack(trackDAO.getTrack(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundPlaylist;
    }

    public ArrayList<PlaylistDTO> getAllPlaylists() {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM playlists");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlists.add(getPlaylist(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    public void deletePlaylist(int id) {
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

    public void addPlaylist(PlaylistDTO playlistDTO) {
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
