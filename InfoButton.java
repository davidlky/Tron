import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Info Button
 */
public class InfoButton extends Buttons
{
    private GreenfootImage button_1 = new GreenfootImage("InstructionButton.png"); //created a private image of the instruction button
    public InfoButton() {
        //when the button is constructed, the button will be set to this, and it will not change under all circumstances
        setImage(button_1);  
    }
}
