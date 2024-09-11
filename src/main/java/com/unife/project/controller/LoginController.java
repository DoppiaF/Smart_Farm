package com.unife.project.controller;

import java.io.IOException;

import com.unife.project.model.dao.UtenteDAOImpl;
import com.unife.project.model.mo.Utente;
import com.unife.project.service.UserService;
import com.unife.project.util.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private UtenteDAOImpl utenteDAO;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    // Costruttore pubblico senza argomenti
    public LoginController() {
    }

    public LoginController(UtenteDAOImpl utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    // Metodo setter per l'iniezione manuale
    public void setUtenteDAO(UtenteDAOImpl utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    private UserService userService = new UserService(); // Supponiamo che questa classe gestisca l'autenticazione

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Aggiungi la logica di autenticazione qui

        // Aggiungi la logica di autenticazione qui
        Utente user = userService.authenticate(username, password);
        //System.out.println("info: " + user.toString());
        if (user != null) {
            // Login ha avuto successo
            navigateToHome(event, user);
        } else {
            // Login fallito
            showErrorDialog("Login Fallito", "Username o password errati." +username + " "+ password);
        }
    }

    /*
    private void navigateToHome(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent homeRoot = loader.load();

            // Ottieni il controller della schermata home
            HomeController homeController = loader.getController();
            homeController.setUserStatus(true, user.isAdmin(), user.isPiantagione(), user.isPastore());

            // Ottieni lo stage corrente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Imposta la nuova scena
            Scene scene = new Scene(homeRoot);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata home.");
        }
    }
        */

    private void navigateToHome(ActionEvent event, Utente user) {
        try{
            //carica la nuova Root per i nodi fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent homeRoot = loader.load();

            // Ottieni il controller della schermata home
            HomeController homeController = loader.getController();

            //passa l'oggetto utente al controller della schermata home
            homeController.setUser(user);
            homeController.setUserStatus();

            //imposta la nuova scena
            Scene homeScene = new Scene(homeRoot);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            appStage.setScene(homeScene);
            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindow(appStage,homeScene,"Smartfarm - Home");

            appStage.show();

        }catch(IOException e){
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata home.");
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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