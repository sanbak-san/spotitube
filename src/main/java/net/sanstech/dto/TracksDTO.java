package net.sanstech.dto;

import net.sanstech.dto.TrackDTO;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {
    private List<TrackDTO> tracks = new ArrayList<>();

    public TracksDTO() {
    }

    public void addTrack(TrackDTO trackDTO) {
        tracks.add(trackDTO);
    }

    public void removeTrack(TrackDTO trackDTO) {
        tracks.remove(trackDTO);
    }

    @Override
    public String toString() {
        return "TracksDTO{" +
                "tracks=" + tracks +
                '}';
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }
}
