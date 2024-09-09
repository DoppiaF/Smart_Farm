package com.unife.project.controller;

import java.io.IOException;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Cisterna;
import com.unife.project.model.mo.Irrigazione;
import com.unife.project.model.mo.IrrigazioneCisterna;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class IrrigazioneController {
    
    private Utente utente;
    private Piantagione piantagione;
    private Irrigazione irrigazione;
    private IrrigazioneCisterna irrigazioneCisterna;
    private Cisterna cisterna;
    private ObservableList<Irrigazione> irrigazioniData = FXCollections.observableArrayList();


    
    @FXML
    private BorderPane zoneIrrigazioneRoot;
    
    @FXML
    private BorderPane zoneIrrigazioneNested;

    @FXML
    private TableView<Irrigazione> irrigationsTable;
    
    @FXML
    private TableColumn<Irrigazione, List> irrigationsTabColumn;

    @FXML
    private GridPane fieldMap;
    
    @FXML
    private ProgressBar livello_cisterna;
    
    @FXML
    private RadioButton irrigazioneAutomatica;

    @FXML
    private Button avviaIrrigazione;

    @FXML
    private void initialize() {
        loadIrrigazioneData();
        irrigationsTable.setItems(irrigazioniData);
        livello_cisterna.setProgress(cisterna.getQuantita()/cisterna.getCapacita());
        irrigazioneAutomatica.setSelected(irrigazione.isAuto());
        if(!irrigazioneAutomatica.isSelected())avviaIrrigazione.setVisible(true); 
    }
    
    private void loadIrrigazioneData() {
        irrigazione = DAOFactory.getIrrigazioneDAO().findById(piantagione.getId());
        irrigazioneCisterna = DAOFactory.getIrrigazioneCisternaDAO().findById_irrigazione(irrigazione.getId_irrigazione());
        cisterna = DAOFactory.getCisternaDAO().findById(irrigazioneCisterna.getId_cisterna());
        irrigazioniData.addAll(irrigazione);
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setPiantagione(Piantagione piantagione){
        this.piantagione = piantagione;
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
            zoneIrrigazioneRoot.setTop(menuBarRoot);
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
            zoneIrrigazioneRoot.setLeft(verticalMenuBarRoot);
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
