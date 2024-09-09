package com.unife.project.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.mo.VisitaVeterinaria;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class VeterinarioController {
    
    @FXML
    private BorderPane vetRoot;
    @FXML
    private BorderPane vetNested;

    //tabella visite
    @FXML
    private TableView<VisitaVeterinaria> visitaTable;
    @FXML
    private TableColumn<VisitaVeterinaria, String> nomeColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> cognomeColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, LocalDate> dataColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> diagnosiColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> curaColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, LocalDate> programmataColumn;


    private Utente utente = null;
    private Animale animale = null;
    private List<VisitaVeterinaria> visiteData = FXCollections.observableArrayList();



    public void initialize() {
        try{
            nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome_veterinario"));
            cognomeColumn.setCellValueFactory(new PropertyValueFactory<>("cognome_veterinario"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
            diagnosiColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosi"));
            curaColumn.setCellValueFactory(new PropertyValueFactory<>("cura_prescritta"));
            programmataColumn.setCellValueFactory(new PropertyValueFactory<>("prossima_visita"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Errore inizializzazione tabella visite");
        }

        
        //loadVeterinarioData();
    }

    public void loadVeterinarioData() {
        if(animale != null){
            visiteData = DAOFactory.getVisitaVeterinariaDAO().findByIdAnimale(animale.getId_animale());
                //DAOFactory.getVisitaVeterinariaDAO().findByIdAnimale(animale.getId_animale()));
        }
        System.out.println("Numero di elementi nella lista visite: " + visiteData.size());

    
    }


    public void setAnimale(Animale animale) {
        this.animale = animale;
        loadVeterinarioData();
    }




    //logica di gestione utente e schermata default------------------------------------
        public void setUser(Utente utente) {
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
                vetRoot.setLeft(verticalMenuBarRoot);
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
                vetNested.setTop(menuBarRoot);
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
