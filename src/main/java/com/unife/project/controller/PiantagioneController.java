package com.unife.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Cisterna;
import com.unife.project.model.mo.Irrigazione;
import com.unife.project.model.mo.Magazzino;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.ProdottoConPrezzo;
import com.unife.project.model.mo.Raccolta;
import com.unife.project.model.mo.RaccoltoPiantaConPrezzo;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

//TableRow importata per cercare di impostare il menu a tendina per l'id irrigazione nelle righe della tabella
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

public class PiantagioneController {

    private Utente utente;
    //private Piantagione piantagione;
    private ObservableList<Piantagione> piantagioneData = FXCollections.observableArrayList();


    @FXML
    private BorderPane piantagioneRoot;
    
    @FXML
    private BorderPane piantagioneNested;

    //tabella piantagioni con colonne
    @FXML
    private TableView<Piantagione> piantagioneTable;

    @FXML
    private TableColumn<Piantagione, Integer> idColumn;

    @FXML
    private TableColumn<Piantagione, String> tipoColumn;

    @FXML
    private TableColumn<Piantagione, Integer> areaColumn;

    @FXML
    private TableColumn<Piantagione, Integer> zoneColumn;

    @FXML
    private TableColumn<Piantagione, String> statoColumn;

    @FXML
    private TableColumn<Piantagione, Boolean> concimazioneColumn;

    @FXML
    private TableColumn<Piantagione, Boolean> raccoltaColumn;

    @FXML
    private TableColumn<Piantagione, Integer> idIrrColumn;

    @FXML
    private TableColumn<Piantagione, Void> actionColumn;

    //grafici
    @FXML
    private BarChart<String, Number> costiGuadagniChart;

    @FXML
    private BarChart<String, Number> produzioneChart;

    
    @FXML
    private LineChart<String, Number> costiPiantagioneChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart<String, Number> guadagniPiantagioneChart;
    @FXML
    private CategoryAxis xAxisGuadagni;
    @FXML
    private NumberAxis yAxisGuadagni;

    @FXML
    private Button goToIrrigazioneButton;
    @FXML
    private Button goToSensoriButton;


