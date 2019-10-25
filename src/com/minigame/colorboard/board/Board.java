package com.minigame.colorboard.board;

import com.minigame.colorboard.solver.Move;
import com.minigame.colorboard.solver.Step;
import com.minigame.colorboard.util.Helper;

import java.util.ArrayList;

public class Board {
    private Cell[][] board;
    private int maxRows;
    private int maxCols;

    public Board(int maxRows, int maxCols, boolean create) {
        this(maxRows, maxCols);

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                board[row][col].setInitialColor(Color.Blue);
            }
        }

        randomeCreate();
    }

    public Board(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
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

                Cell neighborNorth = null;
                Cell neighborEast  = null;
                Cell neighborSouth = null;
                Cell neighborWest  = null;

                //north neighbor
                if(col+1 < this.maxCols) {
                    neighborNorth = board[row][col+1];
                }

                //east neighbor
                if(row+1 < this.maxRows) {
                    neighborEast = board[row+1][col];
                }

                //south neighbor
                if(col-1 >= 0) {
                    neighborSouth = board[row][col-1];
                }

                //west neighbor
                if(row-1 >= 0) {
                    neighborWest = board[row-1][col];
                }

                currentCell.setNeighbors(neighborNorth, neighborEast, neighborSouth, neighborWest);
            }
        }

        initialize();
    }

    private void randomeCreate() {
        ArrayList<Move> moves = new ArrayList();
        Step currentStep = Step.First;

        int totalSteps = Helper.random(4,9);
        for(int i = 0; i < totalSteps; i++ ) {
            int row = Helper.random(0, maxRows-1);
            int col = Helper.random(0, maxCols-1);

            Move move = new Move(maxRows, maxCols, row, col, currentStep);
            moves.add(move);
            applyMove(move);
            currentStep = Step.nextStep(currentStep);
        }
        System.out.println(this.toString());
        System.out.println(moves);
    }

    private void initialize() {
        initializeBlue(0, 0);
        initializeBlue(0, 1);
        initializeBlue(0, 2);
        initializeBlue(0, 3);
        initializeBlue(0, 4);

        initializeBlue(1, 0);
        initializeRed(1, 1);
        initializeBlue(1, 2);
        initializeBlue(1, 3);
        initializeBlue(1, 4);

        initializeBlue(2, 0);
        initializeBlue(2, 1);
        initializeBlue(2, 2);
        initializeBlue(2, 3);
        initializeBlue(2, 4);

        initializeBlue(3, 0);
        initializeBlue(3, 1);
        initializeBlue(3, 2);
        initializeBlue(3, 3);
        initializeBlue(3, 4);
    }

    public void initializeRed(int row, int col) {
        if(row < 0 || row >= maxRows) {
            return;
        }

        if(col < 0 || col >= maxCols) {
            return;
        }

        Cell cell = board[row][col];
        cell.setInitialColor(Color.Red);
    }

    public void initializeBlue(int row, int col) {
        if(row < 0 || row >= maxRows) {
            return;
        }

        if(col < 0 || col >= maxCols) {
            return;
        }

        Cell cell = board[row][col];
        cell.setInitialColor(Color.Blue);
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
        Cell cell = board[move.getRow()][move.getCol()];
        cell.flipColor();
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

    @Override
    public Object clone() {
        return new Board(maxRows, maxCols);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("\n");

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
