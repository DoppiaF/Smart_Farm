package com.unife.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VerticalMenuBarController {

    @FXML
    private void handleGoHome(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/home.fxml");
    }

    @FXML
    private void handleGoAdmin(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/adminPage.fxml");
    }

    @FXML
    private void handleGoPiantagione(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/piantagionePage.fxml");
    }

    @FXML
    private void handleGoPastore(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/pastorePage.fxml");
    }

    private void navigateTo(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}