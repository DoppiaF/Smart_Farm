package com.unife.project.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Listino;
import com.unife.project.model.mo.Magazzino;
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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProdottoController {
    
    private Utente utente;
    private Stalla stalla;
    private Animale animale;
    private String selectedTipoProdotto;
    private XYChart.Data<String, Number> selectedData;

    @FXML
    private BorderPane rootPane;
    @FXML
    private BorderPane nestedPane;
    @FXML
    private Label labelAnnoMese;
    @FXML
    private Button toggleButton;
    @FXML
    private Label tot;
    @FXML
    private BarChart<String, Number> graficoProdotti;
    @FXML
    private NumberAxis xAxis;
    @FXML 
    private NumberAxis yAxis;   
    @FXML
    private Button inserisciProdottoButton;
    //@FXML
    //private Scene scene;

    @FXML
    public void initialize() {
        loadProdottiDataUltimoAnno();
        //setupBarChartSelection();
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
    private void handleVediUltimoMese(ActionEvent event) {
        try {
            // Carica la vista dell'ultimo mese
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/prodottoPageMese.fxml"));
            Parent root = loader.load();

            //unifico la view per le due pagine
            ProdottoMeseController prodottoMeseController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            prodottoMeseController.setUser(utente);
            //prodottoController.loadProdottiDataUltimoAnno();
            prodottoMeseController.setStalla(stalla);
            //aggiunge animale selezionato
            prodottoMeseController.loadAnimale(animale);

            Stage stage = (Stage) toggleButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            WindowUtil.setWindowSize(stage, scene);
        } catch (IOException e) {
            System.out.println("Errore nel caricamento della schermata ProdottoMese.");
        }
    }

    //logica di visualizzazione grafico---------------------------------------------------

    public void loadProdottiDataUltimoAnno() {
        // Recupera i dati dei prodotti dell'ultimo anno
        List<Prodotto> prodottiAnno = DAOFactory.getProdottoDAO().findProdottiUltimoAnno();

        // Raggruppa i prodotti per tipo_prodotto e calcola la quantità totale
        Map<String, Integer> prodottiPerTipo = prodottiAnno.stream()
                .collect(Collectors.groupingBy(Prodotto::getTipoProdotto, Collectors.summingInt(Prodotto::getQuantita)));

        // Recupera il listino prezzi aggiornato
        List<Listino> listino = DAOFactory.getListinoDAO().findAllPrezzoAggiornato();
        // Crea una mappa dei prezzi per tipo_prodotto
        Map<String, Float> prezziPerTipo = listino.stream()
                .collect(Collectors.toMap(Listino::getTipo_prodotto, Listino::getPrezzo));

        if (prodottiAnno != null && !prodottiAnno.isEmpty()) {
            // Calcola il guadagno totale
            float guadagnoTotale = (float) prodottiPerTipo.entrySet().stream()
                    .mapToDouble(entry -> entry.getValue() * prezziPerTipo.getOrDefault(entry.getKey(), 0.0f))
                    .sum();

            // Aggiorna la Label con il guadagno totale
            tot.setText(String.format("Guadagno Totale: %.2f euro", guadagnoTotale));

            // Popola il BarChart
            populateBarChart(prodottiPerTipo);
        } else {
            System.out.println("Nessun elemento prodotto");
        }
    }

    private void populateBarChart(Map<String, Integer> prodottiPerTipo) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : prodottiPerTipo.entrySet()) {
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

                        // Aggiorna il tipo di prodotto selezionato e l'etichetta del pulsante
                        selectedTipoProdotto = entry.getKey();
                        inserisciProdottoButton.setDisable(false);
                        inserisciProdottoButton.setText("Inserisci 100 kg: " + selectedTipoProdotto);
                        System.out.println("Selected Tipo Prodotto: " + selectedTipoProdotto);
                    });
                }
            });
        }

        // Pulisci i dati esistenti
        graficoProdotti.getData().clear();
        
        // Forza il layout per assicurarsi che il grafico venga aggiornato
        graficoProdotti.layout();

        // Aggiungi le nuove serie di dati al BarChart
        graficoProdotti.getData().add(series);
        
        // Forza un altro layout per assicurarsi che il grafico venga ridisegnato correttamente
        graficoProdotti.layout();
    }

    private void setupBarChartSelection() {
        for (XYChart.Series<String, Number> series : graficoProdotti.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    selectedTipoProdotto = data.getXValue();
                    inserisciProdottoButton.setDisable(false);
                    System.out.println("Selected Tipo Prodotto: " + selectedTipoProdotto);
                });
            }
        }
    }

    @FXML
    private void handleInserisciProdotto() {
        if (selectedTipoProdotto != null) {
            // Logica per inserire il tipo di prodotto selezionato nel magazzino
            System.out.println("Inserisci nel magazzino: " + selectedTipoProdotto);
            Prodotto prodotto = new Prodotto();
            prodotto.setQuantita(100);
            prodotto.setTipoProdotto(selectedTipoProdotto);
            prodotto.setDataProduzione(LocalDate.now());
            prodotto.setStalla(stalla.getEtichettaStalla());
            prodotto.setSpecie(stalla.getRazza());
            DAOFactory.getProdottoDAO().save(prodotto);

            // Refresh del grafico per mostrare le modifiche
            loadProdottiDataUltimoAnno();
        }
    }

    public void setUser(Utente utente) {
        this.utente = utente;
        updateVerticalMenuBar();
        updateMenuBar();
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
