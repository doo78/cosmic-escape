
/**
 * This class is part of the "Cosmic Escape" application.
 *
 *This class is used for the items that are used by the player and found in the rooms of the game
 *They can be used, dropped and picked up using the various commands in the game
 *
 */
public class Item
{
    private int weight; //required because the user has a weight carrying limit
    private String name; //name of the item
    

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, int weight)
    {
        // initialise instance variables
        this.weight = weight;
        this.name = name;
    }

    /**

     * @return    name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return    weight
     */
    public int getWeight()
    {
        return weight;
    }   
    
}
