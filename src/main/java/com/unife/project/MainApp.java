package com.unife.project;

import java.sql.Connection;
import java.sql.SQLException;

import com.unife.project.controller.LoginController;
import com.unife.project.model.dao.UtenteDAOImpl;
import com.unife.project.util.DatabaseConnection;
import com.unife.project.view.LoginView;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
       try{ // Configura la connessione al database
            Connection connection = DatabaseConnection.getConnection();
            UtenteDAOImpl utenteDAO = new UtenteDAOImpl(connection);

            // Crea la vista
            LoginView loginView = new LoginView();

            // Crea il controller
            LoginController loginController = new LoginController(utenteDAO, loginView);

            // Mostra la vista
            loginView.start(primaryStage);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Errore nella connessione al database");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}