package net.sanstech.resources;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistsDTO;
import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TracksDTO;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;

import java.util.ArrayList;

public class PlaylistService {
    //private PlaylistsDTO playlistsDTO = new PlaylistsDTO();
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private TrackDAO trackDAO = new TrackDAO();

    public ArrayList<PlaylistDTO> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public PlaylistsDTO deletePlaylist(int id) {
//        playlistDAO.deletePlaylist(id);
        return new PlaylistsDTO();
    }

    public PlaylistsDTO addPlaylist(PlaylistDTO playlistDTO) {
//        playlistDAO.addPlaylist(playlistDTO);
        return new PlaylistsDTO();
    }

    public PlaylistsDTO renamePlaylist(PlaylistDTO playlistDTO) {
//        playlistDAO.renamePlaylist(playlistDTO);
        return new PlaylistsDTO();
    }

    public PlaylistsDTO getPlaylistsDTO() {
        return new PlaylistsDTO();
    }

//    public TracksDTO getTracksByPlaylistId(int playlist_id) {
//        return trackDAO.getTracksByPlaylist(playlist_id);
//    }
//
//    public TracksDTO deleteTrackFromPlaylist(int playlist_id, int track_id) {
//        trackDAO.deleteTrackFromPlaylist(playlist_id, track_id);
//        return trackDAO.getTracksByPlaylist(playlist_id);
//    }
//
//
//    public TracksDTO addTrackToPlaylist(int playlist_id, TrackDTO trackDTO) {
//        playlistDAO.addTrackToPlaylist(playlist_id, trackDTO);
//        return trackDAO.getTracksByPlaylist(playlist_id);
//    }
}
