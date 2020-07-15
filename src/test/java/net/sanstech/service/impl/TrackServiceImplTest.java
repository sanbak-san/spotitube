package net.sanstech.service.impl;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {

    @Mock
    private TrackDAO trackDAO;

    @InjectMocks
    private final TrackService sut = new TrackServiceImpl();

    @Test
    void getAllTracksForPlaylist() {
        // Init
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setTitle("title");
        trackSummaryDTO.getTracks().add(trackDTO);
        when(trackDAO.getAllTracksForPlaylist(0)).thenReturn(trackSummaryDTO);

        // Call
        TrackSummaryDTO result = sut.getAllTracksForPlaylist(0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("title", result.getTracks().get(0).getTitle())
        );
    }
}
