package com.unife.project.controller;

import java.io.IOException;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.dao.ZonaDAO;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.mo.Zona;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ZoneRaccoltaController {
    
    private Utente utente;
    private Piantagione piantagione;
    private List<Zona> listaZone;


    
    @FXML
    private BorderPane zoneRaccoltaRoot;
    
    @FXML
    private BorderPane zoneRaccoltaNested;

    @FXML
    private GridPane fieldMap;
    
    @FXML
    private void initialize() {
        System.out.println("Caricamento pagina");
    }
    
    private void loadSensoriData() {
        if(piantagione != null){
            ZonaDAO zonaDAO = DAOFactory.getZonaDAO();
            listaZone = zonaDAO.findByPiantagione(piantagione.getId());
            
            if(listaZone != null){
                int row = 0;
                int col = 0;
                int numberOfColumns = 5; // Adjust as needed

                for (Zona zona : listaZone) {
                    Pane cell = new Pane();
                    cell.setStyle("-fx-border-color:green;");
                    cell.setOnMouseClicked(event -> {
                        //System.out.println("Cella selezionata: (" + row + ", " + col + ")");
                        cell.setStyle("-fx-border-color: orange;");
                    });
                    fieldMap.add(cell, col, row);
                    col++;
                    if (col == numberOfColumns) {
                        col = 0;
                        row++;
                    }
                }
            } else {System.out.println("In questa piantagione non sono state trovate zone");}
        }else{
            System.out.println("Piantagione non trovata, non è possibile caricare i dati della sua irrigazione");
        }

        
    }
    /* 
    private void handleSelectedZone(){
        
    }*/

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setPiantagione(Piantagione piantagione){
        System.out.println("Settaggio piantagione");
        this.piantagione = piantagione;
        
        loadSensoriData();
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
            zoneRaccoltaNested.setTop(menuBarRoot);
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
            zoneRaccoltaRoot.setLeft(verticalMenuBarRoot);
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
