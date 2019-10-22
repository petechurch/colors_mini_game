package com.minigame.colorboard.solver;

import java.util.ArrayList;

public class MoveGenerator  {
    private int maxRows;
    private int maxCols;

    private ArrayList<Move> currentMoves = new ArrayList<>();

    public MoveGenerator(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
    }

    public Move getNextMove() {
        Move nextMove = null;

        if (currentMoves.size() == 0) {
            nextMove = new Move(maxRows, maxCols, 0, 0, 0);
            currentMoves.add(nextMove);

            return nextMove;
        }

        int currentDepth = 0;
        boolean canIncrement = true;
        for (Move currentMoveCheck : currentMoves) {
            nextMove = currentMoveCheck;
            currentDepth = Math.max(currentDepth, currentMoveCheck.getDepth());
            canIncrement = currentMoveCheck.increment();
            if(canIncrement) {
                break;
            }
        }

        if(!canIncrement) {
            nextMove = new Move(maxRows, maxCols, 0, 0, (currentDepth + 1));
            currentMoves.add(nextMove);
        }

        return nextMove;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Move move : currentMoves) {
            builder.append(move.toString());
            builder.append(" ");
        }

        return builder.toString();
    }
}
