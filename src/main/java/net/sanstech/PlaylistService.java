package net.sanstech;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.persistence.PlaylistDAO;

import java.util.ArrayList;

public class PlaylistService {
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public ArrayList<PlaylistDTO> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public ArrayList<PlaylistDTO> deletePlaylist(int id) {
        playlistDAO.deletePlaylist(id);
        return getAllPlaylists();
    }

    public ArrayList<PlaylistDTO> addPlaylist(PlaylistDTO playlistDTO) {
        playlistDAO.addPlaylist(playlistDTO);
        return getAllPlaylists();
    }
}
