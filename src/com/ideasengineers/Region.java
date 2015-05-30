package com.ideasengineers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author philipp
 */
public class Region {
    
    private String name = "default";
    private ArrayList<Drug> drugs = new ArrayList<>();
    private Random rnd = new Random();

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }
    public static ArrayList<Region> regions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void randomEvent() {
        
        
    }
    
}
    