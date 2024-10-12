

/**
 *This class is part of the "Cosmic Escape" application.
 *
 *This class holds information about each NPC along with methods to allow them to interact with the game
 *There are 3 NPCs in the game: human, corpse and alien
 *The corpse is moving around and when in the same room as the player, it is found as a dead body
 *The human and alien will initiate dialogue and then try to kill the player when in the same room
 * 
 */
public class Npc
{
    private Room currentRoom; //the room the NPC is in
    private String type; //identifies which of the 3 NPCs (human, alien, corpse) this NPC is
    private boolean erase; //determines if the NPC exists or has been erased
    
    /**
     * Creates a NPC
     * @param room Stores the room that the NPC starts in
     * @param type Stores the type of NPC that it is
     */
    public Npc(Room room, String type)
    {
        currentRoom = room;
        this.type = type;
        erase = false; //every NPC starts off as existing
    }

    /**
     * Moves the NPC to a new room
     *
     * @param room After the player runs from an NPC, the NPC cannot move to the same room as the player
     * this parameter specifies the room that the NPC cannot move to
     * @param justMove This parameter specifies whether there is a restriction on the room they can move to,
     * given by the first parameter
     */
    public void move(Room room, boolean justMove)
    {
        // Parameters are explained in method description
        if (justMove == true){
            currentRoom = currentRoom.getRandExit();
            
        }
        else{
            boolean stop = false;
            while (stop == false){
                Room temp = currentRoom.getRandExit();
                
                //Ensures that the room they move to isn't the restricted room, nor the same room they are currently in
                if ((temp != room) && (temp != currentRoom)){
                    stop = true;
                    currentRoom = temp;
                    
                }
            }
           
            
        }
    }
    
    /**
     * Getter method for the currentRoom attribute
     * @return  currentRoom
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Changes the erase attribute so that the NPC is no longer interactable
    
     */
    public void erase()
    {
        erase = true;
    }
    
    /**
     * @return    erase
     */
    public boolean getEraseValue()
    {
        return erase;
    }
}
