package com.minigame.colorboard.solver;

import com.minigame.colorboard.board.Board;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.minigame.colorboard.util.Helper.logIt;

public class Worker
        extends Thread {

    private final boolean DEBUG = false;

    private MutableInt     totalWorkUnits;
    private MoveQueue      moveQueue;
    private Board          board;
    private String         name;
    private int myWorkUnits;

    public Worker(MutableInt totalWorkUnits, MoveQueue moveQueue, Board board, String name) {
        this.totalWorkUnits = totalWorkUnits;
        this.moveQueue = moveQueue;
        this.board = board;
        this.name = name;
        this.myWorkUnits = 0;
    }

    public void run() {
        ArrayList<Move> nextMoves;
        long startTime;

        Thread.currentThread().setName(name);
        logIt("Solving");

        startTime = System.currentTimeMillis();
        while(true) {
            nextMoves = (ArrayList<Move>)moveQueue.remove();
            if(nextMoves == null) {
                try { Thread.sleep(250); } catch (Exception ex) {}
                continue;
            }
            myWorkUnits++;
            synchronized (totalWorkUnits) {
                totalWorkUnits.add(1);
            }

            if(DEBUG) {
                logIt("Applying moves: " + nextMoves );
            }

            if(myWorkUnits % 10000 == 0 ) {
                long currentTime = System.currentTimeMillis();
                long diff = currentTime - startTime;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
                long total = totalWorkUnits.getValue();
                logIt(String.format("Queue size: %1$-3d  Work Units [%2$-7d/%3$-7d] (%d/s)", moveQueue.size(), myWorkUnits, total, (int)((double)total/(double)seconds)));
            }

            for(Move move : nextMoves) {
                board.applyMove(move);
            }

            if(board.isSolved()) {
                try { Thread.sleep(250); } catch (Exception ex) {}
                logIt(board.toString());
                logIt("Solved: " + nextMoves.toString());
                System.exit(0);
            }
            board.reset();
        }
    }
}
