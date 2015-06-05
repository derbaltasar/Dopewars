package com.ideasengineers;

import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marc Zicchino
 */
public class Drug {
    
    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Variablen +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    private static Random rnd = new Random();
    private StringProperty name;
    private double min;                         // minimaler VK
    private double max;                         // maximaler VK
    private double reference;                   // Richtpreis    
    private double value;                       // aktueller Verkaufswert
    private boolean available;
    private Integer amount;
    private String[] logs;

    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Konstruktoren +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    /**
     * Konstruktor 1
     * ohne Parameter
     */
    public Drug() {
        this.name = new SimpleStringProperty("default");
        this.min = 0.0;
        this.max = 0.0;
        this.reference = (min + max) / 2;
        this.value = generateDrugPrice(reference);
        this.available = true;
        this.amount = 0;
    }
    
    /**
     * Konstruktor 2
     * @param name -> Name der Droge
     * @param min -> mindest Preis pro Gramm
     * @param max -> höchster Preis pro Gramm
     */
    public Drug(StringProperty name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.reference = (min + max) / 2;
        this.value = generateDrugPrice(this.reference);
        this.available = true;
        this.amount = 0;
    }
    
    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Methoden +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    public void generateNewValue() {
        this.value = generateDrugPrice(this.reference);
        this.available = rnd.nextBoolean();
        //checkDrugPriceToMessage();
    }
    
    /**
     * 
     * @param reference -> Richtwert der Droge, welcher die Mitte von min und max ist
     * @return -> Drogenpreis
     * 
     * erzeugt Drogenpreise nach Wahrscheinlichkeiten...
     */
    private double generateDrugPrice(Double reference) {
        
        double decisionValue = rnd.nextDouble();
        
        if(decisionValue > 0 && decisionValue <= 0.3) {
            return (reference - (reference / 300 * decisionValue));
        } else if(decisionValue > 0.3 && decisionValue <= 0.6) {
            return (reference + (reference / 600 * decisionValue));
        } else if(decisionValue > 0.6 && decisionValue <= 0.7) {
            return (reference - (reference / 70 * decisionValue));
        } else if(decisionValue > 0.7 && decisionValue <= 0.8) {
            return (reference + (reference / 80 * decisionValue));
        } else if(decisionValue > 0.8 && decisionValue <= 0.85) {
            return (reference - (reference * rnd.nextDouble()));
        } else if(decisionValue > 0.85 && decisionValue <= 0.9) {
            return (reference - (reference * rnd.nextDouble()));
        } else if(decisionValue > 0.9 && decisionValue <= 0.95) {
            return (reference + (reference * (rnd.nextDouble() + 1)));
        } else {
            return (reference + (reference * (rnd.nextDouble() + 1)));
        }
        
    }
    
    private void checkDrugPriceToMessage() {
        if(this.value > this.value * 1.5) {
            logs[0] = "Die Bullen haben einen Haufen " + this.name + " sichergestellt. " + this.name + " ist teuer wie noch nie!";
        }
    }
    
    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Getter und Setter +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }    

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getReference() {
        return reference;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }

    
}
