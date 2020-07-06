package net.sanstech.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlaylistSummaryDTO {

    private List<PlaylistDTO> playlists;
    private int length;

    public PlaylistSummaryDTO() {
        playlists = new ArrayList<>();
    }

    public void incrementLength(final int duration) {
        length += duration;
    }
}
