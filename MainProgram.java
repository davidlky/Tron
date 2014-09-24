import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; //import the list utility 
import java.util.ArrayList; //import the arraylist utility
import java.util.Random; //import random generation utility
import java.awt.Color; //import the colour names
/**
 * The Main program that runs to codes
 * 
 * David Liu 
 * v.3.4
 * Began working: November 12, 2012
 * This: Janurary 21, 2013
 * 
 * Basically, How the game works is similar to light cycle in Tron, what you would wish to do is to not die from hiting yourself or the borders or the body of other player. You may not turn backward to hit your previous path, as then youll hit yourself.
 * The aim of this game is to not die and to beat the other player the best you can. This game will support only two and three player. The keys are shown in the controls screen or the info for this. It is strongly suggested you try to play more defensive as 
 * this is basically a "last man standing" game! Some of the features include the score counter, player selection, a pause button, music button, isntructions and control popup. Note that some keys will not work during game and certain times for better outlook. 
 * Head on head colision means no one gets a point and the game will end! Get Crashing!
 * NOTE: for more fair game, there is a one second delay before game start so that whoever clicks the play button has enough time to come back to keyboard and get ready!
 * NOTE: to replay after winner decided, hit the enter button can work too!
 * All picture and button credits go to me
 * 
 * Suggested features to try out: All buttons that can be pressed, head on head collision, 3 player mode for sure, sound and mute!
 * Font for info and control screen is got at dafont.com, the font name is called Tron
 * Music: thank you to Daft Punk, no copyright intended, all rights are still reserved to the producer to this music. This was the original sountrack to "Tron: Legacy" the movie, which inspired me to do this game! Downloaded at 320kps hi quality at Itunes. 
 * 
 * Bug Report: NONE!
 */

/**this will be the main program coding, including various tools such as player movement
 * reseting game
 * button functions
 * all of the reactions from othe Actors will eventually be used here to react for the game
 */

public class MainProgram extends World
{
    //create lists and actual object of the class of the diffrent players, constructed now
    private Player1Head blueHead = new Player1Head();
    private Player2Head redHead = new Player2Head();
    private Player3Head yellowHead = new Player3Head();
    //NOTE: arrays are not dynamics, and for easier coding, arraylist's dynamic feature will prove usefulness here, constructed now
    private List<Player1> player1Blocks = new ArrayList<Player1>(1);
    private List<Player2> player2Blocks = new ArrayList<Player2>(1);
    private List<Player3> player3Blocks = new ArrayList<Player3>(1);
    //then create the counters, basically the score keeper
    private Counter blueCounter, redCounter, yellowCounter;
    //create all integers 
    //first are the blocks count for each other of the players, having its own variable for easier code interpretation
    private int player1BlocksCount, player2BlocksCount, player3BlocksCount;
    //next come the scores of each players
    private int blueScore, redScore, yellowScore;
    //and also the location of the head, having its own variable for easier code interpretation
    private int x1, y1, x2, y2, x3, y3;
    //next create all the short
    //create the constant of the width it is moving by
    private short WIDTH = 15;
    //create the directions, each number stand for a diffrent direction
    private short direction1,direction2,direction3;
    //lastly, the booleans of true or false instances on what is going on while game is running
    //first come variable for is game running, is game paused, is game running for first time
    private boolean game, pause, firstTime;
    //then comes the boolean for the three diffrent colour, basically saying if each colour is still in the game
    private boolean blue, red, yellow;
    //lastly, the extra true or false of how many players, and clicking allowed or not.
    private boolean twoPlayer, clicking;
    //all next will be the buttons actors, constructed now, each having its own type
    private PlayButton playButton = new PlayButton();
    private ResetButton resetButton = new ResetButton();
    private PlayerButton playerButton = new PlayerButton();
    private ControlButton controlButton = new ControlButton();
    private MusicButton musicButton = new MusicButton();
    private InfoButton infoButton = new InfoButton();
    //back button will be special, and constructed later when used, this will prove useful later on.
    private BackButton backButton;
    //new random number generator, constructed now
    private Random generator  = new Random();
    //new sound, already assigned as well
    private GreenfootSound music = new GreenfootSound("Derezzed.mp3");
    /**
     * Constructor for objects of class MainProgram.
     * this will be the initial look with eveyrthing already prepared!
     */
    public MainProgram()
    {    
        // Create a new world with 900x600 cells with a cell size of 1x1 pixels.
        super(900, 600, 1); 
        //initially, the game will not be running, and not paused, and this will be the first ever game for this session
        game =false;
        pause = false;
        firstTime = true;
        //in case some hackers trying to break the game and crash the system, im just setting the speed for the game now
        Greenfoot.setSpeed(50);
        //it will start out as 2 players
        twoPlayer = true;  
        //clicking is allowed for all buttons
        clicking = true;
        //initialize the game look for amount the player selected
        game(twoPlayer);
        //initialize the score keepers for all three
        blueCounter = new Counter("Player 1", 1);
        redCounter = new Counter("Player 2", 2);
        yellowCounter = new Counter("Player 3", 3);
        //since only two player for now, so only the blue and red score will be visible, for now
        addObject (blueCounter, 810, 450);
        addObject (redCounter, 810, 500);
        //place all the buttons on screen too
        addObject (playButton, 815, 130);
        addObject (resetButton, 815, 190);
        addObject (playerButton, 815, 250);
        addObject (infoButton, 815, 310);
        addObject (controlButton, 815, 370);
        addObject (musicButton, 815, 415);
    }