    @FXML
    public void initialize() {

        if(utente != null){
            if(utente.getRuolo_irrigazione())
                goToIrrigazioneButton.setDisable(false);
            if(utente.getRuolo_raccolta())
                goToSensoriButton.setDisable(false);
        }

        // Inizializza le colonne della tabella
        //nelle property serve usare i nomi dei metodi getter del MO. es tipoPianta diventa getTipoPianta qui dentro.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoPianta"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("numZone"));
        statoColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
        concimazioneColumn.setCellValueFactory(new PropertyValueFactory<>("concimazione"));
        raccoltaColumn.setCellValueFactory(new PropertyValueFactory<>("raccolta"));
        idIrrColumn.setCellValueFactory(new PropertyValueFactory<>("id_irrigazione"));

        // Rendi le colonne editabili
        
        piantagioneTable.setEditable(true);
        tipoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        statoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        concimazioneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        raccoltaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        idIrrColumn.setCellFactory(column -> new TableCell<Piantagione, Integer>(){
            private final ComboBox<Integer> valoriId = new ComboBox<>();

            {
                valoriId.getItems().addAll(DAOFactory.getIrrigazioneDAO().findAllIrrIds());
                valoriId.setOnAction(event -> {
                    if (getTableRow() != null ){
                        Piantagione piantagione = getTableRow().getItem();
                        if (piantagione != null){
                            piantagione.setId_irrigazione(valoriId.getValue());
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    valoriId.setValue(item);
                    setGraphic(valoriId);
                }
            }
        });

         // Gestisci le modifiche delle celle
         tipoColumn.setOnEditCommit(event -> event.getRowValue().setTipoPianta(event.getNewValue()));
         areaColumn.setOnEditCommit(event -> event.getRowValue().setArea(event.getNewValue()));
         zoneColumn.setOnEditCommit(event -> event.getRowValue().setNumZone(event.getNewValue()));
         statoColumn.setOnEditCommit(event -> event.getRowValue().setStato(event.getNewValue()));
         concimazioneColumn.setOnEditCommit(event -> event.getRowValue().setConcimazione(event.getNewValue()));
         raccoltaColumn.setOnEditCommit(event -> event.getRowValue().setRaccolta(event.getNewValue()));
         idIrrColumn.setOnEditCommit(event -> event.getRowValue().setId_irrigazione(event.getNewValue()));
         
        loadGuadagniData();

        // Aggiungi il pulsante di conferma alla tabella
        addConfirmButtonToTable();

        // Carica i dati delle piantagioni dal database
        loadPiantagioneData();

        
        loadCostiData();
        

        piantagioneTable.setItems(piantagioneData);
    }

    @FXML
    private void handleAddPiantagione() {
        // Logica per aggiungere una nuova piantagione
        Piantagione nuovaPiantagione = new Piantagione(0, "", 0, "", 0, true, 1, false);
        piantagioneData.add(nuovaPiantagione);
        piantagioneTable.getSelectionModel().select(nuovaPiantagione);
        // Mostra un popup con un messaggio per l'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nuova Piantagione Aggiunta");
        alert.setHeaderText(null);
        alert.setContentText("Una nuova piantagione è stata aggiunta. Si prega di compilare i dettagli e confermare per aggiungere effettivamente il record.");
        alert.showAndWait();
    
    }

    @FXML
    private void handleGoToIrrigazione(ActionEvent event) {
        Piantagione piantagioneSelezionata = piantagioneTable.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/irrigazionePage.fxml"));
            Parent zoneIrrigazioneRoot = loader.load();

            IrrigazioneController zoneIrrigazioneController = loader.getController();

            zoneIrrigazioneController.setUser(utente);
            //se l'utente aveva già selezionato una piantagione dalla tabella piantagioni, nella tabella irrigazioni verrà mostrata come già selezionata la relativa irrigazione
            if(piantagioneSelezionata != null){
                zoneIrrigazioneController.setPiantagione(piantagioneSelezionata);
            }
            zoneIrrigazioneController.loadIrrigazioneData2();

            
            Scene scene = new Scene(zoneIrrigazioneRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            WindowUtil.setWindow(stage, scene, "Smartfarm - Irrigazione");

            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    
    }

    
    @FXML
    private void handleGoToSensori(ActionEvent event) {
        Piantagione piantagioneSelezionata = piantagioneTable.getSelectionModel().getSelectedItem();
        if(piantagioneSelezionata != null){
            System.out.println("Piantagione selezionata: " + piantagioneSelezionata.toString());
            
            try{
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/zoneRaccolta.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/zonePageV2.fxml"));
                Parent zoneRaccoltaRoot = loader.load();

                //ZoneRaccoltaController zoneRaccoltaController = loader.getController();
                ZonePageV2Controller zoneRaccoltaController = loader.getController();
                
                
                zoneRaccoltaController.setUser(utente);
                zoneRaccoltaController.setPiantagione(piantagioneSelezionata);

                Scene scene = new Scene(zoneRaccoltaRoot);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                WindowUtil.setWindow(stage, scene, "Smartfarm - zone");

                stage.show();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
                // Mostra un popup con un messaggio per l'utente
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informazione");
            alert.setHeaderText(null);
            alert.setContentText("Selezionare una piantagione per poterne visualizzare il sistema di irrigazione o i valori dei sensori.");
            alert.showAndWait();
        }
    
    }


    @FXML
    private void handleEditPiantagione() {
        // Logica per modificare una piantagione esistente
    }

    @FXML
    private void handleRemovePiantagione() {
        // Logica per rimuovere una piantagione
    }

    private void loadPiantagioneData() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        //piantagioneData.clear();
        List<Piantagione> piantagioni = DAOFactory.getPiantagioneDAO().findAll();

        piantagioneData.setAll(piantagioni);
    }


    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
        updateMenuBar();
        updateVerticalMenuBar();

        if(utente != null){
            if(utente.getRuolo_irrigazione())
                goToIrrigazioneButton.setDisable(false);
            if(utente.getRuolo_raccolta())
                goToSensoriButton.setDisable(false);
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
            piantagioneNested.setTop(menuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
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
            piantagioneRoot.setLeft(verticalMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu verticale.");
        }
    }

    //gestione dinamica dell'inserimento di una nuova piantagione dentro la tabella
    private void addConfirmButtonToTable() {
        //Viene creata una nuova colonna di tipo Void chiamata "Action".
        TableColumn<Piantagione, Void> colBtn = new TableColumn<>("Conferma aggiunta");

        //Viene definita una fabbrica di celle (cellFactory) che crea celle per la colonna "Action".
        Callback<TableColumn<Piantagione, Void>, TableCell<Piantagione, Void>> cellFactory = new Callback<TableColumn<Piantagione, Void>, TableCell<Piantagione, Void>>() {
            @Override
            public TableCell<Piantagione, Void> call(final TableColumn<Piantagione, Void> param) {
                final TableCell<Piantagione, Void> cell = new TableCell<Piantagione, Void>() {

                    private final Button btn = new Button("Conferma");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            //controllo l'indice corrente per evitare eccezioni su riga non selezionat
                            int index = getIndex();
                            if(index >= 0 && index < getTableView().getItems().size()){
                                //All'interno della cella, viene creato un pulsante "Conferma". 
                                //Quando il pulsante viene cliccato, viene chiamato il metodo confermaPiantagione 
                                //con la piantagione corrispondente alla riga.
                                Piantagione piantagione = getTableView().getItems().get(index);
                                confermaPiantagione(piantagione);
                            } else {
                                System.out.println("Errore: indice fuori dai limiti di tabella");
                            }
                            
                            
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        /*Il metodo updateItem viene sovrascritto per aggiornare il contenuto della cella. 
                        Se la cella è vuota (empty), 
                        il contenuto grafico viene impostato a null. Altrimenti, viene impostato il pulsante "Conferma". */
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            if (getTableView().getSelectionModel().isSelected(getIndex())) {
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }

                            // Aggiunge un listener per aggiornare la visibilità del pulsante quando la selezione cambia
                            //ho notato che questa lambda expression va in loop fino a ottenere un index troppo grande e facendo scattare index out of bounds exception
                            getTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                                int index = getIndex();
                                if (index >= 0 && 
                                index < getTableView().getItems().size() &&
                                newSelection == getTableView().getItems().get(index)) {
                                    setGraphic(btn);
                                } else {
                                    setGraphic(null);
                                }
                            });
                        
                        }
                    }
                };
                return cell;
            }    
        };

        /*La fabbrica di celle viene impostata sulla colonna "Action" e la colonna viene aggiunta alla TableView. */
        colBtn.setCellFactory(cellFactory);
        piantagioneTable.getColumns().add(colBtn);

        // Aggiungi una nuova colonna per il pulsante "Elimina"
        TableColumn<Piantagione, Void> colDeleteBtn = new TableColumn<>("Elimina");

        Callback<TableColumn<Piantagione, Void>, TableCell<Piantagione, Void>> deleteCellFactory = new Callback<TableColumn<Piantagione, Void>, TableCell<Piantagione, Void>>() {
            @Override
            public TableCell<Piantagione, Void> call(final TableColumn<Piantagione, Void> param) {
                final TableCell<Piantagione, Void> cell = new TableCell<Piantagione, Void>() {

                    private final Button btn = new Button("Elimina");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            // Controllo l'indice corrente per evitare eccezioni su riga non selezionata
                            int index = getIndex();
                            if (index >= 0 && index < getTableView().getItems().size()) {
                                // Rimuovi la piantagione corrispondente alla riga
                                Piantagione piantagione = getTableView().getItems().get(index);
                                piantagioneData.remove(piantagione);
                                // Puoi anche aggiungere la logica per rimuovere la piantagione dal database qui
                                DAOFactory.getPiantagioneDAO().
                                delete(piantagione);
                            } else {
                                System.out.println("Indice fuori dai limiti: " + index);
                            }
                        });
                    }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Controlla se la riga è selezionata
                        if (getTableView().getSelectionModel().isSelected(getIndex())) {
                            setGraphic(btn);
                        } else {
                            setGraphic(null);
                        }

                        // Aggiungi un listener per aggiornare la visibilità del pulsante quando la selezione cambia
                        getTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                            int index = getIndex();
                            if (index >= 0 && index < getTableView().getItems().size() && newSelection == getTableView().getItems().get(index)) {
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }
                        });
                    }
                }
            };
            return cell;
        }
    };

    colDeleteBtn.setCellFactory(deleteCellFactory);
    piantagioneTable.getColumns().add(colDeleteBtn);
}


    //metodo per confermare l'inserimento di una nuova piantagione dentro la tabella.
    private void confermaPiantagione(Piantagione piantagione) {
        if (DAOFactory.getPiantagioneDAO().findById(piantagione.getId()) == null){
            // Aggiungi la piantagione al database
            DAOFactory.getPiantagioneDAO().save(piantagione);
        }
        // Verifica che tutti i campi della riga siano validi
        if (piantagione.getTipoPianta().isEmpty() 
                || (piantagione.getArea() == 0) 
                || (piantagione.getNumZone() == 0)
                || piantagione.getStato().isEmpty()) {
            // Mostra un messaggio di errore
            showErrorDialog("Errore", "Tutti i campi devono essere compilati.");
            return;
        }
        else{

        
            // log per il debug
            System.out.println("La piantagione selezionata verrà aggiornata con questi dati: " + piantagione.toString());
            DAOFactory.getPiantagioneDAO().update(piantagione);
        }

        // Aggiorna la lista delle piantagioni
        loadPiantagioneData();

        // Rimuovi il pulsante "Conferma" dalla cella
        piantagioneTable.refresh();
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loadCostiData() {
        Map<String, Double> speseMesi = new HashMap<>();
        // Costi fissi benzina e acqua
        Double costo_benzina = 1.7;
        Double costo_acqua = 0.00037;
    
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusYears(1).withDayOfMonth(1);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    
        for (LocalDate date = startDate; !date.isAfter(currentDate); date = date.plusMonths(1)) {
            Double spesa_mensile = 0.0;
    
            for (Piantagione p : piantagioneData) {
                List<Raccolta> listaRaccolte = DAOFactory.getRaccoltaDAO().findByIdPiantagioneAndMese(p.getId(), date);
                Double spese_macchina = 0.0;
                Double spese_operatore = 0.0;
                Double spese_irrigazione = 0.0;
                Double spesa_raccolta = 0.0; 

                if (listaRaccolte != null && !listaRaccolte.isEmpty()) {
                    spese_macchina = costo_benzina * p.getArea();
                    spese_operatore = 1.10 * p.getArea();
                    spese_irrigazione = 0.0;
    
                    Irrigazione irrigazione = DAOFactory.getIrrigazioneDAO().findById(p.getId());
                    if (irrigazione != null) {
                        spese_irrigazione = costo_acqua * irrigazione.getLitri_usati();
                    }
    
                    spesa_raccolta = spese_macchina + spese_operatore + spese_irrigazione;
                    spesa_mensile += spesa_raccolta;
                }
            }
            speseMesi.put(date.format(formatter), spesa_mensile);
        }

        Map<String, Double> speseMesiOrd = new TreeMap<>(speseMesi);
    
        populateCostiChart(speseMesiOrd);
    }
    
    
    private void populateCostiChart(Map<String, Double> costiPerMese) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Costi Mensili");

        for (Map.Entry<String, Double> entry : costiPerMese.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Pulisci i dati esistenti
        costiPiantagioneChart.getData().clear();

        // Aggiungi le nuove serie di dati al LineChart
        costiPiantagioneChart.getData().add(series);
         // Configura gli assi
         xAxis.setLabel("Mese");
         yAxis.setLabel("Costo (euro)");
    }

    public void loadGuadagniData() {
        List<RaccoltoPiantaConPrezzo> raccoltoData = DAOFactory.getRaccoltaDAO().findRaccoltaUltimoAnnoConPrezzo();
        //List<ProdottoConPrezzo> prodottiData = DAOFactory.getProdottoDAO().findProdottoUltimoAnnoConPrezzo();

        if (raccoltoData != null && !raccoltoData.isEmpty()) {
            // Raggruppa i dati per mese e calcola il guadagno totale per ogni mese
            Map<String, Double> guadagniPerMese = raccoltoData.stream()
                    .collect(Collectors.groupingBy(
                            raccolta -> raccolta.getDataRaccolto().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                            TreeMap::new, // Utilizza TreeMap per ordinare le chiavi
                            Collectors.summingDouble(raccolta -> raccolta.getQuantita() * raccolta.getPrezzo())
                    ));

            // Popola il LineChart con i dati calcolati
            populateGuadagniChart(guadagniPerMese);
            System.out.println("Dati del raccolto caricati con successo.");
        } else {
            System.out.println("Nessun dato disponibile per il raccolto.");
        }
    }

    private void populateGuadagniChart(Map<String, Double> guadagniPerMese) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Guadagni Mensili");

        for (Map.Entry<String, Double> entry : guadagniPerMese.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Pulisci i dati esistenti
        guadagniPiantagioneChart.getData().clear();

        // Aggiungi le nuove serie di dati al LineChart
        guadagniPiantagioneChart.getData().add(series);

        // Configura gli assi
        xAxisGuadagni.setLabel("Mese");
        yAxisGuadagni.setLabel("Guadagno (euro)");
    }



}