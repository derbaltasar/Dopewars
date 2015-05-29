package com.ideasengineers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philipp
 */
public class Region {
    
    private String name = "default";
    public ArrayList<Drug> drugs = new ArrayList<>();
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    
}
    