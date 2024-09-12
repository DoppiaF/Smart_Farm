package com.unife.project;

import com.unife.project.util.WindowUtil;

//import com.unife.project.model.dao.UtenteDAOImpl;
//import com.unife.project.util.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

//import java.sql.Connection;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Configura la connessione al database
            //Connection connection = DatabaseConnection.getConnection();
            //UtenteDAOImpl utenteDAO = new UtenteDAOImpl(connection);

            // Carica il file FXML della schermata di home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/home.fxml"));
            Parent root = loader.load();

            /*// Carica il file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/login.fxml"));
            loader.setControllerFactory(c -> new LoginController(utenteDAO));
            Parent root = loader.load();*/

            // Imposta la scena
            Scene scene = new Scene(root);
            WindowUtil.setWindow(primaryStage, scene, "Smartfarm - Home");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}