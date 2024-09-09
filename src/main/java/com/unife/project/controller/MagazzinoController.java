package com.unife.project.controller;

import java.io.IOException;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Magazzino;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

    public class MagazzinoController {

        @FXML
        private BarChart<String, Number> barChart;
        @FXML
        private CategoryAxis xAxis;
        @FXML
        private NumberAxis yAxis;

        @FXML
        private BorderPane magazzinoNested;
        @FXML
        private BorderPane magazzinoRoot;
        private Utente utente = null;
        private Magazzino magazzino = null;
        

        public void initialize() {
            loadMagazzinoData();
        }


        //logica di visualizzazione grafico---------------------------------------------------
        public void loadMagazzinoData() {
            List<Magazzino> items = DAOFactory.getMagazzinoDAO().findAll();
            System.out.println("Numero di elementi nel magazzino: " + items.size());
            System.out.println("primo elemento lista" + items.get(0).toString());
            populateBarChart(items);
        }

        private void populateBarChart(List<Magazzino> items) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Magazzino item : items) {
                XYChart.Data<String, Number> data = new XYChart.Data<>(item.getTipoMangime(), item.getQuantita());
                series.getData().add(data);
                System.out.println(item.toString());
    
                // Aggiungi un listener per attendere la creazione del nodo
                data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Aggiungi il prezzo al chilo sopra la barra
                        Label label = new Label(String.format("%.2f €/kg", item.getPrezzo_kg()));
                        StackPane stackPane = (StackPane) newValue;
                        stackPane.getChildren().add(label);
                    }
                });
            }
            barChart.getData().add(series);
        }


        //logica di gestione utente e schermata default------------------------------------
        public void setUser(Utente utente) {
            this.utente = utente;
            updateMenuBar();
            //updateVerticalMenuBar();
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
                magazzinoRoot.setLeft(verticalMenuBarRoot);
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
                magazzinoNested.setTop(menuBarRoot);
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
