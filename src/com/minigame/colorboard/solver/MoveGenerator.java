package com.minigame.colorboard.solver;

import java.util.ArrayList;

public class MoveGenerator  {
    private Step currentStep;
    private int maxRows;
    private int maxCols;
    private int attemptedMoves;

    private ArrayList<Move> currentMoves = new ArrayList();

    public MoveGenerator(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.currentStep = Step.First;
        this.attemptedMoves = 0;
    }

    public ArrayList<Move> nextMoves() {
        Move nextMove = null;

        attemptedMoves ++;

        if (currentMoves.size() == 0) {
            nextMove = new Move(maxRows, maxCols, 0, 0, currentStep);
            currentMoves.add(nextMove);

            return currentMoves;
        }

        boolean canIncrement = true;
        for (Move currentMoveCheck : currentMoves) {
            nextMove = currentMoveCheck;
            canIncrement = currentMoveCheck.increment();
            if(canIncrement) {
                break;
            }
        }

        if(!canIncrement) {
            currentStep = Step.nextStep(currentStep);
            nextMove = new Move(maxRows, maxCols, 0, 0, currentStep);
            currentMoves.add(0, nextMove);
        }

        return reverseList(currentMoves);
    }

    private ArrayList<Move> reverseList(ArrayList<Move> moves) {
        ArrayList<Move> reverseList = new ArrayList();

        for(Move move : moves) {
            reverseList.add(0, move);
        }

        return reverseList;
    }

    public int getAttemptedMoves() {
        return attemptedMoves;
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
