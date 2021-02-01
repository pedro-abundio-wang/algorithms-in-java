package com.cs4everyone.algorithms.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<T> implements Iterable<T> {

  private static final int INIT_CAPACITY = 8;

  private int head;

  private int tail;

  private int size;

  private T[] array;

  public ResizingArrayQueue() {
    head = 0;
    tail = 0;
    size = 0;
    array = (T[]) new Object[INIT_CAPACITY];
  }

  public T dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    T item = array[head];
    head = (head++) % array.length;
    this.size--;
    if (size < array.length / 4) {
      // resize array
      resize(array.length / 2);
    }
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    T item = array[head];
    return item;
  }

  public void enqueue(T item) {
    array[tail] = item;
    tail = (tail++) % array.length;
    this.size++;
    if (head == tail) {
      // resize array
      resize(2 * array.length);
    }
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  private void resize(int capacity) {
    T[] copy = (T[]) new Object[capacity];
    for (int i = 0; i < this.size; i++) {
      copy[i] = array[(head + i) % array.length];
    }
    head = 0;
    tail = size;
    array = copy;
  }

  public Iterator<T> iterator() {
    return new ArrayIterator(head);
  }

  private class ArrayIterator implements Iterator<T> {

    public int current;

    public ArrayIterator(int current) {
      this.current = current;
    }

    public boolean hasNext() {
      return current < tail || current > head;
    }

    public T next() {
      if(!hasNext()) throw new NoSuchElementException();
      T item = array[current];
      current = (current++) % array.length;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }
}
