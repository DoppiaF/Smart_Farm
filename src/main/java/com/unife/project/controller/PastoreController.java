package com.unife.project.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class PastoreController {

    private Utente utente;
    private Stalla stalla;
    private ObservableList<Stalla> stalleData = FXCollections.observableArrayList();
    
    @FXML
    private BorderPane pastoreRoot;
    
    @FXML
    private BorderPane pastoreNested;

    //stabella stalle colonne
    @FXML
    private TableView<Stalla> stalleTable;

    @FXML
    private TableColumn<Stalla, String> nomeColumn;

    @FXML
    private TableColumn<Stalla, Integer> capienzaColumn;

    @FXML
    private TableColumn<Stalla, String> razzaColumn;

    @FXML
    private TableColumn<Stalla, String> pranzoColumn;

    @FXML
    private TableColumn<Stalla, String> cenaColumn;

    @FXML
    private BarChart<String, Number> costiChart;

    @FXML
    private BarChart<String, Number> guadagniChart;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("etichettaStalla"));
        capienzaColumn.setCellValueFactory(new PropertyValueFactory<>("capienza"));
        razzaColumn.setCellValueFactory(new PropertyValueFactory<>("razza"));
        pranzoColumn.setCellValueFactory(new PropertyValueFactory<>("oraPranzo"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("oraCena"));

        // Carica i dati delle stalle dal database
        loadStalleData();
        stalleTable.setItems(stalleData);
    }

    @FXML
    private void handleAddStalla() {
        // Crea una nuova stalla con valori di default
        Stalla nuovaStalla = new Stalla();
        nuovaStalla.setEtichettaStalla("Nuova Stalla");
        nuovaStalla.setCapienza(0);
        nuovaStalla.setRazza("Razza");
        LocalTime oraPranzo = LocalTime.of(12, 0);
        LocalTime oraCena = LocalTime.of(18, 0);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        nuovaStalla.setOraPranzo(oraPranzo.format(timeFormatter));
        nuovaStalla.setOraCena(oraCena.format(timeFormatter));

        // Aggiungi la nuova stalla alla lista
        stalleData.add(nuovaStalla);

        // Seleziona la nuova stalla nella tabella
        stalleTable.getSelectionModel().select(nuovaStalla);

        // Mostra un popup con un messaggio per l'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nuova Stalla Aggiunta");
        alert.setHeaderText(null);
        alert.setContentText("Una nuova stalla è stata aggiunta. Si prega di compilare i dettagli e confermare.");
        alert.showAndWait();
    }

    @FXML
    private void handleEditAnimale() {
        // Logica per modificare un animale esistente
    }

    @FXML
    private void handleRemoveAnimale() {
        // Logica per rimuovere un animale
    }

    private void loadStalleData() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        stalleData.clear();
        List<Stalla> stalle = DAOFactory.getStallaDAO().findAll();

        stalleData.addAll(stalle);
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
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
            pastoreNested.setTop(menuBarRoot);
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
            pastoreRoot.setLeft(verticalMenuBarRoot);
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
}