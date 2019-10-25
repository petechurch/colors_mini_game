package com.minigame.colorboard.test;

import com.minigame.colorboard.board.Board;
import com.minigame.colorboard.solver.Move;
import com.minigame.colorboard.solver.Step;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SolverTest {

    final int TOTAL_ROWS = 4;
    final int TOTAL_COLS = 5;

    @Order(0)
    @Test
    public void generateBoard() {
        System.out.println("generateBoard()");
        Board board = new Board(TOTAL_ROWS, TOTAL_COLS, true);
    }
    @Order(1)
    @Test
    public void moveEquals() {
        System.out.println("Test moveEquals()");
        Move move1 = new Move( 4, 3, 0, 1, Step.First);
        Move move2 = new Move( 4, 3, 0, 1, Step.Second);
        Move move3 = new Move( 4, 3, 0, 1, Step.First);

        System.out.println("Move1 hashcode: " + move1.hashCode() );
        System.out.println("Move2 hashcode: " + move2.hashCode() );
        System.out.println("Move3 hashcode: " + move3.hashCode() );
        assertNotEquals(move1, move2);
        assertEquals(move1, move3);
    }
}
