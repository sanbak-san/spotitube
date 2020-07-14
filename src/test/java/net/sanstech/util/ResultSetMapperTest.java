package net.sanstech.util;

import net.sanstech.dto.*;
import net.sanstech.persistence.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultSetMapperTest {

    ResultSet resultSet;

    @BeforeEach
    void setup() {
        resultSet = mock(ResultSet.class);
    }

    @Test
    void getPlaylistsFromResultSet_withNoTracksInResultSet_returnsEmptyPlaylistSummaryDTO() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(false);
        TokenDTO tokenDTO = new TokenDTO("token", "user");
        TrackDAO trackDAO = mock(TrackDAO.class);

        // Call
        PlaylistSummaryDTO result = ResultSetMapper.getPlaylistsFromResultSet(resultSet, tokenDTO, trackDAO);

        // Assert
        assertEquals(0, result.getPlaylists().size());
    }

    @Test
    void getPlaylistsFromResultSet_withTracksInResultSet_returnsPlaylistSummaryDTOWithOnePlaylist() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        int id = 0;
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("name")).thenReturn("name");
        when(resultSet.getString("owner")).thenReturn("user");

        TokenDTO tokenDTO = new TokenDTO("token", "user");
        TrackDAO trackDAO = mock(TrackDAO.class);

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(200);

        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(trackDTO);
        TrackSummaryDTO trackSummaryDTO = mock(TrackSummaryDTO.class);
        when(trackDAO.getAllTracksFromPlaylist(0)).thenReturn(trackSummaryDTO);
        when(trackSummaryDTO.getTracks()).thenReturn(tracks);

        // Call
        PlaylistSummaryDTO result = ResultSetMapper.getPlaylistsFromResultSet(resultSet, tokenDTO, trackDAO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertTrue(result.getPlaylists().get(0).isOwner()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName())
        );
    }

    @Test
    void getTracksFromResultSet_withNoTracksInResultSet_returnsEmptyObject() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(false);

        // Call
        TrackSummaryDTO result = ResultSetMapper.getTracksFromResultSet(resultSet);

        // Assert
        assertEquals(0, result.getTracks().size());
    }

    @Test
    void getTracksFromResultSet_withTrackInResultSet_returnsObjectWithOneTrack() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("album")).thenReturn("album");
        when(resultSet.getString("description")).thenReturn("description");
        when(resultSet.getString("performer")).thenReturn("performer");
        when(resultSet.getString("title")).thenReturn("title");
        when(resultSet.getString("publicationDate")).thenReturn("publicationDate");
        when(resultSet.getBoolean("offlineAvailable")).thenReturn(true);
        when(resultSet.getInt("duration")).thenReturn(10);

        // Call
        TrackSummaryDTO result = ResultSetMapper.getTracksFromResultSet(resultSet);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("album", result.getTracks().get(0).getAlbum())
        );
    }

    @Test
    void getUserFromResultSet_withNoUserInResultSet_returnsNull() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(false);

        // Call
        UserDTO result = ResultSetMapper.getUserFromResultSet(resultSet);

        // Assert
        assertNull(result);
    }

    @Test
    void getUserFromResultSet_withUserInResultSet_returnsUser() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("user")).thenReturn("user");
        when(resultSet.getString("password")).thenReturn("password");
        when(resultSet.getString("name")).thenReturn("name");

        // Call
        UserDTO result = ResultSetMapper.getUserFromResultSet(resultSet);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user", result.getUser()),
                () -> assertEquals("password", result.getPassword()),
                () -> assertEquals("name", result.getName())
        );
    }

    @Test
    void getTokenFromResultSet_withNoTokenInResultSet_returnsEmptyTokenDTO() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(false);

        // Call
        TokenDTO result = ResultSetMapper.getTokenFromResultSet(resultSet);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertNull(result.getUser()),
                () -> assertNull(result.getToken())
        );
    }

    @Test
    void getTokenFromResultSet_withTokenInResultSet_returnsTokenDTO() throws SQLException {
        // Init
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("user")).thenReturn("user");
        when(resultSet.getString("token")).thenReturn("token");

        // Call
        TokenDTO result = ResultSetMapper.getTokenFromResultSet(resultSet);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user", result.getUser()),
                () -> assertEquals("token", result.getToken())
        );
    }
}
