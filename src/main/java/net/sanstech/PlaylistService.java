package net.sanstech;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.persistence.PlaylistDAO;

import java.util.ArrayList;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public ArrayList<PlaylistDTO> getAllPlaylists() {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(playlistDAO.getPlaylist(1));
        playlists.add(new PlaylistDTO(2, "Pop", false));

        return playlists;
    }
}
