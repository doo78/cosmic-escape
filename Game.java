import java.util.ArrayList;
import java.util.Random;
/**
 *  This class is part of the "Cosmic Escape" application.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Ricky Gordon k23075676
 */

public class Game 
{
    private Parser parser; 
    private Room currentRoom; //stores which room the player is in
    private Player player; //stores the object for player information and methods
    
    private ArrayList<Room> path; //stores the path the user takes, which is needed for the back() method
    private ArrayList<Room> rooms; //stores all of the possible rooms
    
    private Npc alien; //stores the object for the alien NPC
    private Npc human; 
    private Npc corpse; 
    
    private Random rand; //used to generate random numbers 
    
    private boolean alienEncounter; //used to check when the alien encounter is occurring
    private boolean humanEncounter;
    private boolean corpseEncounter;
    private boolean explode; //determines if the game should end because you lost 
    private boolean win; //determines if the game should end because you won
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new ArrayList<Room>(); //initialises the array list
        createRooms(); //explained in the method below
        
        parser = new Parser();
        player = new Player();
        path = new ArrayList<Room>(); //initialises the array list
        
        explode = false;
        win = false;
        
        rand = new Random(); 
        
        alien = new Npc(rooms.get(5), "alien"); //the room at index 5 is the living quarters
        human = new Npc(rooms.get(5), "human"); //...which is where the NPCs will spawn
        corpse = new Npc(rooms.get(5), "corpse");
        
        alienEncounter = false;
        humanEncounter = false;
        corpseEncounter = false;
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Initialises the data type of the rooms.
        Room shuttle, shootingRange, infirmary, securityRoom, reactor, livingQuarters, gym, storageRoom, directorOffice, lab, magicTransporter;
        //Initialises the data type of the items.
        Item medkit, gun, bullet1, bullet2, bullet3, runningShoes, watch, screwdriver, toothbrush, rock, goggles, backpack, sledgehammer, garbageBin;
        
        
        //Instantiating the items with a name and a weight
        
        medkit = new Item ("medkit", 2); 
        gun = new Item ("gun", 2);
        bullet1 = new Item ("bullet", 2);
        bullet2 = new Item ("bullet", 2);
        bullet3 = new Item ("bullet", 2);
        runningShoes = new Item ("runningShoes", 2);
        watch = new Item ("watch", 2);
        screwdriver = new Item ("screwdriver", 1);
        toothbrush = new Item ("toothbrush", 1);
        rock = new Item ("rock", 3);
        goggles = new Item ("goggles", 1);
        backpack = new Item ("backpack", 0);
        sledgehammer = new Item ("sledgehammer", 4);
        garbageBin = new Item ("garbageBin", 4);
        
        
        //Adding items to each of the rooms
        
        ArrayList<Item> shuttleItems = new ArrayList<>(); //the shuttle has no items in it
        
        ArrayList<Item> shootingRangeItems = new ArrayList<>(); //adds the appropriate items to the room's list of items
        shootingRangeItems.add(bullet1);
        shootingRangeItems.add(sledgehammer);
        
        ArrayList<Item> infirmaryItems = new ArrayList<>();
        infirmaryItems.add(medkit);
        
        ArrayList<Item> securityRoomItems = new ArrayList<>();
        
        ArrayList<Item> reactorItems = new ArrayList<>();
        reactorItems.add(watch);
        reactorItems.add(bullet2);
        
        ArrayList<Item> livingQuartersItems = new ArrayList<>();
        livingQuartersItems.add(toothbrush);
        livingQuartersItems.add(garbageBin);
        
        ArrayList<Item> gymItems = new ArrayList<>();
        gymItems.add(runningShoes);
        
        ArrayList<Item> storageRoomItems = new ArrayList<>();
        storageRoomItems.add(screwdriver);
        storageRoomItems.add(backpack);
        
        
        ArrayList<Item> directorOfficeItems = new ArrayList<>(); //the director's office has no items
        
        ArrayList<Item> labItems = new ArrayList<>();
        labItems.add(goggles);
        
        
        //Long descriptions for when the 'analyse' command is called
        
