package com.unife.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.unife.project.util.DatabaseConnection;

public class SensorSimulator {

    private static final Random random = new Random();
    

    public static void main (String[] args) {
        Timer timer = new Timer();
        timer.schedule(new SensorTask(), 0, 5000); // Esegui ogni 5 secondi
    }

    static class SensorTask extends TimerTask {
        @Override
        public void run() {
            double illuminazione = random.nextDouble() * 1000; // Lux
            double umidita = random.nextDouble() * 100; // Percentuale
            double temperatura = random.nextDouble() * 40; // Gradi Celsius
            double ph = random.nextDouble() * 14; // Scala pH
            double vento = random.nextDouble() * 100; // km/h

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO sensori (sensore_illuminazione, sensore_umidita, sensore_temperatura, sensore_PH, sensore_vento) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setDouble(1, illuminazione);
                    stmt.setDouble(2, umidita);
                    stmt.setDouble(3, temperatura);
                    stmt.setDouble(4, ph);
                    stmt.setDouble(5, vento);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}