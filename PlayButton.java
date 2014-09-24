import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Play/Pause Button
 */
public class PlayButton extends Buttons
{
//import the two image one showing play one showing pause
    private GreenfootImage button_1 = new GreenfootImage("PlayButton.png"); 
    private GreenfootImage button_2 = new GreenfootImage("PauseButton.png");
    public PlayButton() {
        //since in the begging the game isnt running, set it to "play" for now
        setImage(button_1);  
    }

    public void act(){
        //use the method from Buttons to set the image thats apporpreate for diffrent time, feed the image to the method
        imageSet(button_1, button_2);
    }
}