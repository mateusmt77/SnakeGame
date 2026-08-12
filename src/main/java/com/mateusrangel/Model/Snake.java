package com.mateusrangel.Model;

import java.util.LinkedList; 

public class Snake { 
    
    // body config
    LinkedList<Point> snakeBody = new LinkedList<>(); 
    Direction currentlyDirection; 
    Direction nextDirection; 
    public int pedingGrowth; 

    // contants 
    private final int INITIAL_LENGHT = 3; 
    private static final Direction INITIALSNAKEDIRECTION = Direction.RIGHT; 
    
    // initial position 
    private int startPointX; // Largura total da tela / 2 
    private int startPointY; // Altura total da tela / 2 
    Point startPointSnake = new Point(startPointX, startPointY); // center of the screen 

    // snake constructor 
    public Snake(Point initialPoint, int initialLenght, Direction initialDirection){} 

    // reset creator (snake) 
    public void resetSnake(Point initialPoint, int initialLenght, Direction initialDirection){
        this.snakeBody.clear(); 
        this.pedingGrowth = 0; 

        this.currentlyDirection = INITIALSNAKEDIRECTION;
        this.nextDirection = INITIALSNAKEDIRECTION;  

        


    }
}
