package net.sanstech.service.impl;

import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.service.TrackService;

import javax.inject.Inject;

public class TrackServiceImpl implements TrackService {

    @Inject
    TrackDAO trackDAO;

    @Override
    public TrackSummaryDTO getAllTracksForPlaylist(int playlistId) {
        return trackDAO.getAllTracksForPlaylist(playlistId);
    }
}