    /**
     * act method for class MainProgram.
     * this will be the actually how the game will work, linking eveyrthing together
     * for proper paradigm, this will consist of merely methods
     */

    public void act(){
        //if not pasued and game is running
        if (game && !pause){
            //check for puase button clicking
            pauseButton();
            //delay every move
            Greenfoot.delay(5);
            //check for last person standing before anything starts
            lastManStanding();
            //after the check, if game is still true
            if (game){
                //play the blue block game
                blueblockGame();
                //check again if game should keep running
                lastManStanding();
                //if so
                if (game){
                    //red runs and does its thing
                    redblockGame();
                    //check another time
                    lastManStanding();
                    //if their player is allowed in
                    if(!twoPlayer&& game){
                        //run yellow's game movements
                        yellowBlockGame();
                        //check for any thing thats suppose to die
                        lastManStanding();
                    }
                }
            }
            //if the game is still running
            if (game){
                //check again for last person standing because some might be missed out
                lastManStanding(); 
            }
            //if the game isnt running or its paused
        } else {
            //check for the clicking on the other buttons
            infoButton();
            playButton();
            resetButton();
            playerButton ();
            controlButton();
            backButton();
            //update the score!
            redCounter.changeScore(redScore);
            blueCounter.changeScore(blueScore);
            yellowCounter.changeScore(yellowScore);  

            //check if the player wants the game restarted, unless this is first time running the game, clicking is REQUIRED
            if(!firstTime){
                enterButton();
            }
        }  
        //no matter whats going on with the game, the clicking to disable the music is always available
        musicButton();

    }

    /**methods for the game
    button methods*/

    //information button clicking
    private void infoButton(){
        //check if clicked and clicking this is allowed
        if (Greenfoot.mouseClicked(infoButton)&&clicking) {
            //disbale clicking for all other buttons
            clicking = false;
            //set a new image for this button, making it look like a pop up
            GreenfootImage image  = new GreenfootImage("Info Screen 2.png");
            infoButton.setImage(image);
            //also, set the location so it fits
            infoButton.setLocation (400,300);
            //add a bakc button so the user can exit this popup
            backButton = new BackButton();
            addObject (backButton, 630,523);
            //make sure this appears in the front!
            setPaintOrder(BackButton.class,InfoButton.class);
        }
    }

    //music button clicking
    private void musicButton(){
        //since this button can be clicked at all times, just check if its clicked
        if (Greenfoot.mouseClicked(musicButton)) {
            //so if the music is clicking, stop it
            if(music.isPlaying()){
                music.pause();
                //if its not playing then play it
            } else{
                music.play();
            }
        }
    }

    //controls button clicking
    private void controlButton(){
        //similar layout to the information button clicking
        //so check if clicked and if its allowed to be clicked
        if (Greenfoot.mouseClicked(controlButton)&&clicking) {
            //disable clicking for all others
            clicking = false;
            //set new image for this button, making it look like a pop up
            GreenfootImage image  = new GreenfootImage("Info Screen.png");
            controlButton.setImage(image);
            //set new location so it fits
            controlButton.setLocation (400,300);
            //add back button to exit the pop up
            backButton = new BackButton();
            addObject (backButton, 630,124);
            //make sure this is in the front!
            setPaintOrder(BackButton.class,ControlButton.class);
        }
    }

