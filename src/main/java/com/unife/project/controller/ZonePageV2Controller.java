package com.unife.project.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class ZonePageV2Controller {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        // Dimensioni della matrice
        int rows = 3; // Numero di righe
        int cols = 3; // Numero di colonne

        // Riempire il GridPane con componenti standard
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Esempio di aggiunta di un'icona
                ImageView icon = new ImageView(new Image("../resources/com/unife/project/images/pratoArido.png"));
                icon.setFitWidth(50);
                icon.setFitHeight(50);
                gridPane.add(icon, col, row);

                // Esempio di aggiunta di un grafico a torta
                PieChart pieChart = new PieChart();
                pieChart.getData().add(new Data("Category 1", 30));
                pieChart.getData().add(new Data("Category 2", 70));
                gridPane.add(pieChart, col, row);
            }
        }
    }
}