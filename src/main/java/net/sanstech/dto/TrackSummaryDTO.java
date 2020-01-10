package net.sanstech.dto;

import java.util.ArrayList;
import java.util.List;

public class TrackSummaryDTO {

    private List<TrackDTO> tracks;

    public TrackSummaryDTO() {
        tracks = new ArrayList<>();
    }

    public List<TrackDTO> getPlaylists() {
        return tracks;
    }

    public void setPlaylists(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

}
