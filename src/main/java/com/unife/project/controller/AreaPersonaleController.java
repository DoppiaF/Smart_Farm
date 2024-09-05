package com.unife.project.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AreaPersonaleController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Button loginButton;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private void initialize() {
        // Inizializza i componenti se necessario
        // Ad esempio, carica i dati nei grafici
        loadChartData();
    }

    @FXML
    private void handleLoginButtonAction() {
        // Logica per gestire il login
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean rememberMe = rememberMeCheckBox.isSelected();

        // Esegui l'autenticazione dell'utente
        authenticateUser(username, password, rememberMe);
    }

    private void loadChartData() {
        // Logica per caricare i dati nei grafici
    }

    private void authenticateUser(String username, String password, boolean rememberMe) {
        // Logica per autenticare l'utente
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Login successful");
            // Aggiorna l'interfaccia utente o naviga a un'altra schermata
        } else {
            System.out.println("Login failed");
        }
    }
}