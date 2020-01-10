package net.sanstech.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSummaryDTO {

    private List<PlaylistDTO> playlists;
    private int length;

    public PlaylistSummaryDTO() {
        playlists = new ArrayList<>();
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public float getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

}
