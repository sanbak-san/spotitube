package net.sanstech.persistence;

import net.sanstech.dto.TrackSummaryDTO;

public interface TrackDAO {
    TrackSummaryDTO getAllTracksForPlaylist(int id);


}
