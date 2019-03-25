package net.sanstech.dto;

import net.sanstech.PlaylistService;
import net.sanstech.persistence.PlaylistDAO;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PlaylistsDTO {
    private ArrayList<PlaylistDTO> playlists;
    private PlaylistService playlistService = new PlaylistService();
    private float length;

    public PlaylistsDTO() {
        this.playlists = playlistService.getAllPlaylists();
        this.length = 1337;
    }

    public ArrayList<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void removePlaylist(int id) {
        //playlistService.deletePlaylist(id);
        this.playlists = playlistService.deletePlaylist(id);
    }

    public void addPlaylist(PlaylistDTO playlistDTO) {
        this.playlists = playlistService.addPlaylist(playlistDTO);
    }
}
