package com.util;


import java.util.Random;
import static com.game.TheWorldInteraction.inventory;
import javax.swing.*;
import com.gamewindow.Gui;
import static com.gamewindow.Gui.setMessage;

public class CombatEngine {
    //TheWorldInteraction theWorldInteraction = new TheWorldInteraction();

    private static final int HAMMER_INDEX = 8;
    private static final int FRYING_PAN = 5;
    private static final int FORK_INDEX = 5;
    private static int destructiveIndex = 0;
    private static String winner = "";
    //function to generate random number
    private static int randomNumber(int number){
        Random random = new Random();
        number = random.nextInt(10);
        return number;
    }

    private  static void showDialogue(){
        if (winner.equals("player"))   {
            JOptionPane.showMessageDialog(Gui.gameWindow,"You have destroyed your enemy");
            //theWorldInteraction.createCurrentRoom("pier");
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
    public  static String winner() {

        String selectWeapon1;
        String selectWeapon = selectWeaponDialogue("Select the weapons from " + inventory);
        if (inventory.size()>=2) {
            while(destructiveIndex == 0){
                if (selectWeapon.equals("frying pan")){
                    destructiveIndex = FRYING_PAN;
                    inventory.remove("frying pan");
                    selectWeapon1= selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("hammer") | selectWeapon1.equals("fork")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue();
                    }
                }else if(selectWeapon.equals("hammer")){
                    destructiveIndex = HAMMER_INDEX;
                    inventory.remove("hammer");
                    selectWeapon1=selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("frying pan")| selectWeapon1.equals("fork")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue();
                    }
                }else if(selectWeapon.equals("fork")){
                    destructiveIndex = FORK_INDEX;
                    inventory.remove("fork");
                    selectWeapon1 =selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("hammer") | selectWeapon1.equals("frying pan")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue();
                    }
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
