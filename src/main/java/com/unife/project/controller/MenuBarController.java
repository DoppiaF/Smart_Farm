package com.unife.project.controller;
import java.io.IOException;

import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuBarController {

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
    
    private boolean isLoggedIn = false;
    private String username = "";

    private Utente utente;


    @FXML
    public void initialize(){
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
        navigateToAreaPersonale(event, utente);
    }

    private void navigateTo(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "SmartFarm - Login");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata.");
        }
    }

    private void navigateToAreaPersonale(ActionEvent event, Utente utente) {
        try{
            //carica la nuova Root per i nodi fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/areaPersonale.fxml"));
            Parent personalRoot = loader.load();

            // Ottieni il controller della schermata area personale
            AreaPersonaleController areaPersonaleController = loader.getController();

            // Passa l'oggetto Utente al controller della schermata area personale
            if (utente != null) {
                areaPersonaleController.setUser(utente);
            } else {
                System.err.println("utente è null");
            }

            //imposta la nuova scena
            Scene areaPersonaleScene = new Scene(personalRoot);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            appStage.setScene(areaPersonaleScene);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(appStage,areaPersonaleScene,"SmartFarm - Area Personale");

            appStage.show();

        }catch(IOException e){
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata di area personale.");
        }
    }



    /************************************************
     * metodi per la gestione della barra di menu
     ************************************************/
    //metodo per impostare lo stato dell'utente, richiamato dal controller della schermata di login
    public void setUserStatus(Utente utente){
        if (utente != null) {
            this.utente = utente;
            this.isLoggedIn = true;
            username = utente.getUserName();
        }
        else{
            this.isLoggedIn = false;
            username = "";
            this.utente = null;
        }
        //updateButtonsVisibility();
        initialize();
    }

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

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "SmartFarm - Home");

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