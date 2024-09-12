package com.unife.project.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
            } else {
                System.out.println("Connessione al database fallita!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}