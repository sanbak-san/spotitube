package net.sanstech.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/spotitube";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private Properties properties;

    public ConnectionFactory() {
        properties = getProperties();
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String propertiesPath = getClass().getClassLoader().getResource("").getPath() + "database.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
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
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }

    }

    private void loadDriver() {
        try {
            Class.forName(properties.getProperty("db.driver")).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
