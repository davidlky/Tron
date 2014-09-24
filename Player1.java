import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 1 body block
 */
public class Player1 extends PlayerBlocks
{
    public void act() 
    {
        //make a new variable for MainProgram every time
        MainProgram world= (MainProgram)getWorld();
        //check if self hit or hit second player
        touchRedHead();
        touchBlueHead();
        //see if the player choice is two player or not, if not
        if(!world.playerChoice()){
            //check for yellow Head touch too!
            touchYellowHead();
        }
    }
}