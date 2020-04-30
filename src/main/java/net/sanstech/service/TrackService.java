package net.sanstech.service;

import net.sanstech.dto.TrackSummaryDTO;

public interface TrackService {
    TrackSummaryDTO getAllTracksForPlaylist(int playlistId);
}
