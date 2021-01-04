package com.cs4everyone.algorithms.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Iterable<T> {

  private LinkedNode top;

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
      if (!hasNext()) throw new NoSuchElementException();
      T item = current.item;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
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

  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    return top.item;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  public Iterator<T> iterator() {
    return new LinkedIterator(top);
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
