package com.cs4everyone.algorithms.queue;

import com.cs4everyone.algorithms.stack.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

  private LinkedNode head;

  private LinkedNode tail;

  private int size;

  private class LinkedNode {
    private T item;
    private LinkedNode next;
  }

  private class LinkedIterator implements Iterator<T> {

    private LinkedNode current;

    public LinkedIterator(LinkedNode current) {
      this.current = current;
    }

    public boolean hasNext() {
      return current != null;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
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
    this.size++;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  public Iterator<T> iterator() {
    return new LinkedIterator(head);
  }

  public String toString() {
    StringBuilder buffer = new StringBuilder();
    for (T item : this) {
      buffer.append(item + " ");
    }
    return buffer.toString();
  }
}
