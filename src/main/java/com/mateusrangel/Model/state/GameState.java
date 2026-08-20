package com.mateusrangel.Model.state;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mateusrangel.Model.entity.Snake;
import com.mateusrangel.Model.enums.DeadReason;
import com.mateusrangel.Model.listener.SnakeListener;

public class GameState implements SnakeListener {

    private int timeIntervalMls = 150;
    private int scorePlayer = 1;

    // constants
    private final int POINTS_PER_FOOD = 5;
    private final int TIME_DECREMENT = 25;
    private static final Logger LOGGER_SYSTEM = Logger.getLogger(GameState.class.getName());

    @Override
    public void onGrow(Snake snake, int newSnakeLength) {
        int initialSize = snake.getInititalLenght();
        int ateFruits = newSnakeLength - initialSize;
        this.scorePlayer = ateFruits * POINTS_PER_FOOD;

        if (newSnakeLength % 5 == 0) {
            increaseSpeed();
        }
    }

    @Override
    public void onDie(Snake snake, DeadReason deadReason) {
        LOGGER_SYSTEM.log(Level.INFO, "Morte Registrada no Sistema: {%s}", deadReason);
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
