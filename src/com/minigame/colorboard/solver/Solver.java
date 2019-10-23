package com.minigame.colorboard.solver;


import com.minigame.colorboard.board.Board;
import com.minigame.colorboard.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.minigame.colorboard.util.Helper.logIt;

public class Solver {
    final private boolean DEBUG = false;
    final private int TOTAL_ROWS = 4;
    final private int TOTAL_COLS = 5;

    private Board board;
    private MoveGenerator generator;

    Solver() {
        generator = new MoveGenerator(TOTAL_ROWS, TOTAL_COLS);
        board     = new Board(TOTAL_ROWS, TOTAL_COLS);

        initialize();
    }

    private void initialize() {
/*
        Step currentStep = Step.First;
        int steps = Helper.random(3,6);
        for(int i = 0; i < 5; i++ ) {
            int row = Helper.random(0, 3);
            int col = Helper.random(0, 4);

            Move move = new Move(TOTAL_ROWS, TOTAL_COLS, row, col, currentStep);
            board.applyMove(move);
            System.out.println(move);
            currentStep = Step.nextStep(currentStep);
        }
        System.out.println(board.toString());
        System.exit(0);
 */

        board.initializeRed(0, 0);
        board.initializeRed(0, 1);
        board.initializeRed(0, 2);
        board.initializeRed(0, 3);
        board.initializeRed(0, 4);

        board.initializeRed(1, 0);
        board.initializeBlue(1, 1);
        board.initializeBlue(1, 2);
        board.initializeRed(1, 3);
        board.initializeRed(1, 4);

        board.initializeBlue(2, 0);
        board.initializeRed(2, 1);
        board.initializeRed(2, 2);
        board.initializeBlue(2, 3);
        board.initializeBlue(2, 4);

        board.initializeRed(3, 0);
        board.initializeBlue(3, 1);
        board.initializeBlue(3, 2);
        board.initializeBlue(3, 3);
        board.initializeBlue(3, 4);
    }

    private int random(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    void solve() {
        generator = new MoveGenerator(TOTAL_ROWS, TOTAL_COLS);

        logIt("Solving");
        logIt(board.toString());

        List<Move> currentMoves;
        while(!board.isSolved()) {
            ArrayList<Move> nextMoves = generator.nextMoves();

            board.reset();
            /* (2,2,Fifth) (3,4,Fourth) (0,1,Third) (0,1,Second) (2,1,First)  */
            System.out.print(String.format("Attempting Moves(%d): %s\r", generator.getAttemptedMoves(), generator.toString()));
            logIt(DEBUG, board.toString());
            for(Move move : nextMoves) {
                board.applyMove(move);
                logIt(DEBUG, "Applying Move: " + move.toString());
                logIt(DEBUG, board.toString());
            }
            logIt(DEBUG, board.toString());
        }

        logIt("Solved");
        logIt(generator.toString());
    }

    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.solve();
    }
}
