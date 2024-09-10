package com.unife.project.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;

public class ProdottoController {
    
    @FXML
    private BarChart<String, Number> graficoProdotti;

    @FXML
    public void initialize() {
        //loadProdottiData();
    }
}
