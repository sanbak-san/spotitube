package net.sanstech;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/spotitube", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE user=?");
        ) {
            preparedStatement.setString(1, "harry");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("fsdjfbdjbf");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("password"));
                System.out.println("fsdjfbdjbf");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
