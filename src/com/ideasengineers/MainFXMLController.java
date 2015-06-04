/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import com.sun.javafx.scene.control.skin.CustomColorDialog;
import java.net.URL;
import java.text.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

/**
 *
 * @author Marc Zicchino
 */
public class MainFXMLController implements Initializable {

    @FXML
    private TableView<Drug> marketTable;                  // Tabelle links für den Marktplatz
    @FXML
    private TableView<Drug> pocketTable;                  // Tabelle rechts für das eigene Material
    @FXML
    private TableColumn<Drug, String> countRow;           // Spalte in Tabelle rechts für die Anzahl der Droge
    @FXML
    private TableColumn<Drug, String> priceRow;           // Spalte in Tabelle links für den Preis der Droge pro Gramm
    @FXML
    private TableColumn<Drug, String> namePocketRow;      // Spalte in Tabelle rechts für den Namen der Droge
    @FXML
    private TableColumn<Drug, String> nameMarketRow;      // Spalte in Tabelle links für den Namen der Droge
    @FXML
    private TextArea logField;        // Anzeigetafel in der Mitte
    @FXML
    private Label regionField;        // Standort
    @FXML
    private Label debtField;          // Schulden
    @FXML
    private Label cashField;          // Momentaner Spielgeldbetrag
    @FXML
    private Label spaceField;         // Noch freier Platz für Drogen
    @FXML
    private Label healthField;        // Verbleibende Lebenspunkte
    @FXML
    private Label dmgField;           // Gesamtschaden der Waffen
    @FXML
    private Label agilityField;       // Treffsicherheit
    @FXML
    private Label dateField;          // Datumsausgabe
    @FXML
    private Label bankField;          // Bankguthaben

    /**
     * Deklarierungen
     */
    private ObservableList<Drug> newOffers;     // DrugList die sich bei Änderung aktuialisiert
    private Random rnd;                         // Objekt mir Methoden um Zufallszahlen zu erstellen
    private Player activePlayer;                // wie der Name sagt: der aktive Spieler
    private FilteredList<Drug> drugsPocket;     // gefilterte Liste für die Drogentasche
    private FilteredList<Drug> drugsMarket;     // gefilterte Liste für den Drogenmarkt
    private SortedList<Drug> drugsPocketSort;   // sortierte Liste für die Drogentasche
    private SortedList<Drug> drugsMarketSort;   // sortierte Liste für den Drogenmarkt
    private LocalDate date;                     // Datumsobjekt
    private double optionHp;
    private double optionCash;
    private double optionAgility;
    private String temp;

    /**
     * Initialisierungen
     */
    private String playerName;
    private final DecimalFormat f = new DecimalFormat("#0.00");         // "2 Stellen hinterm Komma" - Format, für die Ausgabe von Preisen
    private final String[] officerNames = {
        "Officer", "FBI Agent", "NSA Spy", "Soldier", "007"
    };

    @FXML
    private void jetBtnAction(ActionEvent event) {
        if(!(checkPlayTime(activePlayer.getDayCounter(), activePlayer.getPlayTime()))) {
            chooseRegion();
        } else {
            
        }
    }

