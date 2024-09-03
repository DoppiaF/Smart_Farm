package com.unife.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
    
    /************************************************
     * buttons
     ************************************************/

    @FXML
    private Button loginButton;

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
    private void handleLoginButtonAction(ActionEvent event) {
        //logica per gestire il pulsante di login
        System.out.println("Login Button Pressed");
    }

    @FXML
    private void handleAreaPiantagioneButtonAction(ActionEvent event) {
        //logica per gestire il pulsante di login
        System.out.println("Piantagione Button Pressed");
    }

    @FXML
    private void handleAreaAdminButtonAction(ActionEvent event) {
        //logica per gestire il pulsante di login
        System.out.println("Admin Button Pressed");
    }

    @FXML
    private void handleAreaPastoreButtonAction(ActionEvent event) {
        //logica per gestire il pulsante di login
        System.out.println("Admin Button Pressed");
    }
}
