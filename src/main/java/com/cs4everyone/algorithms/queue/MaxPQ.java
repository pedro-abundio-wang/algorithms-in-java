package com.cs4everyone.algorithms.queue;

import java.util.NoSuchElementException;

public class MaxPQ<Item extends Comparable<Item>> {

  private static final int INIT_CAPACITY = 8;

  private Item[] priorityQueue;

  private int size;

  public MaxPQ() {
    priorityQueue = (Item[]) new Comparable[INIT_CAPACITY + 1];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void insert(Item item) {
    if (size == priorityQueue.length - 1) {
      resize(2 * (priorityQueue.length - 1));
    }
    priorityQueue[++size] = item;
    swim(size);
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity + 1];
    for (int i = 0; i <= size; i++) {
      copy[i] = priorityQueue[i];
    }
    priorityQueue = copy;
  }

  public Item delMax() {
    if (isEmpty()) throw new NoSuchElementException("max priority queue underflow");
    Item max = priorityQueue[1];
    swap(1, size--);
    sink(1);
    priorityQueue[size + 1] = null;
    if (size == (priorityQueue.length - 1) / 4) {
      resize((priorityQueue.length - 1) / 2);
    }

    return max;
  }

  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      swap(k, k / 2);
      k = k / 2;
    }
  }

  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && less(j, j + 1)) j++;
      if (!less(k, j)) break;
      swap(k, j);
      k = j;
    }
  }

  private boolean less(int i, int j) {
    return priorityQueue[i].compareTo(priorityQueue[j]) < 0;
  }

  private void swap(int i, int j) {
    Item swap = priorityQueue[i];
    priorityQueue[i] = priorityQueue[j];
    priorityQueue[j] = swap;
  }
}
