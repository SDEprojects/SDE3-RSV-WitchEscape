package com.util;


import java.util.Random;
import static com.game.TheWorldInteraction.inventory;
import javax.swing.*;
import com.gamewindow.Gui;
import static com.gamewindow.Gui.setMessage;

public class CombatEngine {
    //TheWorldInteraction theWorldInteraction = new TheWorldInteraction();

    private static int destructiveIndex = 0;
    private static String winner = "";
    //function to generate random number
    private static int randomNumber(int number){
        Random random = new Random();
        number = random.nextInt(10);
        return number;
    }

    private  static void showDialogue(String character){
        if (winner.equals("player"))   {
            JOptionPane.showMessageDialog(Gui.gameWindow,"You have Killed " + character);
            //theWorldInteraction.createCurrentRoom("pier");
        }else{
            JOptionPane.showMessageDialog(Gui.gameWindow,character+ " killed you! \n Game OVer!");
            System.exit(0);
        }

    }
    private static int weaponsDestructiveIndex(String weaponsName){
        int result=0;
        result=Integer.parseInt(XMLParser.itemsWithStatsMap.get(weaponsName));
        return result;
    }

    private static String selectWeaponDialogue(String message){

        String weaponSelected= JOptionPane.showInputDialog(Gui.gameWindow, message ).toLowerCase();
        return weaponSelected;
    }

    // function to damage enemy based on scale 1 to 10
//    public static String winner(){
//        String winner="";
//        while (destructiveIndex<10) {
//            String selectWeapon = selectWeaponDialogue("Select the weapons from " + inventory);
//            switch (selectWeapon) {
//                case "frying pan":
//                    destructiveIndex = weaponsDestructiveIndex("frying pan");
//                    inventory.remove("frying pan");
//                case "hammer":
//                    destructiveIndex = weaponsDestructiveIndex("hammer");
//                    inventory.remove("hammer");
//                case "fork":
//                    destructiveIndex = weaponsDestructiveIndex("fork");
//                   inventory.remove("fork");
//                case "golden cross":
//                    destructiveIndex = weaponsDestructiveIndex("gold cross");
//                   inventory.remove("gold cross");
//                case "broom":
//                    destructiveIndex = weaponsDestructiveIndex("broom");
//                    inventory.remove("broom");
//                    break;
//            }
//
//        }
//        return winner;
//    }
    public  static String winner(String character) {

        String selectWeapon1;
        String selectWeapon = selectWeaponDialogue("Select the weapons from " + inventory);
        if (inventory.size()>=2) {
            while(destructiveIndex == 0){
                if (selectWeapon.equals("frying pan")){
                    destructiveIndex = weaponsDestructiveIndex("frying pan");
                    inventory.remove("frying pan");
                    selectWeapon1= selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("hammer") | selectWeapon1.equals("fork")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue(character);
                    }
                }else if(selectWeapon.equals("hammer")){
                    destructiveIndex = weaponsDestructiveIndex("hammer");
                    inventory.remove("hammer");
                    selectWeapon1=selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("frying pan")| selectWeapon1.equals("fork")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue(character);
                    }
                }else if(selectWeapon.equals("fork")){
                    destructiveIndex = weaponsDestructiveIndex("fork");
                    inventory.remove("fork");
                    selectWeapon1 =selectWeaponDialogue(" You have destroyed by \n"+ destructiveIndex*10 + "%, Select item from available inventory" + inventory);
                    if (selectWeapon1.equals("hammer") | selectWeapon1.equals("frying pan")){
                        destructiveIndex =10;
                        winner = "player";
                        showDialogue(character);
                    }
                }
            }
//            winner="enemy";
//            showDialogue();
        } else {
            destructiveIndex = randomNumber(10);
            if (destructiveIndex<2){
                winner = "Player";
                showDialogue(character);
            }
            winner ="enemy";
            showDialogue(character);
        }
        return winner;
    }

}
