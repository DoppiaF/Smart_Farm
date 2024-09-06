package com.unife.project.controller;

import java.io.IOException;

import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PastoreController {

    private Utente utente;
    
    @FXML
    private BorderPane pastoreRoot;
    
    @FXML
    private BorderPane pastoreNested;

    @FXML
    private TableView<Stalla> stalleTable;

    @FXML
    private TableColumn<Stalla, String> nomeColumn;

    @FXML
    private TableColumn<Stalla, String> tipoColumn;

    @FXML
    private TableColumn<Stalla, String> dataColumn;

    @FXML
    private BarChart<String, Number> costiChart;

    @FXML
    private BarChart<String, Number> guadagniChart;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        //nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        //tipoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        //dataColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());

        // Carica i dati delle stalle dal database
        loadStalleData();
    }

    @FXML
    private void handleAddAnimale() {
        // Logica per aggiungere un nuovo animale
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
        // Carica i dati delle stalle dal database e impostali nella tabella
        // stalleTable.setItems(...);
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