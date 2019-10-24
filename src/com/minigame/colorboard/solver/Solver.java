package com.minigame.colorboard.solver;


import com.minigame.colorboard.board.Board;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;

import static com.minigame.colorboard.util.Helper.logIt;

public class Solver {
    final private boolean DEBUG = false;
    final private int TOTAL_ROWS = 4;
    final private int TOTAL_COLS = 5;
    final private int TOTAL_THREADS = 10;

    private MutableInt     totalWorkUnits;
    private MoveQueue      moveQueue;
    private Board          board;
    private MoveGenerator  generator;

    Solver() {
        totalWorkUnits = new MutableInt(0);
        moveQueue = new MoveQueue();
        generator = new MoveGenerator(moveQueue, TOTAL_ROWS, TOTAL_COLS);
        board     = new Board(TOTAL_ROWS, TOTAL_COLS);
    }

    void start() throws InterruptedException {

        logIt("Generator started, queueing up work units");
        generator.start();
        try { Thread.sleep(20000); } catch (Exception ex) {}

        logIt("Initial board");
        logIt(board.toString());

        ArrayList<Worker> workers = new ArrayList();
        for(int i = 0; i < TOTAL_THREADS; i++ ) {
            Worker worker = new Worker(totalWorkUnits, moveQueue, (Board)board.clone(), ("Worker" + i));
            worker.start();
            workers.add(worker);
        }

        for(int i = 0; i < TOTAL_THREADS; i++ ) {
            Worker worker = workers.get(i);
            worker.join();
        }
        generator.join();
    }

    public static void main(String[] args) throws Exception {
        Solver solver = new Solver();
        solver.start();
    }
}
