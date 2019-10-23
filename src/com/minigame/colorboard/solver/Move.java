package com.minigame.colorboard.solver;

public class Move {
    private int maxRows;
    private int maxCols;

    private int row;
    private int col;
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

            row++;
            if (row >= maxRows) {
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

    public Step getStep() {
        return step;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof Move)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Move left = this;
        Move right = (Move) o;

        return (left.hashCode() == right.hashCode());
    }

    public int hashCode() {
        int hash = 7;

        hash = 31 * (hash + maxRows);
        hash = 31 * (hash + maxCols);
        hash = 31 * (hash + row);
        hash = 31 * (hash + col);
        hash = 31 * (hash + step.getValue());

        return hash;
    }


    public String toString() {
        return String.format("(%d,%d,%s)", row, col, step.name());
    }
}