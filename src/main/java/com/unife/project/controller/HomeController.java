package com.unife.project.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomeController {
    
    /************************************************
     * buttons
     ************************************************/

    @FXML
    private MenuItem loginMenuItem;

    @FXML
    private Button areaPiantagioneButton;

    @FXML 
    private Button areaAdminButton;

    @FXML
    private Button areaPastoreButton;


    /************************************************
     * handlers
     ************************************************/
    @FXML
    private void handleLoginMenuItemAction(ActionEvent event) {
        //logica per gestire il pulsante 
        System.out.println("Login Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/login.fxml"));
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
}
