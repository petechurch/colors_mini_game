package com.minigame.colorboard.board;

public class Cell {
    private Cell neighborNorth;
    private Cell neighborEast;
    private Cell neighborSouth;
    private Cell neighborWest;

    private Color initialColor;
    private Color currentColor;

    private int row;
    private int col;

    Cell(int row, int col) {
        this.currentColor = Color.Red;
        this.initialColor = Color.Red;

        this.row = row;
        this.col = col;

    }

    void setNeighbors(Cell north, Cell east, Cell south, Cell west) {
        neighborNorth = north;
        neighborEast  = east;
        neighborSouth = south;
        neighborWest  = west;
    }

    void  resetColor() { currentColor = initialColor; }
    Color getInitialColor() { return initialColor; }
    void  setInitialColor(Color color) { initialColor = color; currentColor = color; }
    void  setCurrentColor(Color color) { currentColor = color; }

    boolean isRed() { return currentColor.isRed(); }
    boolean isBlue() { return currentColor.isBlue(); }

    private void flipColor(boolean flipNeighbors) {
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

        if(flipNeighbors) {
            if(neighborNorth != null) {
                neighborNorth.flipColor(!flipNeighbors);
            }
            if(neighborEast != null) {
                neighborEast.flipColor(!flipNeighbors);
            }
            if(neighborSouth != null) {
                neighborSouth.flipColor(!flipNeighbors);
            }
            if(neighborWest != null) {
                neighborWest.flipColor(!flipNeighbors);
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
