package com.minigame.colorboard.board;

import java.util.ArrayList;

public class Cell {
    private ArrayList<Cell> myNeighbors;
    private Color initialColor;
    private Color currentColor;
    private int row;
    private int col;

    Cell(int row, int col) {
        this.currentColor = Color.Red;
        this.initialColor = Color.Red;

        this.row = row;
        this.col = col;

        myNeighbors = new ArrayList<>();
    }

    void addNeighbor(Cell neighbor) { myNeighbors.add(neighbor); }

    void setInitialColor(Color color) { initialColor = color; currentColor = color; }
    void resetColor() { currentColor = initialColor; }
    void setCurrentColor(Color color) { currentColor = color; }

    boolean isRed() { return currentColor.isRed(); }
    boolean isBlue() { return currentColor.isBlue(); }

    private void flipColor(boolean doRecurse) {
        switch(currentColor) {
            case Red: {
                setCurrentColor(Color.Blue);
                break;
            }
            case Blue: {
                setCurrentColor(Color.Red);
                break;
            }
        }

        if(doRecurse) {
            for (Cell neighbor : myNeighbors) {
                neighbor.flipColor(false);
            }
        }
    }

    public void flipColor() {
        flipColor(true);
    }

    public String toString() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED   = "\u001B[31m";
        final String ANSI_BLUE  = "\u001B[34m";

        StringBuilder builder = new StringBuilder();

        String color;
        if(currentColor.isRed()) {
            color = ANSI_RED;
        }
        else {
            color = ANSI_BLUE;
        }
        builder.append(String.format("%s%s%s (%d,%d)", color, currentColor.name(), ANSI_RESET, row, col));

        return builder.toString();
    }
}
