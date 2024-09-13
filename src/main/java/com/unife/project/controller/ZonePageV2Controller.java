package com.unife.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.mo.Zona;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ZonePageV2Controller {

    private Utente utente;
    private Piantagione piantagione;
    private VBox selectedBox;
    int nm[] = new int[2];
    int numZone=0;
    int areaZona=0;
    int righe=0;
    int colonne=0;
    int resto = 0;

    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane rootPane;
    @FXML
    private BorderPane nestedPane;

    @FXML
    public void initialize() {
        gridPane.setMaxWidth(800); // Larghezza massima
        gridPane.setMaxHeight(600); // Altezza massima
    }

    public void setUser(Utente utente){
        this.utente = utente;
        updateMenuBar();
        updateVerticalMenuBar();
    }

    public void setPiantagione(Piantagione piantagione){
        this.piantagione = piantagione;
        calcolaNM();
        creaZone();
        displayMatrice();
        
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
                riempiCella(row, col);
            }
        }

        //ultima riga con colonne variabili (righe -1 perchè partito da 0)
        if(resto != 0){
            for (int col = 0; col < resto; col++){
                riempiCella(righe, col);
            }
        }
    }

    public void riempiCella(int row, int col){
        // Crea un VBox per contenere le etichette dei sensori
        double altezzaPref = 100;
        double larghezzaPref = 20;
        double celle = 100;
        VBox sensorBox = new VBox();
        sensorBox.setSpacing(5); // Spaziatura tra le etichette
        sensorBox.setPrefHeight(150);
        sensorBox.setPrefWidth(180);
        //sensorBox.setMaxWidth(50);
        sensorBox.setAlignment(Pos.CENTER);
        //sensorBox.setMaxHeight(200);
        sensorBox.getStyleClass().add("grid-cell"); // Applica lo stile CSS alla cella

        // Aggiungi un listener di eventi di mouse per rendere selezionabile il VBox
        sensorBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (selectedBox != null) {
                    selectedBox.setStyle(""); // Deseleziona il nodo precedente
                }
                sensorBox.setStyle("-fx-border-color: blue; -fx-border-width: 2px;"); // Seleziona il nodo corrente
                selectedBox = sensorBox;

                // Mostra un pop-up con le informazioni dei sensori
                        Zona zona = DAOFactory.getZonaDAO().findByCoordAndPiantagione(col, row, piantagione.getId());
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Informazioni dei Sensori");
                        alert.setHeaderText(null);
                        alert.setContentText(
                            "Umidità: " + zona.getSensoreUmidita() + "%\n" +
                            "Temperatura: " + zona.getSensoreTemperatura() + "°C\n" +
                            "Illuminazione: " + zona.getSensoreIluminazione() + " Lux\n" +
                            "Vento: " + zona.getSensoreVento() + " km/h\n" +
                            "pH: " + zona.getSensorePH()
                        );
                        alert.showAndWait();
            }
        });

        Zona zona = DAOFactory.getZonaDAO().findByCoordAndPiantagione(col, row,piantagione.getId());
        // Aggiungi etichette per ogni valore del sensore
        /*Label humidityLabel = new Label("Umidità: " + zona.getSensoreUmidita() + "%");
        Label temperatureLabel = new Label("Temperatura: " + zona.getSensoreTemperatura() + "°C");
        Label illuminationLabel = new Label("Illuminazione: " + zona.getSensoreIluminazione() + " Lux");
        Label windLabel = new Label("Vento: " + zona.getSensoreVento() + " km/h");
        Label phLabel = new Label("pH: " + zona.getSensorePH());
                                                                            */
        // Crea le icone SVG per ogni sensore
        SVGPath windIcon = new SVGPath();
        windIcon.setContent("M12.5 2A2.5 2.5 0 0 0 10 4.5a.5.5 0 0 1-1 0A3.5 3.5 0 1 1 12.5 8H.5a.5.5 0 0 1 0-1h12a2.5 2.5 0 0 0 0-5m-7 1a1 1 0 0 0-1 1 .5.5 0 0 1-1 0 2 2 0 1 1 2 2h-5a.5.5 0 0 1 0-1h5a1 1 0 0 0 0-2M0 9.5A.5.5 0 0 1 .5 9h10.042a3 3 0 1 1-3 3 .5.5 0 0 1 1 0 2 2 0 1 0 2-2H.5a.5.5 0 0 1-.5-.5");
        windIcon.setFill(Color.AQUAMARINE);

        SVGPath illuminationIcon = new SVGPath();
        illuminationIcon.setContent("M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8M8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0m0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13m8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5M3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8m10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0m-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0m9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707M4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708");
        illuminationIcon.setFill(Color.YELLOW);

        SVGPath humidityIcon = new SVGPath();
        humidityIcon.setContent("M8 16a6 6 0 0 0 6-6c0-1.655-1.122-2.904-2.432-4.362C10.254 4.176 8.75 2.503 8 0c0 0-6 5.686-6 10a6 6 0 0 0 6 6M6.646 4.646l.708.708c-.29.29-1.128 1.311-1.907 2.87l-.894-.448c.82-1.641 1.717-2.753 2.093-3.13");
        humidityIcon.setFill(Color.BLUE);

        SVGPath temperatureIcon = new SVGPath();
        temperatureIcon.setContent("M8 16c3.314 0 6-2 6-5.5 0-1.5-.5-4-2.5-6 .25 1.5-1.25 2-1.25 2C11 4 9 .5 6 0c.357 2 .5 4-2 6-1.25 1-2 2.729-2 4.5C2 14 4.686 16 8 16m0-1c-1.657 0-3-1-3-2.75 0-.75.25-2 1.25-3C6.125 10 7 10.5 7 10.5c-.375-1.25.5-3.25 2-3.5-.179 1-.25 2 1 3 .625.5 1 1.364 1 2.25C11 14 9.657 15 8 15");
        temperatureIcon.setFill(Color.RED);

        Text phIcon = new Text("pH");
        phIcon.setFill(Color.web("#000d00"));
        phIcon.setStyle("-fx-font-size: 14px;");
        phIcon.setStroke(Color.web("#67ec65"));
        phIcon.setStrokeWidth(2.0);
        phIcon.setStrokeType(StrokeType.OUTSIDE);

        //progress bar umidità
        ProgressBar humidityBar = new ProgressBar(zona.getSensoreUmidita() / 100.0); // Supponendo che il valore massimo sia 100%
        humidityBar.setStyle("-fx-accent: blue;");
        humidityBar.setPrefWidth(altezzaPref);
        humidityBar.setPrefHeight(larghezzaPref);
        

        //progress bar temperatura
        ProgressBar temperatureBar = new ProgressBar(zona.getSensoreTemperatura() / 40.0); // Supponendo che il valore massimo sia 40°C
        temperatureBar.setStyle("-fx-accent: red;");
        temperatureBar.setPrefWidth(altezzaPref);
        temperatureBar.setPrefHeight(larghezzaPref);
        

        // Crea una ProgressBar per l'illuminazione
        ProgressBar illuminationBar = new ProgressBar(zona.getSensoreIluminazione() / 1000.0); // Supponendo che il valore massimo sia 1000 Lux
        illuminationBar.setStyle("-fx-accent: yellow;");
        illuminationBar.setPrefWidth(altezzaPref);
        illuminationBar.setPrefHeight(larghezzaPref);
        

        // Crea una ProgressBar per il vento
        ProgressBar windBar = new ProgressBar(zona.getSensoreVento() / 100.0); // Supponendo che il valore massimo sia 100 km/h
        windBar.setStyle("-fx-accent: grey;");
        windBar.setPrefWidth(altezzaPref);
        windBar.setPrefHeight(larghezzaPref);
     
        //progress bar ph
        ProgressBar phBar = new ProgressBar(zona.getSensorePH() / 14.0); // Supponendo che il valore massimo sia 14
        phBar.setStyle("-fx-accent: green;");
        phBar.setPrefWidth(altezzaPref);
        phBar.setPrefHeight(larghezzaPref);

        HBox hum = new HBox();
        hum.getChildren().addAll(humidityIcon, humidityBar);
        hum.setPrefHeight(celle);
        hum.setPrefWidth(celle);
        hum.setSpacing(10);
        hum.setAlignment(Pos.CENTER);

        HBox temp = new HBox();
        temp.getChildren().addAll(temperatureIcon, temperatureBar);
        temp.setPrefHeight(celle);
        temp.setPrefWidth(celle);
        temp.setSpacing(10);
        temp.setAlignment(Pos.CENTER);

        HBox ill = new HBox();
        ill.getChildren().addAll(illuminationIcon, illuminationBar);
        ill.setPrefHeight(celle);
        ill.setPrefWidth(celle);
        ill.setSpacing(10);
        ill.setAlignment(Pos.CENTER);

        HBox win = new HBox();
        win.getChildren().addAll(windIcon,windBar );
        win.setPrefHeight(celle);
        win.setPrefWidth(celle);
        win.setSpacing(10);
        win.setAlignment(Pos.CENTER);

        HBox ph = new HBox();
        ph.getChildren().addAll(phIcon, phBar);
        ph.setPrefHeight(celle);
        ph.setPrefWidth(celle);
        ph.setSpacing(10);
        ph.setAlignment(Pos.CENTER);
        // Aggiungi le etichette al VBox
        //sensorBox.getChildren().addAll(humidityLabel, humidityBar, temperatureLabel, temperatureBar, illuminationLabel, illuminationBar, windLabel, windBar, phLabel, phBar);
        sensorBox.getChildren().addAll(hum, temp, ill, win, ph);

        // Aggiungi il VBox al GridPane
        gridPane.add(sensorBox, col, row);

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