package com.gamecodeschool.expressingyourself;

/**
 * Created by BHAVIK PATEL on 13-May-17.
 */

public class Hospital {
    public void heal(Soldier patient ){
        int health = patient.getHealth();
        health = health + 10;
        patient.setHealth(health);
    }
    public int getHealth(Soldier patient){
        return patient.getHealth();
    }
}
