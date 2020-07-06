package net.sanstech.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlaylistDTO {
    private int id;
    private String name;

    private boolean owner;
    private List<TrackDTO> tracks = new ArrayList<>();

    public PlaylistDTO() {
    }

    public PlaylistDTO(final int id, final String name, final boolean owner, final List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }
}
