package com.mateusrangel.Model.state;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mateusrangel.Model.entity.Food;
import com.mateusrangel.Model.entity.Point;
import com.mateusrangel.Model.entity.Snake;
import com.mateusrangel.Model.enums.DeadReason;
import com.mateusrangel.Model.enums.GameStatus;
import com.mateusrangel.Model.listener.SnakeListener;

public class GameState implements SnakeListener {

    // main entity's 
    private Snake snake;
    private Food food; 

    // match data objects 
    private int timeIntervalMls = 150;
    private int scorePlayer; 
    private int highScore;
    private int levelMatch;
    private GameStatus statusGame;
    private boolean isSnakeDied; 

    // constants
    private final int POINTS_PER_FOOD = 5;
    private final int TIME_DECREMENT = 25;
    private static final Logger LOGGER_SYSTEM = Logger.getLogger(GameState.class.getName());

    public boolean collisionWithWall(int widthScreen, int heightScreen) {
        Point headSnake = snake.getHeadSnake();
        return headSnake.x() < 0 || headSnake.x() >= widthScreen
                || headSnake.y() < 0 || headSnake.y() >= heightScreen;
    }

    public boolean isSnakeDied(int width, int height) {
        return snake.selfCollision() || collisionWithWall(width, height);
    }
    
    public void startGame() {
        this.scorePlayer = 0;
        this.timeIntervalMls = 150;
        this.levelMatch = 1;

        this.snake.resetSnake(defaultInitialPoint); // <- necessário implementar posteriormente 
        // this.food = necessário implementar local de nascimento no mapa 
    }
    
    public void pauseGame() {
        if (this.statusGame == GameStatus.RUNNING) {
            this.statusGame = GameStatus.PAUSED;

            /** Implementação do Travamento do Tick do Jogo */
        }
    }
    
    public void stopGame() {
        this.statusGame = GameStatus.GAMEOVER; 
    }
    
    @Override
    public void onGrow(Snake snake, int newSnakeLength) {
        int initialSize = snake.getInititalLenght();
        int ateFruits = newSnakeLength - initialSize;
        this.scorePlayer = (ateFruits * POINTS_PER_FOOD) * levelMatch;

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
