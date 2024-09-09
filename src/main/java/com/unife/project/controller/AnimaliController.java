package com.unife.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.unife.project.model.dao.DAOFactory;

public class AnimaliController {

    @FXML
    private BorderPane rootPane;
    @FXML
    private BorderPane nestedPane;

    //tabella animali
    @FXML
    private TableView<Animale> animaliTable;
    @FXML
    private TableColumn<Animale, Integer> idColumn;
    @FXML
    private TableColumn<Animale, Integer> pesoColumn;
    @FXML
    private TableColumn<Animale, String> razzaColumn;
    @FXML
    private TableColumn<Animale, Character> sessoColumn;
    @FXML 
    private TableColumn<Animale, String> mangimeColumn;
    @FXML
    private TableColumn<Animale, LocalDate> nascitaColumn;
    @FXML
    private TableColumn<Animale, LocalDate> ingressoColumn;
    @FXML
    private TableColumn<Animale, LocalDate> vaccinoColumn;
    @FXML
    private TableColumn<Animale, LocalDate> uscitaColumn;
    @FXML
    private TableColumn<Animale, LocalDate> morteColumn;
    /*@FXML
    private TableColumn<Animale, Integer> etaColumn;*/

    private ObservableList<Animale> animaliData = FXCollections.observableArrayList();
    private Stalla stalla = new Stalla();
    private Utente utente = new Utente();

    @FXML
    private void initialize() {


        try{
            // Verifica che la stalla non sia null prima di utilizzarla
            if (stalla != null) {
                System.out.println("Caricamento dati animali. stalla selezionata: " + stalla.toString());
            } else {
                System.out.println("Stalla non inizializzata.");
            }

            // Inizializza le colonne della tabella
            

            
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_animale"));
            pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));
            razzaColumn.setCellValueFactory(new PropertyValueFactory<>("razza"));
            sessoColumn.setCellValueFactory(new PropertyValueFactory<>("sesso"));
            mangimeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoAlimentazione"));
            nascitaColumn.setCellValueFactory(new PropertyValueFactory<>("data_nascita"));
            ingressoColumn.setCellValueFactory(new PropertyValueFactory<>("data_ingresso"));
            vaccinoColumn.setCellValueFactory(new PropertyValueFactory<>("data_vaccino"));
            uscitaColumn.setCellValueFactory(new PropertyValueFactory<>("data_uscita"));
            morteColumn.setCellValueFactory(new PropertyValueFactory<>("data_morte"));
            //etaColumn.setCellValueFactory(new PropertyValueFactory<>("eta"));
            
            // Carica i dati degli animali
            //System.out.println("Caricamento dati animali. stalla selezionata: " + stalla.toString());

            

            // Carica i dati degli animali dal database
            //System.out.println("Caricamento dati animali completato. primo animale" + animaliData.get(0).toString());
            //animaliTable.setItems(animaliData);
        }catch (Exception e) {
            
            showErrorDialog("Errore", "Impossibile caricare i dati degli animali.");
        }
    }

    public void setStalla(Stalla stalla) {
        this.stalla = stalla;
        loadAnimaliData();
        if (!animaliData.isEmpty()) {
            System.out.println("Caricamento dati animali completato. primo animale: " + animaliData.get(0).toString());
        } else {
            System.out.println("Nessun animale trovato.");
        }

        
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
        updateMenuBar();
        updateVerticalMenuBar();
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
            rootPane.setLeft(verticalMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
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
            nestedPane.setTop(menuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }

    private void loadAnimaliData() {
        if (stalla != null) {
            animaliData.setAll(DAOFactory.getAnimaleDAO().findByStalla(stalla.getEtichettaStalla()));
        }
        animaliTable.setItems(animaliData);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}