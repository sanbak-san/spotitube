package net.sanstech.persistence;

import net.sanstech.exception.SpotitubePersistenceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/spotitube";
    private static final String DB_USER = "san";
    private static final String DB_PASS = "pass";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private Properties properties;

    public ConnectionFactory() {
        properties = getProperties();
    }

    private Properties getProperties() {
        properties = new Properties();

        final String propertiesPath = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath() + "database.properties";
        try {
            final FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);
        } catch (final IOException e) {
            properties.setProperty("db.url", DB_URL);
            properties.setProperty("db.user", DB_USER);
            properties.setProperty("db.pass", DB_PASS);
            properties.setProperty("db.driver", DB_DRIVER);
            e.printStackTrace();
        }

        return properties;
    }

    public Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.pass"));
        } catch (final SQLException e) {
            throw new SpotitubePersistenceException(e);
        }

    }

    private void loadDriver() {
        try {
            Class.forName(properties.getProperty("db.driver")).newInstance();
        } catch (final IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
