package com.unife.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Magazzino;
import com.unife.project.model.mo.Prodotto;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;

public class AnimaliController {

    @FXML
    private BorderPane rootPane;
    @FXML
    private BorderPane nestedPane;

    //tabella animali
    @FXML
    private TableView<Animale> animaliTable;
    @FXML
    private TableColumn<Animale, Integer> idColumn;
    @FXML
    private TableColumn<Animale, Integer> pesoColumn;
    @FXML
    private TableColumn<Animale, String> razzaColumn;
    @FXML
    private TableColumn<Animale, Character> sessoColumn;
    @FXML 
    private TableColumn<Animale, String> mangimeColumn;
    @FXML
    private TableColumn<Animale, LocalDate> nascitaColumn;
    @FXML
    private TableColumn<Animale, LocalDate> ingressoColumn;
    @FXML
    private TableColumn<Animale, LocalDate> vaccinoColumn;
    @FXML
    private TableColumn<Animale, LocalDate> uscitaColumn;
    @FXML
    private TableColumn<Animale, LocalDate> morteColumn;
    /*@FXML
    private TableColumn<Animale, Integer> etaColumn;*/

    @FXML
    private Label etichettaStallaLabel;
    @FXML
    private PieChart pieMagazzino;
    @FXML
    private PieChart pieProdotto;
    
    private ObservableList<Animale> animaliData = FXCollections.observableArrayList();
    private Stalla stalla = null;
    private Utente utente = null;

