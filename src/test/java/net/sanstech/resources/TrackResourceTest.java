package net.sanstech.resources;

import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.exception.SpotitubeTokenException;
import net.sanstech.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackResourceTest {

    @Mock
    TrackService trackService;

    @InjectMocks
    TrackResource sut;

    @BeforeEach
    void setup() {
        sut = new TrackResource(trackService);
    }

    @Test
    void getAllTracks_withEmptyToken_throwsSpotitubeTokenException() {
        // Init
        String token = "";

        // Call & Assert
        assertThrows(SpotitubeTokenException.class, () -> sut.getAllTracks(token, 0));
    }

    @Test
    void getAllTracks_withValidToken_returnsOkAndTrackSummary() {
        // Init
        String token = "token";
        int id = 0;
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        when(trackService.getAllTracksForPlaylist(id)).thenReturn(trackSummaryDTO);

        // Call
        Response result = sut.getAllTracks(token, id);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(trackSummaryDTO, result.getEntity());
    }
}
