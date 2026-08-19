package com.mateusrangel.Model;

public record Point(int x, int y) {

    public Point translate(Direction direction) {
        return new Point(this.x + direction.getDx(), this.y + direction.getDy());
    }
    
    public int distanceTo(Point otherPoint) {
        return Math.abs(this.x - otherPoint.x) + Math.abs(this.y - otherPoint.y);
    }

}

