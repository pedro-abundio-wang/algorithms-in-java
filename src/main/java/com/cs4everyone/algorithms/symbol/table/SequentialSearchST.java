package com.cs4everyone.algorithms.symbol.table;

import com.cs4everyone.algorithms.queue.LinkedQueue;

public class SequentialSearchST<Key, Value> {

  // number of key-value pairs
  private int size;

  // the linked list of key-value pairs
  private LinkedNode head;

  private class LinkedNode {
    private Key key;
    private Value val;
    private LinkedNode next;

    public LinkedNode(Key key, Value val, LinkedNode next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  public SequentialSearchST() {}

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  public Value get(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    for (LinkedNode node = head; node != null; node = node.next) {
      if (key.equals(node.key)) return node.val;
    }
    return null;
  }

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("argument to put() is null");
    if (val == null) {
      delete(key);
      return;
    }

    for (LinkedNode node = head; node != null; node = node.next) {
      if (key.equals(node.key)) {
        node.val = val;
        return;
      }
    }

    head = new LinkedNode(key, val, head);
    size++;
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    head = delete(head, key);
  }

  private LinkedNode delete(LinkedNode head, Key key) {
    if (head == null) return null;
    if (key.equals(head.key)) {
      // to avoid loitering
      head = head.next;
      size--;
      return head;
    }
    head.next = delete(head.next, key);
    return head;
  }

  public Iterable<Key> keys() {
    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    for (LinkedNode node = head; node != null; node = node.next)
      queue.enqueue(node.key);
    return queue;
  }
}
