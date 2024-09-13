package com.unife.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class ZonePageV2Controller {

    private Utente utente;
    private Piantagione piantagione;
    int nm[] = new int[2];
    int numZone=0;
    int areaZona=0;
    int righe=0;
    int colonne=0;
    int resto = 0;

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        
    }

    public void setUser(Utente utente){
        this.utente = utente;
    }

    public void setPiantagione(Piantagione piantagione){
        this.piantagione = piantagione;
        calcolaNM();
        creaMatrice();
    }

    public void setRigheColonne(int righe, int colonne){
        this.righe = righe;
        this.colonne = colonne;
    }

    public void calcolaNM(){
        int i = 0;
        numZone = piantagione.getNumZone();
        areaZona = piantagione.getArea()/numZone;

        do{
            i++;
            if(i*i >= numZone){
                colonne = i;
                righe = numZone / i;
                resto = numZone % i;

            }
            
        }while(i < 6 && righe == 0);
    }


    public void creaZone(){
        // Dimensioni della matrice righe x (righe-1) + ultima riga lunga = colonne
        /* es 11 zone. 3x3 + 2. avrò le seguenti coordinate.
         * (0,0) (0,1) (0,2)
         * (1,0) (1,1) (1,2)
         * (2,0) (2,1) (2,2)
         * (3,0) (3,1) 
         */ 

        //controllo esistenza numZone
        if(numZone != DAOFactory.getZonaDAO().findByPiantagione(piantagione.getId()).size()){
            //zone non presenti nel database, creale
            
            for (int row = 0; row < righe; row++) {
                for (int col = 0; col < colonne; col++) {
                    //add zona con queste coordinate (row, col)
                }
            }
            //ultima riga con colonne variabili (righe -1 perchè partito da 0)
            if(resto != 0){
                for (int col = 0; col < resto; col++){
                   //add zona (righe,col)
                }
            }
            
        }        
    }

    public void creaMatrice(){
        if (gridPane == null) {
            System.out.println("GridPane non è inizializzato correttamente.");
            return;
        }
        // Dimensioni della matrice righe x (righe-1) + ultima riga lunga = colonne
        /* es 11 zone. 3x3 + 2. avrò le seguenti coordinate.
         * (0,0) (0,1) (0,2)
         * (1,0) (1,1) (1,2)
         * (2,0) (2,1) (2,2)
         * (3,0) (3,1) 
         */ 
        // Riempire il GridPane con componenti standard
        for (int row = 0; row < righe; row++) {
            for (int col = 0; col < colonne; col++) {
                // Esempio di aggiunta di un'icona
                /*ImageView icon = new ImageView(new Image(getClass().getResource("/images/pratoArido.png").toExternalForm()));
                icon.setFitWidth(50);
                icon.setFitHeight(50);
                gridPane.add(icon, col, row);
*/          
                // Esempio di aggiunta di un grafico a torta
                PieChart pieChart = new PieChart();
                pieChart.getData().add(new Data("Category 1", col+1));
                pieChart.getData().add(new Data("Category 2", row+1));
                gridPane.add(pieChart, col, row);


                System.out.println("Riga: " + row + " Colonna: " + col);
            }
        }

        //ultima riga con colonne variabili (righe -1 perchè partito da 0)
        if(resto != 0){
            for (int col = 0; col < resto; col++){
                PieChart pieChart = new PieChart();
                pieChart.getData().add(new Data("Category 1", col+1));
                pieChart.getData().add(new Data("Category 2", righe));
                gridPane.add(pieChart, col, righe);
            }
        }
    }
}