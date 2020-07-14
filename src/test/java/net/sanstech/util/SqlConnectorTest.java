package net.sanstech.util;

import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SqlConnectorTest {

    ConnectionFactory connectionFactory;

    SqlConnector sut;

    @BeforeEach
    void setup() {
        connectionFactory = mock(ConnectionFactory.class);
        sut = new SqlConnector(connectionFactory);
    }

    @Test
    void getPreparedStatement_withSQLExceptionThrown_throwsSpotitubePersistenceException() throws SQLException {
        // Init
        Connection connection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement("SELECT *")).thenThrow(SQLException.class);

        // Call & Assert
        assertThrows(SpotitubePersistenceException.class, () -> sut.getPreparedStatement("SELECT *"));
    }

    @Test
    void getPreparedStatement_withNoSQLExceptionThrown_returnsPreparedStatement() throws SQLException {
        // Init
        Connection connection = mock(Connection.class);
        when(connectionFactory.getConnection()).thenReturn(connection);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement("SELECT *")).thenReturn(preparedStatement);

        // Call
        PreparedStatement result = sut.getPreparedStatement("SELECT *");

        // Assert
        assertEquals(resultSet, result.executeQuery());
    }


}
