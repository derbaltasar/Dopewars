/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 *
 * @author Marc Zicchino
 */
public class MainFXMLController implements Initializable {

    @FXML private TableColumn<?, ?> priceRow;
    @FXML private Label bankField;
    @FXML private TableColumn<?, ?> nameMarketRow;
    @FXML private TableView<?> marketTable;
    @FXML private Label regionField;
    @FXML private Label debtField;
    @FXML private Label cashField;
    @FXML private TextArea logField;
    @FXML private TableView<?> pocketTable;
    @FXML private TableColumn<?, ?> countRow;
    @FXML private TableColumn<?, ?> namePocketRow;
    @FXML private Label spaceField;         // Noch freier Platz für Drogen
    @FXML private Label healthField;        // Verbleibende Lebenspunkte
    @FXML private Label dmgField;           // Gesamtschaden der Waffen
    @FXML private Label agilityField;       // Treffsicherheit

    private ArrayList<Drug> newOffers = new ArrayList<>();
    private Random rnd = new Random();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
         
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initDrugs();
        initRegions();
        
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
        lsd.setName("Lysergsäurediethylamid");
        lsd.setMin(1000);
        lsd.setMax(100000);
        kokain.setName("Kokain");
        kokain.setMin(25);
        kokain.setMax(150);
        heroin3.setName("Heroin#3");
        heroin3.setMin(10);
        heroin3.setMax(100);
        cannabis.setName("Cannabis");
        cannabis.setMin(2);
        cannabis.setMax(20);
        hash.setName("Haschisch");
        hash.setMin(1);
        hash.setMax(50);
        mdma.setName("MDMA");
        mdma.setMin(15);
        mdma.setMax(85);
        gbl.setName("Gammabutyrolacton");
        gbl.setMin(0.05);
        gbl.setMax(3);
        speed.setName("Amphetamin (Speed, Pep)");
        speed.setMin(0.5);
        speed.setMax(30);
        crystal.setName("Methamphetamin (Crystal, Meth)");
        crystal.setMin(25);
        crystal.setMax(125);
        cb2.setName("4-Brom-2,5-dimethoxyphenylehthylamin (2-CB)");
        cb2.setMin(20);
        cb2.setMax(200);
        methadon.setName("Methadon");
        methadon.setMin(2.5);
        methadon.setMax(35);
        polamidon.setName("Polamidon");
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
    
    
}
