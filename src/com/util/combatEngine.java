package com.util;

import java.util.Random;

public class combatEngine {
    //function to generate random number
    private int randomNumber(int number){
        Random random = new Random();
        number = random.nextInt(10);
        return number;
    }
}
