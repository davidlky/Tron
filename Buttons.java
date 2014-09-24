import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Button Actors
 *These will contain codes for all the buttons in this program
 */
public class Buttons extends Actor
{
    //methods for showing which type of image during which situation
    protected void imageSet(GreenfootImage a, GreenfootImage b){
        //make a variable for the world so it can recieve the accessor method in the world
        MainProgram world = (MainProgram)getWorld();
        //if the game is running and the world isnt paused, these methods ARE in the MainProgram World
        if (world.gameRunning()&& !world.pause()){
            //set the image to one
            setImage(b); 
            //if the game is not running
        } else if (!world.gameRunning()){
            //set the image to the first one
            setImage(a); 
            //if the game is paused
        } else if (world.pause()){
            //also set the timage to the first one
            setImage(a); 
        }
    }
    
    //methods for showing which type of image during which situation
     protected void imageSet2(GreenfootImage a, GreenfootImage b){
         //make a variable for the world so it can recieve the accessor method in the world
        MainProgram world = (MainProgram)getWorld();
        //if the game choice is to be two player
        if (world.playerChoice()){
            //set the image to the second one
            setImage(b); 
            //if not two player
        } else {
            //set the image to the first one
            setImage(a);             
        } 
    }
}
