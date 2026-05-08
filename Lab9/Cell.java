package org.example;

public class Cell {
    int x, y;
    boolean isExit;
    String occupant;

    public Cell(int x, int y, boolean isExit) {
        this.x = x;
        this.y = y;
        this.isExit = isExit;
        this.occupant = null;
    }
}