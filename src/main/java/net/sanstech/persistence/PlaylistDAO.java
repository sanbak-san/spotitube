package net.sanstech.persistence;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;

public interface PlaylistDAO {

    PlaylistDTO getPlaylist(int id);

    PlaylistSummaryDTO getAllPlaylists(TokenDTO tokenDTO);

    void deletePlaylist(int id);

    void addPlaylist(TokenDTO tokenDTO, PlaylistDTO playlistDTO);

    void editPlaylist(PlaylistDTO playlistDTO);
}
