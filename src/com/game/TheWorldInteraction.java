package com.game;
import static com.gamewindow.Gui.displayCurrentLocation;
import static com.gamewindow.Gui.setMessage;

import static com.util.CombatEngine.winner;

import com.gamewindow.Gui;
import com.util.XMLParser;

import javax.swing.*;
import java.util.*;

public class TheWorldInteraction {

    //store name of the current room
    public  String currentRoom;
    //store all the info for the current room
    public  Location currentRoomObj;
    static ArrayList<ArrayList> world = XMLParser.locations;
    //Scanner
    Scanner scanner = new Scanner(System.in);
    //Current inventory
    public static ArrayList<String> inventory = new ArrayList<>();

    public void start() {
        //parse the xml file
        XMLParser parser = new XMLParser();
        parser.parser();
        //output the game intro
        System.out.println(XMLParser.gameIntro);


        //start in the house
        currentRoom = "house";
        createCurrentRoom(currentRoom);
        String message = XMLParser.gameIntro + roomPrompt();
        setMessage(message);

    }
    public String help(){
        String challenge = "";
        if (!currentRoomObj.getChallenge().equals("none")){
            challenge = "\nThere is a challenge in this room. You can access it by typing 'challenge'";
        }

        String message =  "\n" + "Type 'open', 'explore', 'go', or 'unlock' to go to the following locations: \n" + currentRoomObj.getAvailableExits() +
                "\n\nType 'get' or 'take' to pick up the following available items: \n" + currentRoomObj.getRoomItems() + "\n"+ challenge;

        return message;
    }

    public void evaluateChallenge(){
        switch(currentRoomObj.getChallenge()){
            case "zombie":
                //combatEngine.winner();
                if(winner("zombie").equals("player")){
                    createCurrentRoom("pier");
                    setMessage(roomPrompt());
                }
                break;
            case "tradeHammerForLeatherOrSandwich":
                //method to trade hammer for leather or sandwich
                tradeHammerForLeatherOrSandwich();
                break;
            case "getTheHorse":
                //method to exchange leather or cross for horse or persuade shoe lady with the frying pan or cross
                getTheHorse();
                break;
            case "surviveChurch":
                //method to combat ppl in church with the frying pan
                surviveTheChurch();
                break;
            case "shipChallenge":
                //method to pick a ship and then either go back to the house, die, or solve a riddle and escape
                shipChallenge();
                break;
            case "none":
                setMessage("There is no challenge at this location");
        }
    }

    //challenges
    public void shipChallenge(){
        setMessage("You see three ships: Pick one: black, red, or white!");
    }
    public void tradeHammerForLeatherOrSandwich(){
        if(inventory.contains("hammer")){
            setMessage("You have a hammer in your inventory, would you like to trade it for leather or sandwich? ");
        }
        else{
            setMessage("You have nothing useful to exchange");
        }
    }
    public void trade(String [] inputArray){
        System.out.println("inside the trade method!");
        ArrayList<String> inner = new ArrayList<>(Arrays.asList(inputArray));
        System.out.println(inner);
        if (inventory.contains("hammer")) {
            if (inner.contains("sandwich")) {
                inventory.remove("hammer");
                inventory.add("sandwich");
            } else if (inner.contains("leather")) {
                inventory.remove("hammer");
                inventory.add("leather");
            }
        }
        if (inner.contains("horse") && (inventory.contains("leather") || inventory.contains("frying pan"))) {
            String message = "";
            if (inventory.contains("leather")) {
                inventory.remove("leather");
                message = "You successfully exchanged leather for the horse and you are now headed to the pier!";
            } else if (inventory.contains("frying pan")) {
                message = "You used the frying pan to persuade the shoe lady to give you the horse \n You are now happily riding the horse to the pier!";
            }
            setMessage(message);
            createCurrentRoom("pier");
        }
        else{
            setMessage("You don't get a horse! You have nothing to exchange or intimidate with");
        }

    }
    public void getTheHorse(){
        setMessage("Here you can exchange leather for the horse! \n It is also possible to persuade the shoe lady with the frying pan in case " +
                "you decided to eat a sandwich at the sandwich shop! ");
    }
    public void surviveTheChurch(){
        if (inventory.contains("fork")){
            setMessage("You were able to fight people off with the fork!");
        }
        else{
            setMessage("You lost! You got set on fire!");
            //System.exit(0);
        }
    }

    public void pickShip(ArrayList<String> inputList){
        if(inputList.contains("white")){

            //setMessage("You got on the white ship which ended up being full of crusaders \n Guess what happened? You died!");

            JOptionPane.showMessageDialog(Gui.gameWindow, "You got on the white ship which ended up being full of crusaders \n Guess what happened? You died!");
            System.exit(0);
        }
        else if(inputList.contains("red")){
            setMessage("The red ship is really a portal. It took you back to where you started! The house.");
            createCurrentRoom("house");
        }
        else if (inputList.contains("black")){
            JOptionPane.showMessageDialog(Gui.gameWindow,"You chose the right ship! \n This one will take you to safety. \n You have escaped!");
            //setMessage("You chose the right ship! \n This one will take you to safety. \n You have escaped!");
            System.exit(0);
        }

    }

