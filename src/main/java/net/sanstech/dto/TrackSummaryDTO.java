package net.sanstech.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrackSummaryDTO {

    private List<TrackDTO> tracks;

    public TrackSummaryDTO() {
        tracks = new ArrayList<>();
    }
}
