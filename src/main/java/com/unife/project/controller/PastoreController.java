package com.unife.project.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.unife.project.model.dao.DAOFactory;
import com.unife.project.model.mo.Piantagione;
import com.unife.project.model.mo.Stalla;
import com.unife.project.model.mo.Utente;
import com.unife.project.util.WindowUtil;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class PastoreController {

    private Utente utente;
    private Stalla stalla;
    private ObservableList<Stalla> stalleData = FXCollections.observableArrayList();
    
    @FXML
    private BorderPane pastoreRoot;
    
    @FXML
    private BorderPane pastoreNested;

    //stabella stalle colonne
    @FXML
    private TableView<Stalla> stalleTable;

    @FXML
    private TableColumn<Stalla, String> nomeColumn;

    @FXML
    private TableColumn<Stalla, Integer> capienzaColumn;

    @FXML
    private TableColumn<Stalla, String> razzaColumn;

    @FXML
    private TableColumn<Stalla, LocalTime> pranzoColumn;

    @FXML
    private TableColumn<Stalla, LocalTime> cenaColumn;

    @FXML
    private BarChart<String, Number> costiChart;

    @FXML
    private BarChart<String, Number> guadagniChart;

    @FXML
    private void initialize() {
        // Inizializza le colonne della tabella
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("etichettaStalla"));
        capienzaColumn.setCellValueFactory(new PropertyValueFactory<>("capienza"));
        razzaColumn.setCellValueFactory(new PropertyValueFactory<>("razza"));
        pranzoColumn.setCellValueFactory(new PropertyValueFactory<>("oraPranzo"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("oraCena"));


        // Imposta i converter per le colonne LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        StringConverter<LocalTime> localTimeStringConverter = new LocalTimeStringConverter(timeFormatter, null);

        //rende le colonne editabili
        stalleTable.setEditable(true);
        nomeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        capienzaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        razzaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pranzoColumn.setCellFactory(TextFieldTableCell.forTableColumn(localTimeStringConverter));
        cenaColumn.setCellFactory(TextFieldTableCell.forTableColumn(localTimeStringConverter));
        
        //gestisci modifiche delle celle
        nomeColumn.setOnEditCommit(event -> event.getRowValue().setEtichettaStalla(event.getNewValue()));
        capienzaColumn.setOnEditCommit(event -> event.getRowValue().setCapienza(event.getNewValue()));
        razzaColumn.setOnEditCommit(event -> event.getRowValue().setRazza(event.getNewValue()));
        pranzoColumn.setOnEditCommit(event -> event.getRowValue().setOraPranzo(event.getNewValue()));
        cenaColumn.setOnEditCommit(event -> event.getRowValue().setOraCena(event.getNewValue()));

        // Carica i dati delle stalle dal database
        loadStalleData();
        stalleTable.setItems(stalleData);

        // Aggiungi la colonna del pulsante di conferma
        addConfirmButtonToTable();

        // Aggiungi la colonna del pulsante "goTo"
        addGoToButtonToTable();
    }

    @FXML
    private void handleAddStalla() {
        // Crea una nuova stalla con valori di default
        Stalla nuovaStalla = new Stalla();
        nuovaStalla.setEtichettaStalla("Nuova Stalla");
        nuovaStalla.setCapienza(0);
        nuovaStalla.setRazza("Razza");
        nuovaStalla.setOraPranzo(LocalTime.of(12, 0));
        nuovaStalla.setOraCena(LocalTime.of(18, 0));

        // Aggiungi la nuova stalla alla lista
        stalleData.add(nuovaStalla);

        // Seleziona la nuova stalla nella tabella
        stalleTable.getSelectionModel().select(nuovaStalla);

        // Mostra un popup con un messaggio per l'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nuova Stalla Aggiunta");
        alert.setHeaderText(null);
        alert.setContentText("Una nuova stalla è stata aggiunta. Si prega di compilare i dettagli e confermare.");
        alert.showAndWait();
    }

    @FXML
    private void handleEditAnimale() {
        // Logica per modificare un animale esistente
    }

    @FXML
    private void handleRemoveAnimale() {
        // Logica per rimuovere un animale
    }

    private void loadStalleData() {
        // Carica i dati delle piantagioni dal database e impostali nella tabella
        stalleData.clear();
        List<Stalla> stalle = DAOFactory.getStallaDAO().findAll();

        stalleData.addAll(stalle);
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
            pastoreNested.setTop(menuBarRoot);
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
            pastoreRoot.setLeft(verticalMenuBarRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Errore", "Impossibile caricare la barra di menu.");
        }
    }
    

    private void addConfirmButtonToTable() {
        TableColumn<Stalla, Void> colBtn = new TableColumn<>("Conferma Modifica");

        Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>> cellFactory = new Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>>() {
            @Override
            public TableCell<Stalla, Void> call(final TableColumn<Stalla, Void> param) {
                final TableCell<Stalla, Void> cell = new TableCell<Stalla, Void>() {

                    private final Button btn = new Button();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int index = getIndex();
                            if (index >= 0 && index < getTableView().getItems().size()) {
                                Stalla stalla = getTableView().getItems().get(index);
                                List<String> etichette = DAOFactory.getStallaDAO().findAllEtichette();

                                if (etichette.contains(stalla.getEtichettaStalla())) {
                                    // chiama il metodo DAO update, etichetta già esistente.
                                    DAOFactory.getStallaDAO().update(stalla);
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Stalla modificata.");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Stalla esistente modificata con successo.");
                                    alert.showAndWait();
                                } else {
                                    // Chiama il metodo DAO save, etichetta non esistente.
                                    DAOFactory.getStallaDAO().save(stalla);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Stalla Aggiunta");
                                    alert.setHeaderText(null);
                                    alert.setContentText("La nuova stalla è stata aggiunta con successo.");
                                    alert.showAndWait();
                                }
                            } else {
                                System.out.println("Indice non valido: " + index);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            int index = getIndex();
                            Stalla stalla = getTableView().getItems().get(index);
                            if (getTableView().getSelectionModel().isSelected(index)) {
                                List<String> etichette = DAOFactory.getStallaDAO().findAllEtichette();

                                if (etichette.contains(stalla.getEtichettaStalla())) {
                                    btn.setText("Modifica");
                                    btn.setDisable(false);
                                } else {
                                    btn.setText("Aggiungi");
                                    btn.setDisable(false);
                                }
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }

                            // Aggiungi un listener per aggiornare la visibilità del pulsante quando la selezione cambia
                            getTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                                if (newSelection == stalla) {
                                    List<String> etichette = DAOFactory.getStallaDAO().findAllEtichette();
                                    if (etichette.contains(stalla.getEtichettaStalla())) {
                                        btn.setText("Modifica");
                                        btn.setDisable(false);
                                    } else {
                                        btn.setText("Aggiungi");
                                        btn.setDisable(false);
                                    }
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
        colBtn.setCellFactory(cellFactory);
        stalleTable.getColumns().add(colBtn);

        // Aggiungi una nuova colonna per il pulsante "Elimina"
        TableColumn<Stalla, Void> colDeleteBtn = new TableColumn<>("Elimina");

        Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>> deleteCellFactory = new Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>>() {
            @Override
            public TableCell<Stalla, Void> call(final TableColumn<Stalla, Void> param) {
                final TableCell<Stalla, Void> cell = new TableCell<Stalla, Void>() {

                    private final Button btn = new Button("Elimina");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            // Ottieni l'indice della riga corrente
                            int index = getIndex();
                            
                            // Controlla se l'indice è valido
                            if (index >= 0 && index < getTableView().getItems().size()) {
                                // Ottieni la stalla corrente
                                Stalla stalla = getTableView().getItems().get(index);
                                
                                // Controlla se l'etichetta della stalla è univoca
                                boolean etichettaUnica = stalleData.stream()
                                    .filter(s -> !s.equals(stalla))
                                    .noneMatch(s -> s.getEtichettaStalla().equals(stalla.getEtichettaStalla()));
                                
                                if (etichettaUnica) {
                                    // Rimuovi la stalla dalla lista
                                    stalleData.remove(stalla);
                                    
                                    // Puoi anche aggiungere la logica per rimuovere la stalla dal database qui
                                    DAOFactory.getStallaDAO().delete(stalla);
                                } else {
                                    System.out.println("Etichetta già esistente: " + stalla.getEtichettaStalla());
                                }
                            } else {
                                System.out.println("Indice non valido: " + index);
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
        stalleTable.getColumns().add(colDeleteBtn);

    }


    private void addGoToButtonToTable() {
        TableColumn<Stalla, Void> colBtn = new TableColumn<>("Vai a Animali");

        Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>> cellFactory = new Callback<TableColumn<Stalla, Void>, TableCell<Stalla, Void>>() {
            @Override
            public TableCell<Stalla, Void> call(final TableColumn<Stalla, Void> param) {
                final TableCell<Stalla, Void> cell = new TableCell<Stalla, Void>() {

                    private final Button btn = new Button("goTo");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Stalla stalla_selezionata = getTableView().getItems().get(getIndex());

                            System.out.println("Vai a Animali nella stalla: " + stalla_selezionata.toString());
                            goToAnimaleView(stalla_selezionata, event);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            if (getTableView().getSelectionModel().isSelected(getIndex())) {
                                setGraphic(btn);
                            } else {
                                setGraphic(null);
                            }

                            // Aggiungi un listener per aggiornare la visibilità del pulsante quando la selezione cambia
                            getTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                                if (newSelection == getTableView().getItems().get(getIndex())) {
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

        colBtn.setCellFactory(cellFactory);
        stalleTable.getColumns().add(colBtn);
    }


    private void goToAnimaleView(Stalla stalla, ActionEvent event) {
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

            // Imposta le dimensioni della finestra utilizzando il metodo statico
            WindowUtil.setWindowSize(stage);

            // Imposta la nuova scena
            Scene scene = new Scene(animaleRoot);
            //stage.setTitle("Animali nella Stalla: " + stalla.getEtichettaStalla());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento della schermata Animali.");
        }
    }

    private void confermaStalla(Stalla stalla) {
        // Logica per aggiungere la stalla al database  
        DAOFactory.getStallaDAO().save(stalla);


        // Mostra un popup di conferma
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Stalla Confermata");
        alert.setHeaderText(null);
        alert.setContentText("La stalla è stata confermata e aggiunta al database.");
        alert.showAndWait();
    }


    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}