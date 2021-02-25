package com.cs4everyone.algorithms.queue;

public class MaxPQ<Key extends Comparable<Key>> {

  private Key[] priorityQueue;

  private int size;

  public MaxPQ(int capacity) {
    priorityQueue = (Key[]) new Comparable[capacity + 1];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void insert(Key key) {
    priorityQueue[++size] = key;
    swim(size);
  }

  public Key delMax() {
    Key max = priorityQueue[1];
    swap(1, size--);
    sink(1);
    priorityQueue[size + 1] = null;
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
    Key swap = priorityQueue[i];
    priorityQueue[i] = priorityQueue[j];
    priorityQueue[j] = swap;
  }
}
