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
    public Snake(Point initialPoint, int initialLenght, Direction initialDirection){
        resetSnake(initialPoint, initialLenght, initialDirection); 
    } 

    // reset creator (snake) 
    public void resetSnake(Point initialPoint, int initialLenght, Direction initialDirection){
        this.snakeBody.clear(); 
        this.pedingGrowth = 0; 

        this.currentlyDirection = INITIALSNAKEDIRECTION;
        this.nextDirection = INITIALSNAKEDIRECTION;  

        int opossiteDX = -initialDirection.getDx();  
        int opossiteDY = -initialDirection.getDy();  

        for (int i = 0; i < INITIAL_LENGHT; i++) { // criando o corpo inicial da snake 
            int indiceX = initialPoint.x() + (opossiteDX * i);
            int indiceY = initialPoint.y() + (opossiteDY * i);  

            this.snakeBody.addLast(new Point(indiceX, indiceY));  
        }
    }

    public void setNextDirection(Direction newDirectionInput){
        boolean ehOposta = newDirectionInput.isOpposite(currentlyDirection);
        if(!ehOposta){
            this.nextDirection = newDirectionInput; 
        }
    }

    public void moveSnake(){
        this.currentlyDirection = nextDirection;

        int novaPosicaoX = snakeBody.getFirst().x() + currentlyDirection.getDx();
        int novaPosicaoY = snakeBody.getFirst().y() + currentlyDirection.getDy(); 

        this.snakeBody.addFirst(new Point(novaPosicaoX, novaPosicaoY));
        boolean temComida = pedingGrowth > 0;   

        if(temComida){
            pedingGrowth --;
        } else {
            this.snakeBody.removeLast();  
        }
    }
}