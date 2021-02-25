package com.cs4everyone.algorithms.queue;

public class MinPQ<Key extends Comparable<Key>> {

  private static final int INIT_CAPACITY = 8;

  private Key[] priorityQueue;

  private int size;

  /**
   * Initializes an empty priority queue with the given initial capacity.
   *
   * @param initCapacity the initial capacity of this priority queue
   */
  public MinPQ(int initCapacity) {
    priorityQueue = (Key[]) new Comparable[initCapacity + 1];
    size = 0;
  }

  /** Initializes an empty priority queue. */
  public MinPQ() {
    this(INIT_CAPACITY);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void insert(Key key) {
    // double size of array if necessary
    if (size == priorityQueue.length - 1) resize(2 * priorityQueue.length);
    priorityQueue[++size] = key;
    swim(size);
  }

  public Key delMin() {
    Key min = priorityQueue[1];
    swap(1, size--);
    sink(1);
    // to avoid loitering and help with garbage collection
    priorityQueue[size + 1] = null;
    if ((size > 0) && (size == (priorityQueue.length - 1) / 4)) resize(priorityQueue.length / 2);
    return min;
  }

  private void swim(int k) {
    while (k > 1 && greater(k / 2, k)) {
      swap(k, k / 2);
      k = k / 2;
    }
  }

  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && greater(j, j + 1)) j++;
      if (!greater(k, j)) break;
      swap(k, j);
      k = j;
    }
  }

  // resize the underlying array to have the given capacity
  private void resize(int capacity) {
    Key[] copy = (Key[]) new Comparable[capacity];
    for (int i = 1; i <= size; i++) {
      copy[i] = priorityQueue[i];
    }
    priorityQueue = copy;
  }

  private boolean greater(int i, int j) {
    return priorityQueue[i].compareTo(priorityQueue[j]) > 0;
  }

  private void swap(int i, int j) {
    Key swap = priorityQueue[i];
    priorityQueue[i] = priorityQueue[j];
    priorityQueue[j] = swap;
  }
}
