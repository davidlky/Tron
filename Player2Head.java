import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 2 Head Block
 */
public class Player2Head extends PlayerHeads
{
    /**
     * Act - do whatever the Player2Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //check if it hit the blue head
        blueHeadHit();
    }    
}
