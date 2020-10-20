package com.game.market;

import com.game.house.House;


import com.game.player.Player;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//EDITS
//HOUSE  LINE 37 COM SPELLING
//DID WE PUT DIRECTIONS N S E W
//****from Tom
//house market and pier are scenes****
//if similar put in ONE superclass****
//if user enters 1 goto wichwich sandwich shoppe
//if user enters 2 goto to shoe lady's shoppe
//if user enters 3 goto puritan church

//every attribute should be pvt unless final

//put market is east of house or direction
//pier is north of market
// inventory, text parsing, item management  all use xml

//make external input class. initialize like a .txt file. an idea for later not sprint 3
//System.out.println("Grab Items for your bag. \n ");
// "You can only have 5 items in your bag at any given time.
//can be private bc not called by outside
//but keep public for testing purposes

// worry about limiting to five items later
//delete previously grabbed items
//add only what user grabbed
//
//inventory is what the user has it in its bag
// show what user has in bag
//      for when user returns from the pier
// why is player input below fourth prompt


public class ArisMarketSquare{
    // user input-user selects items to put in bag
    private static ArrayList<String> marketList = new ArrayList<String>(Arrays.asList("Pumpkin Spice Latte", "Leather", "Zombie Tooth", "Barret .50cal",  "lucky Penny", "Harry Potter's Broom", "Witch's Hat", "Magic Potion", "Spell Book"));

    public static void myMarket() throws InterruptedException {
        firstPrompt();
 //      insideWichWich();
   //     insideShoppe();
//      secondPrompt();
        thirdPrompt();
    }
    public static void firstPrompt() throws InterruptedException {    // ask player where they want to go
        System.out.println("\nYou are now in Aris Market Square. What area would you like to explore next? \n " +
                "Enter 1 for WichWich SandWich \n " +
                "Enter 2 for Shoe Lady's Shoppe \n " +
                "Enter 3 for WhichToSlay Church");
        // a response of .1 breaks code
//        while (true) {
        Scanner locationInput = new Scanner(System.in);
        int respLocInput1 = locationInput.nextInt();
    System.out.println(respLocInput1);

        switch (respLocInput1) {
            case 1:
                System.out.println("You are now inside WichWich Sandwich Shoppe");
                insideWichWich();
                System.out.println("\n");
                break;
            case 2:
                System.out.println("You are now inside Shoe Lady's Shoppe.\n" +
                        "Make a deal with the lady to obtain your horse for the escape.\n" +
                        "You must collect 3 pieces of leather to trade for the horse.");
                System.out.println("\n");
                insideShoppe();
                break;
            case 3:
                System.out.println("You are now inside WhichToSlay Church. You have also been slayed. ");
                Thread.sleep(1000);

                System.out.println("GAME OVER");
                System.exit(0);
                break;
            default:
                System.out.println("You must be great at many things. Following simple directions is not one of them");
        }
//        }
    }

    public static void insideWichWich() {

        System.out.println("\nGrab an item.\n" +
                "Enter 0 for " + marketList.get(0) + "\n" +
                "Enter 1 for " + marketList.get(1) + "\n" +
                "Enter 2 for " + marketList.get(2) + "\n" +
                "Enter 3 for " + marketList.get(3) + "\n" +
                "Enter 4 for " + marketList.get(4));
        Scanner itemInput = new Scanner(System.in);
        int respItemInput1 = itemInput.nextInt();
        System.out.println(respItemInput1);
//can call from list using index later can code this
        if (respItemInput1 == 0) {
//            System.out.println("you now have " + marketList.get(0) + " in your bag");
            Player.addToBag(marketList.get(0));
            Player.changeHealth(1);
            Player.printStats();
        } else if (respItemInput1 == 1) {
//            System.out.println("you now have " + marketList.get(1) + " in your bag");
            Player.addToBag(marketList.get(1));
            Player.changeHealth(1);
            Player.printStats();
//                break;
        } else if (respItemInput1 == 2) {
//            System.out.println("you now have " + marketList.get(2) + " in your bag");
            System.out.println("Your health decreases 1.");
            Player.addToBag(marketList.get(2));
            Player.changeHealth(-1);
           Player.printStats();
//                break;
        } else if (respItemInput1 == 3) {
//            System.out.println("you now have " + marketList.get(3) + " in your bag");
            Player.addToBag(marketList.get(3));
            Player.changeHealth(0);
            Player.printStats();
//                break;
        } else if (respItemInput1 == 4) {
//            System.out.println("you now have " + marketList.get(4) + " in your bag");
            System.out.println("Must be your lucky day!..NOT \n" +
                    "Your health decreases 1.");

            Player.addToBag(marketList.get(4));
            Player.changeHealth(-1);
            Player.printStats();
//                break;
        } else {
        }
    }

