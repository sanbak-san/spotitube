package net.sanstech.service;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TrackSummaryDTO;

public interface PlaylistService {
    PlaylistSummaryDTO getAllPlaylists(String token);

    PlaylistSummaryDTO deletePlaylist(String token, int id);

    PlaylistSummaryDTO addPlaylist(String token, PlaylistDTO playlistDTO);

    PlaylistSummaryDTO editPlaylist(String token, PlaylistDTO playlistDTO);

    TrackSummaryDTO getAllTracks(int forPlaylist);
}
