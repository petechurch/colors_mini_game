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

        board.initializeBlue(0, 0);
        board.initializeBlue(0, 1);
        board.initializeBlue(0, 2);
        board.initializeBlue(0, 3);
        board.initializeBlue(0, 4);

        board.initializeBlue(1, 0);
        board.initializeRed(1, 1);
        board.initializeBlue(1, 2);
        board.initializeBlue(1, 3);
        board.initializeBlue(1, 4);

        board.initializeBlue(2, 0);
        board.initializeBlue(2, 1);
        board.initializeBlue(2, 2);
        board.initializeBlue(2, 3);
        board.initializeBlue(2, 4);

        board.initializeBlue(3, 0);
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
            assertMoves(nextMoves);

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
        logIt(board.toString());
        logIt(generator.toString());
    }

    private void assertMoves(ArrayList<Move> moves) {

        /* (2,2,Fifth) (3,4,Fourth) (0,1,Third) (0,1,Second) (2,1,First)  */
        /*
        Move first  = new Move(TOTAL_ROWS, TOTAL_COLS, 2, 1, Step.First);
        Move second = new Move(TOTAL_ROWS, TOTAL_COLS, 0, 1, Step.Second);
        Move third  = new Move(TOTAL_ROWS, TOTAL_COLS, 0, 1, Step.Third);
        Move fourth = new Move(TOTAL_ROWS, TOTAL_COLS, 3, 4, Step.Fourth);
        Move fifth  = new Move(TOTAL_ROWS, TOTAL_COLS, 2, 2, Step.Fifth);

        if(moves.size() == 5) {
            Move firstCheck  = moves.get(0);
            Move secondCheck = moves.get(1);
            Move thirdCheck  = moves.get(2);
            Move fourthCheck = moves.get(3);
            Move fifthCheck  = moves.get(4);

            if(firstCheck.equals(first)   &&
               secondCheck.equals(second) &&
               thirdCheck.equals(third)   &&
               fourthCheck.equals(fourth) &&
               fifthCheck.equals(fifth)) {
                System.out.println("OK THIS SHOULD BE A WINNER");
            }
        }
         */
    }

    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.solve();
    }
}
