package com.minigame.colorboard.solver;

public class Move {
    private int maxRows;
    private int maxCols;

    public int row;
    public int col;
    public int depth;

    public Move(int maxRows, int maxCols, int row, int col, int depth) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.row = row;
        this.col = col;
        this.depth = depth;
    }

    public boolean increment() {
        boolean canIncrement = true;

        col++;
        if (col >= maxCols) {
            col = 0;

            row ++;
            if(row >= maxRows) {
                row = 0;
                canIncrement = false;
            }
        }
        return canIncrement;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getDepth() {
        return depth;
    }
    public Move copy() { return new Move(maxRows, maxCols, row, col, depth); }
    public String toString() {
        return String.format("(%d,%d,%d)", row, col, depth);
    }
}