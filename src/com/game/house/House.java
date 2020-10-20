package com.game.house;

import com.game.player.Player;
import java.util.Scanner;

public class House {
    private static String housePrompt = "You wake up to the sound of the church bell ringing.\n" +
            "Your mother is baking bread on the hearth and rocking in a chair.\n" +
            "You get out of bed, and go to her.\n" +
            "\"They're coming for you,\" she says. She hands you a bag, and says\n" +
            "\"Better pack your things and sneak out while you can.\"";

    private static String whatsInTheBox = "There is a front door and a back door, \n" +
            "a closet, and window. Your mother is baking bread \n" +
            "on the hearth.";

    private static String options1 = "What would you like to do?\n" +
            "1. Open front door.\n" +
            "2. Open back door.\n" +
            "3. Talk to your mother.\n" +
            "4. Open the closet.\n" +
            "5. Pick up the frying pan.\n" +
            "6. Open the window.";

    private static String op1 = "You open the front door and a mob is waiting outside.\n" +
            "They throw stones at you, decreasing your health.\n" +
            "You close the door and look for another way out. [DECREMENT HEALTH -2]";

    private static String op2 = "You open the back door. Several zombies heard the door,\n" +
            "and swarm your house. Your mother tries to help you\n" +
            "close the door, but the zombies rip off her arm before\n" +
            "devouring you both. [GAME OVER]";

    private static String op3 = "You ask your mother to come with you, but she says she can't.\n" +
            "She gives you a piece of bread and you add it to your bag. [ADD BREAD TO BAG]";

    private static String op4 = "You open the closet and find a scrap of leather \n" +
            "and a locket your mother gave you. You can't do anything with the leather\n" +
            "put the locket on. [INCREMENT HEALTH +1]";

    private static String op5 = "You pick up a frying pan. You don't have a weapon,\n" +
            "but this should do for now.[SET FRYING PAN TO EQUIPPED]";

    private static String op6 = "You hug your mother, and climb out the window that faces the Market. [ENTER MARKET]";

    private static String op7 = "You open the front door, and a villager grabs you.\n" +
            "They drag you to the gallows and hang you for being a witch.[GAME OVER]";

    private static String op8 = "A group of zombies attacks the villagers waiting outside your house.\n" +
            "You sneak out the back door toward the market. [ENTER MARKET]";

    private static String op9 = "You open the closet and find a scrap piece of leather. [ADD LEATHER TO BAG]";

    private static String op10 = "A villager saw you crawl in through the window, and rounded up others to catch you.\n" +
            "They carry you off, tie you to a pyre, and burn you alive for being a witch.[GAME OVER]";

    private static String whatsLeftInTheBox = "You thought you saw a piece of leather in here earlier.";

    private static String options2 = "What would you like to do?\n" +
            "1. Open front door.\n" +
            "2. Open back door.\n" +
            "3. Check the closet. \n" +
            "4. Exit out the window.";

    public static void play() {
        System.out.println("THE HOUSE");
        System.out.println(housePrompt);
        System.out.println(" ");
        System.out.println(whatsInTheBox);
        System.out.println(" ");
        collectInput();
    }

    public static void returnFromMarket() {
        System.out.println("THE HOUSE");
        System.out.println(whatsLeftInTheBox);
        System.out.println(" ");
        collectReturnInput();
    }

    private static void collectInput() {
        Scanner input = new Scanner(System.in);
        System.out.println(" ");
        Player.printStats();
        System.out.println(" ");
        System.out.println(options1);
        int houseInput = input.nextInt();
        printConsequence(houseInput);
    }

    private static void collectReturnInput(){
        Scanner input = new Scanner(System.in);
        System.out.println(" ");
        System.out.println(options2);
        int houseInput = input.nextInt();
        printReturnConsequence(houseInput);
    }

    private static void printConsequence(int houseInput) {
        switch (houseInput) {
            case 1:
                System.out.println(op1);
                Player.changeHealth(-2);
                collectInput();
                break;
            case 2:
                System.out.println(op2);
                System.exit(0);
                break;
            case 3:
                System.out.println(op3);
                Player.addToBag("bread");
                collectInput();
                break;
            case 4:
                System.out.println(op4);
                Player.changeHealth(1);
                collectInput();
                break;
            case 5:
                System.out.println(op5);
                Player.setEquipped("Frying Pan");
                collectInput();
                break;
            case 6:
                System.out.println(op6);
                //ArisMarketSquare.myMarket();
                break;
            default:
                System.out.println("The number you entered was invalid. Please try again.");
                collectInput();
        }
    }
    private static void printReturnConsequence(int returnInput){
        switch(returnInput){
            case 1:
                System.out.println(op7);
                System.out.println("GAME OVER");
                System.exit(0);
            case 2:
                System.out.println(op8);
                break;
            case 3:
                System.out.println(op9);
                Player.addToBag("leather");
                collectReturnInput();
                break;
            case 4:
                System.out.println(op10);
                System.out.println("GAME OVER");
                System.exit(0);
            default:
                System.out.println("The number you entered was invalid. Please try again.");
                collectReturnInput();
                break;
            }
        }
}