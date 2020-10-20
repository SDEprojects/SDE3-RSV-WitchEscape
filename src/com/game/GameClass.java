package com.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import com.game.player.Player;
import com.game.market.ArisMarketSquare;
import com.game.pier.Pier_Gameplay;
import com.game.house.*;

public class GameClass {
    public GameClass() {
        // instantiates a new HealthBar
    }
    public void startNewGame() throws InterruptedException {
        banner();
        wantInstructions();
        intro();
        // run Amanda's code
        House.play();

        // run Sohail's code
        ArisMarketSquare.myMarket();

        // run Laura's code
        Pier_Gameplay.run_pier();
        ArisMarketSquare.fourthPrompt();
    }

     //Prints opening banner
    private static void banner(){
        Thread thd = new Thread();
        File file = new File("src/Banner.txt");
        try {
            int charValue = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            thd.start();
            while ((charValue = reader.read()) != -1) {
                char character = (char)charValue;
                if(character == '\0'){
                    System.out.println();
                }
                System.out.print(character);
                Thread.sleep(1);
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        // Add sound clip here
    }

    // Asks player if they want instructions
    private static void wantInstructions(){
        Scanner instInput = new Scanner(System.in);
        System.out.println("Would you like instructions on how to play the game? Y/N");
        String resp = instInput.nextLine().toUpperCase();

        switch(resp) {
            case "Y":
                System.out.println(" \n" +
                        "INSTRUCTIONS \n" +
                        "Witch Escape is a text-based role-playing game.\n" +
                        "Enter the prompted number or phrase on your turn. \n" +
                        "Longer blocks of text may require you to press enter to continue.\n" +
                        " ");
                break;
            case "N":
                break;
            default:
                System.out.println("Please enter a valid input.");
                wantInstructions();
        }
    }

    private static void intro(){
        // Add typewriter effect to this later. See notes
        // The following text needs to be moved to XML once we get it working.
        System.out.println("INTRODUCTION \n" +
                "   In the year 1692, during the Salem Witch trials, another heretical entity comes into being: the Undead. \n" +
                "You are a young witch trying to leave your village in order to escape from the Puritans \n" +
                "who have hanged or burned other suspected witches in town. Leaving the village has become more complicated \n" +
                "now that you have to avoid not only the townsfolk, but also...ZOMBIES.\n" +
                " ");
    }
}