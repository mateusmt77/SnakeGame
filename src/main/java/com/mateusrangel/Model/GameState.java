package com.mateusrangel.Model;

public class GameState implements SnakeListener{ 
    
    private int timeIntervalMls = 150;
    private final int TIME_DECREMENT = 25;
    private int scorePlayer = 0;
    private final int POINTS_PER_FOOD = 5;

    @Override
    public void onGrow(Snake snake, int newSnakeLength) {
        this.scorePlayer = snake.getSnakeLength() 
    }

    @Override
    public void onDie(Snake snake, DeadReason deadReason) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getScore() {
        return Math.max(0, (snake.getSnakeLength() - snake.getInititalLenght()) * POINTS_PER_FOOD);
    }
    
    public int getTickIntervalMs() {
        return this.timeIntervalMls;
    }

    public void increaseSpeed() {
        if (timeIntervalMls > 25) {
            this.timeIntervalMls -= TIME_DECREMENT;
        }
    }
    
}
