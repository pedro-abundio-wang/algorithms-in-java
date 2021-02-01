package com.cs4everyone.algorithms.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements Iterable<T> {

  private static final int INIT_CAPACITY = 8;

  private T[] array;

  private int top;

  public ResizingArrayStack() {
    array = (T[]) new Object[INIT_CAPACITY];
    top = 0;
  }

  public void push(T item) {
    if (top == array.length) {
      resize(2 * array.length);
    }
    array[top] = item;
    top++;
  }

  public T pop() {
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    T item = array[top - 1];
    array[top - 1] = null;
    top--;
    if (top > 0 && top == array.length / 4) {
      resize(array.length / 2);
    }
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    return array[top - 1];
  }

  private boolean isEmpty() {
    return top == 0;
  }

  public int size() {
    return top;
  }

  private void resize(int capacity) {
    T[] copy = (T[]) new Object[capacity];
    for (int i = 0; i < top; i++) {
      copy[i] = array[i];
    }
    array = copy;

    // array = java.util.Arrays.copyOf(array, capacity);
  }

  public Iterator<T> iterator() {
    return new ReverseArrayIterator();
  }

  private class ReverseArrayIterator implements Iterator<T> {

    private int current;

    public ReverseArrayIterator() {
      current = top - 1;
    }

    public boolean hasNext() {
      return current >= 0;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T item = array[current];
      current--;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }
}
