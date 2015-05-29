/* Ideas Engineers
 *  All rights by Marc Zicchino
 */

package com.ideasengineers;

/**
 *
 * @author Marc Zicchino
 * 
 * Waffenklasse
 * 
 */
public class Gun {

    private String name = "default";
    private int id = 0;
    private double price = 0;
    private double dmg = 0;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getDmg() {
        return dmg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }
    
    

}
