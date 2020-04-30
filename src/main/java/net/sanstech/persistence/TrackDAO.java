package net.sanstech.persistence;

import net.sanstech.dto.TrackSummaryDTO;

public interface TrackDAO {
    TrackSummaryDTO getAllTracksFromPlaylist(int playlistId);
    TrackSummaryDTO getAllTracksForPlaylist(int playlistId);
}
