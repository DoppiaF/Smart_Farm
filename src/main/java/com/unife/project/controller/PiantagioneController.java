package com.unife.project.controller;

import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PiantagioneController {

    private Utente utente;

    @FXML
    private TableView<Piantagione> piantagioneTable;

    @FXML
    private TableColumn<Piantagione, String> nomeColumn;

    @FXML
    private TableColumn<Piantagione, String> tipoColumn;

    @FXML
    private TableColumn<Piantagione, String> dataColumn;

    @FXML
    private BarChart<String, Number> costiGuadagniChart;

    @FXML
    private BarChart<String, Number> produzioneChart;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        //nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        //tipoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        //dataColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());

        // Carica i dati delle piantagioni dal database
        loadPiantagioneData();
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
        // piantagioneTable.setItems(...);
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
        //updateMenuBar();
    }
}