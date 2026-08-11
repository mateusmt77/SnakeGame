package com.mateusrangel.Model;

import java.util.LinkedList; 

public class Snake { 
    
    // body config
    LinkedList<Point> snakeBody = new LinkedList<>(); 
    Direction currentlyDirection; 
    Direction nextDirection; 
    private final int INITIAL_LENGHT = 3;   
    public int pedingGrowth; 

    // initial position 
    private int startPointX; // Largura total da tela / 2 
    private int startPointY; // Altura total da tela / 2 
    Point startPointSnake = new Point(startPointX, startPointY); // center of the screen 

    // snake constructor 
    public Snake(Point initialPoint, int initialLenght, Direction initialDirection){} 

    // reset creator (snake) 
    public void resetSnake(Point initialPoint, int initialLenght, Direction initialDirection){
        this.snakeBody.clear(); 

    }
}
