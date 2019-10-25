package com.minigame.colorboard.solver;

import com.minigame.colorboard.board.Board;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableLong;

import java.util.ArrayList;

import static com.minigame.colorboard.util.Helper.logIt;

public class Worker
        extends Thread {

    private final boolean DEBUG = false;

    private MutableBoolean shouldContinue;
    private MutableLong    totalWorkUnits;
    private MoveQueue      moveQueue;
    private Board          board;
    private String         name;
    private long           myWorkUnits;

    public Worker(MutableBoolean shouldContinue, MutableLong totalWorkUnits, MoveQueue moveQueue, Board board, String name) {
        this.shouldContinue = shouldContinue;
        this.totalWorkUnits = totalWorkUnits;
        this.myWorkUnits    = 0;
        this.moveQueue      = moveQueue;
        this.board          = board;
        this.name           = name;
    }

    public void run() {
        ArrayList<ArrayList<Move>> nextMoveList;

        Thread.currentThread().setName(name);
        logIt("Solving");

        while(shouldContinue.isTrue()) {
            nextMoveList = (ArrayList<ArrayList<Move>>)moveQueue.remove();
            if(nextMoveList == null) {
                try { Thread.sleep(250); } catch (Exception ex) {}
                continue;
            }

            for(ArrayList<Move> nextMoves : nextMoveList) {
                myWorkUnits++;
                totalWorkUnits.add(1);

                if(DEBUG) {
                    logIt("Applying moves: " + nextMoves );
                }

                for(Move move : nextMoves) {
                    board.applyMove(move);
                }

                if(board.isSolved()) {
                    shouldContinue.setFalse();
                    try { Thread.sleep(250); } catch (Exception ex) {}
                    logIt(board.toString());
                    logIt("Solved: " + nextMoves.toString());
                    break;
                }
                board.reset();
            }
        }
        logIt(String.format("Worker(%s) exiting.", name));
    }
}
