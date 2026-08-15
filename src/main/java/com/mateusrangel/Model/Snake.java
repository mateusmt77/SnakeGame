package com.mateusrangel.Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Snake {

    // body config
    private LinkedList<Point> snakeBody = new LinkedList<>();
    private Direction currentlyDirection;
    private Direction nextDirection; 
    private int pedingGrowth; 

    // contants 
    private final int INITIAL_LENGHT = 3;
    private static final Direction INITIALSNAKEDIRECTION = Direction.RIGHT;

    // snake constructor 
    public Snake(Point initialPoint, int initialLenght) {
        resetSnake(initialPoint, initialLenght);  
    }

    // reset creator (snake) 
    public void resetSnake(Point initialPoint, int initialLenght) {
        this.snakeBody.clear();
        this.pedingGrowth = 0;

        this.currentlyDirection = INITIALSNAKEDIRECTION;
        this.nextDirection = INITIALSNAKEDIRECTION;

        int oppositeDX = -INITIALSNAKEDIRECTION.getDx();
        int oppositeDY = -INITIALSNAKEDIRECTION.getDy();

        for (int i = 0; i < INITIAL_LENGHT; i++) { // criando o corpo inicial da snake 
            int indiceX = initialPoint.x() + (oppositeDX * i);
            int indiceY = initialPoint.y() + (oppositeDY * i);

            this.snakeBody.addLast(new Point(indiceX, indiceY));
        }
    }

    public void setNextDirection(Direction newDirectionInput) {
        boolean ehOposta = newDirectionInput.isOpposite(currentlyDirection);
        if (!ehOposta) {
            this.nextDirection = newDirectionInput;
        }
    }

    public void moveSnake() {
        this.currentlyDirection = nextDirection;

        int novaPosicaoX = snakeBody.getFirst().x() + currentlyDirection.getDx();
        int novaPosicaoY = snakeBody.getFirst().y() + currentlyDirection.getDy();

        this.snakeBody.addFirst(new Point(novaPosicaoX, novaPosicaoY));
        boolean temComida = pedingGrowth > 0;

        if (temComida) {
            pedingGrowth--;
        } else {
            this.snakeBody.removeLast();
        }
    }

    private Point getHeadSnake() {
        return snakeBody.getFirst(); 
    }
    
    public List<Point> getSnakeBody() {
        List<Point> snakeBodyDTO = new LinkedList<>(snakeBody);
        return Collections.unmodifiableList(snakeBodyDTO);
    }
    
    public int getSnakeLength() {
        return this.snakeBody.size();
    }
    
    public boolean pointOccupingSnakeBody(Point point) {
        return this.snakeBody.contains(point); 
    }

    public boolean selfCollision() {
        return snakeBody.stream().skip(1).anyMatch(pedaco -> pedaco.equals(getHeadSnake())); 
    }

    public boolean collisionWithWall(int widthScreen, int heightScreen) {
        return getHeadSnake().x() < 0 || getHeadSnake().x() >= widthScreen
                || getHeadSnake().y() < 0 || getHeadSnake().y() >= heightScreen; 
    }

    public boolean isSnakeDead(int width, int height) {
        return selfCollision() || collisionWithWall(width, height);
    }

}