    /**
     * Initialisierung beim Spielstart
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Standardwerte für Lebenspunkte, Cash und Agility
        this.optionHp = 100;
        this.optionCash = 5000;
        this.optionAgility = 0.05;

        // heutiges Datum als Standardwert
        //this.date = LocalDate.now();

        // Erste frage nach Standardwerteinstellungen... Benutzerdefinierter Schwierigkeitsgrad
        if(createTextInputDialog4Options("Options", "Dopewars")) {
            createTextInputDialog("Spieler", "Dopewars", "Willkommen bei Dopewars\nBitte mit OK bestätigen!", "Bitte gib deinen Namen ein:", 0);
            createTextInputDialog(String.valueOf(this.optionHp), "Dopewars", "Optionen\nLebenspunkte\nBitte mit OK bestätigen!", "Mit wievielen Lebenspunkten möchtest du starten?", 1);
            createTextInputDialog(String.valueOf(this.optionCash), "Dopewars", "Optionen\nBargeld\nBitte mit OK bestätigen!", "Mit wieviel Bargeld möchtest du starten?", 2);
            createTextInputDialog(String.valueOf(this.optionAgility), "Dopewars", "Optionen\nTreffsicherheit\nBitte mit OK bestätigen!", "Mit wieviel Treffsicherheit möchtest du starten?", 3);
        }

        newOffers = FXCollections.observableArrayList();
        date = LocalDate.now();
        activePlayer = new Player(this.playerName, this.optionHp, this.optionCash, this.optionAgility);
        rnd = new Random();

        initDrugs();
        initRegions();

        activePlayer.setActiveRegion(Region.regions.get(rnd.nextInt(Region.regions.size() - 1)));
        System.out.println("Set Region to " + activePlayer.getActiveRegion().getName());

        for(Drug b : activePlayer.getActiveRegion().getDrugs()) {
            b.generateNewValue();
        }

        nameMarketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        priceRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(f.format(param.getValue().getValue())));

        namePocketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        countRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(f.format(param.getValue().getAmount())));

        rescanLists();
        updateFields();

    }

    /**
     * method rescanLists()
     *
     * generiert die beiden Listen (Markt und Tasche) neu
     */
    private void rescanLists() {
        drugsPocket = activePlayer.getActiveRegion().getDrugs().filtered(drug -> drug.getAmount() != 0);            // 
        drugsMarket = activePlayer.getActiveRegion().getDrugs().filtered(drug -> drug.isAvailable());
        drugsPocketSort = drugsPocket.sorted();
        drugsMarketSort = drugsMarket.sorted();

        activePlayer.getActiveRegion().getDrugs().addListener((ListChangeListener.Change<? extends Drug> c) -> {
            drugsPocket = activePlayer.getActiveRegion().getDrugs().filtered(drug -> drug.getAmount() != 0);
            drugsMarket = activePlayer.getActiveRegion().getDrugs().filtered(drug -> drug.isAvailable());
            drugsPocketSort = drugsPocket.sorted();
            drugsMarketSort = drugsMarket.sorted();
        });
        drugsMarketSort.comparatorProperty().bind(marketTable.comparatorProperty());
        nameMarketRow.setSortType(TableColumn.SortType.ASCENDING);
        marketTable.setItems(drugsMarketSort);
        marketTable.getSortOrder().add(nameMarketRow);

        drugsPocketSort.comparatorProperty().bind(pocketTable.comparatorProperty());
        namePocketRow.setSortType(TableColumn.SortType.ASCENDING);
        pocketTable.setItems(drugsPocketSort);
        pocketTable.getSortOrder().add(namePocketRow);
        pocketTable.setPlaceholder(new Label(""));

        // neue Droge anlegen und gleich wieder löschen um Liste zu aktualisieren
        Drug tmp = new Drug(new SimpleStringProperty("tmp"), 10.0, 20.0);
        activePlayer.getActiveRegion().getDrugs().add(tmp);
        activePlayer.getActiveRegion().getDrugs().remove(tmp);

    }

