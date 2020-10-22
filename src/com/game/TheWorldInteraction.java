import java.util.*;

public class TheWorldInteraction {
    //store previous room
    String previousRoom;
    //store name of the current room
    String currentRoom;
    //store all the info for the current room
    Location currentRoomObj;
    static ArrayList<ArrayList> world = XMLParser.locations;
    //Scanner
    Scanner scanner = new Scanner(System.in);
    //Current inventory
    ArrayList<String> inventory = new ArrayList<>();

    public void start() {
        //parse the xml file
        XMLParser parser = new XMLParser();
        parser.parser();
        //output the game intro
         System.out.println(XMLParser.gameIntro);
        //start in the house
        currentRoom = "house";
        createCurrentRoom(currentRoom);
        roomPrompt();
    }

    public void roomPrompt() {
        System.out.println("\n" + currentRoomObj.roomDescription+"\n");
        itemsAvailableForPickUp();
        System.out.println("\n" + currentRoomObj.question +"\n");
        String input = scanner.nextLine();
        evaluateInput(input);
    }

    public void promptIfStayedInTheSameRoom(){
        System.out.println("\n Current items in your inventory: " + inventory);
        itemsAvailableForPickUp();
        System.out.println("\nYour next move: ");
        String input = scanner.nextLine();
        evaluateInput(input);
    }
    public void itemsAvailableForPickUp(){
        if(currentRoomObj.roomItems.size()==0){
            System.out.println("\nThere are no available items to pick up in this room");
        }
        else {
            System.out.println("\nYou can get the following items in this room: ");
            for (var item : currentRoomObj.roomItems) {
                System.out.println("-"+item + " ");
            }
        }
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
        }
        else{
            System.out.println("No such object exist");
        }

        promptIfStayedInTheSameRoom();
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
        if (!currentRoomObj.exitsTo.get(dir).equalsIgnoreCase(currentRoomObj.name)){
            createCurrentRoom(currentRoomObj.exitsTo.get(dir).toLowerCase());
            roomPrompt();
        }
        promptIfStayedInTheSameRoom();
    }
    //create a new room from the Location constructor
    public void createCurrentRoom(String currentRoom) {
        for (int i = 0; i < world.size(); i++) {
            for (int j = 0; j < world.get(i).size(); j++) {
                HashMap<String, String> newMap;
                newMap = (HashMap<String, String>) world.get(i).get(j);
                if (newMap.get("name").equals(currentRoom)) {
                    String name = newMap.get("name");
                    String roomDescription = newMap.get("description");
                    String question = newMap.get("question");
                    List<String> availableExits = Arrays.asList(newMap.get("exits").split(", "));
                    List<String> temp = Arrays.asList(newMap.get("items").split(", "));
                    List<String> roomItems = new ArrayList<>();
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
                    currentRoomObj = new Location(name, roomDescription, question, availableExits, roomItems, exitsTo, actionDescription);
                }
            }
        }
    }

}
