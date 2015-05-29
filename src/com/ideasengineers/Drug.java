package com.ideasengineers;

/**
 *
 * @author philipp
 */
public class Drug {
    
    private String name = "default";
    private Integer id = 0;
    private double value = 0.0;
    private double min = 0.0;
    private double max = 0.0;
    private double reference = 0.0;

    public Drug() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        
    }
    
}
