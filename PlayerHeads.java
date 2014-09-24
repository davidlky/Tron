import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Head of the Player's action
 */
public class PlayerHeads extends Actor
{
    //method for hitting the blue head (head on head collision)
    public void blueHeadHit(){
        //make a variable for the main program to get it everytime this is runned
        MainProgram world = (MainProgram)getWorld();
        //if the game is still running
        if( world.gameRunning()){
            //if the location are the same
            if (getX() == world.blueHeadX() &&getY() == world.blueHeadY()){
                //stop the game from running
                world.gameRunningChange();
            }
        }
    }
    //method for hitting the red head (head on head collision)
    public void redHeadHit(){
        //make a variable for the main program to get it everytime this is runned
        MainProgram world = (MainProgram)getWorld();
        //if the game is still running
        if( world.gameRunning()){
            //if the location are the same
            if (getX() == world.redHeadX() &&getY() == world.redHeadY()){
                //stop the game from running
                world.gameRunningChange();
            }
        }
    
    }
    
    //method for hitting the yellow head (head on head collision)
    public void yellowHeadHit(){
        //make a variable for the main program to get it everytime this is runned
        MainProgram world = (MainProgram)getWorld();
        //if the game is still running
        if( world.gameRunning()){
            //if the location are the same
            if (getX() == world.yellowHeadX() &&getY() == world.yellowHeadY()){
                //stop the game from running
                world.gameRunningChange();
            }
        }
    }
    //NOTE: head on head collision means NO ONE gets a point, therefore no one gets the point
}
