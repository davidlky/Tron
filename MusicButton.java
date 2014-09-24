import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sound Button
 */
public class MusicButton extends Buttons
{
//make two private images that will appear at diffrnet times
    private GreenfootImage button_1 = new GreenfootImage("soundOn.png"); 
    private GreenfootImage button_2 = new GreenfootImage("soundOff.png"); 

    public MusicButton() {
        //when constructed set it as false
        setImage(button_2);  
    }
    public void act(){
        //get the world for this act everytime it runs
        MainProgram world = (MainProgram)getWorld();
        //see if the sound is on in the world
        if(world.soundCheck()){
            //if it is set the image on the first one
            setImage(button_1);
        } if(!world.soundCheck()){
            //if its not, set the image to the second image
            setImage(button_2);
        }
    }
}
