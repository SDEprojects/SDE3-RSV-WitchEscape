package com.game;
import static com.gamewindow.Gui.displayCurrentLocation;
import static com.gamewindow.Gui.setMessage;

import static com.util.CombatEngine.winner;
import com.util.XMLParser;
import java.util.*;

public class TheWorldInteraction {

    //store previous room
    String previousRoom;
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
    public void evaluateChallenge(){
        switch(currentRoomObj.challenge){
            case "zombie":
                //combatEngine.winner();
                if(winner().equals("player")){
                    createCurrentRoom("pier");
                    setMessage(roomPrompt());
                }
                break;
            case "tradeHammerForLeatherOrSandwich":
                //method to trade hammer for leather or sandwich
                break;
            case "getTheHorse":
                //method to exchange leather or cross for horse or persuade shoe lady with the frying pan or cross
                break;
            case "surviveChurch":
                //method to combat ppl in church with the frying pan

                break;
            case "ShipChallenge":
                //method to pick a ship and then either go back to the house, die, or solve a riddle and escape
                break;
        }
    }
    public String roomPrompt() {
        String result= "";
        System.out.println("\n" + currentRoomObj.roomDescription+"\n");
        //setMessage(currentRoomObj.roomDescription+ "\n");

        //itemsAvailableForPickUp();
        System.out.println("\n" + currentRoomObj.question +"\n");
        //setMessage(currentRoomObj.question + "\n");
        //String input = scanner.nextLine();
        //evaluateInput(input);
        result = "\n" +currentRoomObj.roomDescription+ "\n" + currentRoomObj.question +"\n or"+ itemsAvailableForPickUp();
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
        if(currentRoomObj.roomItems.size()==0){

            System.out.println("\nThere are no available items to pick up in this room");
            result= "There are no available items to pick up in this room";
        }
        else {
            System.out.println("\nYou can get the following items in this room: ");
            String message = "\nYou can get the following items in this room: ";
            //setMessage("\nYou can get the following items in this room: ");
            for (var item : currentRoomObj.roomItems) {
                System.out.println(item);
                if(!inventory.contains(item)){
                    result = message+ "\n"+ currentRoomObj.roomItems.toString();

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
        } else {
            System.out.println("You entered the wrong command");
        }
    }
    public void get(String [] input){
        String toChange="";
        for(var word: input){
            toChange += " " + word;
        }
        String item = toChange.toLowerCase().replace(input[0], "").trim();
        if (currentRoomObj.roomItems.contains(item)) {
                inventory.add(item);
                currentRoomObj.roomItems.remove(item);
            setMessage(/*currentRoomObj.roomDescription+*/"\n"+ currentRoomObj.question + "\n or"+ itemsAvailableForPickUp());
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
        System.out.println(currentRoomObj.actionDescription.get(dir));
        String displaymessage =  currentRoomObj.actionDescription.get(dir);
        if (!currentRoomObj.exitsTo.get(dir).equalsIgnoreCase(currentRoomObj.name)){
            createCurrentRoom(currentRoomObj.exitsTo.get(dir).toLowerCase());
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
        displayCurrentLocation.setText(currentRoomObj.name);
        //evaluateChallenge();
    }
}
