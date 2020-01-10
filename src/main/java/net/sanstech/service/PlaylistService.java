package net.sanstech.service;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;

public interface PlaylistService {
    PlaylistSummaryDTO getAllPlaylists(String token);

    PlaylistSummaryDTO deletePlaylist(String token, int id);

    PlaylistSummaryDTO addPlaylist(String token, PlaylistDTO playlistDTO);
}