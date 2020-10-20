package com.game.pier;

import com.game.player.Player;

import java.util.*;

public class Pier {
    public static void ship_picker() {
        int count = 0;
        ships: while (true){
            System.out.println("You see 3 ships on the Pier, there is a brown ship, a black ship and white ship. \n Which one would you like to checkout?");
            Scanner direction_choice = new Scanner(System.in);
            String pick=direction_choice.nextLine();
            switch(pick){
                case"brown ship":
                    boat_1();
                    count++;
                    //System.out.println(count);
                    break;
                case"black ship":
                    boat_2();
                    count++;
                    //System.out.println(count);
                    break;
                case"white ship":
                    boat_3();
                    count++;
                    //System.out.println(count);
                    break;
                default:
                    System.out.println("Please pick a ship to explore.");
                    ship_picker();
                    break;
            }
                    //break ships;
            if (count == 3) {
                break ships;
            }
        }
    }
    //Boats Encounters
    //NEED TO BREAK APART THE 3 DIFFERENT SHIPS INTO 3 SEPERATE FUNCTIONS
    static void boat_1() {
        Scanner input = new Scanner(System.in);
        //Boat 1()-Turned Away
        System.out.println("You approach the brown ship-It's made of dark wood and you see what appear to be bite marks...maybe from mysterious sea creatures?");
        input.nextLine();
        System.out.println("You quickly get on the ramp to aboard the ship, look around and don't see anyone on deck.");
        input.nextLine();
        System.out.println("Hey! You! Get outta here!");
        input.nextLine();
        System.out.println("You're startled as a Sailor appears and rushes you off the ship back on to the pier.");
        input.nextLine();
    }
    //Boat 2()-Zombie attack
        static void boat_2(){
            Scanner input = new Scanner(System.in);
            System.out.println("You approach the ship. This one is black and an eerie fog seems to be lingering around it just above the sea.");
            input.nextLine();
            System.out.println("You look around, coast is clear. You board the ship, creep around the dock when suddenly...");
            input.nextLine();
            System.out.println("ZOMBIES ATTACK!");
            input.nextLine();
            //need to flesh this out further-add health and equipped items
            // you either live or die here.
            if (Player.getEquipped().equals("No item equipped")) {
                System.out.println("You have no weapon equipped! You died! :( ");
                System.exit(0);
            } else {
                Player.printStats();
                System.out.println("You survived the attack!");
            }
        }
    //Boat 3()-Leather found
            static void boat_3() {
                Scanner input = new Scanner(System.in);
        System.out.println("You approach the white ship. It's made from wood but it's white and well taken care of, the paint looks fresh.");
                input.nextLine();
        System.out.println("The ramp is brightly lit, you don't see anyone around. Hopefully you can find just a scrap of leather aboard this ship.");
                input.nextLine();
        System.out.println("You walk around the deck and see a sign on the door-'Laundry'");
        //prompt user to choose to enter or walk away
                input.nextLine();
        System.out.println("You enter the Laundry and start rummaging around the various laundry bags in the room");
                input.nextLine();
        System.out.println("Just when you're about to give up, you find a sailor's pants with the belt still on them!");
                input.nextLine();
        System.out.println("You pull the belt off the pants and place it inside your bag.");
        //add belt to inventory here
                Player.addToBag("leather");
                Player.printStats();
                input.nextLine();
        System.out.println("You run out of the laundry, off the ship's ramp and back onto the pier");
                input.nextLine();
    }

    //Wooden Crate()
    static void wooden_crate () {
        Scanner input = new Scanner(System.in);
        //user input that prompt them to search the wooden crates
        System.out.println("You see some wooden crates filled with exotic looking fresh fruit.");
        input.nextLine();
        System.out.println("You push around the fruit looking for a leather scrap-you find nothing.");
        input.nextLine();
    }
    //Grain Bags()
    static void grain_bags() {
        Scanner input = new Scanner(System.in);
        //user input that prompt them to search the grain bags
        System.out.println("You see some large thick linen grain bags, you untie them hoping you can use the leather wrapping.");
        input.nextLine();
        System.out.println("The leather wrapping comes away and doesn't seem to be enough to give to the lady in the market.");
        input.nextLine();
    }

    //Ale Kegs()
    static void ale_kegs() {
        Scanner input = new Scanner(System.in);
        //user input that prompts them to search the ale kegs
        System.out.println("You see some large ale kegs.");
        input.nextLine();
        System.out.println("You think that it's a good idea to get shield of sorts to protect you from the angry townspeople and even the rare occurrence of Zombie attack ");
        input.nextLine();
        System.out.println("You try to pop off the lid of the first keg-it's sealed shut.");
        input.nextLine();
        System.out.println("You try to pop off the lid of the second keg-it pops off!");
        //equip function here add to inventory/weapons
        Player.setEquipped("Keg Top Shield");
        input.nextLine();
        Player.printStats();
        input.nextLine();
    }

}