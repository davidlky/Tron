import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All the Body block of players
 * inherit codes from this and will run.
 */
public class PlayerBlocks extends Actor
{
    //touch the red head
    protected void touchRedHead(){
        //get the mainprogram variables by making a new world variable
        MainProgram world = (MainProgram)getWorld();
        //check first if game is still running and NOT paused (basically when the game should actually run)
        if (world.gameRunning() && !world.pause()){
            if (world.redHeadX() ==getX() && world.redHeadY() ==getY()){
                //turn off the game for second player
                world.switchGame(2);
            }
        }
    }
    //touch the blue head
    protected void touchBlueHead(){
        //get the mainprogram variables by making a new world variable
        MainProgram world = (MainProgram)getWorld();
        //check first if game is still running and NOT paused (basically when the game should actually run)
        if (world.gameRunning() && !world.pause()){
            if (world.blueHeadX() ==getX() && world.blueHeadY() ==getY()){
                //turn off the game for first player
                world.switchGame(1);  
            }
        }
    }
    //touch the yellow head
    protected void touchYellowHead(){
        //get the mainprogram variables by making a new world variable
        MainProgram world = (MainProgram)getWorld();
        //check first if game is still running and NOT paused (basically when the game should actually run)
        if (world.gameRunning() && !world.pause()){
            if (world.yellowHeadX() ==getX() && world.yellowHeadY() ==getY()){
                //turn off the game for third player
                world.switchGame(3);
            }
        }
    }
}