    //reset button clicking
    private void resetButton(){
        //similar layout to the information button clicking
        if (Greenfoot.mouseClicked(resetButton)&&clicking) {  
            //set all the scores back to 0
            blueScore = 0;
            redScore =0;
            yellowScore = 0;
        }
    }

    //play button clicking
    private void playButton(){
        //if clicked and clicking on this is allowed
        if (Greenfoot.mouseClicked(playButton)&&clicking) {    
            //if the game is NOT running
            if (!game){
                //if clicking for the first time
                if (firstTime){
                    //first time is no longer true
                    firstTime = false;
                    //music start playing
                    music.playLoop();
                } else{
                    //if it is not the first time then everytime you clcik this button the game will reset
                    game(twoPlayer);
                    //sure a 1 second delay before the game actually start so players can get ready
                    Greenfoot.delay(60);
                }
                game =true;
                //if the game is not stopped then unpause 
            } else {
                pause = false;
            }
        }
    }

    //amount of player selection button clicking
    public void playerButton (){
        //if the game is not going or paused
        if(!game || pause){
            //check if this is clicked and if clicking is also enabled
            if(Greenfoot.mouseClicked(playerButton)&&clicking){
                //if two player is running right now, if so, flip it
                if(twoPlayer){
                    twoPlayer = false;                    
                } else {
                    twoPlayer = true;
                }
                //this will be the first time running it everytime you switch, so fist time is true
                firstTime=true;
                //reset the game to whichever set
                game(twoPlayer);
                //reset the scores also!
                blueScore = 0;
                redScore =0;
                yellowScore = 0;
            }
        }
    }

    //pause button clicking
    private void pauseButton(){
        //if it is clicked and clicking is enabled
        if (Greenfoot.mouseClicked(playButton)&&clicking) {  
            //set pause to true
            pause =true;
        }
    }

    //back button clicking
    private void backButton(){
        if (Greenfoot.mouseClicked(backButton)) {
            //enable clicking of other buttons
            clicking =true;
            //create and set the images for the two pop up to its oringinal button images
            GreenfootImage image1  = new GreenfootImage("InstructionButton.png");
            infoButton.setImage(image1);
            infoButton.setLocation (815,310);
            GreenfootImage image2  = new GreenfootImage("ControlButton.png");
            controlButton.setImage(image2);
            controlButton.setLocation (815,370);
            //take this back button away fromt the world as it is no longer needed
            removeObject(backButton);
        }
    }

    /**methods for this game
     * Key Pressing Method: Enter Key for pause and unpause!
     */

    private void enterButton(){
        //if the key enter is pressed
        if(Greenfoot.isKeyDown("enter")){
            //regenerate game and change game to true so it starts running!
            game(twoPlayer);
            Greenfoot.delay(30);
            game = true;
        }
    }

    /**methods for the game
    game running checking methods*/
    private void lastManStanding(){
        //if the game is not two players
        if (!twoPlayer){
            //if yellow and blue are both out
            if(!yellow && !blue){
                //red wins and add score and the game is no longer running
                redScore ++;
                game = false;
                //if yellow and red are out
            }else if(!yellow && !red){
                //add to blue score and the game is no longer going to run
                blueScore ++;
                game = false;
                //if red and blue are out
            } else if (!red && !blue){
                //yellow gets the point and the game is no longer running
                yellowScore++;
                game = false;
            }
            //if two players
        } else {
            //if one is running and the other isnt, add score to the other one and disable the game
            if (!red){
                blueScore++;
                game = false;
            }else if (!blue){
                redScore++;
                game = false;
            }
        }
    }

    //the movements for blue blocks
    private void blueblockGame(){
        //get the direction in the beginning from the method that recieves info on what button is pressed
        short direction = blueDirection();
        //if the game is still running for blue
        if(blue){
            //add one more block to the index of player 1 blocks list, this will also initialize it
            player1Blocks.add(new Player1());
            //make the object appear on screen at the location of where the head of this player was
            addObject (player1Blocks.get(player1BlocksCount),x1, y1);
            //check what direction it is
            if (direction==1){
                //left
                x1-=WIDTH;
                //set direction appropriately 
                direction1 = 1;
            } else if (direction ==4){
                //right
                x1+=WIDTH;
                //set direction appropriately 
                direction1 = 4;
            } else if (direction==3){
                //up
                y1-=WIDTH;
                //set direction appropriately 
                direction1 = 3;
            } else if (direction ==2){
                //down
                y1+=WIDTH;
                //set direction appropriately 
                direction1 = 2;
            }
            //add 1 to the counter that keeps track of all the blocks of player 1
            player1BlocksCount++;
            // see if the head block is still within the border
            if (x1>=25 &&x1<=730 &&y1>=25 &&y1<=585){
                //set the location at the new location that is changed from the past after the above if statements
                blueHead.setLocation(x1, y1);
                //if it is no longer within borders, blue is out of the game
            }   else {
                blue =false;
            }
        }
    }

