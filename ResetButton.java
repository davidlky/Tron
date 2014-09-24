import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Reset Button
 */
public class ResetButton extends Buttons
{
//import image that shows reset button
    private GreenfootImage button_1 = new GreenfootImage("ResetButton.png"); 
    public ResetButton() {
        //when initiallized, show that image which will never change
        setImage(button_1);  
    }
}
