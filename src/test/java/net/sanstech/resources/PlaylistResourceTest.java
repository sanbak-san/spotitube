package net.sanstech.resources;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistResourceTest {

    @Mock
    PlaylistService playlistService;

    @InjectMocks
    PlaylistResource sut = new PlaylistResource();

    @Test
    void showAllPlaylists_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.showAllPlaylists(token);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void showAllPlaylists_withValidToken_returnsOkAndListOfPlaylists() {
        // Init
        String token = "token";
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        when(playlistService.getAllPlaylists(token)).thenReturn(playlistSummaryDTO);

        // Call
        Response result = sut.showAllPlaylists(token);
        PlaylistSummaryDTO resultPlaylistSummary = (PlaylistSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(playlistSummaryDTO, resultPlaylistSummary);
    }

    @Test
    void deletePlaylist_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.deletePlaylist(token, 0);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void deletePlaylist_withValidToken_returnsOkAndListOfPlaylists() {
        // Init
        String token = "token";
        int id = 0;
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        when(playlistService.deletePlaylist(token, id)).thenReturn(playlistSummaryDTO);

        // Call
        Response result = sut.deletePlaylist(token, id);
        PlaylistSummaryDTO resultPlaylistSummary = (PlaylistSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(playlistSummaryDTO, resultPlaylistSummary);
    }

    @Test
    void addPlaylist_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.addPlaylist(token, new PlaylistDTO());

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void addPlaylist_withValidToken_returnsOkAndListOfPlaylists() {
        // Init
        String token = "token";
        PlaylistDTO playlistDTO = new PlaylistDTO();
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        when(playlistService.addPlaylist(token, playlistDTO)).thenReturn(playlistSummaryDTO);

        // Call
        Response result = sut.addPlaylist(token, playlistDTO);
        PlaylistSummaryDTO resultPlaylistSummary = (PlaylistSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(playlistSummaryDTO, resultPlaylistSummary);
    }

    @Test
    void updatePlaylistName_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.updatePlaylistName(token, new PlaylistDTO());

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void updatePlaylistName_withValidToken_returnsOkAndListOfPlaylists() {
        // Init
        String token = "token";
        PlaylistDTO playlistDTO = new PlaylistDTO();
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        when(playlistService.editPlaylist(token, playlistDTO)).thenReturn(playlistSummaryDTO);

        // Call
        Response result = sut.updatePlaylistName(token, playlistDTO);
        PlaylistSummaryDTO resultPlaylistSummary = (PlaylistSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(playlistSummaryDTO, resultPlaylistSummary);
    }

    @Test
    void getTracksForPlaylist_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.getTracksForPlaylist(token, 0);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void getTracksForPlaylist_withValidToken_returnsOkAndListOfTracks() {
        // Init
        String token = "token";
        int id = 0;
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        when(playlistService.getTracks(id)).thenReturn(trackSummaryDTO);

        // Call
        Response result = sut.getTracksForPlaylist(token, id);
        TrackSummaryDTO resultTrackSummary = (TrackSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(trackSummaryDTO, resultTrackSummary);
    }

    @Test
    void addTrackToPlaylist_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.addTrackToPlaylist(token, 0, new TrackDTO());

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void addTrackToPlaylist_withValidToken_returnsOkAndListOfTracks() {
        // Init
        String token = "token";
        int id = 0;
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        TrackDTO trackDTO = new TrackDTO();
        when(playlistService.addTrackToPlaylist(id, trackDTO)).thenReturn(trackSummaryDTO);

        // Call
        Response result = sut.addTrackToPlaylist(token, id, trackDTO);
        TrackSummaryDTO resultTrackSummary = (TrackSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(trackSummaryDTO, resultTrackSummary);
    }

    @Test
    void removeTrackFromPlaylist_withEmptyToken_returnsBadRequest() {
        // Init
        String token = "";

        // Call
        Response result = sut.removeTrackFromPlaylist(token, 0, 0);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
    }

    @Test
    void removeTrackFromPlaylist_withValidToken_returnsOkAndListOfTracks() {
        // Init
        String token = "token";
        int id = 0;
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        when(playlistService.removeTrackFromPlaylist(id, id)).thenReturn(trackSummaryDTO);

        // Call
        Response result = sut.removeTrackFromPlaylist(token, id, id);
        TrackSummaryDTO resultTrackSummary = (TrackSummaryDTO) result.getEntity();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(trackSummaryDTO, resultTrackSummary);
    }
}
