package za.ac.cput.util;

import java.sql.*;

public class Server {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static boolean connect() throws SQLException {
        try {
            String host = "localhost";
            String database = "delivery_package";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + "/" + database,
                    user,
                    password
            );
            return true;
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database: " + e.getMessage());
        }
    }

    public static boolean disconnect() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException("Failed to disconnect from the database: " + e.getMessage());
        }
    }
}
