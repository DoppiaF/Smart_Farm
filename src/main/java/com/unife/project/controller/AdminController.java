package com.unife.project.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Utente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class AdminController {

    private Utente utente = null;

    private ObservableList<Utente> userData = FXCollections.observableArrayList();

    @FXML
    private BorderPane adminRoot;
    
    @FXML
    private BorderPane adminNested;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<Utente> userTable;

    @FXML
    private TableColumn<Utente, String> usernameColumn;

    @FXML
    private TableColumn<Utente, String> emailColumn;

    @FXML
    private TableColumn<Utente, Timestamp> creationColumn;

    @FXML
    private TableColumn<Utente, String> dataNascitaColumn;

    @FXML
    private TableColumn<Utente, String> ruoloIrrigazioneColumn;

    @FXML
    private TableColumn<Utente, String> ruoloRaccoltaColumn;

    @FXML
    private TableColumn<Utente, String> ruoloPastoreColumn;

    @FXML
    private TableColumn<Utente, String> ruoloAdminColumn;

    @FXML
    private TableColumn<Utente, Void> deleteColumn;

    

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dataNascitaField;

    @FXML
    private TextField ruoloIrrigazioneField;

    @FXML
    private TextField ruoloRaccoltaField;

    @FXML
    private TextField ruoloPastoreField;

    @FXML
    private TextField ruoloAdminField;

    @FXML
    private TextField dataCreazioneField;

    //form aggiunta utenti

    @FXML
    private TextField usernameFieldAdd;

    @FXML
    private TextField emailFieldAdd;

    @FXML
    private TextField passwordFieldAdd;

    @FXML
    private CheckBox irrigazioneCheckBox;

    @FXML
    private CheckBox raccoltaCheckBox;

    @FXML
    private CheckBox pastoreCheckBox;

    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {


        // Inizializza le colonne della tabella
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dataNascitaColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascita"));
        ruoloIrrigazioneColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo_irrigazione"));
        ruoloRaccoltaColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo_raccolta"));
        ruoloPastoreColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo_pastore"));
        ruoloAdminColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo_admin"));
        creationColumn.setCellValueFactory(new PropertyValueFactory<>("createTime"));

        // Configura la colonna deleteColumn
        addDeleteButtonToTable();

        // Carica i dati degli utenti dal database
        loadUserData();

        userTable.setItems(userData);
    }

    @FXML
    private void handleSubmitButtonAction() {
        if(usernameFieldAdd.getText().isEmpty() || 
        emailFieldAdd.getText().isEmpty() || 
        passwordFieldAdd.getText().isEmpty() ||
        (!irrigazioneCheckBox.isSelected() && !raccoltaCheckBox.isSelected() && !pastoreCheckBox.isSelected()))
        {
            showErrorDialog("Errore", "Compilare tutti i campi.");
        }else{
        //ottiene valori dai campi del form
            System.out.println("submit button pressed, esempio testo" + usernameFieldAdd.getText());
            String username = usernameFieldAdd.getText();
            String email = emailFieldAdd.getText();
            String password = passwordFieldAdd.getText();
            boolean irrigazione = irrigazioneCheckBox.isSelected();
            boolean raccolta = raccoltaCheckBox.isSelected();
            boolean pastore = pastoreCheckBox.isSelected();

            Utente utente = new Utente();
            utente.setUserName(username);
            utente.setEmail(email);
            utente.setPassword(password);
            utente.setRuolo_irrigazione(irrigazione);
            utente.setRuolo_raccolta(raccolta);
            utente.setRuolo_pastore(pastore);
            utente.setRuolo_admin(false);
            utente.setDataNascita(null);
            
            // Salva l'utente nel database
            DAOFactory.getUtenteDAO().save(utente); 
            
            
            // Ricarica i dati degli utenti
            initialize();
            }
        }

        private void loadUserData() {
            // Svuota la lista userData per evitare duplicati
            // Carica i dati degli utenti dal database e impostali nella tabella
            List<Utente> utenti = DAOFactory.getUtenteDAO().findAll();
            userData.clear();
            userData.addAll(utenti);
    }


    //metodo da chiamare da altri controller per passare l'utente ad admin
    public void setUser(Utente utente){
        this.utente = utente;
        //updateWelcomeLabel();
        updateMenuBar();
        updateVerticalMenuBar();
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
            adminNested.setTop(menuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }

    private void updateVerticalMenuBar(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/verticalMenuBar.fxml"));
            Parent verticalMenuBarRoot = loader.load();

            // Ottieni il controller della barra di menu
            VerticalMenuBarController verticalMenuBarController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            verticalMenuBarController.setUserStatus(utente);

            
            // Aggiungi la barra di menu alla root
            adminRoot.setLeft(verticalMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<Utente, Void>, TableCell<Utente, Void>> cellFactory = new Callback<TableColumn<Utente, Void>, TableCell<Utente, Void>>() {
            @Override
            public TableCell<Utente, Void> call(final TableColumn<Utente, Void> param) {
                final TableCell<Utente, Void> cell = new TableCell<Utente, Void>() {

                    private final Button btn = new Button("Elimina");

                    {
                        btn.setOnAction((event) -> {
                            Utente utente = getTableView().getItems().get(getIndex());
                            deleteUtente(utente);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Utente utenteSelezionato = getTableView().getItems().get(getIndex());
                            if (utenteSelezionato.getRuolo_admin()) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                                btn.setVisible(getTableRow().isSelected());
                                getTableRow().selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                                    btn.setVisible(isNowSelected);
                                });
                            }
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(cellFactory);
    }

    private void deleteUtente(Utente utente) {
        // Rimuovi l'utente dal database
        if (utente != null) {
            DAOFactory.getUtenteDAO().delete(utente);
            System.out.println(utente.toString());
            // Rimuovi l'utente dalla lista userData
            userData.remove(utente);
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