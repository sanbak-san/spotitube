package net.sanstech.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrackSummaryDTO {

    private List<TrackDTO> tracks;

    public TrackSummaryDTO() {
        tracks = new ArrayList<>();
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
