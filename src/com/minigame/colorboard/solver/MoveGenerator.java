package com.minigame.colorboard.solver;

import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.minigame.colorboard.util.Helper.logIt;

public class MoveGenerator
        extends Thread {

    private final boolean DEBUG = false;

    private MutableBoolean shouldContinue;
    private MoveQueue      moveQueue;
    private long           generatedMoves;
    private int            maxRows;
    private int            maxCols;
    private Step           currentStep;

    private ArrayList<Move> currentMoves = new ArrayList();

    public MoveGenerator(MutableBoolean shouldContinue, MoveQueue moveQueue, int maxRows, int maxCols) {
        this.shouldContinue = shouldContinue;
        this.moveQueue      = moveQueue;
        this.maxRows        = maxRows;
        this.maxCols        = maxCols;
        this.currentStep    = Step.First;
        this.generatedMoves = 0;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("MoveGenerator");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        generator();
    }

    private void generator() {
        long workUnits = 0;

        long startTime = System.currentTimeMillis();
        while (shouldContinue.isTrue()) {
            ArrayList<Move> nextMoves = nextMoves();
            moveQueue.add(nextMoves);
            workUnits++;
            if(workUnits % 5000000 == 0) {
                long endTime = System.currentTimeMillis();
                long diff = endTime - startTime;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
                long total = workUnits;
                long unitsPerSecond = (long)((float)total/(float)seconds);

                logIt(String.format("Queue size: %,11d  Rate: %,11d/s  Current moves: %s", moveQueue.size(), unitsPerSecond, nextMoves));
            }
        }
    }

    private ArrayList<Move> nextMoves() {
        Move nextMove = null;

        generatedMoves++;

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

    public long getGeneratedMoves() {
        return generatedMoves;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Generated moves: " + generatedMoves);
        builder.append(" ");

        for (Move move : currentMoves) {
            builder.append(move.toString());
            builder.append(" ");
        }

        return builder.toString();
    }
}
