package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/rental_app"; // Make sure to replace 'your_database' with the actual database name.

    public static Connection getConnection() throws SQLException {
        try {
            // Use no username and password as default in XAMPP
            Connection connection = DriverManager.getConnection(URL, "root", ""); // Default username is 'root' and no password
            System.out.println("Database connection successful");
            return connection;
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            throw e; // Rethrow the exception
        }
    }
}
