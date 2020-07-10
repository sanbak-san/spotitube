package net.sanstech.util;

import net.sanstech.exception.SpotitubePersistenceException;
import net.sanstech.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlConnector implements AutoCloseable {

    @Inject
    private ConnectionFactory connectionFactory;

    private Connection connection;

    public SqlConnector() {
    }

    public PreparedStatement getPreparedStatement(final String statement) {
        try {
            connection = connectionFactory.getConnection();
            return connection.prepareStatement(statement);
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