    @FXML
    void buyBtnAction(ActionEvent event) {

        Drug selected = marketTable.getSelectionModel().getSelectedItem();

        if(selected != null) {
            TextInputDialog dialog = new TextInputDialog("10");
            int maxSpace = activePlayer.getFreeSpace();
            int maxCash = (int) (activePlayer.getCash() / selected.getValue());
            int max;
            if(maxSpace < maxCash) {
                max = maxSpace;
            } else {
                max = maxCash;
            }
            dialog.setTitle("Kaufen");
            dialog.setHeaderText("Bitte die gewünschte Menge " + selected.getName() + " eingeben [MAX=" + max + "]:");
            dialog.setContentText("Menge");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                if(Integer.parseInt(name) < max) {
                    activePlayer.getActiveRegion().getDrugs().remove(selected);
                    selected.setAmount(selected.getAmount() + Integer.parseInt(result.get()));
                    activePlayer.setCash(activePlayer.getCash() - (Integer.parseInt(result.get()) * selected.getValue()));
                    activePlayer.getActiveRegion().getDrugs().add(selected);
                } else {
                    Alert ed = new Alert(Alert.AlertType.ERROR);
                    ed.setTitle("Fehler");
                    ed.setHeaderText("Mögliche Anzahl zu kaufender Drogen überschritten.");
                    ed.showAndWait();

                }

                marketTable.getSelectionModel().clearAndSelect(marketTable.getItems().indexOf(selected));

            });
            rescanLists();
            updateFields();

        }
    }

    @FXML
    void sellBtnAction(ActionEvent event) {

        Drug selected = pocketTable.getSelectionModel().getSelectedItem();

        if(selected != null) {
            TextInputDialog dialog = new TextInputDialog("10");
            dialog.setTitle("Verkaufen");
            dialog.setHeaderText("Bitte die gewünschte Menge " + selected.getName().getValue() + " eingeben:");
            dialog.setContentText("Menge");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {

                activePlayer.getActiveRegion().getDrugs().remove(selected);
                selected.setAmount(selected.getAmount() - Integer.parseInt(result.get()));
                activePlayer.setCash(activePlayer.getCash() + (Integer.parseInt(result.get()) * selected.getValue()));
                activePlayer.getActiveRegion().getDrugs().add(selected);
                pocketTable.getSelectionModel().clearAndSelect(pocketTable.getItems().indexOf(selected));

            });
            rescanLists();
            updateFields();
        }
    }

    @FXML
    void dropBtnAction(ActionEvent event) {
        Drug selected = pocketTable.getSelectionModel().getSelectedItem();

        if(selected != null) {
            TextInputDialog dialog = new TextInputDialog("10");
            dialog.setTitle("Wegschmeißen");
            dialog.setHeaderText("Bitte die gewünschte Menge " + selected.getName() + " eingeben:");
            dialog.setContentText("Menge");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {

                activePlayer.getActiveRegion().getDrugs().remove(selected);
                selected.setAmount(selected.getAmount() - Integer.parseInt(result.get()));
                activePlayer.getActiveRegion().getDrugs().add(selected);
                pocketTable.getSelectionModel().clearAndSelect(pocketTable.getItems().indexOf(selected));

            });
            rescanLists();
            updateFields();
        }
    }

    private void updateFields() {
        regionField.setText(activePlayer.getActiveRegion().getName());
        cashField.setText(f.format(activePlayer.getCash()));
        debtField.setText(f.format(activePlayer.getDept()));
        healthField.setText(f.format(activePlayer.getHp()));
        agilityField.setText(f.format(activePlayer.getAgility()));
        dmgField.setText(f.format(activePlayer.getDmg()));
        activePlayer.initFreeSpace(activePlayer.getActiveRegion().getDrugs());
        spaceField.setText(Integer.toString(activePlayer.getFreeSpace()));
        bankField.setText(f.format(activePlayer.getBalance()));
    }

    private void initRegions() {
        Region kreuzberg = new Region("Kreuzberg");
        Region fhain = new Region("Friedrichshain");
        Region wedding = new Region("Wedding");
        Region neukoelln = new Region("Neu-Kölln");
        Region charlotten = new Region("Charlottenburg");
        Region mitte = new Region("Berlin-Mitte");
        Region reinicken = new Region("Reinickendorf");

        Region.regions.add(kreuzberg);
        Region.regions.add(fhain);
        Region.regions.add(wedding);
        Region.regions.add(neukoelln);
        Region.regions.add(charlotten);
        Region.regions.add(mitte);
        Region.regions.add(reinicken);
        for(Region r : Region.regions) {
            r.setDrugs(newOffers);
        }
    }

    private void initDrugs() {
        Drug lsd = new Drug(new SimpleStringProperty("Lysergsäurediethylamid"), 1000, 100000);
        Drug kokain = new Drug(new SimpleStringProperty("Kokain"), 25, 150);
        Drug heroin3 = new Drug(new SimpleStringProperty("Heroin#3"), 10, 100);
        Drug cannabis = new Drug(new SimpleStringProperty("Cannabis"), 2, 20);
        Drug hash = new Drug(new SimpleStringProperty("Haschisch"), 1, 50);
        Drug mdma = new Drug(new SimpleStringProperty("MDMA"), 15, 85);
        Drug gbl = new Drug(new SimpleStringProperty("Gammabutyrolacton"), 0.05, 30);
        Drug speed = new Drug(new SimpleStringProperty("Amphetamin (Speed, Pep)"), 0.5, 30);
        Drug crystal = new Drug(new SimpleStringProperty("Methamphetamin (Crystal, Meth)"), 25, 125);
        Drug cb2 = new Drug(new SimpleStringProperty("4-Brom-2,5-dimethoxyphenylehthylamin (2-CB)"), 20, 200);
        Drug methadon = new Drug(new SimpleStringProperty("Methadon"), 2.5, 35);
        Drug polamidon = new Drug(new SimpleStringProperty("Polamidon"), 5, 70);

        newOffers.add(lsd);
        newOffers.add(kokain);
        newOffers.add(heroin3);
        newOffers.add(cannabis);
        newOffers.add(hash);
        newOffers.add(mdma);
        newOffers.add(gbl);
        newOffers.add(speed);
        newOffers.add(crystal);
        newOffers.add(cb2);
        newOffers.add(methadon);
        newOffers.add(polamidon);
    }

    private void chooseRegion() {
        String[] regions = new String[Region.regions.size()];
        int i = 0;
        for(Region r : Region.regions) {
            regions[i] = r.getName();
            i++;
        }
        ChoiceDialog dialog = new ChoiceDialog(regions[0], regions);
        dialog.setTitle("Jet!");
        dialog.setHeaderText("Bitte Ziel auswählen:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            for(Region r : Region.regions) {
                if(r.getName() == name) {
                    activePlayer.setActiveRegion(r);
                    break;
                }
            }
            for(Drug b : activePlayer.getActiveRegion().getDrugs()) {
                b.generateNewValue();
            }

        });
        rescanLists();
        updateFields();

    }

    /**
     * 
     * @param days -> hier bitte den dayCounter übergeben, bzw. die bisherig Anzahl der gespielten Tage
     * @param playTime -> hier bitte die maximale Spieldauer angeben
     * @return -> true bedeutet dass die maximale Spielzeit erreicht ist
     */
    private boolean checkPlayTime(int days, int playTime) {
        return days <= playTime;
    }

    private void createTextInputDialog(String tidValue, String tidTitle, String tidHeader, String tidContent, int optionCase) {
        TextInputDialog tid = new TextInputDialog(tidValue);
        tid.setTitle(tidTitle);
        tid.setHeaderText(tidHeader);
        tid.setContentText(tidContent);
        Optional<String> response = tid.showAndWait();
        switch(optionCase) {
            case 0:
                response.ifPresent((name -> {
                    this.playerName = name.trim();
                }));
                break;
            case 1:
                response.ifPresent((hp -> {
                    this.temp = hp;
                    this.optionHp = Double.valueOf(this.temp);
                }));
                break;
            case 2:
                response.ifPresent((agility -> {
                    this.temp = agility;
                    this.optionAgility = Double.valueOf(this.temp);
                }));
                break;
            case 3:
                response.ifPresent((cash -> {
                    this.temp = cash;
                    this.optionCash = Double.valueOf(this.temp);
                }));
                break;
            default:
                System.exit(0);
        }
        if(!response.isPresent()) {
            System.exit(0);
        }
    }

    private boolean createTextInputDialog4Options(String tidTitle, String tidHeader) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(tidTitle);
        alert.setHeaderText(tidHeader);
        alert.setContentText("Möchtest du mit den Standartwerten starten oder diese beliebig anpassen?");

        ButtonType buttonTypeOne = new ButtonType("Standartwerte");
        ButtonType buttonTypeTwo = new ButtonType("Anpassen");
        ButtonType buttonTypeCancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> response = alert.showAndWait();

        if(response.get() == buttonTypeOne) {
            return false;
        } else if(response.get() == buttonTypeTwo) {
            return true;
        } else if(response.get() == buttonTypeCancel) {
            System.exit(0);
        }
        return false;
    }

    private void showEndGameDialog() {
        
    }
    
}
