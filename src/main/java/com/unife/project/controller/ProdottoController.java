package com.unife.project.controller;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProdottoController {
    
    private Utente utente;
    private Stalla stalla;
    private Animale animale;


    @FXML
    private Label tot;
    @FXML
    private BarChart<String, Number> graficoProdotti;

    @FXML
    public void initialize() {
        //loadProdottiData();
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

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindowSize(stage);

            // Imposta la nuova scena
            Scene scene = new Scene(animaleRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento della schermata Animali.");
        }
    }

    //logica di visualizzazione grafico---------------------------------------------------

    public void loadProdottiData() {
        //List<Prodotto> prodotti = DAOFactory.getProdottoDAO().findAll();

        //gestione prodotti per l'ultimo anno
        List<Prodotto> prodottiAnno = DAOFactory.getProdottoDAO().findProdottiUltimoAnno();

        // Raggruppa i prodotti per tipo_prodotto e calcola la quantità totale
        Map<String, Integer> prodottiPerTipo = prodottiAnno.stream()
                .collect(Collectors.groupingBy(Prodotto::getTipoProdotto, Collectors.summingInt(Prodotto::getQuantita)));

        //recupera listino prezzi
        List<Listino> listino = DAOFactory.getListinoDAO().findAllPrezzoAggiornato();
        // Crea una mappa dei prezzi per tipo_prodotto
        Map<String, Float> prezziPerTipo = listino.stream()
                .collect(Collectors.toMap(Listino::getTipo_prodotto, Listino::getPrezzo));


        if (prodottiAnno != null && !prodottiAnno.isEmpty()) {
            // Calcola il guadagno totale
            float guadagnoTotale = (float) prodottiPerTipo.entrySet().stream()
                    .mapToDouble(entry -> entry.getValue() * prezziPerTipo.getOrDefault(entry.getKey(), 0.0f))
                    .sum();

            populateBarChartPerAnno(prodottiPerTipo);
            System.out.println("Numero di elementi prodotti: " + prodottiAnno.size());
            // Aggiorna la Label con il guadagno totale
            tot.setText(String.format("Guadagno Totale: %.2f €", guadagnoTotale));
        } else {
            System.out.println("Nessun elemento prodotto");
        }
    }

    private void populateBarChartPerAnno(Map<String, Integer> prodottiPerTipo) {
        // Crea le serie di dati per il BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : prodottiPerTipo.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Aggiungi le serie di dati al BarChart
        graficoProdotti.getData().add(series);
    }


    public void setUser(Utente utente) {
        this.utente = utente;
    }

    public void setStalla(Stalla stalla) {
        this.stalla = stalla;
    }

    public void loadAnimale(Animale animaleSelezionato) {
        this.animale = animaleSelezionato;
    }


    public void populateBarChart(List<Prodotto> prodotti){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Prodotto prodotto : prodotti) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(prodotto.getTipoProdotto(), prodotto.getQuantita());
            series.getData().add(data);
            System.out.println("\n Prodotto: " + prodotto.getTipoProdotto() + " Quantità: " + prodotto.getQuantita());

            /*
            // Aggiungi un listener per attendere la creazione del nodo
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    // Aggiungi il prezzo al chilo sopra la barra
                    Label label = new Label(String.format("%d kg", prodotto.getQuantita()));
                    StackPane stackPane = (StackPane) newValue;
                    stackPane.getChildren().add(label);
                }
            });  */
            
            graficoProdotti.getData().add(series);
        }
    }
}
