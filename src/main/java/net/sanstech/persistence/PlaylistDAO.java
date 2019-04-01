package net.sanstech.persistence;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.TrackDTO;
import org.bson.Document;

import javax.ws.rs.QueryParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private TrackDAO trackDAO = new TrackDAO();

//    public PlaylistDTO getPlaylist(int id) {
//        PlaylistDTO foundPlaylist = null;
//
//        try (
//                MongoDatabase connection = connectionFactory.getConnection();
//                //PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlists WHERE id=?");
//        ) {
//            preparedStatement.setString(1, String.valueOf(id));
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                foundPlaylist = new PlaylistDTO();
//                foundPlaylist.setId(id);
//                foundPlaylist.setName(resultSet.getString("name"));
//                foundPlaylist.setOwner(true);
//                foundPlaylist.setTrack(trackDAO.getTrack(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return foundPlaylist;
//    }

    public ArrayList<PlaylistDTO> getAllPlaylists() {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();


        MongoDatabase db = connectionFactory.getConnection();
        MongoCollection<Document> collection = db.getCollection("playlist");
        Document document = new Document();
        FindIterable iterable = collection.find(document);

        System.out.println(iterable.first());

        for (Document doc : collection.find()) {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(doc.getString("_id"));
            playlistDTO.setName(doc.getString("name"));
            playlists.add(playlistDTO);
        }

        System.out.println(playlists);
        return playlists;
    }

//    public void deletePlaylist(int id) {
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlists WHERE id=?");
//        ) {
//            preparedStatement.setInt(1, id);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addPlaylist(PlaylistDTO playlistDTO) {
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playlists (name, owner) VALUES (?,?)");
//        ) {
//            preparedStatement.setString(1, playlistDTO.getName());
//            preparedStatement.setString(2, String.valueOf(playlistDTO.getOwner()));
//            preparedStatement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void renamePlaylist(PlaylistDTO playlistDTO) {
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ?");
//        ) {
//            preparedStatement.setString(1, playlistDTO.getName());
//            preparedStatement.setInt(2, playlistDTO.getId());
//            preparedStatement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void addTrackToPlaylist(int playlist_id, TrackDTO trackDTO) {
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO track_in_playlist (playlist_id, track_id, offlineAvailable) VALUES (?,?,?)");
//        ) {
//            preparedStatement.setInt(1, playlist_id);
//            preparedStatement.setInt(2, trackDTO.getId());
//            preparedStatement.setBoolean(3, trackDTO.isOfflineAvailable());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}
