package com.unife.project.controller;

import java.io.IOException;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
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

public class PiantagioneController {

    private Utente utente;
    //private Piantagione piantagione;
    private ObservableList<Piantagione> piantagioneData = FXCollections.observableArrayList();
    
    @FXML
    private BorderPane piantagioneRoot;
    
    @FXML
    private BorderPane piantagioneNested;

    //tabella piantagioni con colonne
    @FXML
    private TableView<Piantagione> piantagioneTable;

    @FXML
    private TableColumn<Piantagione, String> idColumn;

    @FXML
    private TableColumn<Piantagione, String> tipColumn;

    @FXML
    private TableColumn<Piantagione, Integer> areaColumn;

    @FXML
    private TableColumn<Piantagione, Integer> zoneColumn;

    @FXML
    private TableColumn<Piantagione, String> statoColumn;

    @FXML
    private TableColumn<Piantagione, String> concimazioneColumn;

    @FXML
    private TableColumn<Piantagione, String> raccoltaColumn;

    //grafici
    @FXML
    private BarChart<String, Number> costiGuadagniChart;

    @FXML
    private BarChart<String, Number> produzioneChart;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        /*
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_pianta"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("num_zone"));
        statoColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
        concimazioneColumn.setCellValueFactory(new PropertyValueFactory<>("concimazione"));
        raccoltaColumn.setCellValueFactory(new PropertyValueFactory<>("raccolta"));
*/
        
        // Carica i dati delle piantagioni dal database
        //loadPiantagioneData();
        //piantagioneTable.setItems(piantagioneData);
    }

    @FXML
    private void handleAddPiantagione() {
        // Logica per aggiungere una nuova piantagione
    }

    @FXML
    private void handleEditPiantagione() {
        // Logica per modificare una piantagione esistente
    }

    @FXML
    private void handleRemovePiantagione() {
        // Logica per rimuovere una piantagione
    }

    private void loadPiantagioneData() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        if(piantagioneData != null ) piantagioneData.clear();
        List<Piantagione> piantagioni = DAOFactory.getPiantagioneDAO().findAll();

        piantagioneData.addAll(piantagioni);
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
            piantagioneNested.setTop(menuBarRoot);
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
            piantagioneRoot.setLeft(verticalMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu verticale.");
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