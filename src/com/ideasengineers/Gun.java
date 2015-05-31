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

    private String name;
    private int id;
    private double price;
    private double dmg;

    /**
     * Konstruktor 1
     * 
     * @param name -> Name der Waffe
     * @param id -> id der Waffe
     * @param price -> Preis
     * @param dmg -> Schaden den die Waffe verursacht
     */
    public Gun(String name, int id, double price, double dmg) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.dmg = dmg;
    }
    
    /**
     * Konstruktor 2
     */
    public Gun() {
        this.name = "default";
        this.id = 0;
        this.price = 0;
        this.dmg = 0;
    }
    
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