        String shuttleLongDesc = ("This is the location of the only escape from this self-destructing outpost, but requires a key.");
        String shootingRangeLongDesc = ("There is an area for shooting targets, however, all of the weapons have been taken.");
        String infirmaryLongDesc = ("All of the medical equipment has been taken or destroyed. There are beds but no-one in them.");
        String securityRoomLongDesc = ("You are surrounded by monitors and cameras. Under the monitor displaying the Director's office there are jumbled up letters: 'LAEONRXPOTI'");
        String reactorLongDesc = ("The reactor extends to the ceiling and is piping hot. In just a few minutes, it will explode, blowing the outpost to pieces.");
        String livingQuartersLongDesc = ("There are bunk-beds and sofas, but not a single human left. Only the remains of them...");
        String gymLongDesc = ("The equipment is specialised for space expeditions but it is mostly destroyed now...");
        String storageRoomLongDesc = ("There are ripped-open boxes scattered everywhere with no rations left.");
        String directorOfficeLongDesc = ("The director was obsessesed with exploring new planets. There is a lock on the door and a password required to open it. \n (Type password and then the correct password to enter)");
        String labLongDesc = ("Microscopes and lab coats lay obliterated over the floor. A place full of innovation brought to ruin.");
        String magicTransporterLongDesc = ("You ran away, hiding in the transporter room without activating the teleporter.");
                                
     
        // Instantiates all the rooms by adding a short desc, the arraylist of items in the room and the long desc
        //...whilst also adding each room to the arraylist of all rooms
        
        shuttle = new Room("You are at the shuttle", shuttleItems, shuttleLongDesc );
        rooms.add(shuttle); 
        
        shootingRange = new Room("You are in the shooting range", shootingRangeItems, shootingRangeLongDesc);
        rooms.add(shootingRange);
        
        infirmary = new Room("You are in the infirmary", infirmaryItems, infirmaryLongDesc);
        rooms.add(infirmary);
        
        securityRoom = new Room("You are in the security room", securityRoomItems, securityRoomLongDesc);
        rooms.add(securityRoom);
        
        reactor = new Room("You are at the reactor", reactorItems, reactorLongDesc);
        rooms.add(reactor);
        
        livingQuarters = new Room("You are in the living quarters", livingQuartersItems, livingQuartersLongDesc);
        rooms.add(livingQuarters);
        
        gym = new Room("You are in the gym", gymItems, gymLongDesc);
        rooms.add(gym);
        
        storageRoom = new Room("You are in the storage room", storageRoomItems, storageRoomLongDesc);
        rooms.add(storageRoom);
        
        directorOffice = new Room("You are outside the director's office", directorOfficeItems, directorOfficeLongDesc);
        rooms.add(directorOffice);
        
        lab = new Room("You are in the lab", labItems, labLongDesc);
        rooms.add(lab);
        
        //the magic transporter room has no items, just like the director's office, hence using the same item array
        magicTransporter = new Room("You are in the magic transporter room", directorOfficeItems, magicTransporterLongDesc);
        rooms.add(magicTransporter);

        
        // Initialising the room exits
        
        shuttle.setExit("east", shootingRange);
        shuttle.setExit("west", lab);
        
        shootingRange.setExit("west", shuttle);
        shootingRange.setExit("south", infirmary);

        infirmary.setExit("north", shootingRange);
        infirmary.setExit("south", reactor);
        infirmary.setExit("east", securityRoom);

        securityRoom.setExit("west", infirmary);
        
        reactor.setExit("north", infirmary);
        reactor.setExit("west", livingQuarters);
        
        livingQuarters.setExit("east", reactor);
        livingQuarters.setExit("west", gym);
        livingQuarters.setExit("south", magicTransporter);
        
        magicTransporter.setExit("north", livingQuarters);

        gym.setExit("north", storageRoom);
        gym.setExit("east", livingQuarters);

        storageRoom.setExit("west", directorOffice);
        storageRoom.setExit("south", gym);
        storageRoom.setExit("north", lab);
        
        directorOffice.setExit("east", storageRoom);
        
        lab.setExit("south", storageRoom);
        lab.setExit("east", shuttle);