    @FXML
    private void initialize() {
        try{
            
            // Inizializza le colonne della tabella           
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_animale"));
            pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));
            razzaColumn.setCellValueFactory(new PropertyValueFactory<>("razza"));
            sessoColumn.setCellValueFactory(new PropertyValueFactory<>("sesso"));
            mangimeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoAlimentazione"));
            nascitaColumn.setCellValueFactory(new PropertyValueFactory<>("data_nascita"));
            ingressoColumn.setCellValueFactory(new PropertyValueFactory<>("data_ingresso"));
            vaccinoColumn.setCellValueFactory(new PropertyValueFactory<>("data_vaccino"));
            uscitaColumn.setCellValueFactory(new PropertyValueFactory<>("data_uscita"));
            morteColumn.setCellValueFactory(new PropertyValueFactory<>("data_morte"));
            
            
            //rende le colonne editabili
            animaliTable.setEditable(true);
            pesoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            razzaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            sessoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CharacterStringConverter()));
            mangimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nascitaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            ingressoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            vaccinoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            uscitaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            morteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));


            //gestione modifiche celle
            pesoColumn.setOnEditCommit(event -> event.getRowValue().setPeso(event.getNewValue()));
            razzaColumn.setOnEditCommit(event -> event.getRowValue().setRazza(event.getNewValue()));
            sessoColumn.setOnEditCommit(event -> event.getRowValue().setSesso(event.getNewValue()));
            mangimeColumn.setOnEditCommit(event -> event.getRowValue().setTipoAlimentazione(event.getNewValue()));
            nascitaColumn.setOnEditCommit(event -> event.getRowValue().setData_nascita(event.getNewValue()));
            ingressoColumn.setOnEditCommit(event -> event.getRowValue().setData_ingresso(event.getNewValue()));
            vaccinoColumn.setOnEditCommit(event -> event.getRowValue().setData_vaccino(event.getNewValue()));
            uscitaColumn.setOnEditCommit(event -> event.getRowValue().setData_uscita(event.getNewValue()));
            morteColumn.setOnEditCommit(event -> event.getRowValue().setData_morte(event.getNewValue()));

            
        }catch (Exception e) {            
            showErrorDialog("Errore", "Impossibile caricare i dati degli animali.");
        }
        loadMagazzinoData();
        
    }

    public void setStalla(Stalla stalla) {
        this.stalla = stalla;
        etichettaStallaLabel.setText(stalla.getEtichettaStalla()); // Imposta il testo della Label
        
        loadAnimaliData();
        loadProdottiData();
        if (!animaliData.isEmpty()) {
            System.out.println("Caricamento dati animali completato. primo animale: " + animaliData.get(0).toString());
        } else {
            System.out.println("Nessun animale trovato.");
        }
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
        updateMenuBar();
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

    private void loadAnimaliData() {
        if (animaliData == null) {
            animaliData = FXCollections.observableArrayList();
        }
        if (stalla != null) {
            //animaliTable.refresh();
            animaliData.setAll(DAOFactory.getAnimaleDAO().findByStalla(stalla.getEtichettaStalla()));
            animaliTable.setItems(animaliData);
        }else{
            showErrorDialog("Errore", "Impossibile caricare i dati degli animali.");
        }
        
    }

    private void loadMagazzinoData() {
        // Recupera i dati del magazzino per l'ultimo anno
        List<Magazzino> magazzinoData = DAOFactory.getMagazzinoDAO().findAllUltimoAnno();

        // Raggruppa i mangimi per tipo e somma le quantità
        Map<String, Integer> mangimiPerTipo = magazzinoData.stream()
                .collect(Collectors.groupingBy(Magazzino::getTipoMangime, Collectors.summingInt(Magazzino::getQuantita)));

        // Crea una lista osservabile per i dati del PieChart
        ObservableList<PieChart.Data> pieMagazzinoData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : mangimiPerTipo.entrySet()) {
            pieMagazzinoData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        // Imposta i dati nel PieChart
        pieMagazzino.setData(pieMagazzinoData);
    }

    private void loadProdottiData() {
        System.out.println("Caricamento dati prodotti per la razza: " + stalla.getRazza());
        // Recupera i dati dei prodotti
        List<Prodotto> prodottiData = DAOFactory.getProdottoDAO().findProdottoUltimoAnnoPerRazza(stalla.getRazza());

        System.out.println("Prodotti trovati: " + prodottiData.size());
        if(prodottiData == null || prodottiData.isEmpty()){
            System.out.println("Specie non trovata. Caricamento di tutti i prodotti.");
            prodottiData = DAOFactory.getProdottoDAO().findProdottiUltimoAnno();
        }

        // Crea una mappa per sommare le quantità per tipo di prodotto
        Map<String, Integer> prodottiPerTipo = new HashMap<>();
        for (Prodotto prodotto : prodottiData) {
            prodottiPerTipo.merge(prodotto.getTipoProdotto(), prodotto.getQuantita(), Integer::sum);
        }

        // Crea una lista osservabile per i dati della PieChart
        ObservableList<PieChart.Data> pieProdottiData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : prodottiPerTipo.entrySet()) {
            pieProdottiData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        // Imposta i dati nella PieChart
        pieProdotto.setData(pieProdottiData);
    }

    @FXML
    private void handleAddAnimale() {
        // Crea un nuovo animale con valori di default
        Animale nuovoAnimale = new Animale();

        // Imposta i valori di default
        nuovoAnimale.setId_animale(animaliData.size()+1); // id_animale
        nuovoAnimale.setPeso(0); // peso
        nuovoAnimale.setSesso('M'); // sesso
        nuovoAnimale.setRazza(stalla.getRazza()); // razza
        nuovoAnimale.setTipoAlimentazione("granoturco"); // tipoAlimentazione
        nuovoAnimale.setNomeStalla(stalla.getEtichettaStalla()); // nomeStalla
        nuovoAnimale.setData_nascita(null); // data_nascita
        nuovoAnimale.setData_ingresso(null); // data_ingresso
        nuovoAnimale.setData_uscita(null); // data_uscita
        nuovoAnimale.setData_morte(null); // data_morte
        nuovoAnimale.setData_vaccino(null); // data_vaccino        

        //aggiungi l'animale al database
        DAOFactory.getAnimaleDAO().save(nuovoAnimale);

        loadAnimaliData();
    }

    @FXML
    public void handleDeleteAnimale(){
        Animale animaleSelezionato = animaliTable.getSelectionModel().getSelectedItem();
        if(animaleSelezionato != null){
            animaliData.remove(animaleSelezionato);
            //elimina l'animale dal database
            DAOFactory.getAnimaleDAO().delete(animaleSelezionato);
            loadAnimaliData();
        } else {
            showErrorDialog("Errore", "Seleziona dalla lista un animale da eliminare.");
        }
    }

    @FXML
    public void handleModifyAnimale(){
        Animale animaleSelezionato = animaliTable.getSelectionModel().getSelectedItem();
        if(animaleSelezionato != null){
            System.out.println("Modifica animale: " + animaleSelezionato.toString());
            //modifica l'animale nel database
            DAOFactory.getAnimaleDAO().update(animaleSelezionato);
            loadAnimaliData();
        } else {
            showErrorDialog("Errore", "Seleziona dalla lista un animale da modificare.");
        }
    }

    @FXML
    public void handleGoToMagazzino(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/magazzinoPage.fxml"));
            Parent magazzinoRoot = loader.load();

            // Ottieni il controller della barra di menu
            MagazzinoController magazzinoController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            magazzinoController.setUser(utente);
            magazzinoController.loadMagazzinoData();
            magazzinoController.setStalla(stalla);

            //AZ Aggiunto ActionEvent come parametro del metodo, + queste 4 righe per regolare la finestra quando viene caricata
            Scene scene = new Scene(magazzinoRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            WindowUtil.setWindow(stage,scene,"Smartfarm - Magazzino");

            // Aggiungi la barra di menu alla root
            //rootPane.setCenter(magazzinoRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata del magazzino.");
        }
    }

    @FXML
    public void handleGoToProdotto(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/prodottoPage.fxml"));
            Parent prodottoRoot = loader.load();

            // Ottieni il controller della barra di menu
            ProdottoController prodottoController = loader.getController();
            //passa utente al controller menu bar e aggiorna visibilità bottoni
            prodottoController.setUser(utente);
            //prodottoController.loadProdottiDataUltimoAnno();
            prodottoController.setStalla(stalla);

            Animale animaleSelezionato = animaliTable.getSelectionModel().getSelectedItem();
            prodottoController.loadAnimale(animaleSelezionato);
            
            

            // Ottieni lo stage principale
            Stage stage = (Stage) rootPane.getScene().getWindow();
            // Crea una nuova scena con il contenuto della nuova vista
            Scene scene = new Scene(prodottoRoot);
            // Imposta la nuova scena sullo stage principale
            stage.setScene(scene);
            // Mostra lo stage principale con la nuova scena
            WindowUtil.setWindowSize(stage, scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la schermata del prodotto.");
        }
    }

    @FXML
    public void handleGoToVeterinario(){
        Animale animaleSelezionato = animaliTable.getSelectionModel().getSelectedItem();
        if(animaleSelezionato == null){
            showErrorDialog("Errore", "Seleziona dalla lista un animale per visualizzare le visite veterinarie.");
        }else {
            try{
                System.out.println("Animale selezionato: " + animaleSelezionato.toString());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/veterinarioPage.fxml"));
                Parent veterinarioRoot = loader.load();

                // Ottieni il controller della barra di menu
                VeterinarioController veterinarioController = loader.getController();
                //passa utente al controller menu bar e aggiorna visibilità bottoni
                veterinarioController.setUser(utente);
                veterinarioController.setAnimale(animaleSelezionato);
                //veterinarioController.loadVeterinarioData();

                // Aggiungi la barra di menu alla root
                rootPane.setCenter(veterinarioRoot);
            } catch (IOException e) {
                e.printStackTrace();
                showErrorDialog("Errore", "Impossibile caricare la schermata del veterinario. ");
            }
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