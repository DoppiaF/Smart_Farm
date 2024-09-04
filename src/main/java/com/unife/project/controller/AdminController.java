package com.unife.project.controller;

import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminController {

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

    private void addUserToDatabase(String username, String email) {
        // Aggiungi l'utente al database
    }
}