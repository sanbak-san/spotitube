package net.sanstech.persistence.impl;

import net.sanstech.dto.TrackDTO;
import net.sanstech.dto.TrackSummaryDTO;
import net.sanstech.exception.SpotitubePersistenceException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackDAOImplTest {

    @Mock
    private SqlConnector sqlConnector;

    @InjectMocks
    private final TrackDAO sut = new TrackDAOImpl();

    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void getAllTracksFromPlaylist_withPlaylistId_returnsTrackSummaryDTO() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
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
        TrackSummaryDTO result = sut.getAllTracksFromPlaylist(0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("album", result.getTracks().get(0).getAlbum()),
                () -> verify(preparedStatement, times(1)).setString(1, "0")
        );
    }

    @Test
    void getAllTracksFromPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getAllTracksFromPlaylist(0));
    }

    @Test
    void getAllTracksForPlaylist_withPlaylistId_returnsTrackSummaryDTO() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id NOT IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
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
        TrackSummaryDTO result = sut.getAllTracksForPlaylist(0);

        // Assert
        assertAll(
                () -> assertEquals(1, result.getTracks().size()),
                () -> assertEquals("album", result.getTracks().get(0).getAlbum()),
                () -> verify(preparedStatement, times(1)).setString(1, "0")
        );
    }

    @Test
    void getAllTracksForPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("SELECT * FROM tracks WHERE id NOT IN (SELECT track_id FROM tracks_in_playlists WHERE playlist_id =?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getAllTracksForPlaylist(0));
    }

    @Test
    void addTrackToPlaylist_withPlaylistAndTrack_usesFunctions() {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO tracks_in_playlists (track_id, playlist_id, offline_available) VALUES (?,?,?)")).thenReturn(preparedStatement);
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setOfflineAvailable(true);

        // Call
        sut.addTrackToPlaylist(0, trackDTO);

        // Assert
        assertAll(
                () -> verify(preparedStatement, times(1)).setInt(1, trackDTO.getId()),
                () -> verify(preparedStatement, times(1)).setInt(2, 0),
                () -> verify(preparedStatement, times(1)).setBoolean(3, trackDTO.isOfflineAvailable())
        );
    }

    @Test
    void addTrackToPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO tracks_in_playlists (track_id, playlist_id, offline_available) VALUES (?,?,?)")).thenReturn(preparedStatement);
        TrackDTO trackDTO = new TrackDTO();
        when(preparedStatement.execute()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.addTrackToPlaylist(0, trackDTO));
    }

    @Test
    void removeTrackFromPlaylist_withTrackAndPlaylist_usesFunctions() {
        // Init
        when(sqlConnector.getPreparedStatement("DELETE FROM tracks_in_playlists WHERE track_id=? AND playlist_id=?")).thenReturn(preparedStatement);

        // Call
        sut.removeTrackFromPlaylist(0, 0);

        // Assert
        assertAll(
                () -> verify(preparedStatement, times(1)).setInt(1, 0),
                () -> verify(preparedStatement, times(1)).setInt(2, 0),
                () -> verify(preparedStatement, times(1)).execute()
        );
    }

    @Test
    void removeTrackFromPlaylist_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("DELETE FROM tracks_in_playlists WHERE track_id=? AND playlist_id=?")).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.removeTrackFromPlaylist(0, 0));
    }
}