    //challenges
    public String roomPrompt() {
        String result= "";
        System.out.println("\n" + currentRoomObj.getRoomDescription()+"\n");
        //setMessage(currentRoomObj.roomDescription+ "\n");

        //itemsAvailableForPickUp();
        System.out.println("\n" + currentRoomObj.getQuestion() +"\n");
        //setMessage(currentRoomObj.question + "\n");
        //String input = scanner.nextLine();
        //evaluateInput(input);
        result = "\n" +currentRoomObj.getRoomDescription()+ "\n" + currentRoomObj.getQuestion() +"\n or"+ itemsAvailableForPickUp();
        return result;
    }

    public void promptIfStayedInTheSameRoom(){
        System.out.println("\n Current items in your inventory: " + inventory);
        itemsAvailableForPickUp();
        System.out.println("\nYour next move: ");

        String input = scanner.nextLine();
        evaluateInput(input);
    }
    public String itemsAvailableForPickUp(){
        String result="";
        if(currentRoomObj.getRoomItems().size()==0){

            System.out.println("\nThere are no available items to pick up in this room");
            result= "There are no available items to pick up in this room";
        }
        else {
            System.out.println("\nYou can get the following items in this room: ");
            String message = "\nYou can get the following items in this room: ";
            //setMessage("\nYou can get the following items in this room: ");
            for (var item : currentRoomObj.getRoomItems()) {
                System.out.println(item);
                if(!inventory.contains(item)){
                    result = message+ "\n"+ currentRoomObj.getRoomItems().toString();

                }
                //setMessage(currentRoomObj.roomItems.toString());
            }
        }
        return result;
    }
    public void evaluateInput(String inputIn) {
        String input = inputIn.toLowerCase();
        String[] inputArray = input.split(" ");
        if (XMLParser.open.contains(inputArray[0])) {
            open(inputArray);
        } else if (XMLParser.get.contains(inputArray[0])) {
            get(inputArray);
        }
        else if (inputArray[0].equalsIgnoreCase("challenge")){
            evaluateChallenge();
        }
        else {
            System.out.println("You entered the wrong command");
        }
    }
    public void get(String [] input){
        String toChange="";
        for(var word: input){
            toChange += " " + word;
        }
        String item = toChange.toLowerCase().replace(input[0], "").trim();
        if (currentRoomObj.getRoomItems().contains(item)) {
            inventory.add(item);
            currentRoomObj.getRoomItems().remove(item);
            setMessage(/*currentRoomObj.roomDescription+*/"\n"+ currentRoomObj.getQuestion() + "\n or"+ itemsAvailableForPickUp());
        }
        else{
            setMessage("No such object exists");
            System.out.println("No such object exist");
        }

        //promptIfStayedInTheSameRoom();
    }
    //open location
    public void open(String[] input) {
        String direction="";
        for (var word : input) {
            direction += " "+ word;
        }
        String dir = direction.toLowerCase().replace(input[0], "").trim();
        System.out.println(dir);
        System.out.println(currentRoomObj.getActionDescription().get(dir));
        String displaymessage =  currentRoomObj.getActionDescription().get(dir);
        if (!currentRoomObj.getExitsTo().get(dir).equalsIgnoreCase(currentRoomObj.getName())){
            createCurrentRoom(currentRoomObj.getExitsTo().get(dir).toLowerCase());
            roomPrompt();
        }
        setMessage(displaymessage+"\n"+ roomPrompt());
        //promptIfStayedInTheSameRoom();
    }
    //create a new room from the Location constructor
    public  void createCurrentRoom(String currentRoom) {
        for (int i = 0; i < world.size(); i++) {
            for (int j = 0; j < world.get(i).size(); j++) {
                HashMap<String, String> newMap;
                newMap = (HashMap<String, String>) world.get(i).get(j);
                if (newMap.get("name").equals(currentRoom)) {
                    String name = newMap.get("name");
                    String roomDescription = newMap.get("description");
                    String question = newMap.get("question");
                    String challenge = newMap.get("challenge");
                    List<String> availableExits = Arrays.asList(newMap.get("exits").split(", "));
                    List<String> temp = Arrays.asList(newMap.get("items").split(", "));
                    List<String>roomItems = new ArrayList<>();
                    roomItems.addAll(temp);
                    HashMap<String, String> exitsTo = new HashMap<>();
                    HashMap<String, String> actionDescription = new HashMap<>();
                    for (String exit : availableExits) {
                        //populate exitsTo
                        String toFindKey = exit.replaceAll("\\s", "");
                        String value = newMap.get(toFindKey);
                        exitsTo.put(exit, value);
                        //populate actionDescription
                        String toFindThisKey = toFindKey + "desc";
                        String thisValue = newMap.get(toFindThisKey);
                        actionDescription.put(exit, thisValue);
                    }
                    //create the current location with all the parameters.
                    currentRoomObj = new Location(name, roomDescription, question,challenge, availableExits, roomItems, exitsTo, actionDescription);

                }
            }
        }
        displayCurrentLocation.setText(currentRoomObj.getName());
    }
}