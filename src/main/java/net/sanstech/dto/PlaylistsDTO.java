package net.sanstech.dto;

import net.sanstech.resources.PlaylistService;

import java.util.ArrayList;

public class PlaylistsDTO {
    private PlaylistService playlistService = new PlaylistService();
    private ArrayList<PlaylistDTO> playlists;
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

    public PlaylistsDTO refreshPlaylists() {
        playlists.clear();
        playlists = playlistService.getAllPlaylists();
        return this;
    }
}
