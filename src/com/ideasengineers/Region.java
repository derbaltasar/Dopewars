package com.ideasengineers;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author philipp
 */
public class Region {
    
    private String name = "default";
    private ObservableList<Drug> drugs = FXCollections.observableArrayList();
    public static ArrayList<Region> regions = new ArrayList<>();

    public Region(String name) {
        this.name = name;
    }
    
    public ObservableList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ObservableList<Drug> drugs) {
        this.drugs = drugs;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void randomEvent() {
                
    }
    
}
    