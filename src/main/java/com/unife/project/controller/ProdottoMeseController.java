package com.unife.project.controller;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Listino;
import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

public class ProdottoMeseController {

    private Utente utente;
    private Stalla stalla;
    private Animale animale;

    @FXML
    private BorderPane rootPane;
    @FXML
    private BorderPane nestedPane;
    @FXML
    private Label labelMese;
    @FXML
    private Button toggleButton;
    @FXML
    private Label tot;
    @FXML
    private BarChart<String, Number> graficoProdotti;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        loadProdottiDataUltimoMese();
    }

    public void loadProdottiDataUltimoMese() {
        // Recupera i dati dei prodotti dell'ultimo mese
        List<Prodotto> prodottiMese = DAOFactory.getProdottoDAO().findProdottiUltimoMese();

        // Raggruppa i prodotti per tipo_prodotto e calcola la quantità totale
        Map<String, Integer> prodottiPerTipo = prodottiMese.stream()
                .collect(Collectors.groupingBy(Prodotto::getTipoProdotto, Collectors.summingInt(Prodotto::getQuantita)));

        // Recupera il listino prezzi aggiornato
        List<Listino> listino = DAOFactory.getListinoDAO().findAllPrezzoAggiornato();
        // Crea una mappa dei prezzi per tipo_prodotto
        Map<String, Float> prezziPerTipo = listino.stream()
                .collect(Collectors.toMap(Listino::getTipo_prodotto, Listino::getPrezzo));

        if (prodottiMese != null && !prodottiMese.isEmpty()) {
            // Calcola il guadagno totale
            float guadagnoTotale = (float) prodottiPerTipo.entrySet().stream()
                    .mapToDouble(entry -> entry.getValue() * prezziPerTipo.getOrDefault(entry.getKey(), 0.0f))
                    .sum();

            // Aggiorna la Label con il guadagno totale
            tot.setText(String.format("Guadagno Totale: %.2f €", guadagnoTotale));

            // Popola il BarChart
            populateBarChart(prodottiPerTipo);
        } else {
            System.out.println("Nessun elemento prodotto");
        }
    }

    private void populateBarChart(Map<String, Integer> prodottiPerTipo) {
        // Crea le serie di dati per il BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : prodottiPerTipo.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Pulisci i dati esistenti
        graficoProdotti.getData().clear();
        
        // Forza il layout per assicurarsi che il grafico venga aggiornato
        graficoProdotti.layout();

        // Aggiungi le nuove serie di dati al BarChart
        graficoProdotti.getData().add(series);
        
    }

    @FXML
    private void handleVediUltimoAnno(ActionEvent event) {
        try {
            // Carica la vista dell'ultimo anno
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/prodottoPage.fxml"));
            Parent root = loader.load();

            ProdottoController prodottoController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            prodottoController.setUser(utente);
            //prodottoController.loadProdottiDataUltimoAnno();
            prodottoController.setStalla(stalla);

            Stage stage = (Stage) toggleButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            WindowUtil.setWindow(stage, scene,"Smart Farm - Animali");

           
            //stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento della schermata Animali.");
        }
    }


    public void setUser(Utente utente) {
        this.utente = utente;
        updateMenuBar();
        updateVerticalMenuBar();
    }

    public void setStalla(Stalla stalla) {
        this.stalla = stalla;
    }

    public void loadAnimale(Animale animaleSelezionato) {
        this.animale = animaleSelezionato;
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

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}