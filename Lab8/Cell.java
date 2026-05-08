package org.example;

import java.io.Serializable;

public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    int row, col;
    boolean top = true, right = true, bottom = true, left = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}