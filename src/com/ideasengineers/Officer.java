/* Ideas Engineers
 *  All rights by Marc Zicchino
 */

package com.ideasengineers;

import java.util.Random;

/**
 *
 * @author Marc Zicchino
 */
public class Officer {

    private Random rnd;
    private String name;
    private double hp;
    private double agility;
    private double dmg;
    private int officerKey;

    /**
     * Konstruktor 1
     * wird dieser benutzt, wird immer der gleich Bulle erzeugt
     */
    public Officer() {
        this.rnd = new Random();
        this.name = "Police Officer";
        this.hp = calcHp();
        this.agility = rnd.nextDouble();
        this.dmg = this.agility * 10;
    }
    
    /**
     * Konstruktor 2
     * @param name -> ein String-Array mit Namen für Polizisten, muss genau 5 verschiedene Namen enthalten
     * @param hp -> hier können die Lebenspunkte festgelegt werden, welches sich aber je nach Zufall noch erhöhen oder senken kann
     * @param agility -> hier wird die Trefferwertung festgelegt, welche sich auch wieder durch Zufall erhöhen kann
     * @param dmg -> der Schadenswert, den der Polizist verursacht, verändert sich auch wieder durch Zufall
     */
    public Officer(String[] name, double hp, double agility, double dmg) {
        this.rnd = new Random();
        this.officerKey = this.officerNameChance();
        this.name = name[officerKey];
        this.hp = calcHp(officerKey);
        if(officerKey >= 0) {
            this.agility = agility * officerKey;
            this.dmg = dmg * this.agility * officerKey;
        } else {
            this.agility = agility;
            this.dmg = dmg * this.agility;
        }
    }    
    
    /**
     * 
     * @return 0 - 4
     * 
     * je höher die Zahl (0-4), desdo unwahrscheinlicher, dass sie returned wird
     */
    private int officerNameChance() {
        this.rnd = new Random();
        int a = rnd.nextInt(100);
        if(a >= 0 && a < 50) {
            return 0;
        } else if(a >= 50 && a < 75) {
            return 1;
        } else if(a >= 75 && a < 90) {
            return 2;
        } else if(a >= 90 && a < 97) {
            return 3;
        }
        return 4;        
    }
    
    /**
     * 
     * @return 
     * Zufallszahl zwischen 0 und 1, liegt diese bei 0.5 oder darunter, wird true ausgegeben,
     * liegt sie darüber, wird false ausgegeben
     */
    private boolean fiftyFifty() {
        double a = this.rnd.nextDouble();
        return a >= 0.5;
    }
    
    /**
     * 
     * @return 
     * 
     * Kalkuliert eine Lebenspunktezahl zwischen 50 und 150
     * 
     */
    private double calcHp() {
        if(fiftyFifty()) {
            return 100 + this.rnd.nextDouble() * 50;
        }
        return 100 - this.rnd.nextDouble() * 50;
    }
    
    /**
     * 
     * @param level -> hier sollte der key des Bullen-Namen-Arrays übergeben werden
     * @return 
     * 
     * kalkuliert Lebenspunktewert, je höher der Parameter level, desdo höher die Lebenspunkte
     */
    private double calcHp(int level) {        
        switch(level) {
            case 0:
                return (100 + this.rnd.nextDouble() * 50);
            case 1:
                return (100 + this.rnd.nextDouble() * 100);
            case 2:
                return (100 + this.rnd.nextDouble() * 150);
            case 3:
                return (100 + this.rnd.nextDouble() * 200);
        }
        return (100 + this.rnd.nextDouble() * 250);
    }
    
}
