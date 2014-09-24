import greenfoot.*;  
import java.awt.Color;  //import the Color names so selection of color is easier
/**
 * Counters aka Score Counting Tool
 */
public class Counter extends Actor  
{  
    //make private integers to keep track of the score and the color
    private int score, colour; 
    //one more string to keep the name for the object
    private String name;

    public Counter(String name, int colour)  
    {
        //make the variable's score is 0 when it is initialized
        score =0;
        //set the name, aka what player
        this.name = name;
        //set colour see what colour this is
        this.colour = colour;
        updateBoard(); //Tells the board to update when called  
    }  

    //constantly update the score
    public void act(){
        updateBoard();
    }

    private void updateBoard()  
    {  
        //the colour represent the player and what colour the text i supposed to be
        //for player 1, it is blue
        if (colour ==1){
            setImage(new GreenfootImage(name + ": " + score, 30, Color.blue, new Color(210, 210, 210, 210))); //Print text saying Name: Score# with a box with a whitish background.
        } else if (colour ==2) {
            //for player 2, it is red
            setImage(new GreenfootImage(name + ": " + score, 30, Color.red, new Color(210, 210, 210, 210)));//print same thing with diffrent colour for diffrent player
        }  else {
            //for player 3, it is yellow
            setImage(new GreenfootImage(name + ": " + score, 30, Color.yellow, new Color(210, 210, 210, 210)));
        }
    }	

    public void changeScore(int score)  
    {  
        //change the score of this into the socre entered
        this.score = score;  
    }  
}  