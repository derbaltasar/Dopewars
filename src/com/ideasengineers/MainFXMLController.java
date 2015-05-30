/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author Marc Zicchino
 */
public class MainFXMLController implements Initializable {

    @FXML private TableColumn<Drug, String> priceRow;
    @FXML private Label bankField;
    @FXML private TableColumn<Drug, String> nameMarketRow;
    @FXML private TableView<Drug> marketTable;
    @FXML private Label regionField;
    @FXML private Label debtField;
    @FXML private Label cashField;
    @FXML private TextArea logField;
    @FXML private TableView<Drug> pocketTable;
    @FXML private TableColumn<Drug, String> countRow;
    @FXML private TableColumn<Drug, String> namePocketRow;
    @FXML private Label spaceField;         // Noch freier Platz für Drogen
    @FXML private Label healthField;        // Verbleibende Lebenspunkte
    @FXML private Label dmgField;           // Gesamtschaden der Waffen
    @FXML private Label agilityField;       // Treffsicherheit

    private ObservableList<Drug> newOffers = FXCollections.observableArrayList();
    private Random rnd = new Random();
    private Player activePlayer;
    
    @FXML
    private void jetBtnAction(ActionEvent event) {
        
        activePlayer.setActiveRegion(chooseRegion());
        
        for(Drug b : activePlayer.getActiveRegion().getDrugs()) {
            b.generateNewValue();
        }
        
        regionField.setText(activePlayer.getActiveRegion().getName());
        bankField.setText(String.valueOf(activePlayer.getCash()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        activePlayer = new Player();
        Random rnd = new Random();
        
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
        countRow.setCellValueFactory((CellDataFeatures<Drug, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getCount())));
        pocketTable.setItems(activePlayer.getDrugPocket());
        
        regionField.setText(activePlayer.getActiveRegion().getName());
        bankField.setText(String.valueOf(activePlayer.getCash()));
    }
    
    private void initRegions() {
        Region kreuzberg = new Region();
        Region fhain = new Region();
        Region wedding = new Region();
        Region neukoelln = new Region();
        Region charlotten = new Region();
        Region mitte = new Region();
        Region reinicken = new Region();
        kreuzberg.setName("Kreuzberg");
        fhain.setName("Friedrichshain");
        wedding.setName("Wedding");
        neukoelln.setName("Neu-Kölln");
        charlotten.setName("Charlottenburg");
        mitte.setName("Berlin-Mitte");
        reinicken.setName("Reinickendorf");
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
        Drug lsd = new Drug();
        Drug kokain = new Drug();
        Drug heroin3 = new Drug();
        Drug cannabis = new Drug();
        Drug hash = new Drug();
        Drug mdma = new Drug();
        Drug gbl = new Drug();
        Drug speed = new Drug();
        Drug crystal = new Drug();
        Drug cb2 = new Drug();
        Drug methadon = new Drug();
        Drug polamidon = new Drug();
        lsd.setName(new SimpleStringProperty("Lysergsäurediethylamid"));
        lsd.setMin(1000);
        lsd.setMax(100000);
        kokain.setName(new SimpleStringProperty("Kokain"));
        kokain.setMin(25);
        kokain.setMax(150);
        heroin3.setName(new SimpleStringProperty("Heroin#3"));
        heroin3.setMin(10);
        heroin3.setMax(100);
        cannabis.setName(new SimpleStringProperty("Cannabis"));
        cannabis.setMin(2);
        cannabis.setMax(20);
        hash.setName(new SimpleStringProperty("Haschisch"));
        hash.setMin(1);
        hash.setMax(50);
        mdma.setName(new SimpleStringProperty("MDMA"));
        mdma.setMin(15);
        mdma.setMax(85);
        gbl.setName(new SimpleStringProperty("Gammabutyrolacton"));
        gbl.setMin(0.05);
        gbl.setMax(3);
        speed.setName(new SimpleStringProperty("Amphetamin (Speed, Pep)"));
        speed.setMin(0.5);
        speed.setMax(30);
        crystal.setName(new SimpleStringProperty("Methamphetamin (Crystal, Meth)"));
        crystal.setMin(25);
        crystal.setMax(125);
        cb2.setName(new SimpleStringProperty("4-Brom-2,5-dimethoxyphenylehthylamin (2-CB)"));
        cb2.setMin(20);
        cb2.setMax(200);
        methadon.setName(new SimpleStringProperty("Methadon"));
        methadon.setMin(2.5);
        methadon.setMax(35);
        polamidon.setName(new SimpleStringProperty("Polamidon"));
        polamidon.setMin(5);
        polamidon.setMax(70);
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
            return (b - a) * Math.random() + a;
        }
        return (a - b) * Math.random() + b;
    }
    
    private int randomIntMinMax(int a, int b) {
        if(a < b) {
            return (int) ((b - a) * Math.random() + a);
        }
        return (int) ((a - b) * Math.random() + b);
    }
    
    private Region chooseRegion() {
        return Region.regions.get(rnd.nextInt(Region.regions.size()));
    }
    
}
