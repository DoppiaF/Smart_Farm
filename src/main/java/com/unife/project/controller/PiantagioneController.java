package com.unife.project.controller;

import java.io.IOException;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Cisterna;
import com.unife.project.model.mo.Irrigazione;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;


import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
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

    private ObservableList<Irrigazione> irrigazioneData = FXCollections.observableArrayList();


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
    private TableColumn<Piantagione, Void> actionColumn;

    @FXML
    private TableView<Irrigazione> irrigazioneTable;
    
    @FXML
    private TableColumn<Irrigazione, Boolean> autoColumn;
    
    @FXML
    private TableColumn<Irrigazione, Integer> durataColumn;
    
    @FXML
    private TableColumn<Irrigazione, Integer> litriColumn;
    
    @FXML
    private TableColumn<Irrigazione, LocalTime> timeColumn;
    
    
    @FXML
    private TableColumn<Irrigazione, String> stIrrColumn;
    
    @FXML
    private TableColumn<Cisterna, Integer> cisternaColumn;

    //grafici
    @FXML
    private BarChart<String, Number> costiGuadagniChart;

    @FXML
    private BarChart<String, Number> produzioneChart;

    @FXML
    public void initialize() {
        // Inizializza le colonne della tabella
        //nelle property serve usare i nomi dei metodi getter del MO. es tipoPianta diventa getTipoPianta qui dentro.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoPianta"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("numZone"));
        statoColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
        concimazioneColumn.setCellValueFactory(new PropertyValueFactory<>("concimazione"));
        raccoltaColumn.setCellValueFactory(new PropertyValueFactory<>("raccolta"));

        //faccio lo stesso per le colonne della tabella irrigazioni
        /*autoColumn.setCellValueFactory(new PropertyValueFactory<>("auto"));
        durataColumn.setCellValueFactory(new PropertyValueFactory<>("durata"));
        litriColumn.setCellValueFactory(new PropertyValueFactory<>("litri_usati"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
        stIrrColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));*/

        //Imposto la cisterna da utilizzare
        //cisternaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Rendi le colonne editabili
        
        piantagioneTable.setEditable(true);
        tipoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        statoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        concimazioneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        raccoltaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

        /*autoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        durataColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        litriColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        stIrrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cisternaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
*/
         // Gestisci le modifiche delle celle
         tipoColumn.setOnEditCommit(event -> event.getRowValue().setTipoPianta(event.getNewValue()));
         areaColumn.setOnEditCommit(event -> event.getRowValue().setArea(event.getNewValue()));
         zoneColumn.setOnEditCommit(event -> event.getRowValue().setNumZone(event.getNewValue()));
         statoColumn.setOnEditCommit(event -> event.getRowValue().setStato(event.getNewValue()));
         concimazioneColumn.setOnEditCommit(event -> event.getRowValue().setConcimazione(event.getNewValue()));
         raccoltaColumn.setOnEditCommit(event -> event.getRowValue().setRaccolta(event.getNewValue()));

      /* autoColumn.setOnEditCommit(event -> event.getRowValue().setAuto(event.getNewValue()));
         durataColumn.setOnEditCommit(event -> event.getRowValue().setDurata(event.getNewValue()));
         litriColumn.setOnEditCommit(event -> event.getRowValue().setLitri_usati(event.getNewValue()));
         timeColumn.setOnEditCommit(event -> event.getRowValue().setOra_inizio(event.getNewValue()));
         stIrrColumn.setOnEditCommit(event -> event.getRowValue().setStato(event.getNewValue()));
         cisternaColumn.setOnEditCommit(event -> event.getRowValue().setId(event.getNewValue()));
         */
        // Aggiungi il pulsante di conferma alla tabella
        addConfirmButtonToTable();

        // Carica i dati delle piantagioni dal database
        loadPiantagioneData();
        
        // Aggiungi il pulsante di conferma alla tabella delle irrigazione
        //addConfirmButtonToIrrigationTable();

        // Carica i dati delle irrigazioni dal database
        //loadIrrigazioneData();

        piantagioneTable.setItems(piantagioneData);
        irrigazioneTable.setItems(irrigazioneData);
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
        if(piantagioneSelezionata != null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/irrigazionePage.fxml"));
                Parent zoneIrrigazioneRoot = loader.load();

                IrrigazioneController zoneIrrigazioneController = loader.getController();

                zoneIrrigazioneController.setUser(utente);
                zoneIrrigazioneController.setPiantagione(piantagioneSelezionata);

                
                Scene scene = new Scene(zoneIrrigazioneRoot);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                WindowUtil.setWindow(stage, scene, "Smartfarm - Irrigazione");

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
    private void handleGoToSensori(ActionEvent event) {
        Piantagione piantagioneSelezionata = piantagioneTable.getSelectionModel().getSelectedItem();
        if(piantagioneSelezionata != null){
            System.out.println("Piantagione selezionata: " + piantagioneSelezionata.toString());
            //funzione per calcolare righe e colonne da passare al grid pane in initialize
                int righe = 0;
                int colonne = 0;
            
                int i = 1;
                int numZone = piantagioneSelezionata.getNumZone();
                int areaZona = piantagioneSelezionata.getArea()/numZone;
        
                do{
                    if(i*i >= numZone){
                        righe = i;
                        colonne = numZone/i;
                    }
                    i++;
                }while(i < 6 && righe == 0);
            
                System.out.println("pre --- Righe: " + righe + " Colonne: " + colonne);
            try{
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/zoneRaccolta.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unife/project/view/zonePageV2.fxml"));
                Parent zoneRaccoltaRoot = loader.load();

                //ZoneRaccoltaController zoneRaccoltaController = loader.getController();
                ZonePageV2Controller zoneRaccoltaController = loader.getController();
                
                
                zoneRaccoltaController.setUser(utente);
                System.out.println("chiamato setUser");
                zoneRaccoltaController.setPiantagione(piantagioneSelezionata);
                System.out.println("chiamato setPiantagione");

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
        piantagioneData.clear();
        List<Piantagione> piantagioni = DAOFactory.getPiantagioneDAO().findAll();

        piantagioneData.addAll(piantagioni);
        irrigazioneData.clear();
    }
    
    private void loadIrrigazioneData() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        irrigazioneData.clear();
        List<Irrigazione> irrigazioni = DAOFactory.getIrrigazioneDAO().findAll();

        irrigazioneData.addAll(irrigazioni);
    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setUser(Utente utente){
        this.utente = utente;
        updateMenuBar();
        updateVerticalMenuBar();
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
                            getTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                                int index = getIndex();
                                if (index >= 0 && index <=getTableView().getItems().size() && newSelection == getTableView().getItems().get(index)) {
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
                                DAOFactory.getPiantagioneDAO().delete(piantagione);
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
        // Verifica che tutti i campi della riga siano validi
        if (piantagione.getTipoPianta().isEmpty() 
                || (piantagione.getArea() == 0) 
                || (piantagione.getNumZone() == 0)
                || piantagione.getStato().isEmpty()) {
            // Mostra un messaggio di errore
            showErrorDialog("Errore", "Tutti i campi devono essere compilati.");
            return;
        }

        
        // log per il debug
        System.out.println("Conferma piantagione. cosa sta inserendo nel db: " + piantagione.toString());
        
        // Aggiungi la piantagione al database
        DAOFactory.getPiantagioneDAO().save(piantagione);

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

}