/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Marc Zicchino
 */
public class MainFXMLController implements Initializable {

    @FXML private TableView<Drug> marketTable;                  // Tabelle links für den Marktplatz
    @FXML private TableView<Drug> pocketTable;                  // Tabelle rechts für das eigene Material
    @FXML private TableColumn<Drug, String> countRow;           // Spalte in Tabelle rechts für die Anzahl der Droge
    @FXML private TableColumn<Drug, String> priceRow;           // Spalte in Tabelle links für den Preis der Droge pro Gramm
    @FXML private TableColumn<Drug, String> namePocketRow;      // Spalte in Tabelle rechts für den Namen der Droge
    @FXML private TableColumn<Drug, String> nameMarketRow;      // Spalte in Tabelle links für den Namen der Droge
    @FXML private TextArea logField;        // Anzeigetafel in der Mitte
    @FXML private Label regionField;        // Standort
    @FXML private Label debtField;          // Schulden
    @FXML private Label cashField;          // Momentaner Spielgeldbetrag
    @FXML private Label spaceField;         // Noch freier Platz für Drogen
    @FXML private Label healthField;        // Verbleibende Lebenspunkte
    @FXML private Label dmgField;           // Gesamtschaden der Waffen
    @FXML private Label agilityField;       // Treffsicherheit
    @FXML private Label dateField;          // Datumsausgabe
    @FXML private Label bankField;          // Bankguthaben
    

    private ObservableList<Drug> newOffers;
    private Random rnd;
    private Player activePlayer;
    private LocalDate date;
    private String playerName = "Spieler";
    private int dayCounter = 0;
    
    @FXML
    private void jetBtnAction(ActionEvent event) {
        
        activePlayer.setActiveRegion(chooseRegion());
        
        for(Drug b : activePlayer.getActiveRegion().getDrugs()) {
            b.generateNewValue();
        }
        
        nameMarketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        priceRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        marketTable.setItems(activePlayer.getActiveRegion().getDrugs());
        namePocketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        countRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getAmount())));
        
        regionField.setText(activePlayer.getActiveRegion().getName());
        bankField.setText(String.valueOf(activePlayer.getCash()));
        cashField.setText(Double.toString(activePlayer.getCash()));
        debtField.setText(Double.toString(activePlayer.getDept()));
        healthField.setText(Double.toString(activePlayer.getHp()));
        agilityField.setText(Double.toString(activePlayer.getAgility()));
        dmgField.setText(Double.toString(activePlayer.getDmg()));
        spaceField.setText(Integer.toString(activePlayer.getFreeSpace()));
        dateField.setText(date.toString());
        date.plusDays(1);
        activePlayer.setPlayTime(dayCounter);        
        dayCounter++;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TextInputDialog tid = new TextInputDialog("Spieler");
        tid.setTitle("Dopewars");
        tid.setHeaderText("Willkommen bei Dopewars!");
        tid.setContentText("Bitte gib deinen Namen ein:");
        Optional<String> response = tid.showAndWait();
        response.ifPresent((name -> {
            playerName = name;
        }));
        if(!response.isPresent()) {
            System.exit(0);
        }
        
        newOffers = FXCollections.observableArrayList();
        date = LocalDate.now();
        activePlayer = new Player(playerName, 100, 5000, 0.05);
        rnd = new Random();
        
        initDrugs();
        initRegions();
        
        activePlayer.setActiveRegion(Region.regions.get(rnd.nextInt(Region.regions.size() - 1)));
        System.out.println("Set Region to " + activePlayer.getActiveRegion().getName());
        
        for(Drug b : activePlayer.getActiveRegion().getDrugs()) {
            b.generateNewValue();
            System.out.println("GEN " + b.getName() + ": " + b.getValue());
        }
        
        nameMarketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        priceRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        marketTable.setItems(activePlayer.getActiveRegion().getDrugs());
        namePocketRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> param.getValue().getName());
        countRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getAmount())));
        
        regionField.setText(activePlayer.getActiveRegion().getName());
        bankField.setText(String.valueOf(activePlayer.getCash()));
        cashField.setText(Double.toString(activePlayer.getCash()));
        debtField.setText(Double.toString(activePlayer.getDept()));
        healthField.setText(Double.toString(activePlayer.getHp()));
        agilityField.setText(Double.toString(activePlayer.getAgility()));
        dmgField.setText(Double.toString(activePlayer.getDmg()));
        spaceField.setText(Integer.toString(activePlayer.getFreeSpace()));
        dateField.setText(date.toString());
        
        logField.setText("Start Game with Player: " + activePlayer.getName() + "\nHP: " + activePlayer.getHp());
        
        tid.setResultConverter(Btn -> {
            if(Btn == ButtonType.CANCEL) {
                System.exit(0);
                return null;
            }
            return tid.getResult();
        });
        
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
    
    private double randomDoubleMinMax(double a, double b) {
        if(a < b) {
            return (b - a) * this.rnd.nextDouble() + a;
        }
        return (a - b) * this.rnd.nextDouble() + b;
    }
    
    private int randomIntMinMax(int a, int b) {
        if(a < b) {
            return (int) ((b - a) * this.rnd.nextDouble() + a);
        }
        return (int) ((a - b) * this.rnd.nextDouble() + b);
    }
    
    private Region chooseRegion() {
        return Region.regions.get(rnd.nextInt(Region.regions.size()));
    }

    private LocalDate Date() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
