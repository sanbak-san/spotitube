package net.sanstech.dto;

import net.sanstech.PlaylistService;

import java.util.ArrayList;

public class PlaylistsDTO {
    private ArrayList<PlaylistDTO> playlists;
    private float length;

    public PlaylistsDTO() {
        this.playlists = new PlaylistService().getAllPlaylists();
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
}
