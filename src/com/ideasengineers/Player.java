/* Ideas Engineers
 *  All rights by Marc Zicchino
 */

package com.ideasengineers;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Marc Zicchino
 */
public class Player {

    private String name = "default";
    private double hp = 100;
    private double cash = 5000;
    private double dmg = 0;
    private double agility = 0.5;
    private int maxGuns = 6;        // maximale Anzahl an Waffen
    private int maxDrugs = 100;     // maximale Anzahl an Drogen
    private int region = 0;
    private Gun[] gunPocket = new Gun[maxGuns];
    private Drug[] drugPocket = new Drug[maxDrugs];

    public void Player(String name, double hp, double cash, double agility) {
        this.name = name;
        this.hp = hp;
        this.cash = cash;
        this.agility = agility;
    }
    
    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public double getCash() {
        return cash;
    }

    public double getDmg() {
        return dmg;
    }

    public double getAgility() {
        return agility;
    }

    public Gun[] getGunPocket() {
        return gunPocket;
    }

    public Drug[] getDrugPocket() {
        return drugPocket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setGunPocket(Gun[] gunPocket) {
        this.gunPocket = gunPocket;
    }

    public void setDrugPocket(Drug[] drogPocket) {
        this.drugPocket = drogPocket;
    }

    public int getMaxGuns() {
        return maxGuns;
    }

    public int getMaxDrugs() {
        return maxDrugs;
    }

    public void setMaxGuns(int maxGuns) {
        this.maxGuns = maxGuns;
    }

    public void setMaxDrugs(int maxDrugs) {
        this.maxDrugs = maxDrugs;
    }
    
    
    

}
