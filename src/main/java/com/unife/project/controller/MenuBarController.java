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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuBarController {

    /************************************************
     * buttons and items fxml
     ************************************************/

    @FXML
    private HBox root;
    
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
    public void initialize(){
        /*if(!isLoggedIn){
            root.getChildren().remove(logoutButton);
            root.getChildren().remove(areaPersonaleButton);
        }
        else{
            root.getChildren().remove(loginButton);
        }*/
        updateButtons();
    }

    @FXML
    private void handleGoLogin(ActionEvent event) {
        navigateTo(event, "/com/unife/project/view/login.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        goHome(event);
    }


    @FXML
    private void handleAreaPersonaleButton(ActionEvent event) {
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
    //metodo per impostare lo stato dell'utente, richiamato dal controller della schermata di login
    public void setUserStatus(Utente utente){
        if (utente != null) {
            this.isLoggedIn = true;
            username = utente.getUserName();
        }
        else{
            this.isLoggedIn = false;
            username = "";
        }
        //updateButtonsVisibility();
        initialize();
    }


    /*
    private void updateButtonsVisibility(){
        logoutButton.setVisible(isLoggedIn);
        areaPersonaleButton.setVisible(isLoggedIn);
        if (isLoggedIn){
            areaPersonaleButton.setText(username);
            loginButton.setVisible(false);
        }
    }*/

    private void updateButtons(){
        root.getChildren().removeAll(loginButton, logoutButton, areaPersonaleButton);
        if(isLoggedIn){
            root.getChildren().addAll(logoutButton, areaPersonaleButton);
            areaPersonaleButton.setText(username);
        }
        else{
            root.getChildren().add(loginButton);
        }
    }

    private void goHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent root = loader.load();
            Stage stage;
            if (event != null && event.getSource() != null) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else {
                // Se l'evento è null, prendi lo stage corrente
                stage = (Stage) root.getScene().getWindow();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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