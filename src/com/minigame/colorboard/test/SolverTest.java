package com.minigame.colorboard.test;

import com.minigame.colorboard.board.Board;
import com.minigame.colorboard.solver.Move;
import com.minigame.colorboard.solver.Step;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest {

    final int TOTAL_ROWS = 4;
    final int TOTAL_COLS = 5;

    @Test
    public void solve() {
        Move move1 = new Move( 4, 3, 0, 1, Step.First);
        Move move2 = new Move( 4, 3, 0, 1, Step.Second);
        Move move3 = new Move( 4, 3, 0, 1, Step.First);

        System.out.println("Move1 hashcode: " + move1.hashCode() );
        System.out.println("Move2 hashcode: " + move2.hashCode() );
        System.out.println("Move3 hashcode: " + move3.hashCode() );
        assertNotEquals(move1, move2);
        assertNotEquals(move1, move3);
    }
}
