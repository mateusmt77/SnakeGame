package com.mateusrangel.Model;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1), // responsável pelas coordenadas
    LEFT(-1, 0),
    RIGHT(1, 0); 

    private final int dx;
    private final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx; // construtor interno privado da classe 
        this.dy = dy;
    }

    public int getDx() { return dx; } // métodos usados para que outras classes acessem os valores
    public int getDy() { return dy; } // do enum de forma simplificada 

    public boolean isOpposite(Direction other) { // validação lógica de "contramão" 
        return this.dx + other.dx == 0 && this.dy + other.dy == 0;
    }
}