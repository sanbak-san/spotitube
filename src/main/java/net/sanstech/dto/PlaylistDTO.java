package net.sanstech.dto;

import net.sanstech.persistence.TrackDAO;

import java.util.ArrayList;

public class PlaylistDTO {
    private int id;
    private String name;

    private boolean owner;
    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        tracks = new ArrayList<>();
        //tracks.add(trackDAO.getTrack(1));
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

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void setTrack(TrackDTO track) {
        tracks.add(track);
    }

    public boolean getOwner() {
        return owner;
    }

}
