package com.unife.project.controller;

import java.io.IOException;

import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class VerticalMenuBarController {

    private Utente utente = null;
    private boolean isLoggedIn = false;

    @FXML
    private VBox root;

    @FXML
    private Button homeButtonV;

    @FXML
    private Button adminButtonV;

    @FXML
    private Button piantagioneButtonV;

    @FXML
    private Button pastoreButtonV;

    @FXML
    public void initialize(){
        updateButtonsVisibility();
    }
    
    @FXML
    private void handleGoHome(ActionEvent event) {
        System.out.println("home Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent homeRoot = loader.load();

            // Ottieni il controller della schermata admin
            HomeController homeController = loader.getController();

            
            // Passa l'oggetto Utente al controller della schermata admin
            homeController.setUser(utente);
            //homeController.setUserStatus();

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(homeRoot);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "SmartFarm - Home");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoAdmin(ActionEvent event) {
        System.out.println("admin Button Pressed");
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

            // Imposta la nuova scena
            Scene scene = new Scene(adminRoot);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "Smartfarm - Area amministratore");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoPiantagione(ActionEvent event) {
        System.out.println("piantagione Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/piantagionePage.fxml"));
            Parent piantagioneRoot = loader.load();

            // Ottieni il controller della schermata admin
            PiantagioneController piantagioneController = loader.getController();

            
            // Passa l'oggetto Utente al controller della schermata admin
            piantagioneController.setUser(utente);

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(piantagioneRoot);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "Smartfarm - Piantagione");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGoPastore(ActionEvent event) {
        System.out.println("pastore Button Pressed");
        try {
            // Carica il file FXML della schermata admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/pastorePage.fxml"));
            Parent pastoreRoot = loader.load();

            // Ottieni il controller della schermata admin
            PastoreController pastoreController = loader.getController();

            
            // Passa l'oggetto Utente al controller della schermata admin
            pastoreController.setUser(utente);

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(pastoreRoot);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(stage, scene, "Smartfarm - Area pastore");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUserStatus(Utente utente){
        if (utente != null) {
            this.utente = utente;
            this.isLoggedIn = true;
        }
        else{
            this.isLoggedIn = false;
            this.utente = null;
        }
        //updateButtonsVisibility();
        initialize();
    }

    private void updateButtonsVisibility(){
        if(utente != null){
            homeButtonV.setDisable(false);
            piantagioneButtonV.setDisable(!(utente.getRuolo_irrigazione() || utente.getRuolo_raccolta()));
            pastoreButtonV.setDisable(!utente.getRuolo_pastore());
            adminButtonV.setDisable(!utente.getRuolo_admin());
        }
    }
}