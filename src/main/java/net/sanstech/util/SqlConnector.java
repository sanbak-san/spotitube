package net.sanstech.util;

import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlConnector {

    @Inject
    private ConnectionFactory connectionFactory;

    private final Connection connection;

    public SqlConnector() {
        connection = connectionFactory.getConnection();
    }

    public PreparedStatement getPreparedStatement(final String statement) {
        try {
            return connection.prepareStatement(statement);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
