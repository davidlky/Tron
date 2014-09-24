import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player 3 body block
 */
public class Player3 extends PlayerBlocks
{
    public void act() 
    {
       //player three check for touching itself or any other player
       touchRedHead();
       touchBlueHead();
       touchYellowHead();
    }    
}
