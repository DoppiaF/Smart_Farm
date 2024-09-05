package com.unife.project.controller;

import java.io.IOException;

import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AdminController {

    private Utente utente = null;

    @FXML
    private BorderPane adminRoot;
    
    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<Utente> userTable;

    @FXML
    private TableColumn<Utente, String> usernameColumn;

    @FXML
    private TableColumn<Utente, String> emailColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        //usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        //emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        // Carica i dati degli utenti dal database
        loadUserData();
    }

    @FXML
    private void handleAddUser() {
        String username = usernameField.getText();
        String email = emailField.getText();

        // Aggiungi l'utente al database
        addUserToDatabase(username, email);

        // Ricarica i dati degli utenti
        loadUserData();
    }

    private void loadUserData() {
        // Carica i dati degli utenti dal database e impostali nella tabella
        // userTable.setItems(...);
    }


    //metodo da chiamare da altri controller per passare l'utente ad admin
    public void setUser(Utente utente){
        this.utente = utente;
        //updateWelcomeLabel();
        updateMenuBar();
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
            adminRoot.setTop(menuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }

    private void addUserToDatabase(String username, String email) {
        // Aggiungi l'utente al database
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}