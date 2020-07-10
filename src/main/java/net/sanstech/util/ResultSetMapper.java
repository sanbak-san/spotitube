package net.sanstech.util;

import net.sanstech.dto.*;
import net.sanstech.persistence.TrackDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultSetMapper {
    public static PlaylistSummaryDTO getPlaylistsFromResultSet(final ResultSet resultSet, final TokenDTO tokenDTO, final TrackDAO trackDAO) throws SQLException {
        final PlaylistSummaryDTO playlists = new PlaylistSummaryDTO();
        while (resultSet.next()) {
            final List<TrackDTO> tracks = trackDAO.getAllTracksFromPlaylist(resultSet.getInt("id")).getTracks();

            playlists.getPlaylists().add(
                    new PlaylistDTO(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            tokenDTO.getUser().equals(resultSet.getString("owner")),
                            tracks));

            tracks.stream()
                    .map(TrackDTO::getDuration)
                    .forEach(playlists::incrementLength);
        }

        return playlists;
    }

    public static TrackSummaryDTO getTracksFromResultSet(final ResultSet resultSet) throws SQLException {
        final TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();

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

        return trackSummaryDTO;
    }

    public static UserDTO getUserFromResultSet(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            final UserDTO foundUser = new UserDTO();
            foundUser.setUser(resultSet.getString("user"));
            foundUser.setPassword(resultSet.getString("password"));
            foundUser.setName(resultSet.getString("name"));
            return foundUser;
        }

        return null;
    }

    public static TokenDTO getTokenFromResultSet(final ResultSet resultSet) throws SQLException {
        final TokenDTO tokenDTO = new TokenDTO();

        if (resultSet.next()) {
            tokenDTO.setUser(resultSet.getString("user"));
            tokenDTO.setToken(resultSet.getString("token"));
        }

        return tokenDTO;
    }
}