        currentRoom = shuttle;  // start game at the shuttle
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        
        //to close the game, you either lose (explode) win (win) or quit (finish)
        //...when either one is met, the game ends.
        while (!finished && !explode && !win) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        
        //What happens when you win:
        if (win == true){
            System.out.println("You entered the shuttle, quickly entering the key and took off.");
            System.out.println("A few minutes later and you would have died.");
            System.out.println("Now, you just had to wait to get to earth.");
            System.out.println("You were the only person to survive... that is, if you actually are a person...");
            System.out.println("You arrive at earth, and prepare for the invasion.");
            System.out.println("THE END");
        }
        
        //What happens if you quit or lose
        else{
            System.out.println();
            System.out.println("BOOOOOOOOOOOM!!!!!!");
            System.out.println();
            System.out.println("The outpost exploded. You were too late.");
            System.out.println();
            System.out.println("GAME OVER");
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You wake up.");
        System.out.println("This outpost on a distant planet is about to explode.");
        System.out.println("Aliens have attacked and the outpost has set to self-destruct soon.");
        System.out.println("You are next to the only escape shuttle that can only hold one person.");
        System.out.println("But it requires a key. You must explore the outpost to find the key before the time runs out.");
        System.out.println("And keep in mind... you are not alone.");
        System.out.println();
        System.out.println("(You can move 20 times before the time runs out)");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getShortDescription()); //Tells the player what room they have started in
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("search")) {
            searchRoom();
        }       
        else if (commandWord.equals("pickup")) {
            pickup(command);
        }       
        else if (commandWord.equals("inventory")) {
            inventory();
        }           
        else if (commandWord.equals("drop")) {
            drop(command);
        }     
        else if (commandWord.equals("back")) {
            back();
        }          
        else if (commandWord.equals("use")) {
            use(command);
        }       
        else if (commandWord.equals("analyse")) {
            analyse();
        }          
        else if (commandWord.equals("password")) {
            password(command);
        }            
        else if (commandWord.equals("run")){
    
            //you can only run when encountering a human or an alien
            if (alienEncounter == true || humanEncounter == true){
                run();
            }
            else{
                System.out.println ("There is nothing here to run from.");
            }
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Displays all of the items in the room
     */
    private void searchRoom() 
    {
        System.out.println();
        System.out.println("You found:");
        
        //Loops through all the room's items and prints each one, by using their getName() method
        for (int i = 0; i < currentRoom.getItems().size(); i++){
            System.out.println(currentRoom.getItems().get(i).getName());
        }
        
        System.out.println();
        parser.showCommands();
    }
    
        /**
     * Moves the player to the previous room they entered
     * ...by accessing the end of the path list and removing that room subsequently
     */
    private void back() 
    {
        if (path.size() == 0){
            System.out.println("You have returned to where you started.");
        }
        else{
            player.move(); //takes away a movement point
            
            //moves the NPCs
            alien.move(currentRoom, true);
            human.move(currentRoom, true);
            corpse.move(currentRoom, true);
            
            currentRoom = path.get(path.size()-1); //changes the current room to the previous one
            path.remove(path.size()-1); //removes that item from the list
            
            System.out.println(currentRoom.getShortDescription()); //says which room they have moved back to
            
            //it would be unfair if you could keep using the back command without initiating any encounters
            //...so encounters with NPCs are also checked for every time you call back
            if (currentRoom == corpse.getRoom() && corpse.getEraseValue() == false){
                corpseEncounter = true;
                corpseEncounterDialogue();
            }
            else if (currentRoom == alien.getRoom() && alien.getEraseValue() == false){
                alienEncounter = true;
                alienEncounterDialogue();
            }
            else if (currentRoom == human.getRoom() && human.getEraseValue() == false){
                humanEncounter = true;
                humanEncounterDialogue();
            }
        }
     }
    
     /**
     * Gives more information about the room
     * or in certain rooms, gives clues for where the key is and how to open the door to it

     */
    private void analyse() 
    {
        System.out.println();
        System.out.println(currentRoom.getLongDescription()); //Gives information about the room in more detail
     }
     
    /**
     * Print out what each command does and how to call them
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println();
        
        
        //Explains all of the commands
        
        System.out.println("Type 'go (direction)' to move");
        System.out.println("Type 'quit' to quit the game");
        System.out.println("Type 'search' to search the room for items");
        System.out.println("Type 'pickup (item)' to place an item in the room into your inventory");
        System.out.println("Type 'analyse' to see more info on the current room you are in");
        System.out.println("Type 'back' to go to the previous room you were in");
        System.out.println("Type 'drop (item)' to remove an item in your inventory and leave it in the room");
        System.out.println("Type 'inventory' to view the items you are carrying");
        System.out.println("Type 'use (item) to use an item");
        System.out.println("Type 'password (password)' to enter a password when in the appropriate room");
        System.out.println("Type 'run' to try and escape an encounter");
        
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command   The command given as an input
     */
    private void goRoom(Command command) 
    {
        if (player.getMovementPoints() == 0){
            explode = true; //If the player has no movement points left they lose
            
        }
        else{
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return;
            }
    
            String direction = command.getSecondWord();
    
            // Try to leave current room.
            Room nextRoom = currentRoom.getExit(direction);
    
            if (nextRoom == null) {
                System.out.println("There is no door!");
            }
            else {
                
                //teleports you to a random room if you move into the magic transporter room
                if (nextRoom.getDescription().equals("You are in the magic transporter room")){
                    
                    System.out.println("You entered the magic transporter room, teleporting you to a random location in the outpost.");
                    nextRoom = rooms.get(rand.nextInt(rooms.size())); //generates a random room
                    
                    //ensures that you don't transport to the magic transporter room
                    while (nextRoom.getDescription().equals("You are in the magic transporter room.")){
                        nextRoom = rooms.get(rand.nextInt(rooms.size())); //Generates a new random room
                        
                    }
                }
                
                //moves the player and the NPCs
                
                player.move();
                alien.move(currentRoom, true);
                human.move(currentRoom, true);
                corpse.move(currentRoom, true);
                
                
                //adds the room to the path list and changes the current room
                
                path.add(currentRoom);
                currentRoom = nextRoom;
                
                System.out.println();
                System.out.println(currentRoom.getShortDescription());
                
                //checks for encounters with NPCs
                // (npc).getEraseValue() checks if the NPC is still interactable
                if (currentRoom == corpse.getRoom() && corpse.getEraseValue() == false){
                    corpseEncounter = true;
                    corpseEncounterDialogue(); //plays the interaction
                }
                else if (currentRoom == alien.getRoom() && alien.getEraseValue() == false){
                    alienEncounter = true;
                    alienEncounterDialogue();
                }
                else if (currentRoom == human.getRoom() && human.getEraseValue() == false){
                    humanEncounter = true;
                    humanEncounterDialogue();
                }
            }
        }
    }
    
        /** 
     * Uses the item given by the second word
     * optionally uses the item on the NPC given by the third word, depending on if the NPC is there and the item
     */
    private void use(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't what item to use...
            System.out.println("Use what?");
            return;
        }
        

        String item = command.getSecondWord();
        
        boolean itemFound = false; //Used later on for when choosing to tell the user if they have used the item or not
        
        //checks the player's inventory for the item and if the player has the item, it is used
        for (int i = 0; i < player.getItemsCarried().size(); i++){
            //If the item given as input is in the inventory...
            if (item.equals(player.getItemsCarried().get(i).getName())){
                itemFound = true;
                
                //Carries out the item function depending on the item
                
                if (item.equals("medkit")){
                    //ensures the player does not heal when at max HP
                    if (player.getHP() != 100){ 
                        player.heal(); 
                        
                        //removes the medkit and the weight of the medkit from the player class
                        player.removeWeightCarrying(player.getItemsCarried().get(i).getWeight()); 
                        player.removeItem(player.getItemsCarried().get(i).getName());
                        System.out.println("You are now at max HP.");
                    }
                    else{
                        System.out.println("You already have max HP.");
                    }
                }
                else if (item.equals("runningShoes")){
                    //Gives the player more movement points
                    player.addTime();
                    System.out.println("You now run faster, giving you more times to move before the time runs out.");
                    
                    //the item is removed after use because otherwise, users would drop it and pick it up repeatedly
                    //... allowing them to get infinite movement points.
                    player.removeItem(player.getItemsCarried().get(i).getName());
                }
                else if (item.equals("watch")){
                    //informs the user how many moves they have left (which in the context of the story is time left)
                    System.out.println("You have " + player.getMovementPoints() + " minutes remaining (minutes = moves left)");                   
                }
                else if (item.equals("screwdriver")){
                    //the screwdriver does not do anything
                    System.out.println("You pulled out the screwdriver. You look at it... it's just a screwdriver.");
                }
                else if (item.equals("toothbrush")){
                    //the toothbrush does not do anything
                    System.out.println("You started brushing your teeth. Despite the outpost about to explode, for some odd reason, you stand there brushing your teeth...");
                }
                else if (item.equals("rock")){
                    //the rock does not do anything
                    System.out.println("You pulled out the rock. It was certainly, without a doubt, a rock.");
                }
                else if (item.equals("goggles")){
                    //the goggles do not do anything
                    System.out.println("You put on the goggles. It is now slightly harder to see but at least they look cool...?");
                }
                else if (item.equals("key")){
                    //The key must have a third word in the command to be used
                    if (command.hasThirdWord() == false){
                        System.out.println("Use the key on what?");
                    }
                    //if the player uses the key at the shuttle, they win the game
                    else if (currentRoom.getDescription() == "You are at the shuttle" && command.getThirdWord().equals("shuttle")){
                        ending();
                    }
                    //if the player gives the key to the other human, they get an alternative ending
                    else if (humanEncounter == true && command.getThirdWord().equals("human")){
                        ending2();
                    }
                    else{
                        System.out.println("The key must be used at the shuttle.");
                    }
                }
                else if (item.equals("gun")){
                    shoot(command);
                }
            }
        }
        
        if (itemFound == false){
            System.out.println("That is not an item in your inventory.");
        }        
    }
    
        /** 
     * Drops the item in the current room, so that it leaves the player's inventory and is added to the room's items.
     */
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();
        
        boolean itemInInventory = false;
        
        //Loops through the inventory for the item
        for (int i = 0; i < player.getItemsCarried().size(); i++){
            //checks if the item is in the inventory
            if (item.equals(player.getItemsCarried().get(i).getName())){
                itemInInventory = true;
                
                currentRoom.addItem(player.getItemsCarried().get(i)); //adds the item to the room
                
                //removes the item and it's weight from the player
                player.removeWeightCarrying(player.getItemsCarried().get(i).getWeight());
                player.removeItem(item);
                break; //required as without it, for example, it could remove all your bullets instead of one
            }
        }
        
        if (itemInInventory == false){
            System.out.println("You do not have this item in your inventory.");
            System.out.println();
        }
        else{
            System.out.println(item + " has been dropped");
        }

    }
    
    /** 
     * Picks up the specified item that is in the room and adds it to the inventory of the player
     * as long as the item is one that can be picked up and the player is not carrying to much to carry it
     */
    private void pickup(Command command) 
    {
    if(!command.hasSecondWord()) {
        // if there is no second word, we don't what to pickup...
        System.out.println("Pickup what?");
        return;
    }

    String item = command.getSecondWord();
        
    //these items cannot be picked up
    if (item.equals("sledgehammer") || item.equals("garbageBin")){
        System.out.println("This item cannot be picked up");
    }
    //the backpack can be picked up regardless of weight carried
    else if (item.equals("backpack")){
        player.addWeightLimit(); //increases the weight limit
        currentRoom.removeItem("backpack"); //removes the item from the room
        System.out.println("You can now carry more weight.");
    }
    else{
        boolean itemPresent = false;
        String itemPickedUp = "";
        boolean lightEnough = false;
            
        //checks if the item is in the room
        for (int i = 0; i < currentRoom.getItems().size(); i++){
            //checks if the current item in the iteration matches the inputted item
            if (item.equals(currentRoom.getItems().get(i).getName())){
                itemPresent = true;
                
                //checks if the item is too heavy for the player to pick up
                if ((currentRoom.getItems().get(i).getWeight() + player.getWeightCarrying()) <= player.getWeightLimit()){
                    //Adds the item to the player's inventory
                    itemPickedUp = currentRoom.getItems().get(i).getName();
                    player.addItem(currentRoom.getItems().get(i));
                    player.addWeightCarrying(currentRoom.getItems().get(i).getWeight());
                    lightEnough = true;
                }
            }
        }
            
    
        if (itemPresent == false) {
            System.out.println("That item is not here.");
        }
        else if (lightEnough == false){
            System.out.println("The item is too heavy for you to carry at the moment.");
        }
        else {
            currentRoom.removeItem(item); //removes the item from the room
            
            System.out.println("You picked up:" + itemPickedUp);
            System.out.println();
            System.out.println(currentRoom.getShortDescription());
            System.out.println();
                
        }
    }
    }

        /** 
     * Displays all of the items the player is carrying
     */
    private void inventory() 
    {
        //checks if the inventory is empty
        if (player.getItemsCarried().size() <1){ 
            System.out.println("Your inventory is empty.");
        }
        
        //prints out the name of each item
        else{
            for (int i = 0; i < player.getItemsCarried().size(); i++){
                System.out.println(player.getItemsCarried().get(i).getName());
        }
        }

    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * Allows the user to enter the password to open the door to the director's office.
     */
    private void password(Command command) 
    {
        //checks if they are by the director's office
        if (currentRoom.getDescription() != "You are outside the director's office"){
            System.out.println("There is no password to enter in this room.");
        }
        else if(!command.hasSecondWord()) {
            System.out.println("EMPTY PASSWORD IS NOT VALID");
        }
        else if (!command.getSecondWord().equals("EXPLORATION")){
            System.out.println("INCORRECT");
        }
        else{
            //Prints the dialogue and drops the key in the room
            
            System.out.println("CORRECT. ACCESS GRANTED.");
            System.out.println("You enter, looking for the key. There is not much time until the place explodes.");
            System.out.println("Sweat runs down your temple.");
            System.out.println();
            System.out.println("Pulling out the director's drawer you find... the key!");
            
            Item key = new Item ("key", 2);
            currentRoom.addItem(key); //adds the key to the room
            
            System.out.println("You can now finally escape, using the key after you pick it up!");
        }

    }
    
    /** 
     * Activates the dialogue for the win event
     */
    private void ending() 
    {
        win = true;
    }
    
    /** 
     * Activates the dialogue for the alternative ending and ends the game
     */
    private void ending2() 
    {
        System.out.println("Man: That's the key!!!");
        System.out.println("Man: Wait... you're giving it to me..?");
        System.out.println("The man takes the key with a confused expression and heads to the shuttle.");
        System.out.println("You sit down. Haven given your only means of escape to someone else, you accept death.");
        System.out.println("But you wonder if maybe it's better this way. Maybe at the end of it all, you finally did something to be proud of.");
        explode = true; //ends the game
    }
    
     /** 
      * Prints dialogue for when you encounter the alien
     */
    private void alienEncounterDialogue() 
    {
        System.out.println();
        System.out.println("You enter the room, looking around you carefully.");
        System.out.println("Suddenly, you feel a wet hand creep over your shoulder...");
        System.out.println("You turn around and see a massive alien in front of you!");
        System.out.println("What will you do?");
        System.out.println("(Options: use gun alien, run)");
        System.out.println();
    }
        
    /** 
    * Occurs when you use the gun on an NPC
    * if you have a bullet and gun in your inventory, it kills the NPC
    */
    private void shoot(Command command) 
    {
        if (!command.hasThirdWord()){
            System.out.println("You must specify who you will shoot. Options: human, alien");
        }
        else{
            //checks the inventory for a bullet
            boolean hasBullet = false;
            for (int i = 0; i < player.getItemsCarried().size(); i++){
                if (player.getItemsCarried().get(i).getName().equals("bullet")){
                    hasBullet = true;
                }
            }
            
            if (hasBullet == false){
                System.out.println("You need a bullet to shoot the gun.");
            }
            else{
                //if there is an alien encounter occurring, you shoot the alien
                if (command.getThirdWord().equals("alien")){
                    if (alienEncounter == true){
                        System.out.println("You shoot the alien in its stomach, as it shrieks in pain and eventually dies.");
                        alien.erase(); //makes it so the alien is no longer interactable
                        alienEncounter = false;
                        player.removeItem("bullet"); //removes the bullet from the player's inventory
                    }
                    else{
                        System.out.println("There is no alien here to shoot.");
                    }
                }
                //if there is a human encounter occurring, you shoot the human
                else if (command.getThirdWord().equals("human")){
                    if (humanEncounter == true){
                        //same code as the alien but for the human instead
                        System.out.println("You swiftly shoot the human between his eyes, before his bullet left his gun. He lays lifeless.");
                        human.erase();
                        humanEncounter = false;
                        player.removeItem("bullet");
                    }
                    else{
                        System.out.println("There is no human here to shoot.");
                    }
                }
            }
        }
    }
    
    
    /** 
     * Provides dialogue for when the corpse is in the same room as the player
     * the corpse will drop the gun in the room
     */
    private void corpseEncounterDialogue() 
    {
        System.out.println();
        System.out.println("You enter the room and see someone dying across the room.");
        System.out.println("Going closer, you can see that it is too late for them. They soon pass on.");
        System.out.println("In their hand, you can see a pistol.");
        Item gun = new Item ("gun", 2);
        currentRoom.addItem(gun); //drops the gun in the room
        corpse.erase(); //becomes no longer interactable
        corpseEncounter = false;
    }
    
    /** 
     * Displays dialogue for when the human is encountered
     */
    private void humanEncounterDialogue() 
    {
        System.out.println();
        System.out.println("You enter the room, seeing a man in there looking at you.");
        System.out.println("Man: Ah, you're looking for the key aren't you? Gonna take the shuttle all for yourself, huh?");
        System.out.println("Man: Well, not on my watch!");
        System.out.println("You can see him reaching for his gun...");
        System.out.println();
        System.out.println("Options: run, use gun human, use (?)");
    }
    
     /** 
     * Allows you try and escape the encounter with a human or alien
     */
    private void run() 
    {
        System.out.println("You try running around and out of the room.");
        int x = rand.nextInt(2); // 0 or 1, which gives you a 50/50 chances to escape
        
        if (x == 0){
            System.out.println("You succesfully make it out unharmed and escape to the adjacent room.");
            currentRoom = currentRoom.getRandExit(); //you escape to a random exit
            System.out.println(currentRoom.getShortDescription());
            alienEncounter = false;
            
            //the NPC will need to move because if the NPC and the player are in adjacent rooms
            //... then they will never interact as they move at the same time.
            //therefore the NPC needs to move as well so there is an odd number of rooms between them
            //this concept is applied multiple times throughout the program
            alien.move(currentRoom, false); 
        }
        else{
            //if the player is at half HP, they die and you lose the game
            if (player.getHP() == 50){
                if (alienEncounter == true){
                    System.out.println("It grabs you by the arm and then rips you to shreds.");
                    System.out.println("You lie there, lifeless, until the time runs out.");
                    explode = true; //you lose
                }
                else if (humanEncounter == true){
                    System.out.println("You make a break for it, but his gun is faster.");
                    System.out.println("You are shot in the stomach and left there bleeding, until the time runs out.");
                    explode = true;
                }
            }
            else{
                //if you get hit but are at full HP, you survive and escape to a random room whilst taking damage
                if (alienEncounter == true){
                    System.out.println("It grabs you by the arm and slashes your body... but you escape.");
                    player.injure(); //lowers the player's HP
                    currentRoom = currentRoom.getRandExit();
                    System.out.println(currentRoom.getShortDescription());
                    alienEncounter = false;
                    
                    //why all NPCs move here is explained under if (x==0) above
                    alien.move(currentRoom, false);
                    corpse.move(currentRoom, true);
                    human.move(currentRoom, true);
                }
                else if (humanEncounter == true){
                    System.out.println("He shoots you in your arm... but you get away before he can finish you off.");
                    player.injure();
                    currentRoom = currentRoom.getRandExit();
                    System.out.println(currentRoom.getShortDescription());
                    humanEncounter = false;
                    human.move(currentRoom, false);
                    alien.move(currentRoom, true);
                    corpse.move(currentRoom, true);
                }
            }
        }
        
    }
}