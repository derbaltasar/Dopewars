/* Ideas Engineers
 *  All rights by Marc Zicchino
 */

package com.ideasengineers;

/**
 *
 * @author Marc Zicchino
 */
public class Officer {

    private String name = "Police Officer";
    private double hp = calcHp();
    private double agility = Math.random();
    private double dmg = Math.random() * 10;

            
    private boolean fiftyFifty() {
        double a = Math.random();
        return a >= 0.5;
    }
    
    private double calcHp() {
        if(fiftyFifty()) {
            return 100 + Math.random() * 50;
        }
        return 100 - Math.random() * 50;
    }
}
