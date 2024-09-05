package com.unife.project.controller;

import com.unife.project.model.mo.Utente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuBarController {

    /************************************************
     * buttons and items fxml
     ************************************************/

    @FXML
    private Button logoutButton;

    @FXML
    private Button loginButton;

    @FXML 
    private Button areaPersonaleButton;

    @FXML
    private ImageView loginIcon;

    @FXML
    private ImageView logoutIcon;
    

    /************************************************
     * initialize
     ************************************************/

    private boolean isLoggedIn = false;
    private String username = "";


    @FXML
    private void handleGoLogin(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/login.fxml");
    }

    @FXML
    private void handleGoLogout(ActionEvent event) {
        setUserStatus(null);
        updateButtonsVisibility();
        //gestire anche la visibilità bottoni della home
    }

    @FXML
    private void handleGoAreaPersonale(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/areaPersonale.fxml");
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
            showErrorDialog("Errore", "Impossibile caricare la schermata.");
        }
    }



    /************************************************
     * metodi per la gestione della barra di menu
     ************************************************/
    public void setUserStatus(Utente utente){
        if (utente != null) 
            this.isLoggedIn = true;
        else 
            this.isLoggedIn = false;
        username = utente.getUserName();
        updateButtonsVisibility();
    }

    private void updateButtonsVisibility(){
        logoutButton.setVisible(isLoggedIn);
        areaPersonaleButton.setVisible(isLoggedIn);
        if (isLoggedIn){
            areaPersonaleButton.setText(username);
        }
    }



    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}