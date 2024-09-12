package com.unife.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Animale;
import com.unife.project.model.mo.Utente;
import com.unife.project.model.mo.VisitaVeterinaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;

public class VeterinarioController {
    
    @FXML
    private BorderPane vetRoot;
    @FXML
    private BorderPane vetNested;

    //tabella visite
    @FXML
    private TableView<VisitaVeterinaria> visiteTable;
    @FXML
    private TableColumn<VisitaVeterinaria, String> nomeColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> cognomeColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, LocalDate> dataColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> diagnosiColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> curaColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, Boolean> programmataColumn;
    @FXML
    private TableColumn<VisitaVeterinaria, String> statoAnimaleColumn;


    private Utente utente = null;
    private Animale animale = null;
    private VisitaVeterinaria visitaVeterinaria = null;
    private ObservableList<VisitaVeterinaria> visiteData = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;
    @FXML
    private Button confirmButton;
      
    
    public void initialize() {
        try{
            nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nomeVeterinario"));
            cognomeColumn.setCellValueFactory(new PropertyValueFactory<>("cognomeVeterinario"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
            diagnosiColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosi"));
            curaColumn.setCellValueFactory(new PropertyValueFactory<>("curaPrescritta"));
            programmataColumn.setCellValueFactory(new PropertyValueFactory<>("programmata"));
            statoAnimaleColumn.setCellValueFactory(new PropertyValueFactory<>("statoAnimale"));

            // Permette la modifica delle celle
            visiteTable.setEditable(true);
            nomeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            cognomeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            diagnosiColumn.setCellFactory(TextFieldTableCell.forTableColumn()); 
            curaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            //programmataColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
            statoAnimaleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            //gestione modifiche celle
            nomeColumn.setOnEditCommit(event -> event.getRowValue().setNomeVeterinario(event.getNewValue()));
            cognomeColumn.setOnEditCommit(event -> event.getRowValue().setCognomeVeterinario(event.getNewValue()));
            diagnosiColumn.setOnEditCommit(event -> event.getRowValue().setDiagnosi(event.getNewValue()));
            curaColumn.setOnEditCommit(event -> event.getRowValue().setCuraPrescritta(event.getNewValue()));
            //programmataColumn.setOnEditCommit(event -> event.getRowValue().setProgrammata((event.getNewValue())));
            statoAnimaleColumn.setOnEditCommit(event -> event.getRowValue().setStatoAnimale(event.getNewValue()));

            //imposta datePikcer personalizzato
            datePicker.setDayCellFactory(getDateCellFactory());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Errore inizializzazione tabella visite");
        }

        // Associa un'azione al pulsante
        confirmButton.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                aggiungiVisitaVeterinaria(selectedDate);
            }
        });

        loadVeterinarioData();
    }


    private void aggiungiVisitaVeterinaria(LocalDate data) {
        
            // Implementa la logica per aggiungere una visita veterinaria al database
            System.out.println("Aggiungi visita veterinaria per la data: " + data);
            // Esempio di codice per aggiungere la visita al database
            visitaVeterinaria = new VisitaVeterinaria(
                data,
                "Da compilare", // Sostituisci con i dati reali
                animale.getId_animale(), // ID animale esempio, sostituisci con il valore reale
                "Da compilare", // Sostituisci con i dati reali
                "Da compilare", // Sostituisci con i dati reali
                "Da compilare", // Sostituisci con i dati reali
                "Da compilare", // Sostituisci con i dati reali
                true // Sostituisci con il valore reale
            );
            System.out.println("Visita veterinaria creata: " + visitaVeterinaria.toString());
            // Aggiungi la visita al database
            DAOFactory.getVisitaVeterinariaDAO().save(visitaVeterinaria);
            // Aggiorna la tabella
            loadVeterinarioData();
        
    }

    @FXML
    public void handleModifyVisita(){
        VisitaVeterinaria visitaSelezionata = visiteTable.getSelectionModel().getSelectedItem();
        if(visitaSelezionata != null) {
            try{
                visitaSelezionata.setProgrammata(false);
                System.out.println("Modifica visita selezionata: " + visitaSelezionata.toString());
                DAOFactory.getVisitaVeterinariaDAO().update(visitaSelezionata);
                System.out.println("Visita modificata correttamente.");
                loadVeterinarioData();
            }catch(Exception e){
                e.printStackTrace();
                showErrorDialog("Errore", "Impossibile modificare la visita selezionata.");
            }
        }
    }

    public void loadVeterinarioData() {
        try {
            if (animale != null) {
                visiteData.setAll(DAOFactory.getVisitaVeterinariaDAO().findByIdAnimale(animale.getId_animale()));
            } else {
                visiteData.clear();
            }
            visiteTable.setItems(visiteData);
            System.out.println("Numero di elementi nella lista visite: " + visiteData.size());
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare i dati delle visite veterinarie.");
        }
    }

    public void setAnimale(Animale animale) {
        this.animale = animale;
        if (animale != null) {
            System.out.println("Animale settato: " + animale.getId_animale());
            loadVeterinarioData();
        } else {
            System.out.println("Animale nullo");
            visiteData.clear();
            visiteTable.setItems(visiteData);
        }
    }

    //logica di gestione utente e schermata default------------------------------------
        public void setUser(Utente utente) {
            this.utente = utente;
            updateMenuBar();
            //updateVerticalMenuBar();
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
                vetRoot.setLeft(verticalMenuBarRoot);
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
                vetNested.setTop(menuBarRoot);
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


        private Callback<DatePicker, DateCell> getDateCellFactory() {
            return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disabilita le date passate
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Colore rosso chiaro
                        }

                        // Disabilita le date già presenti nella tabella
                        List<LocalDate> dateList = visiteTable.getItems().stream()
                                .map(VisitaVeterinaria::getData)
                                .collect(Collectors.toList());
                        if (dateList.contains(item)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Colore rosso chiaro
                        }
                    }
                };
            }
        };
    }
}
