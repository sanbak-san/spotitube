package net.sanstech.persistence.impl;

import net.sanstech.dto.*;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TrackDAO;
import net.sanstech.util.SqlConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistDAOImplTest {

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private SqlConnector sqlConnector;

    @InjectMocks
    private final PlaylistDAO sut = new PlaylistDAOImpl();

    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void getAllPlaylists_withPlaylistInResultSet_returnPlaylistSummaryDTOWithPlaylist() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(sqlConnector.getPreparedStatement("SELECT * FROM playlists")).thenReturn(preparedStatement);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(0);
        when(resultSet.getString("name")).thenReturn("name");
        when(resultSet.getString("owner")).thenReturn("user");

        TokenDTO tokenDTO = new TokenDTO("token", "user");

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(200);

        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(trackDTO);
        TrackSummaryDTO trackSummaryDTO = new TrackSummaryDTO();
        trackSummaryDTO.setTracks(tracks);
        when(trackDAO.getAllTracksFromPlaylist(0)).thenReturn(trackSummaryDTO);

        // Call
        PlaylistSummaryDTO result = sut.getAllPlaylists(tokenDTO);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getPlaylists().size()),
                () -> assertTrue(result.getPlaylists().get(0).isOwner()),
                () -> assertEquals("name", result.getPlaylists().get(0).getName())
        );
    }

    @Test
    void getAllPlaylists_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM playlists")).thenReturn(preparedStatement);
        TokenDTO tokenDTO = new TokenDTO();

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getAllPlaylists(tokenDTO));
    }

    @Test
    void deletePlaylist_withPlaylistId_usesProperFunctions() {
        // Init
        when(sqlConnector.getPreparedStatement("DELETE FROM playlists WHERE id=?")).thenReturn(preparedStatement);

        // Call
        sut.deletePlaylist(0);

        // Assert
        assertAll(
                () -> verify(preparedStatement, times(1)).setInt(1, 0),
                () -> verify(preparedStatement, times(1)).execute()
        );
    }

    @Test
    void deletePlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("DELETE FROM playlists WHERE id=?")).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.deletePlaylist(0));
    }

    @Test
    void addPlaylist_withTokenDTOAndPlayListDTO_usesProperFunctions() {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO playlists (name, owner) VALUES (?,?)")).thenReturn(preparedStatement);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUser("username");
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");

        // Call
        sut.addPlaylist(tokenDTO, playlistDTO);

        // Assert
        assertAll(
                () -> verify(preparedStatement, times(1)).setString(1, "name"),
                () -> verify(preparedStatement, times(1)).setString(2, "username"),
                () -> verify(preparedStatement, times(1)).execute()
        );
    }

    @Test
    void addPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO playlists (name, owner) VALUES (?,?)")).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(SQLException.class);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUser("username");
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.addPlaylist(tokenDTO, playlistDTO));
    }

    @Test
    void editPlaylist_withTokenDTOAndPlayListDTO_usesProperFunctions() {
        // Init
        when(sqlConnector.getPreparedStatement("UPDATE playlists SET name =? WHERE id =?")).thenReturn(preparedStatement);
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("name");
        playlistDTO.setId(1);

        // Call
        sut.editPlaylist(playlistDTO);

        // Assert
        assertAll(
                () -> verify(preparedStatement, times(1)).setString(1, "name"),
                () -> verify(preparedStatement, times(1)).setString(2, "1"),
                () -> verify(preparedStatement, times(1)).execute()
        );
    }

    @Test
    void editPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("UPDATE playlists SET name =? WHERE id =?")).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(SQLException.class);
        PlaylistDTO playlistDTO = new PlaylistDTO();

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.editPlaylist(playlistDTO));
    }
}
