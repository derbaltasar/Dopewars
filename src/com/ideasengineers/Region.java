package com.ideasengineers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philipp
 */
public class Region {
    
    private String name = "default";
    private Integer id = 0;
    public ArrayList<Drug> drugs = new ArrayList<>();
    

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
}
    