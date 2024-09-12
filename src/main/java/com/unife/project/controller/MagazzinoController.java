package com.unife.project.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Magazzino;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        @FXML
        private Button inserisciMagazzinoButton;    

        private Utente utente = null;
        private Magazzino magazzino = null;
        private Stalla stalla = null;

        private String selectedTipoMangime;
        private XYChart.Data<String, Number> selectedData;
        

        public void initialize() {
            //loadMagazzinoData();
            //setupBarChartSelection();
        }


        //logica di visualizzazione grafico---------------------------------------------------
        public void loadMagazzinoData() {
            List<Magazzino> mangimi = DAOFactory.getMagazzinoDAO().findAllUltimoAnno();


            if(mangimi != null && !mangimi.isEmpty()){
                Map<String, Integer> mangimiPerTipo = mangimi.stream()
                        .collect(Collectors.groupingBy(Magazzino::getTipoMangime, Collectors.summingInt(Magazzino::getQuantita)));
                
                populateBarChart(mangimiPerTipo);
                System.out.println("Numero di elementi nel magazzino: " + mangimi.size());
                //System.out.println("primo elemento lista" + items.get(0).toString());
            } else {
                System.out.println("Nessun elemento nel magazzino");
            }
            
            //populateBarChart(mangimi);
        }

        private void populateBarChart(Map<String, Integer> mangimiPerTipo) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : mangimiPerTipo.entrySet()) {
                XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey(), entry.getValue());
                series.getData().add(data);
    
                // Aggiungi un listener per attendere la creazione del nodo
                data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Aggiungi un listener per rendere selezionabile la barra
                        newValue.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                            // Rimuovi l'evidenziazione dalla barra precedentemente selezionata
                            if (selectedData != null) {
                                selectedData.getNode().getStyleClass().remove("selected-bar");
                            }

                            // Evidenzia la barra selezionata
                            selectedData = data;
                            selectedData.getNode().getStyleClass().add("selected-bar");

                            // Aggiorna il tipo di mangime selezionato e l'etichetta del pulsante
                            selectedTipoMangime = entry.getKey();
                            inserisciMagazzinoButton.setDisable(false);
                            inserisciMagazzinoButton.setText("Aggiungi 1000Kg di: " + selectedTipoMangime);
                            System.out.println("Selected Tipo Mangime: " + selectedTipoMangime);
                        });
                    }
                });
            }
            // Pulisci i dati esistenti
            barChart.getData().clear();            
            // Forza il layout per assicurarsi che il grafico venga aggiornato
            barChart.layout();
            // Aggiungi le nuove serie di dati al BarChart
            barChart.getData().add(series);
            // Forza un altro layout per assicurarsi che il grafico venga ridisegnato correttamente
            barChart.layout();
        }

        public void setStalla(Stalla stalla) {
            this.stalla = stalla;
        }
        
        public void handleGoBackStalla(ActionEvent event){
            try {
                String etichetta = stalla.getEtichettaStalla();
                System.out.println("Stalla passata ad animali: " + etichetta );
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/animalePage.fxml"));
                Parent animaleRoot = loader.load(); 

                // Ottieni il controller e passa i dati
                AnimaliController controller = loader.getController();
                
                controller.setStalla(stalla);
                controller.setUtente(utente);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


                // Imposta la nuova scena
                Scene scene = new Scene(animaleRoot);
                // Imposta le dimensioni della finestra utilizzando il metodo statico
                WindowUtil.setWindow(stage, scene, "Smartfarm - Animale");
                stage.show();
            } catch (IOException e) {
                System.out.println("Errore nel caricamento della schermata Animali.");
            }
        }


        @FXML
        private void handleInserisciMagazzino() {
            if (selectedTipoMangime != null) {
                // Logica per inserire il tipo di prodotto selezionato nel magazzino
                System.out.println("Inserisci nel magazzino: " + selectedTipoMangime);
                Magazzino magazzino = new Magazzino();
                magazzino.setQuantita(1000);
                magazzino.setTipoMangime(selectedTipoMangime);
                magazzino.setData(LocalDate.now());
                DAOFactory.getMagazzinoDAO().save(magazzino);

                loadMagazzinoData();
            }
        }

        //logica di gestione utente e schermata default------------------------------------
        public void setUser(Utente utente) {
            this.utente = utente;
            updateMenuBar();
            //AZ decommentato da me per tenere la menu bar verticale posizionata nel modo corretto
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
