package com.mateusrangel.Model;

public record Point(int x, int y) {
}

public Point translatePoint(Direction direction){
    int newPointX = this.x + direction.getDx();  
    int newPointY = this.y + direction.getDy();
    return new Point(newPointX, newPointY); 
} 
