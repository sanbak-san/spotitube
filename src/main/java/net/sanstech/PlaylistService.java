package net.sanstech;

import net.sanstech.dto.PlaylistDTO;

import java.util.ArrayList;

public class PlaylistService {
    public ArrayList<PlaylistDTO> getAllPlaylists() {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(new PlaylistDTO(1, "Death metal", true));
        playlists.add(new PlaylistDTO(2, "Pop", false));

        return playlists;
    }
}
