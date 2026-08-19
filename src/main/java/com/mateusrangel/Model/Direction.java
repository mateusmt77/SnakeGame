package com.mateusrangel.Model;

import java.util.Optional;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1), 
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx; 
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    } 

    public int getDy() {
        return dy;
    } 

    public boolean isOpposite(Direction other) {
        return this.dx + other.dx == 0 && this.dy + other.dy == 0;
    }

    @Override
    public String toString() { 
        switch (this) {
            case UP: return "Cima";  
            case DOWN: return "Baixo";
            case LEFT: return "Esquerda";
            case RIGHT: return "Direita";
            default: return this.name();  
        }
    }

    public Optional<Direction> keyboardChar(char inputKeyboard) {
        char enterUpper = Character.toUpperCase(inputKeyboard);
        switch (enterUpper) {
            case 'W':
                return Optional.of(Direction.UP);
            case 'S':
                return Optional.of(Direction.DOWN);
            case 'A':
                return Optional.of(Direction.LEFT);
            case 'D':
                return Optional.of(Direction.RIGHT);
            default:
                return Optional.empty();
        } 
    }
}
