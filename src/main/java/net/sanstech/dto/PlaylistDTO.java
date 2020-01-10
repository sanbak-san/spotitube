package net.sanstech.dto;

import net.sanstech.persistence.TrackDAO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;

    private boolean owner;
    private List<TrackDTO> tracks = new ArrayList<>();

    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistDTO() {
    }

    public PlaylistDTO(final int id, final String name, final boolean owner, final List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void setTrack(TrackDTO track) {
        tracks.add(track);
    }

    public boolean getOwner() {
        return owner;
    }

}
