/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import java.net.URL;
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

    private Drug[] newOffers = new Drug[1];
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double rnd = Math.random();
        initRegions();
        initDrugs();
        initRnd(rnd);
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
    }
    
    private void initDrugs() {
        Drug lsd = new Drug();
        Drug kokain = new Drug();
        Drug heroin = new Drug();
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
        heroin.setName("Heroin#3");
        cannabis.setName("Cannabis");
        hash.setName("Haschisch");
        mdma.setName("MDMA");
        gbl.setName("Gammabutyrolacton");
        speed.setName("Amphetamin (Speed, Pep)");
        crystal.setName("Methamphetamin (Crystal, Meth)");
        cb2.setName("4-Brom-2,5-dimethoxyphenylehthylamin (2-CB)");
        methadon.setName("Methadon");
        polamidon.setName("Polamidon");
    }
    
    private void initRnd(Double rnd) {
        if(rnd >= 0 && rnd < 0.25) {
            
        }
    }
    
    private double randomValue(int a, int b) {
        return 0.0;
    }
    
    private int randomIntMinMax(int a, int b) {
        return 1;
    }
}
