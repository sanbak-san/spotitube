package net.sanstech.util;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.TrackDTO;
import net.sanstech.persistence.TrackDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistMapper {
    public static PlaylistSummaryDTO getPlaylistsFromResultSet(final ResultSet resultSet, final TokenDTO tokenDTO, final TrackDAO trackDAO) throws SQLException {
        final PlaylistSummaryDTO playlists = new PlaylistSummaryDTO();
        while (resultSet.next()) {
            List<TrackDTO> tracks = trackDAO.getAllTracksFromPlaylist(resultSet.getInt("id")).getTracks();

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
}