    public static void insideShoppe() {

        System.out.println("\nGrab an item.\n" +
                "Enter 5 for " + marketList.get(5) + "\n" +
                "Enter 6 for " + marketList.get(6) + "\n" +
                "Enter 7 for " + marketList.get(7) + "\n" +
                "Enter 8 for " + marketList.get(8) + "\n" +
                "Enter 9 for " + marketList.get(4));

        //prompt to grab another item until 5 items in bag
//        while (true) {
        Scanner itemInput = new Scanner(System.in);
        int respInShoppe = itemInput.nextInt();

        System.out.println(respInShoppe);
//can call from list using index later can code this
        if (respInShoppe == 5) {
//            System.out.println("you now have " + marketList.get(5) + " in your bag");
            System.out.println("Harry Potter flew away on his real broom." +
                    "But you can definitely do some cleaning with broom you filthy slime!");
            Player.addToBag(marketList.get(5));
            Player.changeHealth(0);
            Player.printStats();
        } else if (respInShoppe == 6) {
//            System.out.println("you now have " + marketList.get(6) + " in your bag");
            Player.addToBag(marketList.get(6));
            Player.changeHealth(0);
            Player.printStats();
//                break;
        } else if (respInShoppe == 7) {
//            System.out.println("you now have " + marketList.get(7) + " in your bag");
            System.out.println("Silly rabbit, Potions are mine...Mine only!");
            Player.addToBag(marketList.get(7));
            Player.changeHealth(-1);
            Player.printStats();
//                break;
        } else if (respInShoppe == 8 ) {
//            System.out.println("you now have " + marketList.get(8) + " in your bag");
            System.out.println("You have no power here!");
            Player.addToBag(marketList.get(8));
            Player.changeHealth(-1);
            Player.printStats();
//                break;
        } else if (respInShoppe == 9) {
//            System.out.println("you now have " + marketList.get(4) + " in your bag");
            System.out.println("Must be your lucky day!" +
                    "Your health increases 1.");
            Player.addToBag(marketList.get(4));
            Player.changeHealth(1);
            Player.printStats();
//                break;
        } else {
        }
//                break;
//        }
    }

    public static void thirdPrompt() {
        System.out.println("\nWhich area would you like to explore next? \n" +
                "Enter 1 for WichWich SandWich \n" +
                "Enter 2 for Shoe Lady's Shoppe \n" +
                "Enter 3 for WhichToSlay Church \n" +
                "Enter 4 for Pier");
//      while (true) {
        Scanner locationInput2 = new Scanner(System.in);
        int respLocInput2 = locationInput2.nextInt();
        System.out.println(respLocInput2);

        switch (respLocInput2) {
            case 1:
                System.out.println("You are now inside WichWich Sandwich Shoppe");
                insideWichWich();
                thirdPrompt();
                break;
            case 2:
                System.out.println("You are now inside Shoe Lady's Shoppe.\n" +
                        "'Why must tho come to my Shoppe once more.'\n" +
                        "'Leave now! Get my leather!' \n");
                thirdPrompt();
                break;
            case 3:
                System.out.println("You are now inside WhichToSlay Church. You have also been slayed.");
                try { Thread.sleep(1000); }
                catch (InterruptedException ex) { Thread.currentThread().interrupt();}
                System.out.println("GAME OVER");
                System.exit(0);
            case 4:
                System.out.println("You are now leaving Aris Market Square \n");
                break;
            default:
                System.out.println("Let us try again. By 'us' we mean 'YOU'!");
                thirdPrompt();
        }
//        System.out.println("\nYou now have " + marketList + " in your bag! ");
//      }
    }

    public static void fourthPrompt() {
                System.out.println("\nWhich area would you like to explore next? \n" +
                "Enter 1 for WichWich SandWich \n" +
                "Enter 2 for Shoe Lady's Shoppe \n" +
                "Enter 3 for WhichToSlay Church \n" +
                "Enter 4 for Pier \n" + "Enter 5 for House");
//      while (true) {
        Scanner locationInput2 = new Scanner(System.in);
        int respLocInput2 = locationInput2.nextInt();
        System.out.println(respLocInput2);

        switch (respLocInput2) {
            case 1:
                System.out.println("You are now inside WichWich Sandwich Shoppe");
                insideWichWich();
                thirdPrompt();
                break;
            case 2:
                System.out.println("You are now inside Shoe Lady's Shoppe.\n" +
                        "'Why must tho come to my Shoppe once more.'\n" +
                        "'Leave now! Get my leather!' \n");
                thirdPrompt();
                break;
            case 3:
                System.out.println("You are now inside WhichToSlay Church. You have also been slayed.");
                try { Thread.sleep(1000); }
                catch (InterruptedException ex) { Thread.currentThread().interrupt();}
                System.out.println("GAME OVER");
                System.exit(0);
            case 4:
                System.out.println("You are now leaving Aris Market Square \n");
                break;
            case 5:
                System.out.println("You are leaving the market to go back to the House");
                House.returnFromMarket();
            default:
                System.out.println("Let us try again. By 'us' we mean 'YOU'!");
                thirdPrompt();
        }
//        System.out.println("\nYou now have " + marketList + " in your bag! ");
//      }

    }
//    static void player_input() throws InterruptedException {
//        //health and item inventory count
//        loop: while(true) {
//            Scanner direction_choice = new Scanner(System.in);  // Create a Scanner object
//            System.out.println("Where would you like to go?");
//
//            String choice = direction_choice.nextLine();  // Read user input
//            //System.out.println(choice);
//
//            //DISPLAY PARAMETER PROMPT HERE
//            switch (choice) {
//                case "sailor group":
//                    System.out.println("You arrive at " + choice);
//                    break;
//                case "ale house":
//                    System.out.println("You arrive at " + choice);
//
//                    break;
//                case "pier":
//                    System.out.println("You arrive at " + choice);
//
//                    break;
//                case "wooden crates":
//                    System.out.println("You arrive at " + choice);
//
//                    break;
//                case "ale kegs":
//                    System.out.println("You arrive at " + choice);
//
//                    break;
//                case "grain bags":
//                    System.out.println("You arrive at " + choice);
//                    break;
//                default:
//                    System.out.println("Sorry! You can't go there.");
//                    player_input(); //REMOVING PLAYERINPUT() AND BREAK
//                    break loop; //add to end of switch
//            }
//        }
//    }
}






