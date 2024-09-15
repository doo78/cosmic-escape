import java.util.ArrayList;

/**
 * This class is part of the "Cosmic Escape" application.
 *
 * It contains information about the player and controls how they interact with the game.
 * 
 * @author Ricky Gordon k23075676
 */
public class Player
{
    private int weightCarrying; //tracks how much weight is being carried
    private int weightLimit;    //will not allow more weight than this
    private int hp; //tracks the hp
    private int movementPoints; //tracks how long the player can move before they lose
    private ArrayList<Item> itemsCarried; //contains what the player is holding in their inventory

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        weightCarrying = 0; //starts at 0
        weightLimit = 6; //the limit is 6
        hp = 100; //starts at 100, which is max
        movementPoints = 20; //they can move 20 times until the outpost explodes
        itemsCarried = new ArrayList<Item>(); //initialises the array list
    }

    /**
     * @return    movementPoints
     */
    public int getMovementPoints()
    {
        return movementPoints;
    }
    
    /**
     * @return   hp
     */
    public int getHP()
    {
        return hp;
    }

    /**
     * @return    weightCarrying
     */
    public int getWeightCarrying()
    {
        return weightCarrying;
    }
    
    /**
     * @return    itemsCarried
     */
    public ArrayList<Item> getItemsCarried()
    {
        return itemsCarried;
    }
    
    /**
     * Adds the weight to the player's load
     *
     * @param  the weight of the item being added
     */
    public void addWeightCarrying(int weight)
    {
        weightCarrying = weightCarrying + weight;
    }
    
    /**
     * Removes weight from the player's load
     *
     * @param  the weight of the item being removed
     */
    public void removeWeightCarrying(int weight)
    {
        weightCarrying = weightCarrying - weight;
    }
    
    /**
     * @return    weightLimit
     */
    public int getWeightLimit()
    {
        return weightLimit;
    }
 
    /**
     * Increases the weight limit for when the backpack is picked up
     */
    public void addWeightLimit()
    {
        weightLimit = weightLimit + 4;
    }
    
    /**
     * Adds the specified item to the inventory
     * @param item    The item
     */
    public void addItem(Item item)
    {
        itemsCarried.add(item);
    }
    
    /**
     * Removes the specified item from the inventory
     * 
     * @param item  The specified item
     */
    public void removeItem(String item)
    {
        //locates the item in the inventory
        for (int i = 0; i < itemsCarried.size(); i++){
            
            if (item.equals(itemsCarried.get(i).getName())){
                itemsCarried.remove(i);
                break; //so it doesn't remove all instances of an item e.g. all bullets
            }
        }       
    }
    
    /**
     * Increases hp when the medkit is used
     */
    public void heal()
    {
        hp = hp + 50;
    }
    
    /**
     * Adds movements points when the running shoes are used
     */
    public void addTime()
    {
        movementPoints = movementPoints + 5;
    }
    
    /**
     * Decreases movement points every time the player moves
     */
    public void move()  
    {
        movementPoints = movementPoints -1;
    }
    
    /**
     * Decreases HP when the player is hurt by the alien or human
     */
    public void injure()
    {
        hp = hp - 50;
    }
}
