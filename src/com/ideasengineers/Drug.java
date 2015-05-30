package com.ideasengineers;

import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author philipp
 */
public class Drug {
    
    private StringProperty name = new SimpleStringProperty("default");
    private double value = 1.0;     // aktueller Verkaufswert
    private double min = 0.0;       // minimaler VK
    private double max = 0.0;       // maximaler VK
    private double reference = (min + max) / 2; // Richtpreis
    private static Random rnd = new Random();
    private boolean available = true;
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Drug() {
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
    
    public void generateNewValue() {
        value = generateDrugPrice(reference);
        available = rnd.nextBoolean();
    }
    
    
    private static double generateDrugPrice(Double reference) {
        
        double decisionValue = rnd.nextDouble();
        
        if(decisionValue > 0 && decisionValue <= 0.3) {
            return (reference - (reference / 100 * decisionValue));
        } else if(decisionValue > 0.3 && decisionValue <= 0.6) {
            return (reference + (reference / 100 * decisionValue));
        } else if(decisionValue > 0.6 && decisionValue <= 0.7) {
            return (reference - (reference / 10 * decisionValue));
        } else if(decisionValue > 0.7 && decisionValue <= 0.8) {
            return (reference + (reference / 10 * decisionValue));
        } else if(decisionValue > 0.8 && decisionValue <= 0.85) {
            return (reference - (reference * decisionValue));
        } else if(decisionValue > 0.85 && decisionValue <= 0.9) {
            return (reference + (reference * decisionValue));
        } else if(decisionValue > 0.9 && decisionValue <= 0.95) {
            return (reference + (reference * (decisionValue + 1)));
        } else {
            return (reference - (reference * (decisionValue + 1)));
        }
        
    }
    
    private int randomIntMinMax(int a, int b) {
        if(a < b) {
            return (int) ((b - a) * Math.random() + a);
        }
        return (int) ((a - b) * Math.random() + b);
    }
    
}
