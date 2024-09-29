package com.unife.project.controller;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Cisterna;
import com.unife.project.model.mo.Irrigazione;
import com.unife.project.model.mo.IrrigazioneCisterna;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

public class IrrigazioneController {
    
    private Utente utente;
    private Piantagione piantagione;
    private Irrigazione irrigazione;
    private IrrigazioneCisterna irrigazioneCisterna;
    private Cisterna cisterna;
    private ObservableList<Irrigazione> irrigazioniData = FXCollections.observableArrayList();


    
    @FXML
    private BorderPane irrigazioneRoot;
    
    @FXML
    private BorderPane irrigazioneNested;
    //per tabella piccola con 1 sola irrigazione, relativa alla piantagione scelta nella pagina delle piantagioni
    //@FXML
    //private TableView<Irrigazione> irrigationsTable;
    /*
    @FXML
    private TableColumn<Irrigazione, Integer> idColumn;
    @FXML
    private TableColumn<Irrigazione, LocalTime> timeColumn;
    @FXML
    private TableColumn<Irrigazione, Integer> durationColumn;
    @FXML
    private TableColumn<Irrigazione, Boolean> autoColumn;
    @FXML
    private TableColumn<Irrigazione, String> stateColumn;
    @FXML
    private TableColumn<Irrigazione, Integer> reqLitresColumn;
    */
    //la mappa del campo sembra non essere più necessaria
    //@FXML
    //private GridPane fieldMap;
    
    @FXML
    private ProgressBar livello_cisterna;

    @FXML
    private Text livello_cisterna_text;
    
    //@FXML
    //private RadioButton irrigazioneAutomatica;

    @FXML
    private Button avviaIrrigazione;

    //tabellona_con_tutte le irrigazioni
    @FXML
    private TableView<Irrigazione> irrigazioneTable;
    
    @FXML
    private TableColumn<Irrigazione, Integer> irrigazioneIdColumn;
    
    @FXML
    private TableColumn<Irrigazione, Boolean> autoColumn2;
    
    @FXML
    private TableColumn<Irrigazione, Integer> durataColumn;
    
    @FXML
    private TableColumn<Irrigazione, Integer> litriColumn;
    
    @FXML
    private TableColumn<Irrigazione, LocalTime> timeColumn2;
    
    
    @FXML
    private TableColumn<Irrigazione, String> stIrrColumn;
    
    @FXML
    private TableColumn<Cisterna, Integer> cisternaColumn;


    //private ObservableList<Irrigazione> irrigazioneData = FXCollections.observableArrayList();
    //private ObservableList<Integer> cisternaData = FXCollections.observableArrayList();
    private ObservableList<Irrigazione> irrigazioneAndCisternaData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        System.out.println("Caricamento pagina");

