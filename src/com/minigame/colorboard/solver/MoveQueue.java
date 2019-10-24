package com.minigame.colorboard.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MoveQueue
        implements Queue {

    ArrayList<ArrayList<Move>> queue;

    public MoveQueue() {
        this.queue = new ArrayList();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public synchronized Iterator iterator() {
        return null;
    }

    @Override
    public synchronized Object[] toArray() {
        return null;
    }

    @Override
    public synchronized Object[] toArray(Object[] a) {
        return null;
    }

    @Override
    public synchronized boolean add(Object o) {
        if(!(o instanceof ArrayList)) {
            return false;
        }

        return queue.add((ArrayList<Move>)o);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return false;
    }

    @Override
    public synchronized boolean addAll(Collection c) {
        return false;
    }

    @Override
    public synchronized void clear() {
        queue.clear();
    }

    @Override
    public synchronized boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public synchronized boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public synchronized boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public synchronized boolean offer(Object o) {
        return false;
    }

    @Override
    public synchronized Object remove() {
        if(queue.size() == 0) {
            return null;
        }

        return queue.remove(0);
    }

    @Override
    public synchronized Object poll() {
        return null;
    }

    @Override
    public synchronized Object element() {
        return null;
    }

    @Override
    public synchronized Object peek() {
        if(queue.size() == 0) {
            return null;
        }

        return queue.get(0);
    }
}
