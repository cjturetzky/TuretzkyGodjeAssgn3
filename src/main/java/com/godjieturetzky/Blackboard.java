package com.godjieturetzky;

/*
 * Blackboard is the data heart of this system, holding all the critical information to be shared
 * isRunning : Is the program currently running, or has it been shut down? This is to turn
 * off the MQTTPub and MQTTSub automatically
 * 
 * squares : List<Square> A list of squares, including the player, to be displayed & updated as needed
 * 
 * Implements observer pattern as Observable by WorldPanel, so that updates to 
 * playerPosition or otherPositions are immediately reflected. 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.beans.PropertyChangeSupport;

public class Blackboard extends PropertyChangeSupport {
    private static Blackboard instance = null;
    public boolean isRunning;
    
    public List<Square> squares;


    private Blackboard() {
        super(new Object());
        isRunning = true;
        Square self = new Square(0, 0, 50, Color.RED, Main.IDNAME);
        squares = new ArrayList<Square>();
        squares.add(self);
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    public List<Square> getSquares(){
        return squares;
    }

    public void updateSquare(String name, int x, int y){
        // NOTE: This might not work if s is passed by value rather than reference
        // In that case, we'd need to iterate the indecies directly, and work within the List

        // Iterate through existing squares; If this square is present, update it's position
        boolean updated = false;
        for(Square s : squares){
            if(s.getName() == name){
                s.updatePosition(x, y);
                updated = true;
                break;
            }
        }
        // Square name not found, means it's a new square and must be created & added
        if(!updated){ Square s = new Square(x, y, 50, Color.BLUE, name); squares.add(s); }
        this.firePropertyChange("squares", null, null);
        return;
    }

    public void stop(){
        isRunning = false;
        return;
    }
}
