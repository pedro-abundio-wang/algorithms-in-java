package com.cs4everyone.algorithms.stack;

import java.util.NoSuchElementException;

public class LinkedStack<T> {

    private LinkedNode top;

    private int size;

    private class LinkedNode {
        private T item;
        private LinkedNode next;
    }

    public void push(T item) {
        LinkedNode backup = top;
        top = new LinkedNode();
        top.item = item;
        top.next = backup;
        this.size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T item = top.item;
        top = top.next;
        this.size--;
        return item;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

}
