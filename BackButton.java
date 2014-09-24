import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Back Button
 */
public class BackButton extends Buttons
{
    //create two private images of the back button that can be shown under diffrent circumstances
    private GreenfootImage button_1 = new GreenfootImage("BackButton1.png"); 
    private GreenfootImage button_2 = new GreenfootImage("BackButton2.png");
    
    public BackButton() {
        //when constructed, the image will be the first image
        setImage(button_1);  
    }    
    
    public void act(){
        if (Greenfoot.mousePressed(this)){
            //the act method states that when the mouse is pressing down on this, the image will be diffrent
            setImage(button_2);
        }
    }
}
