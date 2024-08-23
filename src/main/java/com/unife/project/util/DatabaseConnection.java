package com.unife.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;
    static String databaseName = "fattoria";
    private static final String URL = "jdbc:mysql://localhost:3306/" + databaseName;
    private static final String USER = "root";
    private static final String PASSWORD = "Pannocchie98!?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}