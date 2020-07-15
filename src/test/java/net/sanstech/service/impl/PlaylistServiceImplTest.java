package net.sanstech.service.impl;

import net.sanstech.dto.*;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceImplTest {

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    private TokenDAO tokenDAO;

    @Mock
    private TrackDAO trackDAO;

    @InjectMocks
    private final PlaylistService sut = new PlaylistServiceImpl();

    @Test
    void getAllPlaylists() {
        // Init
        String token = "token";
        TokenDTO tokenDTO = new TokenDTO();
        when(tokenDAO.getToken(token)).thenReturn(tokenDTO);
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");
        playlistSummaryDTO.getPlaylists().add(playlistDTO);
        when(playlistDAO.getAllPlaylists(tokenDTO)).thenReturn(playlistSummaryDTO);

        // Call
        PlaylistSummaryDTO result = sut.getAllPlaylists(token);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName())
        );
    }

    @Test
    void deletePlaylist() {
        // Init
        String token = "token";
        TokenDTO tokenDTO = new TokenDTO();
        when(tokenDAO.getToken(token)).thenReturn(tokenDTO);
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");
        playlistSummaryDTO.getPlaylists().add(playlistDTO);
        when(playlistDAO.getAllPlaylists(tokenDTO)).thenReturn(playlistSummaryDTO);

        // Call
        PlaylistSummaryDTO result = sut.deletePlaylist(token, 0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName()),
                () -> verify(playlistDAO, times(1)).deletePlaylist(0)
        );
    }

    @Test
    void addPlaylist() {
        // Init
        String token = "token";
        TokenDTO tokenDTO = new TokenDTO();
        when(tokenDAO.getToken(token)).thenReturn(tokenDTO);
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");
        playlistSummaryDTO.getPlaylists().add(playlistDTO);
        when(playlistDAO.getAllPlaylists(tokenDTO)).thenReturn(playlistSummaryDTO);

        // Call
        PlaylistSummaryDTO result = sut.addPlaylist(token, playlistDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName()),
                () -> verify(playlistDAO, times(1)).addPlaylist(tokenDTO, playlistDTO)
        );
    }

    @Test
    void editPlaylist() {
        // Init
        String token = "token";
        TokenDTO tokenDTO = new TokenDTO();
        when(tokenDAO.getToken(token)).thenReturn(tokenDTO);
        PlaylistSummaryDTO playlistSummaryDTO = new PlaylistSummaryDTO();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");
        playlistSummaryDTO.getPlaylists().add(playlistDTO);
        when(playlistDAO.getAllPlaylists(tokenDTO)).thenReturn(playlistSummaryDTO);

        // Call
        PlaylistSummaryDTO result = sut.editPlaylist(token, playlistDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName()),
                () -> verify(playlistDAO, times(1)).editPlaylist(playlistDTO)
        );
    }

    @Test
    void getTracks() {
        // Init
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("title");
        trackSummaryDTO.getTracks().add(trackDTO);
        when(trackDAO.getAllTracksFromPlaylist(0)).thenReturn(trackSummaryDTO);

        // Call
        TrackSummaryDTO result = sut.getTracks(0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("title", result.getTracks().get(0).getTitle()),
                () -> verify(trackDAO, times(1)).getAllTracksFromPlaylist(0)
        );
    }

    @Test
    void addTrackToPlaylist() {
        // Init
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("title");
        trackSummaryDTO.getTracks().add(trackDTO);
        when(trackDAO.getAllTracksFromPlaylist(0)).thenReturn(trackSummaryDTO);

        // Call
        TrackSummaryDTO result = sut.addTrackToPlaylist(0, trackDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("title", result.getTracks().get(0).getTitle()),
                () -> verify(trackDAO, times(1)).getAllTracksFromPlaylist(0),
                () -> verify(trackDAO, times(1)).addTrackToPlaylist(0, trackDTO)
        );
    }

    @Test
    void removeTrackFromPlaylist() {
        // Init
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("title");
        trackSummaryDTO.getTracks().add(trackDTO);
        when(trackDAO.getAllTracksFromPlaylist(0)).thenReturn(trackSummaryDTO);

        // Call
        TrackSummaryDTO result = sut.removeTrackFromPlaylist(0, 0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("title", result.getTracks().get(0).getTitle()),
                () -> verify(trackDAO, times(1)).getAllTracksFromPlaylist(0),
                () -> verify(trackDAO, times(1)).removeTrackFromPlaylist(0, 0)
        );
    }
}
