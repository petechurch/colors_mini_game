package com.minigame.colorboard.test;

import com.minigame.colorboard.board.Board;
import org.junit.jupiter.api.Test;

public class SolverTest {

    final int TOTAL_ROWS = 4;
    final int TOTAL_COLS = 5;

    @Test
    public void solve() {
        Board board = new Board(TOTAL_ROWS, TOTAL_COLS);

        System.out.println("Begin unit tests");
        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLS; col++) {
                System.out.println(board.toString());
                System.out.println("Flipping (" + row + ", " + col + ")");
                board.applyMove(row, col, 0);
                System.out.println(board.toString());
                board.applyMove(row, col, 0);
            }
        }
        System.out.println("End unit tests");
    }
}
