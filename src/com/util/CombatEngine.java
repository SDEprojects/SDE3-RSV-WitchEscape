package com.util;

import java.awt.*;
import java.util.Random;
import com.game.TheWorldInteraction;
import com.gamewindow.Gui;
import com.gamewindow.Gui;

import javax.swing.*;

import static com.gamewindow.Gui.setMessage;

public class CombatEngine {
    private static final int HAMMER_INDEX = 8;
    private static final int FRYING_PAN = 5;
    private static int destructiveIndex = 0;
    private static String winner = "";
    //function to generate random number
    private static int randomNumber(int number){
        Random random = new Random();
        number = random.nextInt(10);
        return number;
    }

    private static void showDialogue(){
        if (winner.equals("player"))   {
            JOptionPane.showMessageDialog(Gui.gameWindow,"You have destroyed your enemy");
        }else{
            JOptionPane.showMessageDialog(Gui.gameWindow," You are killed ! \n Game OVer!");
            System.exit(0);
        }

    }

    private static String selectWeaponDialogue(String message){

        String weaponSelected= JOptionPane.showInputDialog(Gui.gameWindow, message ).toLowerCase();
        return weaponSelected;
    }

    // function to damage enemy based on scale 1 to 10
    public static String winner() {

        String selectWeapon1;
        String selectWeapon = selectWeaponDialogue("Select the weapons from " + TheWorldInteraction.inventory);
        if (TheWorldInteraction.inventory.size()==2) {
            while(destructiveIndex == 0){
                if (selectWeapon.equals("frying pan")){
                    destructiveIndex = FRYING_PAN;
                    TheWorldInteraction.inventory.remove("frying pan");
                    selectWeapon1= selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + TheWorldInteraction.inventory);
                    if (selectWeapon1.equals("hammer")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue();
                    }
                }else if(selectWeapon.equals("hammer")){
                    destructiveIndex = HAMMER_INDEX;
                    TheWorldInteraction.inventory.remove("hammer");
                    selectWeapon1=selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + TheWorldInteraction.inventory);
                    destructiveIndex=10;
                    winner = "player";
                    showDialogue();
                }
            }
//            winner="enemy";
//            showDialogue();
        } else {
            destructiveIndex = randomNumber(10);
            if (destructiveIndex<2){
                winner = "Player";
                showDialogue();
            }
            winner ="enemy";
            showDialogue();
        }
        return winner;
    }

}
