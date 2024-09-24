package com.unife.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //modifiche per selezionare le variabili d'ambiente docker hub se presenti
    static Connection connection = null;
    static String databaseName = System.getenv("MYSQL_DATABASE") != null ? System.getenv("MYSQL_DATABASE") : "fattoria";
    //private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:" + System.getenv("MYSQL_PORT") + "/" + databaseName;
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:" + (System.getenv("MYSQL_PORT") != null ? System.getenv("MYSQL_PORT") : "3307") + "/" + databaseName;
    private static final String USER = System.getenv("MYSQL_USER") != null ? System.getenv("MYSQL_USER") : "root";
    private static final String PASSWORD = System.getenv("MYSQL_PASSWORD") != null ? System.getenv("MYSQL_PASSWORD") : "Pannocchie98!?";


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

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}