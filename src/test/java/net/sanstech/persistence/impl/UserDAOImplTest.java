package net.sanstech.persistence.impl;

import net.sanstech.dto.UserDTO;
import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.UserDAO;
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
class UserDAOImplTest {

    @Mock
    private SqlConnector sqlConnector;

    @InjectMocks
    private final UserDAO sut = new UserDAOImpl();

    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void getUser_withUsernameAndPassword_returnsUser() throws SQLException {
        // Init
        ResultSet resultSet = mock(ResultSet.class);
        when(sqlConnector.getPreparedStatement("SELECT * FROM account WHERE user=? AND password=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("user")).thenReturn("user");
        when(resultSet.getString("password")).thenReturn("password");
        when(resultSet.getString("name")).thenReturn("name");

        // Call
        UserDTO result = sut.getUser("user", "password");

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user", result.getUser()),
                () -> assertEquals("password", result.getPassword()),
                () -> assertEquals("name", result.getName()),
                () -> verify(preparedStatement, times(1)).setString(1, "user"),
                () -> verify(preparedStatement, times(1)).setString(2, "password")
        );
    }

    @Test
    void getUser_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        when(sqlConnector.getPreparedStatement("SELECT * FROM account WHERE user=? AND password=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(SQLException.class);

        // Call
        assertThrows(SpotitubePersistenceException.class, () -> sut.getUser("user", "password"));
    }
}
