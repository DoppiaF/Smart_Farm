package com.unife.project;

import java.sql.Connection;
import java.sql.SQLException;


import com.unife.project.service.SensorSimulator;
import com.unife.project.util.DatabaseConnection;
import com.unife.project.util.WindowUtil;

//import com.unife.project.model.dao.UtenteDAOImpl;
//import com.unife.project.util.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.sql.Connection;

public class MainApp extends Application {

    private SensorSimulator sensorSimulator;

    @Override
    public void start(Stage primaryStage) {
        try {
            //per tenere un log del funzionamento di mysql
            /*Logger logger = Logger.getLogger("com.mysql.cj");
            logger.setLevel(Level.FINE);
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.FINE);
            logger.addHandler(handler);*/

            // Inizializza e attiva il SensorSimulator
            sensorSimulator = new SensorSimulator();
            sensorSimulator.start();

            // Carica il file FXML della schermata di home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent root = loader.load();

            // Imposta la scena
            Scene scene = new Scene(root);
            WindowUtil.setWindow(primaryStage, scene, "Smartfarm - Home");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Chiudi il SensorSimulator
        if (sensorSimulator != null) {
            sensorSimulator.stop();
        }

        // Chiudi la connessione al database
        try {
            DatabaseConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}