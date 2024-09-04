package com.unife.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuBarController {

    @FXML
    private void handleGoHome(ActionEvent event) {
        try {
            // Carica il file FXML della schermata home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/homePage.fxml"));
            Parent homeRoot = loader.load();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(homeRoot);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}