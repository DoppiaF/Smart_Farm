package com.unife.project.controller;

import java.io.IOException;

import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeController {
    
    /************************************************
     * buttons and items fxml
     ************************************************/

    /*@FXML
    private MenuItem loginMenuItem;*/

    @FXML
    private Label welcomeLabel;

    @FXML
    private BorderPane rootPane;

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

    private Utente utente;

    @FXML
    public void initialize() {
        // Imposta la visibilità e l'abilitazione dei bottoni in base allo stato di login e ruolo
        updateButtonVisibility();
    }

    private void updateButtonVisibility() {

        if (areaAdminButton != null 
            && areaPiantagioneButton != null 
            && areaPastoreButton != null) {

            areaAdminButton.setDisable(!(isLoggedIn && isAdmin));

            areaPiantagioneButton.setDisable(!(isLoggedIn && isPiantagione));

            areaPastoreButton.setDisable(!(isLoggedIn && isPastore));
        }
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

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindowSize(stage);

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

            // Ottieni il controller della schermata admin
            AdminController adminController = loader.getController();

            // Passa l'oggetto Utente al controller della schermata admin
            adminController.setUser(utente);

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindowSize(stage);

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

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindowSize(stage);
            
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
    public void setUserStatus() {
        
        if(utente != null){
            isLoggedIn = true;
            isAdmin = utente.getRuolo_admin();
            isPiantagione = (utente.getRuolo_raccolta() || utente.getRuolo_irrigazione());
            isPastore = utente.getRuolo_pastore();
        }
        else{
            isLoggedIn = false;
            isAdmin = false;
            isPiantagione = false;
            isPastore = false;
        }
        updateButtonVisibility();
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
        updateWelcomeLabel();
        updateMenuBar();
    }










    /************************************************
     * private methods
     ************************************************/

    //metodo per aggiornare la label di benvenuto
    private void updateWelcomeLabel(){
        if (utente != null){
            welcomeLabel.setText("Benvenuto "+ utente.getUserName());
        }
    }

    public void updateContent() {
        // Logica per aggiornare il contenuto della home
        rootPane.getChildren().clear();
        initialize();
    }

    private void updateMenuBar(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/menuBar.fxml"));
            Parent menuBarRoot = loader.load();

            // Ottieni il controller della barra di menu
            MenuBarController menuBarController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            menuBarController.setUserStatus(utente);

            // Aggiungi la barra di menu alla root
            rootPane.setTop(menuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }

    private void updateVerticalMenuBar(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/verticalMenuBar.fxml"));
            Parent vMenuBarRoot = loader.load();

            // Ottieni il controller della barra di menu
            VerticalMenuBarController verticalMenuBarController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            verticalMenuBarController.setUserStatus(utente);

            // Aggiungi la barra di menu alla root
            rootPane.setLeft(vMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
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
