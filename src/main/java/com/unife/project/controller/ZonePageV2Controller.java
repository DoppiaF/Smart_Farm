package com.unife.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.mo.Zona;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

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
        displayMatrice();
        creaZone();
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
                    Zona zona = new Zona();
                    zona.setCoordX(col);
                    zona.setCoordY(row);
                    zona.setPortataSensore(0);
                    zona.setStatoTerreno("buono");
                    zona.setSensoreIluminazione(0);
                    zona.setSensoreUmidita(0);
                    zona.setSensoreTemperatura(0);
                    zona.setSensorePH(0);
                    zona.setId_piantagione(piantagione.getId());
                    DAOFactory.getZonaDAO().save(zona);
                }
            }
            //ultima riga con colonne variabili (righe -1 perchè partito da 0)
            if(resto != 0){
                for (int col = 0; col < resto; col++){
                    Zona zona = new Zona();
                    zona.setCoordX(col);
                    zona.setCoordY(righe);
                    zona.setPortataSensore(0);
                    zona.setStatoTerreno("buono");
                    zona.setSensoreIluminazione(0);
                    zona.setSensoreUmidita(0);
                    zona.setSensoreTemperatura(0);
                    zona.setSensorePH(0);
                    zona.setId_piantagione(piantagione.getId());
                    DAOFactory.getZonaDAO().save(zona);
                }
            }
            
        }        
    }

    public void displayMatrice(){
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
                // Crea un VBox per contenere le etichette dei sensori
                VBox sensorBox = new VBox();
                sensorBox.setSpacing(5); // Spaziatura tra le etichette
                sensorBox.getStyleClass().add("grid-cell"); // Applica lo stile CSS alla cella

                Zona zona = DAOFactory.getZonaDAO().findByCoordAndPiantagione(col, row,piantagione.getId());
                // Aggiungi etichette per ogni valore del sensore
                Label humidityLabel = new Label("Umidità: " + zona.getSensoreUmidita() + "%");
                Label temperatureLabel = new Label("Temperatura: " + zona.getSensoreTemperatura() + "°C");
                Label illuminationLabel = new Label("Illuminazione: " + zona.getSensoreIluminazione() + " Lux");
                Label windLabel = new Label("Vento: " + zona.getSensoreVento() + " km/h");
                Label phLabel = new Label("pH: " + zona.getSensorePH());

                //progress bar umidità
                ProgressBar humidityBar = new ProgressBar(zona.getSensoreUmidita() / 100.0); // Supponendo che il valore massimo sia 100%
                humidityBar.setStyle("-fx-accent: blue;");

                //progress bar temperatura
                ProgressBar temperatureBar = new ProgressBar(zona.getSensoreTemperatura() / 40.0); // Supponendo che il valore massimo sia 40°C
                temperatureBar.setStyle("-fx-accent: red;");

                // Crea una ProgressBar per l'illuminazione
                ProgressBar illuminationBar = new ProgressBar(zona.getSensoreIluminazione() / 1000.0); // Supponendo che il valore massimo sia 1000 Lux
                illuminationBar.setStyle("-fx-accent: yellow;");

                // Crea una ProgressBar per il vento
                ProgressBar windBar = new ProgressBar(zona.getSensoreVento() / 100.0); // Supponendo che il valore massimo sia 100 km/h
                windBar.setStyle("-fx-accent: grey;");

                //progress bar ph
                ProgressBar phBar = new ProgressBar(zona.getSensorePH() / 14.0); // Supponendo che il valore massimo sia 14
                phBar.setStyle("-fx-accent: green;");

                // Aggiungi le etichette al VBox
                sensorBox.getChildren().addAll(humidityLabel, humidityBar, temperatureLabel, temperatureBar, illuminationLabel, illuminationBar, windLabel, windBar, phLabel, phBar);

                // Aggiungi il VBox al GridPane
                gridPane.add(sensorBox, col, row);


                System.out.println("Riga: " + row + " Colonna: " + col);
            }
        }

        //ultima riga con colonne variabili (righe -1 perchè partito da 0)
        if(resto != 0){
            for (int col = 0; col < resto; col++){
                // Crea un VBox per contenere le etichette dei sensori
                VBox sensorBox = new VBox();
                sensorBox.setSpacing(5); // Spaziatura tra le etichette
                sensorBox.getStyleClass().add("grid-cell"); // Applica lo stile CSS alla cella

                Zona zona = DAOFactory.getZonaDAO().findByCoordAndPiantagione(col, righe,piantagione.getId());
                // Aggiungi etichette per ogni valore del sensore
                Label humidityLabel = new Label("Umidità: " + zona.getSensoreUmidita() + "%");
                Label temperatureLabel = new Label("Temperatura: " + zona.getSensoreTemperatura() + "°C");
                Label illuminationLabel = new Label("Illuminazione: " + zona.getSensoreIluminazione() + " Lux");
                Label windLabel = new Label("Vento: " + zona.getSensoreVento() + " km/h");
                Label phLabel = new Label("pH: " + zona.getSensorePH());

                //progress bar umidità
                ProgressBar humidityBar = new ProgressBar(zona.getSensoreUmidita() / 100.0); // Supponendo che il valore massimo sia 100%
                humidityBar.setStyle("-fx-accent: blue;");

                //progress bar temperatura
                ProgressBar temperatureBar = new ProgressBar(zona.getSensoreTemperatura() / 40.0); // Supponendo che il valore massimo sia 40°C
                temperatureBar.setStyle("-fx-accent: red;");

                // Crea una ProgressBar per l'illuminazione
                ProgressBar illuminationBar = new ProgressBar(zona.getSensoreIluminazione() / 1000.0); // Supponendo che il valore massimo sia 1000 Lux
                illuminationBar.setStyle("-fx-accent: yellow;");

                // Crea una ProgressBar per il vento
                ProgressBar windBar = new ProgressBar(zona.getSensoreVento() / 100.0); // Supponendo che il valore massimo sia 100 km/h
                windBar.setStyle("-fx-accent: grey;");

                //progress bar ph
                ProgressBar phBar = new ProgressBar(zona.getSensorePH() / 14.0); // Supponendo che il valore massimo sia 14
                phBar.setStyle("-fx-accent: green;");

                // Aggiungi le etichette al VBox
                sensorBox.getChildren().addAll(humidityLabel, humidityBar, temperatureLabel, temperatureBar, illuminationLabel, illuminationBar, windLabel, windBar, phLabel, phBar);

                // Aggiungi il VBox al GridPane
                gridPane.add(sensorBox, col, righe);
            }
        }
    }
}