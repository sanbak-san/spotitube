package net.sanstech.persistence;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;

public interface TrackDAO {
    TrackSummaryDTO getAllTracksFromPlaylist(int playlistId);

    TrackSummaryDTO getAllTracksForPlaylist(int playlistId);

    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO);

    void removeTrackFromPlaylist(int playlistId, int trackId);
}
