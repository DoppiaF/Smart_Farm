package com.unife.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.unife.project.model.mo.Zona;
import com.unife.project.util.DatabaseConnection;

public class SensorSimulator {

    private static final Random random = new Random();
    private static List<Zona> sensori = new ArrayList<>();
    private Timer timer;

    public SensorSimulator() {
    }
    
    public void start() {
        timer = new Timer();    //crea nuovo timer e crea un nuovo task che fa partire la simulazione dei sensori
        timer.schedule(new SensorTask(), 0, 300000); // Esegui ogni 5 minuti
        System.out.println("SensorSimulator avviato.");
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;       //ferma il timer
            System.out.println("SensorSimulator fermato.");
        }
    }

    private class SensorTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Simulazione sensori");
            sensori = recuperaTuttiISensori();
            aggiornaSensori();
        }
    }

     private static List<Zona> recuperaTuttiISensori() {
        List<Zona> sensori = new ArrayList<>();
        String selectQuery = "SELECT coord_x, coord_y, id_piantagione FROM zona";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Zona zona = new Zona();
                zona.setCoordX(rs.getInt("coord_x"));
                zona.setCoordY(rs.getInt("coord_y"));
                zona.setId_piantagione(rs.getInt("id_piantagione"));
                sensori.add(zona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sensori;
    }

    public static void aggiornaSensori() {
        //sensori = recuperaTuttiISensori();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String updateQuery = "UPDATE zona SET sensore_illuminazione = ?, sensore_umidita = ?, sensore_temperatura = ?, sensore_PH = ?, sensore_vento = ? WHERE id_piantagione = ? AND coord_x = ? AND coord_y = ?";

            double illuminazione = 0;
            double umidita = 0;
            double temperatura = 0;
            double ph = 0;
            double vento = 0;
            for (Zona sensore : sensori) {
                illuminazione = random.nextDouble() * 1000; // Lux
                umidita = random.nextDouble() * 100; // Percentuale
                temperatura = random.nextDouble() * 40; // Gradi Celsius
                ph = random.nextDouble() * 14; // Scala pH
                vento = random.nextDouble() * 100; // km/h
                try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                    stmt.setDouble(1, illuminazione);
                    stmt.setDouble(2, umidita);
                    stmt.setDouble(3, temperatura);
                    stmt.setDouble(4, ph);
                    stmt.setDouble(5, vento);
                    stmt.setInt(6, sensore.getId_piantagione());
                    stmt.setInt(7, sensore.getCoordX());
                    stmt.setInt(8, sensore.getCoordY());
                    stmt.executeUpdate();
                }
            }
            System.out.println("Aggiornamento sensori: illuminazione=" + illuminazione + " umidita=" + umidita + " temperatura=" + temperatura + " ph=" + ph + " vento=" + vento);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}