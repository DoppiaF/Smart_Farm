package com.unife.project.controller;

import com.unife.project.model.dao.UtenteDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private UtenteDAOImpl utenteDAO;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public LoginController(UtenteDAOImpl utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Aggiungi la logica di autenticazione qui
    }
}