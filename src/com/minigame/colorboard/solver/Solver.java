package com.minigame.colorboard.solver;


import com.minigame.colorboard.board.Board;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableLong;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.minigame.colorboard.util.Helper.logIt;

public class Solver {
    final private boolean DEBUG = false;
    final private int TOTAL_THREADS = 9;
    final private int TOTAL_ROWS    = 4;
    final private int TOTAL_COLS    = 5;

    private MutableBoolean shouldContinue;
    private MutableLong totalWorkUnits;
    private MoveQueue      moveQueue;
    private Board          board;
    private MoveGenerator  generator;

    Solver() {
        shouldContinue = new MutableBoolean(true);
        totalWorkUnits = new MutableLong(0);
        moveQueue      = new MoveQueue();
        generator      = new MoveGenerator(shouldContinue, moveQueue, TOTAL_ROWS, TOTAL_COLS);
        board          = new Board(TOTAL_ROWS, TOTAL_COLS);
    }

    void start() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        logIt("Generator started, queueing up work units");
        generator.start();
        try { Thread.sleep(10000); } catch (Exception ex) {}

        logIt("Initial board");
        logIt(board.toString());

        ArrayList<Worker> workers = new ArrayList();
        for(int i = 0; i < TOTAL_THREADS; i++ ) {
            Worker worker = new Worker(shouldContinue, totalWorkUnits, moveQueue, (Board)board.clone(), ("Worker" + i));
            worker.start();
            workers.add(worker);
        }

        while(shouldContinue.isTrue()) {
            long endTime = System.currentTimeMillis();
            long diff = endTime - startTime;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
            long total = totalWorkUnits.getValue();
            long unitsPerSecond = (long)((float)total/(float)seconds);

            logIt(String.format("Queue size: %,11d  Rate: %,11d/s  Total Processed: %,11d", moveQueue.size(), unitsPerSecond, total));
            try { Thread.sleep(5000); } catch (Exception ex) {}
        }

        for(int i = 0; i < TOTAL_THREADS; i++ ) {
            Worker worker = workers.get(i);
            worker.join();
        }
        generator.join();

        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        diff -= (hours * 3600000);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        diff -= (minutes * 60000);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        logIt(String.format("Total solve time: %d:%d:%d", hours, minutes, seconds));
    }

    public static void main(String[] args) throws Exception {
        Solver solver = new Solver();
        solver.start();
    }
}
