package net.sanstech.persistence.impl;

import net.sanstech.dto.TokenDTO;
import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.TokenDAO;
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
class TokenDAOImplTest {

    @Mock
    private SqlConnector sqlConnector;

    @InjectMocks
    private final TokenDAO sut = new TokenDAOImpl();

    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void getToken_withUserDTO_returnsTokenDTO() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM tokens WHERE user=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("user")).thenReturn("user");
        when(resultSet.getString("token")).thenReturn("token");
        UserDTO userDTO = new UserDTO("user", "pass");

        // Call
        TokenDTO result = sut.getToken(userDTO);

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user", result.getUser()),
                () -> assertEquals("token", result.getToken()),
                () -> verify(preparedStatement, times(1)).setString(1, userDTO.getUser())
        );
    }

    @Test
    void getToken_withUserDTOAndSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("SELECT * FROM tokens WHERE user=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);
        UserDTO userDTO = new UserDTO("user", "pass");

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getToken(userDTO));
    }

    @Test
    void getToken_withToken_returnsTokenDTO() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM tokens WHERE token=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("user")).thenReturn("user");
        when(resultSet.getString("token")).thenReturn("token");

        // Call
        TokenDTO result = sut.getToken("token");

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user", result.getUser()),
                () -> assertEquals("token", result.getToken()),
                () -> verify(preparedStatement, times(1)).setString(1, "token")
        );
    }

    @Test
    void getToken_withTokenAndSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("SELECT * FROM tokens WHERE token=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getToken("token"));
    }

    @Test
    void insertToken_withTokenAndUser_returnsTokenDTO() {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO tokens (token, user) VALUES (?,?)")).thenReturn(preparedStatement);
        UserDTO userDTO = new UserDTO("user", "pass");
        userDTO.setName("name");
        String token = "token";

        // Call
        TokenDTO result = sut.insertToken(token, userDTO);

        // Assert
        assertAll(
                () -> assertEquals("token", result.getToken()),
                () -> assertEquals("name", result.getUser()),
                () -> verify(preparedStatement, times(1)).setString(1, token),
                () -> verify(preparedStatement, times(1)).setString(2, userDTO.getUser()),
                () -> verify(preparedStatement, times(1)).execute()
        );
    }

    @Test
    void insertToken_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("INSERT INTO tokens (token, user) VALUES (?,?)")).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(SQLException.class);
        UserDTO userDTO = new UserDTO("user", "pass");
        String token = "token";

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.insertToken(token, userDTO));
    }
}
