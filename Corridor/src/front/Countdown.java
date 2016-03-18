/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import front.control.GameField;


/**
 *
 * @author Péter
 */
public class Countdown extends Thread {

    private GameField control;
    private boolean usedaction;
    
    public Countdown(GameField gf) {
        this.control = gf;
    }

    @Override
    public void run() {
        int i=10;
        usedaction=false;
        while (i >= 0 && !usedaction) {
            if (i == 0) {
                this.control.switchToNextPlayer();
            }
            
            try {
                //System.out.println(i);
                control.UpdateCountDown(i);
                i--;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }
    
    public void setusedaction(){
        usedaction=true;
    }

}
