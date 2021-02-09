package com.cs4everyone.algorithms.queue;

import com.cs4everyone.algorithms.stack.LinkedStack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

  private LinkedNode head;

  private LinkedNode tail;

  private int size;

  private int enqueueCount;

  private int dequeueCount;

  private class LinkedNode {
    private T item;
    private LinkedNode next;
  }

  private class LinkedIterator implements Iterator<T> {

    private LinkedNode current;

    private int enqueueCountSnapshot;

    private int dequeueCountSnapshot;

    public LinkedIterator(LinkedNode current, int enqueueCountSnapshot, int dequeueCountSnapshot) {
      this.current = current;
      this.enqueueCountSnapshot = enqueueCountSnapshot;
      this.dequeueCountSnapshot = dequeueCountSnapshot;
    }

    public boolean hasNext() {
      if (enqueueCountSnapshot != enqueueCount || dequeueCountSnapshot != dequeueCount) {
        throw new ConcurrentModificationException();
      }
      return current != null;
    }

    public T next() {
      if (enqueueCountSnapshot != enqueueCount || dequeueCountSnapshot != dequeueCount) {
        throw new ConcurrentModificationException();
      }
      if (!hasNext()) throw new NoSuchElementException();
      T item = current.item;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }

  public T dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    T item = head.item;
    head = head.next;
    this.size--;
    if (isEmpty()) {
      tail = null;
    }
    dequeueCount++;
    return item;
  }

  public T peek() {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    return head.item;
  }

  public void enqueue(T item) {
    LinkedNode backup = tail;
    tail = new LinkedNode();
    tail.item = item;
    if (isEmpty()) {
      head = tail;
    } else {
      backup.next = tail;
    }
    enqueueCount++;
    this.size++;
  }

  public boolean isEmpty() {
    return this.head == null;
  }

  public int size() {
    return this.size;
  }

  public Iterator<T> iterator() {
    return new LinkedIterator(head, enqueueCount, dequeueCount);
  }

  public String toString() {
    StringBuilder buffer = new StringBuilder();
    for (T item : this) {
      buffer.append(item + " ");
    }
    return buffer.toString();
  }
}
