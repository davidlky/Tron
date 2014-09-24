import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 3 Head
 */
public class Player3Head extends PlayerHeads
{
    /**
     * Act - do whatever the Player3Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //check if it hit the red or blue head
        redHeadHit();
        blueHeadHit();
    }    
}
