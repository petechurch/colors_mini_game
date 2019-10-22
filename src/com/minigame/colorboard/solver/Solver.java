package com.minigame.colorboard.solver;


import com.minigame.colorboard.board.Board;

import java.util.List;

public class Solver {
    final int TOTAL_ROWS = 4;
    final int TOTAL_COLS = 5;

    private Board board;
    private MoveGenerator generator;

    Solver() {
        generator = new MoveGenerator(TOTAL_ROWS, TOTAL_COLS);
        board     = new Board(TOTAL_ROWS, TOTAL_COLS);

        initialize();
    }

    private void initialize() {
        board.initializeRed(0, 0);
        board.initializeRed(0, 1);
        board.initializeBlue(0, 2);
        board.initializeBlue(0, 3);
        board.initializeBlue(0, 4);

        board.initializeRed(1, 0);
        board.initializeBlue(1, 1);
        board.initializeBlue(1, 2);
        board.initializeBlue(1, 3);
        board.initializeBlue(1, 4);

        board.initializeBlue(2, 0);
        board.initializeBlue(2, 1);
        board.initializeBlue(2, 2);
        board.initializeBlue(2, 3);
        board.initializeRed(2, 4);

        board.initializeBlue(3, 0);
        board.initializeBlue(3, 1);
        board.initializeBlue(3, 2);
        board.initializeRed(3, 3);
        board.initializeRed(3, 4);
    }

    void solve() {
        generator = new MoveGenerator(TOTAL_ROWS, TOTAL_COLS);

        System.out.println("Solving");
        System.out.println(board.toString());

        Move lastMove = null;
        List<Move> currentMoves;
        while(!board.isSolved()) {
            Move nextMove = generator.getNextMove();

            System.out.print("NextMove: " + nextMove + " Generator: " + generator + "\r");
            if(lastMove != null) {
                //This resets the board to previous attempt
                board.applyMove(lastMove);
                System.out.println(board.toString());
            }

            board.applyMove(nextMove);
            System.out.println(board.toString());
            lastMove = nextMove.copy();
        }

        System.out.println("Solved");
        System.out.println(generator.toString());
    }

    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.solve();
    }
}
