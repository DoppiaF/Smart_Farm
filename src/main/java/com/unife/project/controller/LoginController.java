package com.unife.project.controller;

import com.unife.project.model.dao.UtenteDAOImpl;
import com.unife.project.model.mo.Utente;
import com.unife.project.view.LoginView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    private UtenteDAOImpl utenteDAO;
    private LoginView loginView;

    public LoginController(UtenteDAOImpl utenteDAO, LoginView loginView) {
        this.utenteDAO = utenteDAO;
        this.loginView = loginView;

        loginView.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = loginView.getUserNameField().getText();
        String password = loginView.getPasswordField().getText();

        Utente utente = utenteDAO.findByUsernameAndPassword(username, password);
        if (utente != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome, " + utente.getUserName() + "!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
    }
}