        try{
            //inizializzazione colonne tabella piccola
            /*
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_irrigazione"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
            durationColumn.setCellValueFactory(new PropertyValueFactory<>("durata"));
            autoColumn.setCellValueFactory(new PropertyValueFactory<>("auto"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
            reqLitresColumn.setCellValueFactory(new PropertyValueFactory<>("litri_usati"));
            */


            //inizializzazione colonne tabellona
            irrigazioneIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_irrigazione"));
            autoColumn2.setCellValueFactory(new PropertyValueFactory<>("auto"));
            durataColumn.setCellValueFactory(new PropertyValueFactory<>("durata"));
            litriColumn.setCellValueFactory(new PropertyValueFactory<>("litri_usati"));
            timeColumn2.setCellValueFactory(new PropertyValueFactory<>("ora_inizio"));
            stIrrColumn.setCellValueFactory(new PropertyValueFactory<>("stato"));
            
            //Imposto la cisterna da utilizzare
            cisternaColumn.setCellValueFactory(new PropertyValueFactory<>("idIrrCisterna"));
            
            //le righe della tabellona vengono rese editabili
            irrigazioneIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            autoColumn2.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
            durataColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            litriColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            timeColumn2.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
            stIrrColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            //codice da sistemare per permettere la modifica corretta della colonna cisternaColumn
            cisternaColumn.setCellFactory(column -> new TableCell<Cisterna, Integer>(){
                private final ComboBox<Integer> valoriId = new ComboBox<>();

                {
                    valoriId.getItems().addAll(DAOFactory.getCisternaDAO().findAllIds());
                    valoriId.setOnAction(event -> {
                        if (getTableRow() != null ){
                            Cisterna cisterna = getTableRow().getItem();
                            if (cisterna != null){
                                cisterna.setId(valoriId.getValue());
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

            //gestisco le modifiche delle celle
            autoColumn2.setOnEditCommit(event -> event.getRowValue().setAuto(event.getNewValue()));
            durataColumn.setOnEditCommit(event -> event.getRowValue().setDurata(event.getNewValue()));
            litriColumn.setOnEditCommit(event -> event.getRowValue().setLitri_usati(event.getNewValue()));
            timeColumn2.setOnEditCommit(event -> event.getRowValue().setOra_inizio(event.getNewValue()));
            stIrrColumn.setOnEditCommit(event -> event.getRowValue().setStato(event.getNewValue()));
            cisternaColumn.setOnEditCommit(event -> event.getRowValue().setId(event.getNewValue()));
        
            //loadIrrigazioneData();

            
            // Aggiungi il pulsante di conferma alla tabella delle irrigazione
            //addConfirmButtonToIrrigationTable();

            // Carica i dati delle irrigazioni dal database nella tabellona
            //loadIrrigazioneData2();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Errore inizializzazione tabella irrigazioni");
        }

    }

    @FXML
    private void handleAddIrrigazione() {
        Irrigazione nuovaIrrigazione = new Irrigazione();

        Integer idIrrigazione = DAOFactory.getIrrigazioneDAO().findMaxId() +1;
        Integer idCisternaDft = 1;
        
        nuovaIrrigazione.setId_irrigazione(idIrrigazione);
        nuovaIrrigazione.setAuto(false);
        nuovaIrrigazione.setDurata(0);
        nuovaIrrigazione.setLitri_usati(0);
        nuovaIrrigazione.setOra_inizio(null);
        nuovaIrrigazione.setStato(null);

        IrrigazioneCisterna irrCis = new IrrigazioneCisterna(idIrrigazione, idCisternaDft);

        nuovaIrrigazione.setIdIrrCisterna(idCisternaDft);

        DAOFactory.getIrrigazioneDAO().save(nuovaIrrigazione);
        DAOFactory.getIrrigazioneCisternaDAO().save(irrCis);
        loadIrrigazioneData2();
    }

    @FXML
    private void handleDeleteIrrigazione() {
        Irrigazione irrigazioneSelezionata = irrigazioneTable.getSelectionModel().getSelectedItem();
        if(irrigazioneSelezionata != null){
            irrigazioniData.remove(irrigazioneSelezionata);
            //elimina l'animale dal database
            DAOFactory.getIrrigazioneDAO().delete(irrigazioneSelezionata);
            
            loadIrrigazioneData2();
        } else {
            showErrorDialog("Errore", "Seleziona dalla lista un' irrigazione che vuoi eliminare.");
        }
    }

    @FXML
    private void handleModifyIrrigazione() {
        Irrigazione irrigazioneSelezionata = irrigazioneTable.getSelectionModel().getSelectedItem();
        if(irrigazioneSelezionata != null){
            System.out.println("Modifica irrigazione: " + irrigazioneSelezionata.toString());
            //modifica l'animale nel database
            DAOFactory.getIrrigazioneDAO().update(irrigazioneSelezionata);
            loadIrrigazioneData2();
        } else {
            showErrorDialog("Errore", "Seleziona dalla lista un'irrigazione da modificare.");
        }
    }

        
    protected void loadIrrigazioneData2() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        irrigazioneAndCisternaData.clear();
        List<Irrigazione> irrigazioni = DAOFactory.getIrrigazioneDAO().findAllWCisterna();
        irrigazioneAndCisternaData.addAll(irrigazioni);

        irrigazioneTable.setItems(irrigazioneAndCisternaData);
        if(piantagione != null){
            Integer id_irrPiantagioneSelez = piantagione.getId_irrigazione();
            if(id_irrPiantagioneSelez != null && id_irrPiantagioneSelez != 0){
                irrigazioneTable.getSelectionModel().select(id_irrPiantagioneSelez-1);
                Cisterna cisterna = (DAOFactory.getCisternaDAO().findById((irrigazioneTable.getSelectionModel().getSelectedItem()).getIdIrrCisterna()));
                livello_cisterna.setProgress(cisterna.getPercRiempimento());
                livello_cisterna_text.setText("Cisterna n° " + cisterna.getId() + "\nLivello: " + (int)(cisterna.getPercRiempimento()*100) + " % +\nCapacità: "+ cisterna.getCapacita()+"l \nQuantità attuale: " + cisterna.getQuantita() + "l");
            }
        }
        //aggiorna l'icona della cisterna in base alla riga selezionata nella tabella delle irrigazioni
        irrigazioneTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null){
                Cisterna newCisterna = (DAOFactory.getCisternaDAO().findById(newSelection.getIdIrrCisterna()));
                livello_cisterna.setProgress(newCisterna.getPercRiempimento()
                );
                livello_cisterna_text.setText("Cisterna n° " + newCisterna.getId() + "\nLivello: " + (int)(newCisterna.getPercRiempimento()*100) + " % +\nCapacità: "+ newCisterna.getCapacita()+"l \nQuantità attuale: " + newCisterna.getQuantita() + "l");

                

            } else if(oldSelection != null){
                Cisterna oldCisterna = (DAOFactory.getCisternaDAO().findById(oldSelection.getIdIrrCisterna()));
                livello_cisterna.setProgress(oldCisterna.getPercRiempimento()
                );
                livello_cisterna_text.setText("Cisterna n° " + oldCisterna.getId() + "\nLivello: " + oldCisterna.getPercRiempimento()*100 + " %");

            }else {
                livello_cisterna.progressProperty().unbind();
                livello_cisterna.setProgress(0.00);
            }
        });

    }
    
    private void loadIrrigazioneData() {
        if(piantagione != null){
            irrigazione = DAOFactory.getIrrigazioneDAO().findById(piantagione.getId_irrigazione());

            irrigazioniData.setAll(irrigazione);
            //irrigationsTable.setItems(irrigazioniData);
            
            irrigazioneCisterna = DAOFactory.getIrrigazioneCisternaDAO().findById_irrigazione(irrigazione.getId_irrigazione());
            cisterna = DAOFactory.getCisternaDAO().findById(irrigazioneCisterna.getId_cisterna());

            
            //


            System.out.println("irrigazione, cisterna e la tabella sono stati caricati e settati con successo");
            System.out.println("irrigazione: " + irrigazione.toString() + 
            " irrigazione-cisterna: " + irrigazioneCisterna.toString() +
            " cisterna: " + cisterna.toString() +
            " irrigazioniData: " + irrigazioniData.toString() );

            Double riempimento = (double)cisterna.getQuantita()/(double)cisterna.getCapacita();

            //qui resta da caricare fieldMap
            System.out.println("riempimento cisterna = " + riempimento);

            //livello_cisterna.setProgress(riempimento);
            //livello_cisterna_text.setText("Cisterna n° " + cisterna.getId() + "\nLivello: " + cisterna.getPercRiempimento()*100 + " %");
            //irrigazioneAutomatica.setSelected(irrigazione.isAuto());
            //if(!irrigazioneAutomatica.isSelected())avviaIrrigazione.setVisible(true); 
        }else{
            System.out.println("Piantagione non trovata, non è possibile caricare i dati della sua irrigazione");
            irrigazioniData.clear();
        }
        
        

    }

    //metodo da chiamare da altri controller per passare l'utente alla home
    public void setPiantagione(Piantagione piantagione){
        
        System.out.println("Settaggio piantagione");
        this.piantagione = piantagione;
        //loadIrrigazioneData();
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
            irrigazioneNested.setTop(menuBarRoot);
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
            irrigazioneRoot.setLeft(verticalMenuBarRoot);
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
