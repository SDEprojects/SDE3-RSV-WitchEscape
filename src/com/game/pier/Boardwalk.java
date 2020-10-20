package com.game.pier;

import java.util.Random;
import java.util.Scanner;

public class Boardwalk {

    //random gossip generated as Sabrina walks around the game
    static void random_gossip() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("You hear people walking around you whisper...");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        String townspeople_gossip;
        String[] gossip = {"What is that witchy little girl doing out here alone? She better watch her back- \n there are some angry people in town",
                "I hope she gets what she deserves...devilish witch!",
                "These witches will doom us all if we don't do something about them quick!" };
        Random rand = new Random();
        townspeople_gossip = gossip [rand.nextInt(gossip.length)];

        System.out.println(townspeople_gossip);
        input.nextLine();
    }

    //encounter with sailor group
    static void sailor_group() {
        Scanner input = new Scanner(System.in);
        String[] sailor = {"Hey little girl! You should be at home doing laundry-not out here!", "Leather? We don't have leather to give to you!", "Yeah, the only leather we use is on the ship with our belongings."};
        System.out.println(sailor[0]);
        input.nextLine();
        System.out.println("You ask the Sailors if they have any leather to give you...");
        input.nextLine();
        System.out.println("One of them angrily answers: ");
        input.nextLine();
        System.out.println(sailor[1]);
        input.nextLine();
        System.out.println("You take a step back startled, and then the youngest Sailor comes over and whispers...");
        input.nextLine();
        System.out.println(sailor[2]);
        System.out.println("You turn around and leave the group.");
        input.nextLine();
    }

    //encounter at Ale House
    static void ale_House() {
        Scanner input = new Scanner(System.in);
        System.out.println("You approach the Ale House, you hear people talking and laughing and music playing.");
        input.nextLine();
        System.out.println("There's a group of men outside, they look like old sailors. \n The waitress approaches them and they start asking her about the Zombies in the area.");
        input.nextLine();
        System.out.println("The waitress says 'There hasn't been a lot of Zombie sightings out on the pier but it's bound to happen'.");
        input.nextLine();
        System.out.println("The sailors start telling a story about a Zombie encounter on their ship. \n 'We didn't have any weapons! We used mops and buckets to protect ourselves and shove them Zombies overboard!'");
        input.nextLine();
        System.out.println("'Yeah! I even used the top of an ale keg as a shield to protect me from their bites! \n If I had gotten bit, i'd be a goner!");
        input.nextLine();
        System.out.println("Suddenly, they all stopped talking and noticed you standing there on the boardwalk, intently listening to their conversation.");
        input.nextLine();
        System.out.println("The waitress says to you, 'Child, you best be going. \n It's late and it's not safe to be who you are on this side of town.");
        input.nextLine();
        System.out.println("You snap out of it, turn and leave. \n You head toward the pier, still looking for that piece of leather.");
        input.nextLine();
    }

    //Zombie Attack()?
}