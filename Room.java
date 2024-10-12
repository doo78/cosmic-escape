import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Cosmic Escape" application.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 */

public class Room 
{
    private String description;  //Gives a short description of the room
    private HashMap<String, Room> exits;  // stores exits of this room.
    private ArrayList<Item> items; //stores the items in the room
    private String longDescription; //gives a long description for when the 'analyse' command is used
    private Random rand; //used for generating random numbers
    
    /**
     * Create a room 
     * 
     * @param description The room's description.
     */
    public Room(String description, ArrayList<Item> items, String longDescription) 
    {
        //Initialises the variables
        
        this.description = description;
        this.items = items;
        this.longDescription = longDescription;
        exits = new HashMap<>();
        rand = new Random();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

        /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public int exitsSize() 
    {
        return exits.size();
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description + ".\n" + getExitString();
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return longDescription;
    }
    
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Removes the specified item from the list of items in the room
     * 
     * @param item   The name of the item that is to be removed
     */
    public void removeItem(String item) 
    {
        //locates the item in the arraylist and then removes it
        for (int i = 0; i < items.size(); i++){
            
            if (item.equals(items.get(i).getName())){
                items.remove(i);
            }
        }       
    }
    
    /**
     * Adds the specified item to the list of items in the room
     * 
     * @param item  The specified item
     */
    public void addItem(Item item) 
    {
        items.add(item);   
    }
    
    /**
     * @return   the description
     */
    public String getDescription() 
    {
        return description;  
    }
    
    /**
     * Returns a random exit of the current room
     * 
     * @return a random exit
     */
    public Room getRandExit() 
    {
        Set<String> keys = exits.keySet(); //creates a set of the possible directions for the exit
        //turns the set into an array and generates a random index in that array to use as a parameter for the get method
        //...to select a random exit
        return exits.get(keys.toArray()[rand.nextInt(keys.size())]); 
    }
}

