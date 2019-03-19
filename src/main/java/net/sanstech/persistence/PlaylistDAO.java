package net.sanstech.persistence;

import net.sanstech.dto.PlaylistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistDTO getPlaylist(int id) {
        PlaylistDTO foundPlaylist = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists WHERE id=?");
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();

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

}
