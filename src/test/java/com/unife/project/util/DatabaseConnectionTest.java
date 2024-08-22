package com.unife.project.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
                connection.close();
            } else {
                System.out.println("Connessione al database fallita!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}