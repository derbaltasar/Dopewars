/* Ideas Engineers
 *  All rights by Marc Zicchino
 */

package com.ideasengineers;

import javafx.collections.ObservableList;

/**
 *
 * @author Marc Zicchino
 */
public class Player {

    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Variablen +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    private String name;
    private double hp;          // Lebenspunkte
    private double cash;        // Guthaben
    private double balance;     // Bankguthaben
    private double dept;        // Schulden
    private double dmg;         // Schaden den man mit den momentan zur Verfügung stehenden Waffen anrichtet
    private double agility;     // Dmg Multiplikator
    private int playTime;       // Spielzeit in Tagen
    private int maxGuns;        // maximale Anzahl an Waffen
    private int maxDrugs;       // maximale Anzahl an Drogen
    private final int existingDrugs = 12;
    private Region activeRegion;
    private Gun[] gunPocket = new Gun[maxGuns];
    private int minusSpace = 0;
    private int freeSpace = 100;
    private double minAccountValue = 0;
    private ObservableList<Drug> drugPocket;

    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Konstruktoren +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    /**
     * Konstruktor 1
     * ohne Parameter
     */
    public Player() {
        this.name = "default";
        this.hp = 100;
        this.cash = 5000;
        this.agility = 0.05;
        this.dept = cash;
        this.playTime = 30;
        this.maxGuns = 6;
        this.maxDrugs = 100;
        this.freeSpace = maxDrugs;
        this.balance = 0.0;
    }
    
    /**
     * Konstruktor 2
     * @param name -> Name des Spielers
     * @param hp -> Lebenspunkte
     * @param cash -> Bargeld
     * @param agility -> Trefferwertung
     */
    public Player(String name, double hp, double cash, double agility) {
        this.name = name;
        this.hp = hp;
        this.cash = cash;
        this.agility = agility;
        this.dept = cash;
        this.playTime = 30;
        this.maxGuns = 6;
        this.maxDrugs = 100;
        this.freeSpace = maxDrugs;
        this.balance = 0.0;
    }
    
    /**
     * Konstruktor 3
     * @param name -> Name des Spielers
     * @param hp -> Lebenspunkte
     * @param cash -> Bargeld
     * @param agility -> Trefferwertung
     * @param days -> Spielzeit in Tagen oder auch Runden
     */
    public Player(String name, double hp, double cash, double agility, int days) {
        this.name = name;
        this.hp = hp;
        this.cash = cash;
        this.agility = agility;
        this.dept = cash;
        this.playTime = days;
        this.maxGuns = 6;
        this.maxDrugs = 100;
        this.freeSpace = maxDrugs;
        this.balance = 0.0;
    }
    
    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Methoden +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
    public void initFreeSpace(ObservableList<Drug> drugPocket) {
        this.drugPocket = drugPocket;
        for(Drug drug : drugPocket) {
            this.minusSpace += drug.getAmount();
        }
        this.freeSpace = 100 - this.minusSpace;
        System.out.println(this.freeSpace);
    }
    
    public int spaceVolume() {
        int a = 0;
        for(Drug drug : drugPocket) {
            a += drug.getAmount();
        }
        return a;
    }
    
    //+#+#+#+#+#+#+#+#+#+#+#+#+#+#+ Getter und Setter +#+#+#+#+#+#+#+#+#+#+#+#+#+#+
    
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

    public Region getActiveRegion() {
        return activeRegion;
    }

    public void setActiveRegion(Region activeRegion) {
        this.activeRegion = activeRegion;
    }

    public double getDept() {
        return dept;
    }

    public void setDept(double dept) {
        this.dept = dept;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public ObservableList<Drug> getDrugPocket() {
        return drugPocket;
    }

    public void setDrugPocket(ObservableList<Drug> drugPocket) {
        this.drugPocket = drugPocket;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }    
    
}