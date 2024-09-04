package com.unife.project.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomeController {
    
    /************************************************
     * buttons
     ************************************************/

    /*@FXML
    private MenuItem loginMenuItem;*/

    @FXML
    private Button areaPiantagioneButton;

    @FXML 
    private Button areaAdminButton;

    @FXML
    private Button areaPastoreButton;

    /************************************************
     * initialize
     ************************************************/
    // Variabile per lo stato di login e ruolo dell'utente
    private boolean isLoggedIn = false;
    private boolean isAdmin = false;
    private boolean isPiantagione = false;
    private boolean isPastore = false;

    @FXML
    public void initialize() {
        // Imposta la visibilità e l'abilitazione dei bottoni in base allo stato di login e ruolo
        updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        //areaAdminButton.setVisible(isLoggedIn && isAdmin);
        areaAdminButton.setDisable(!(isLoggedIn && isAdmin));

        //areaPiantagioneButton.setVisible(isLoggedIn && isPiantagione);
        areaPiantagioneButton.setDisable(!(isLoggedIn && isPiantagione));

        //areaPastoreButton.setVisible(isLoggedIn && isPastore);
        areaPastoreButton.setDisable(!(isLoggedIn && isPastore));
    }

    /************************************************
     * handlers
     ************************************************/
    /*@FXML
    private void handleLoginMenuItemAction(ActionEvent event) {
        //logica per gestire il pulsante 
        System.out.println("Login Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/login.fxml"));
            Parent loginRoot = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(loginRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    private void handleAreaPiantagioneButtonAction(ActionEvent event) {
        //logica per gestire il pulsante 
        System.out.println("Piantagione Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/piantagionePage.fxml"));
            Parent adminRoot = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(adminRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAreaAdminButtonAction(ActionEvent event) {
        //logica per gestire il pulsante
        System.out.println("Admin Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/adminPage.fxml"));
            Parent adminRoot = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(adminRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAreaPastoreButtonAction(ActionEvent event) {
        //logica per gestire il pulsante 
        System.out.println("Pastore Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/pastorePage.fxml"));
            Parent adminRoot = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(adminRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /************************************************
     * public methods
     ************************************************/
    // Metodo per aggiornare lo stato di login e ruolo
    public void setUserStatus(boolean loggedIn, boolean admin, boolean piantagione, boolean pastore) {
        this.isLoggedIn = loggedIn;
        this.isAdmin = admin;
        this.isPiantagione = piantagione;
        this.isPastore = pastore;
        updateButtonVisibility();
    }


    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
