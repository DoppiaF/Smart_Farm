package com.unife.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

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
