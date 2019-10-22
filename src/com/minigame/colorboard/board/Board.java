package com.minigame.colorboard.board;

import com.minigame.colorboard.solver.Move;

public class Board {
    private int maxRows;
    private int maxCols;
    private Cell[][] board;

    public Board(int rows, int cols) {
        this.maxRows = rows;
        this.maxCols = cols;
        this.board = new Cell[maxRows][maxCols];

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                Cell cell = new Cell(row, col);
                board[row][col] = cell;
            }
        }

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                Cell currentCell = board[row][col];

                Cell neighbor;
                //north neighbor
                if(col+1 < maxCols) {
                    neighbor = board[row][col+1];
                    currentCell.addNeighbor(neighbor);
                }

                //east neighbor
                if(row+1 < maxRows) {
                    neighbor = board[row+1][col];
                    currentCell.addNeighbor(neighbor);
                }

                //south neighbor
                if(col-1 >= 0) {
                    neighbor = board[row][col-1];
                    currentCell.addNeighbor(neighbor);
                }

                //west neighbor
                if(row-1 >= 0) {
                    neighbor = board[row-1][col];
                    currentCell.addNeighbor(neighbor);
                }
            }
        }
    }


    public void initializeRed(int row, int col) {
        if(row < 0 || row >= maxRows) {
            return;
        }

        if(col < 0 || col >= maxCols) {
            return;
        }

        Cell cell = board[row][col];
        cell.setCurrentColor(Color.Red);
    }

    public void initializeBlue(int row, int col) {
        if(row < 0 || row >= maxRows) {
            return;
        }

        if(col < 0 || col >= maxCols) {
            return;
        }

        Cell cell = board[row][col];
        cell.setCurrentColor(Color.Blue);
    }

    public void reset() {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                Cell cell = board[row][col];
                cell.resetColor();
            }
        }
    }

    public void applyMove(Move move) {
        //System.out.println("Before flip:\n" + this.toString());
        Cell cell = board[move.row][move.col];
        cell.flipColor();
        //System.out.println("After flip:\n" + this.toString());
    }

    public void applyMove(int row, int col, int depth) {
        Move move = new Move(maxRows, maxCols, row, col, 0);
        applyMove(move);
    }

    public boolean isSolved() {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                Cell cell = board[row][col];
                if(cell.isRed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < maxRows; row++) {

            builder.append("+");
            for (int col = 0; col < maxCols; col++) {
                builder.append("------------+");
            }
            builder.append("\n");

            builder.append("|");
            for (int col = 0; col < maxCols; col++) {
                Cell cell = board[row][col];
                builder.append(String.format(" %-19s |", cell.toString()));
            }
            builder.append("\n");

        }

        builder.append("+");
        for (int col = 0; col < maxCols; col++) {
            builder.append("------------+");
        }
        builder.append("\n");

        return builder.toString();
    }
}
