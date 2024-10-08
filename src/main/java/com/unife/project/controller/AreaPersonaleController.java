package com.unife.project.controller;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.dao.UtenteDAO;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AreaPersonaleController {

    private Utente utente = null;

    @FXML
    private BorderPane personaleRoot;

    @FXML
    private BorderPane personaleNested;

    @FXML
    private GridPane gridPane;

    
    @FXML
    private GridPane modifyRoot;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField myUsernameField;

    @FXML
    private PasswordField myPasswordField;
    
    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private CheckBox agricoltoreCheckBox;
    @FXML
    private CheckBox irrigatoreCheckBox;
    @FXML
    private CheckBox allevatoreCheckBox;

    @FXML
    private Button loginButton;
    
    @FXML
    private Button modifyButton;
    
    @FXML
    private Button confirmButton;
    
    @FXML
    private Button goBackButton;

    @FXML
    private Pane unmodifiablePainForUsername;
    @FXML
    private Pane unmodifiablePainForEmail;
    @FXML
    private Pane unmodifiablePainForPassword;
    @FXML
    private Pane unmodifiablePainForAgricoltoreCk;
    @FXML
    private Pane unmodifiablePainForIrrigatoreCk;
    @FXML
    private Pane unmodifiablePainForAllevatoreCk;
    @FXML
    private Pane unmodifiablePainForBirthDate;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private void initialize() {
        
        modifyRoot.getChildren().removeAll(confirmButton, goBackButton);
        
        // Inizializza i componenti se necessario
        // Ad esempio, carica i dati nei grafici
        loadChartData();
    }

    @FXML
    private void handleLoginButtonAction() {
        // Logica per gestire il login
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean rememberMe = rememberMeCheckBox.isSelected();

        // Esegui l'autenticazione dell'utente
        authenticateUser(username, password, rememberMe);
    }

    private void loadChartData() {
        // Logica per caricare i dati nei grafici
    }

    private void authenticateUser(String username, String password, boolean rememberMe) {
        // Logica per autenticare l'utente
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Login successful");
            // Aggiorna l'interfaccia utente o naviga a un'altra schermata
        } else {
            System.out.println("Login failed");
        }
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
        updateMenuBar();
        setPersonalFields();
        updateVerticalMenuBar();
    }

    private void setPersonalFields(){
        if (utente != null){
            
            myUsernameField.setText(utente.getUserName());
            myPasswordField.setText(utente.getPassword());
            emailField.setText(utente.getEmail());
            birthDateField.setValue(utente.getDataNascita());
            adminCheckBox.setSelected(utente.getRuolo_admin());
            agricoltoreCheckBox.setSelected(utente.getRuolo_raccolta());        
            allevatoreCheckBox.setSelected(utente.getRuolo_pastore());
            irrigatoreCheckBox.setSelected(utente.getRuolo_irrigazione());

        }
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
            personaleNested.setTop(menuBarRoot);
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
            personaleRoot.setLeft(vMenuBarRoot);
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

    
    @FXML
    private void handleModifyButtonAction() {
        unmodifiablePainForUsername.setDisable(true);
        unmodifiablePainForEmail.setDisable(true);
        unmodifiablePainForPassword.setDisable(true);
        unmodifiablePainForBirthDate.setDisable(true);
        modifyButton.setDisable(true);
        
        modifyRoot.getChildren().remove(modifyButton);
        modifyRoot.getChildren().addAll(confirmButton, goBackButton);

    }

    @FXML
    private void handleConfirmButtonAction() {
        unmodifiablePainForUsername.setDisable(false);
        unmodifiablePainForEmail.setDisable(false);
        unmodifiablePainForPassword.setDisable(false);        
        unmodifiablePainForBirthDate.setDisable(false);
        modifyButton.setDisable(false);

        
        String username = myUsernameField.getText();
        String email = emailField.getText();
        String password = myPasswordField.getText();
        LocalDate dataNascita = birthDateField.getValue();

        Utente nuovoUtente = new Utente();
        nuovoUtente.setUserName(username);
        nuovoUtente.setEmail(email);
        nuovoUtente.setPassword(password);
        nuovoUtente.setRuolo_irrigazione(this.utente.getRuolo_irrigazione());
        nuovoUtente.setRuolo_raccolta(this.utente.getRuolo_raccolta());
        nuovoUtente.setRuolo_pastore(this.utente.getRuolo_pastore());
        nuovoUtente.setRuolo_admin(this.utente.getRuolo_admin());
        nuovoUtente.setDataNascita(dataNascita);
        nuovoUtente.setId(this.utente.getId());

        System.out.println(nuovoUtente.toString());

        updateUserData(nuovoUtente);
        setUser(nuovoUtente);
        
        modifyRoot.getChildren().removeAll(confirmButton, goBackButton);
        modifyRoot.getChildren().add(modifyButton);

        
        initialize();

    }

    @FXML
    private void handleGoBackButtonAction() {
        
        unmodifiablePainForUsername.setDisable(false);
        unmodifiablePainForEmail.setDisable(false);
        unmodifiablePainForPassword.setDisable(false);
        unmodifiablePainForAgricoltoreCk.setDisable(false);
        unmodifiablePainForIrrigatoreCk.setDisable(false);
        unmodifiablePainForAllevatoreCk.setDisable(false);
        modifyButton.setDisable(false);

        
        modifyRoot.getChildren().removeAll(confirmButton, goBackButton);
        modifyRoot.getChildren().add(modifyButton);

        setPersonalFields();


        initialize();

    }

    private void updateUserData(Utente nuovoUtente){
        UtenteDAO utenteDAO = DAOFactory.getUtenteDAO();
        
        utenteDAO.update(nuovoUtente);
    }

}