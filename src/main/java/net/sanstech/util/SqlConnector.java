package net.sanstech.util;

import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlConnector {

    private final ConnectionFactory connectionFactory;

    @Inject
    public SqlConnector(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PreparedStatement getPreparedStatement(final String statement) {
        try (
                final Connection connection = connectionFactory.getConnection()
        ) {
            return connection.prepareStatement(statement);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }
}
