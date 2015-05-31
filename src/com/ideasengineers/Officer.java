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

    private Random rnd = new Random();
    private String name = "Police Officer";
    private double hp = calcHp();
    private double agility = rnd.nextInt();
    private double dmg = rnd.nextDouble() * 10;

            
    private boolean fiftyFifty() {
        double a = this.rnd.nextDouble();
        return a >= 0.5;
    }
    
    private double calcHp() {
        if(fiftyFifty()) {
            return 100 + this.rnd.nextDouble() * 50;
        }
        return 100 - this.rnd.nextDouble() * 50;
    }
}