    //the movements for red block game!
    private void redblockGame(){
        //get the direction in the beginning from the method that recieves info on what button is pressed
        short direction = redDirection();
        //if the game is still running for red
        if (red){
            //add one more block to the index of player 2 blocks list, this will also initialize it
            player2Blocks.add(new Player2());
            //make the object appear on screen at the location of where the head of this player was
            addObject (player2Blocks.get(player2BlocksCount),x2, y2);
            //check what direction it is
            if (direction ==1){
                //left
                x2-=WIDTH;
                //set direction appropriately 
                direction2 = 1;
            } else if (direction ==4){
                //right
                x2+=WIDTH;
                //set direction appropriately 
                direction2 = 4;
            } else if (direction ==3){
                //up
                y2-=WIDTH;
                //set direction appropriately 
                direction2 = 3;
            } else if (direction ==2){
                //down
                y2+=WIDTH;
                //set direction appropriately 
                direction2 = 2;
            }
            //add 1 to the counter that keeps track of all the blocks of player 2
            player2BlocksCount++;
            //which the head block is still within the border
            if (x2>=25 &&x2<=730 &&y2>=25 &&y2<=585){
                //set the location at the new location that is changed from the past after the above if statements
                redHead.setLocation(x2, y2);
                //if it is no longer within borders, red is out of the game
            }   else {
                red =false;
            }
        }
    }

    //the movements for yellow block game
    private void yellowBlockGame(){
        //get the direction in the beginning from the method that recieves info on what button is pressed
        short direction = yellowDirection();
        //if the game is still running for yellow
        if(yellow){
            //add one more block to the index of player 3 blocks list, this will also initialize it
            player3Blocks.add(new Player3());
            //make the object appear on screen at the location of where the head of this player was
            addObject (player3Blocks.get(player3BlocksCount),x3, y3);
            //check what direction it is
            if (direction ==3){
                //left
                x3-=WIDTH;
                //set direction appropriately 
                direction3 = 3;
            } else if (direction ==4){
                //right
                x3+=WIDTH;
                //set direction appropriately 
                direction3 = 4;
            } else if (direction ==2){
                //up
                y3-=WIDTH;
                //set direction appropriately 
                direction3 = 2;
            } else if (direction ==1){
                //down
                y3+=WIDTH;
                //set direction appropriately 
                direction3 = 1;
            }
            //add 1 to the counter that keeps track of all the blocks of player 3
            player3BlocksCount++;
            //which the head block is still within the border
            if (x3>=25 &&x3<=730 &&y3>=25 &&y3<=585){
                //set the location at the new location that is changed from the past after the above if statements
                yellowHead.setLocation(x3, y3);
                //if it is no longer within borders, yellow is out of the game
            }   else {
                yellow =false;
            }
        }
    }

    //game reseting method
    private void game (boolean two){
        //remove the yellow score keeper no matter what
        removeObject(yellowCounter);
        //take off the head block of all blocks
        removeObject(blueHead);
        removeObject(redHead);
        removeObject(yellowHead);
        //take off the body blocks for each player, each gets its own for loop as some have diffrent lengths
        for (int i =0; i<player1BlocksCount; i++){
            removeObject(player1Blocks.get(i));
        }
        for (int i =0; i<player2BlocksCount; i++){
            removeObject(player2Blocks.get(i));
        }
        for (int i =0; i<player3BlocksCount; i++){
            removeObject(player3Blocks.get(i));
        }
        //regenerate where the locations are set at for both blue and red, each will be starting 15 pixel "blocks" and its just finding how many blocks it is at
        //basically getting an integer for an amount of spaces of 15 added to the length that is preset so there is no bumping to walls or players as soon as game start
        x1 = (generator.nextInt(20))*15+80;
        y1 = (generator.nextInt(25))*15+110;
        x2 =(generator.nextInt(20))*15+320;
        y2 = (generator.nextInt(25))*15+110;
        //reset the block count!
        player1BlocksCount = 0;
        player2BlocksCount = 0;
        //find the direction to go in, they will add difrent number to avoid the selection of direction as some will appear close to certain wall and they should not head toward that direction
        direction1 = (short)(generator.nextInt(2)+2);
        direction2 = (short)(generator.nextInt(2)+1);
        //add the two head at their location so the player where they are satrting at
        addObject (blueHead,x1,y1);
        addObject (redHead,x2,y2);
        //set the game for them to be true
        blue =true;
        red = true;
        //if it is not two player
        if (!two){
            //find the direction of the thrid player, again following an algarithum avoiding immediate death
            direction3 =(short)(generator.nextInt(2)+1);
            //reseting the counter
            player3BlocksCount = 0;
            //again, generating the location for yellow, with the algerithum to separate from other players
            x3 = (generator.nextInt(15))*15+500;
            y3 = (generator.nextInt(20))*15+200;
            //add the head object so player know location
            addObject(yellowHead, x3, y3);
            //set the game for yellow to be true
            yellow= true;
            //add the score keeper for yellow
            addObject (yellowCounter, 810, 550);
        }
    }

