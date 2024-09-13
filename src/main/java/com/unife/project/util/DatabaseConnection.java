package com.unife.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;
    static String databaseName = "fattoria";
    //static String connectInfo = "?autoReconnect=true&useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/" + databaseName;// + connectInfo;
    private static final String USER = "root";
    private static final String PASSWORD = "Pannocchie98!?";

    static {
        try {
            // Carica il driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}