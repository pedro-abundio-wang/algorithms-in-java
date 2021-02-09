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
    array[top++] = item;
  }

  public T pop() {
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    T item = array[--top];
    array[top] = null;
    if (top > 0 && top == array.length / 4) {
      resize(array.length / 2);
    }
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    return array[top - 1];
  }

  public boolean isEmpty() {
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
    return new ArrayIterator();
  }

  private class ArrayIterator implements Iterator<T> {

    private int current;

    public ArrayIterator() {
      current = top;
    }

    public boolean hasNext() {
      return current > 0;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T item = array[--current];
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }
}
