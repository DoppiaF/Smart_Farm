package com.unife.project.controller;

import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ProdottoController {
    
    private Utente utente;
    private Stalla stalla;
    private Animale animale;


    @FXML
    private BarChart<String, Number> graficoProdotti;

    @FXML
    public void initialize() {
        loadProdottiData();
    }

    public void loadProdottiData() {
        List<Prodotto> prodotti = DAOFactory.getProdottoDAO().findAll();

        //gestione prodotti per l'ultimo anno
        List<Prodotto> prodottiAnno = null;

        if(prodotti != null && !prodotti.isEmpty()){
            populateBarChart(prodotti);
            System.out.println("Numero di elementi prodotti: " + prodotti.size());
        } else {
            System.out.println("Nessun elemento prodotto");
        }
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
