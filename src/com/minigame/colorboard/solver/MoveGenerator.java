package com.minigame.colorboard.solver;

import java.util.ArrayList;

import static com.minigame.colorboard.util.Helper.logIt;

public class MoveGenerator
        extends Thread {

    private final boolean DEBUG = false;

    private MoveQueue      moveQueue;
    private Step           currentStep;
    private int            maxRows;
    private int            maxCols;
    private int            attemptedMoves;

    private ArrayList<Move> currentMoves = new ArrayList();

    public MoveGenerator(MoveQueue moveQueue, int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.currentStep = Step.First;
        this.attemptedMoves = 0;
        this.moveQueue = moveQueue;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("MoveGenerator");
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY+2);
        generator();
    }

    private void generator() {
        int workUnits = 0;

        while (true) {
            ArrayList<Move> nextMoves = nextMoves();
            moveQueue.add(nextMoves);
            workUnits++;
            if(workUnits % 100000 == 0) {
                logIt(String.format("Queue(%d) Current moves: %s\r", moveQueue.size(), nextMoves));
            }
        }
    }

    private ArrayList<Move> nextMoves() {
        Move nextMove = null;

        attemptedMoves++;

        if (currentMoves.size() == 0) {
            nextMove = new Move(maxRows, maxCols, 0, 0, currentStep);
            currentMoves.add(nextMove);
        } else {
            boolean canIncrement = true;
            for (Move currentMoveCheck : currentMoves) {
                nextMove = currentMoveCheck;
                canIncrement = currentMoveCheck.increment();
                if (canIncrement) {
                    break;
                }
            }

            if (!canIncrement) {
                currentStep = Step.nextStep(currentStep);
                nextMove = new Move(maxRows, maxCols, 0, 0, currentStep);
                currentMoves.add(0, nextMove);
            }
        }

        return copyMoves();
    }

    private ArrayList<Move> copyMoves() {
       ArrayList<Move> copyMoves = new ArrayList<>();

       for(Move move : currentMoves) {
           copyMoves.add(0, (Move)move.clone());
       }

       return copyMoves;
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
