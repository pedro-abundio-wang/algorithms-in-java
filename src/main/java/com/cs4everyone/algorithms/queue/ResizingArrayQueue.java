package com.cs4everyone.algorithms.queue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<T> implements Iterable<T> {

  private static final int INIT_CAPACITY = 8;

  private int head;

  private int tail;

  private int size;

  private T[] array;

  private int enqueueCount;

  private int dequeueCount;

  public ResizingArrayQueue() {
    head = 0;
    tail = 0;
    size = 0;
    array = (T[]) new Object[INIT_CAPACITY];
  }

  public T dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    T item = array[head];
    array[head] = null;
    head++;
    head = head % array.length;
    this.size--;
    if (size > 0 && size == array.length / 4) {
      // resize array
      resize(array.length / 2);
    }
    dequeueCount++;
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    T item = array[head];
    return item;
  }

  public void enqueue(T item) {
    array[tail++] = item;
    tail = tail % array.length;
    this.size++;
    if (this.size == array.length) {
      // resize array
      resize(2 * array.length);
    }
    enqueueCount++;
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
    array = copy;
    head = 0;
    tail = size;
  }

  public Iterator<T> iterator() {
    return new ArrayIterator(enqueueCount, dequeueCount);
  }

  private class ArrayIterator implements Iterator<T> {

    public int i = 0;

    private int enqueueCountSnapshot;

    private int dequeueCountSnapshot;

    public ArrayIterator(int enqueueCountSnapshot, int dequeueCountSnapshot) {
      this.enqueueCountSnapshot = enqueueCountSnapshot;
      this.dequeueCountSnapshot = dequeueCountSnapshot;
    }

    public boolean hasNext() {
      if (enqueueCountSnapshot != enqueueCount || dequeueCountSnapshot != dequeueCount) {
        throw new ConcurrentModificationException();
      }
      return i < size;
    }

    public T next() {
      if (enqueueCountSnapshot != enqueueCount || dequeueCountSnapshot != dequeueCount) {
        throw new ConcurrentModificationException();
      }
      if (!hasNext()) throw new NoSuchElementException();
      T item = array[(i + head) % array.length];
      i++;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    for (T item : this) {
      buffer.append(item);
      buffer.append(' ');
    }
    return buffer.toString();
  }
}
