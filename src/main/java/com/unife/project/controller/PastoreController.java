package com.unife.project.controller;

import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PastoreController {

    private Utente utente;

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
        //updateMenuBar();
    }
}