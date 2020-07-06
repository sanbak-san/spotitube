package net.sanstech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO {
    private int id;
    private String title;
    private String performer;
    private String album;
    private int playcount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;
    private int duration;


    public TrackDTO() {
    }
}
