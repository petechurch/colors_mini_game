package com.minigame.colorboard.solver;

public class Move {
    private int maxRows;
    private int maxCols;

    private int  row;
    private int  col;
    private Step step;

    public Move(int maxRows, int maxCols, int row, int col, Step step) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.row = row;
        this.col = col;
        this.step = step;
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

    public int  getRow() { return row; }
    public int  getCol() { return col; }
    public Step getStep() { return step; }

    public String toString() { return String.format("(%d,%d,%s)", row, col, step.name()); }
}