    //directions for blue player
    private short blueDirection(){
        //make a inner direction too see what key pressed and compare that to previous keys
        short direction = direction1;
        //if key A is down, and the last diretion was not th opposite direction, set this number
        if (Greenfoot.isKeyDown("A")&&direction1 !=4){
            direction =1;
            //same type of thing expcpet for D key
        }else if (Greenfoot.isKeyDown("D")&&direction1 !=1){
            direction =4;
            //similar for key W
        }else if (Greenfoot.isKeyDown("W")&&direction1 !=2){
            direction =3;
            //same
        }else if(Greenfoot.isKeyDown("S")&&direction1 !=3){
            direction =2;
        }
        //return the number associated
        return direction;
    }

    //direction for red player
    private short redDirection(){
        //make a inner direction too see what key pressed and compare that to previous keys
        short direction = direction2;
        //If key LEFT is down and the last key wasnt the opposite direction, set this number
        if (Greenfoot.isKeyDown("left")&&direction2 !=4){
            direction =1;
            //similar for right clicking
        }else if (Greenfoot.isKeyDown("right")&&direction2 !=1){
            direction =4;
            //similar for up key
        }else if (Greenfoot.isKeyDown("up")&&direction2 !=2){
            direction =3;
            //similar for down key
        }else if(Greenfoot.isKeyDown("down")&&direction2 !=3){
            direction =2;
        }
        //return the number associated
        return direction;
    }

    //direction for yellow player
    private short yellowDirection(){
        //make a inner direction too see what key pressed and compare that to previous keys
        short direction = direction3;
        //if key pressed is K and previous key press wasnt the opposite direction, set this number
        if (Greenfoot.isKeyDown("k")&&direction3 !=2){
            direction =1;
            //similar for I
        }else if (Greenfoot.isKeyDown("i")&&direction3 !=1){
            direction =2;
            //similar for J
        }else if (Greenfoot.isKeyDown("j")&&direction3 !=4){
            direction =3;
            //similar for L
        }else if(Greenfoot.isKeyDown("l")&&direction3 !=3){
            direction  =4;
        }
        //return the number associated
        return direction;
    }

    /** Accessors*/
    //these should be self explainitory, for encapsulation the actual variables are portected this way
    //all location of head are able to be accessed through these accessors
    public int blueHeadX () {
        return blueHead.getX();
    }

    public int blueHeadY () {
        return blueHead.getY();
    }

    public int redHeadX () {
        return redHead.getX();
    }

    public int redHeadY () {
        return redHead.getY();
    }

    public int yellowHeadX () {
        return yellowHead.getX();
    }

    public int yellowHeadY () {
        return yellowHead.getY();
    }

    //game running boolean accessor
    public boolean gameRunning (){
        return game;
    }

    //is game paused accessor
    public boolean pause (){
        return pause;
    }

    //player amount choices accessor
    public boolean playerChoice(){
        return twoPlayer;
    }

    //is there sound accssor
    public boolean soundCheck(){
        return music.isPlaying();
    }

    /**Mutators*/
    //change where game is going or not
    public void gameRunningChange(){
        if (game){
            //switch to opposite if game running, then its not, if 
            game =false;
        }else{
            game =true;
        }
    }

    //change what player is running or not
    public void switchGame(int i){
        //check for inputed number, associated to player 1 to player 1, 2 to player 2 and 3 to player 3
        if(i==1){
            blue = false;
        } else if (i==2){
            red = false;
        } else {
            yellow = false;
        }
    }

}
