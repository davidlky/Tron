import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player Choice Button
 */
public class PlayerButton extends Buttons
{
    //make two image file, one fore 2 player button one for 3 player button
    private GreenfootImage button_1 = new GreenfootImage("2PButton.png"); 
    private GreenfootImage button_2 = new GreenfootImage("3PButton.png");
    
    public PlayerButton(){
        //in the beggining the player selection is 2 player, so it will show the second image which is the choice to do three player
        setImage(button_2);
    }
    public void act() 
    {
        //use the method in the Buttons Class
        imageSet2(button_1, button_2);
    }
}    
