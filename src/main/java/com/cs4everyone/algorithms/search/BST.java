package com.cs4everyone.algorithms.search;

import com.cs4everyone.algorithms.queue.LinkedQueue;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    private Key key;
    private Value val;
    private int count;
    private Node left, right;

    public Node(Key key, Value val) {
      this.key = key;
      this.val = val;
      this.count = 1;
    }
  }

  public int size() {
    return size(root);
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  private int size(Node node) {
    if (node == null) return 0;
    return node.count;
  }

  public int size(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("argument to size() is null");
    if (hi == null) throw new IllegalArgumentException("argument to size() is null");

    if (lo.compareTo(hi) > 0) return 0;
    if (contains(hi)) return rank(hi) - rank(lo) + 1;
    else return rank(hi) - rank(lo);
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  private Node put(Node node, Key key, Value val) {
    if (node == null) return new Node(key, val);
    int cmp = key.compareTo(node.key);
    if (cmp < 0) node.left = put(node.left, key, val);
    else if (cmp > 0) node.right = put(node.right, key, val);
    else node.val = val;
    node.count = 1 + size(node.left) + size(node.right);
    return node;
  }

  public Value get(Key key) {
    Node node = root;
    while (node != null) {
      int cmp = key.compareTo(node.key);
      if (cmp < 0) node = node.left;
      else if (cmp > 0) node = node.right;
      else return node.val;
    }
    return null;
  }

  public Key min() {
    if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
    return min(root).key;
  }

  private Node min(Node node) {
    if (node.left == null) return node;
    else return min(node.left);
  }

  public Key max() {
    if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
    return max(root).key;
  }

  private Node max(Node node) {
    if (node.right == null) return node;
    else return max(node.right);
  }

  public Key floor(Key key) {
    // Largest key ≤ a given key
    Node node = floor(root, key);
    if (node == null) return null;
    return node.key;
  }

  private Node floor(Node node, Key key) {
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0) return node;
    if (cmp < 0) return floor(node.left, key);
    Node x = floor(node.right, key);
    if (x != null) return x;
    else return node;
  }

  public Key ceil(Key key) {
    // Smallest key ≥ a given key
    Node node = ceil(root, key);
    if (node == null) return null;
    return node.key;
  }

  private Node ceil(Node node, Key key) {
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0) return node;
    if (cmp > 0) return ceil(node.right, key);
    Node x = ceil(node.left, key);
    if (x != null) return x;
    else return node;
  }

  public int rank(Key key) {
    return rank(root, key);
  }

  private int rank(Node node, Key key) {
    if (node == null) return 0;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) return rank(node.left, key);
    else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
    else return size(node.left);
  }

  public Key select(int rank) {
    if (rank < 0 || rank >= size()) {
      throw new IllegalArgumentException("argument to select() is invalid: " + rank);
    }
    return select(root, rank);
  }

  // Return key in BST rooted at node of given rank.
  // Precondition: rank is in legal range.
  private Key select(Node node, int rank) {
    if (node == null) return null;
    int leftSize = size(node.left);
    if (leftSize > rank) return select(node.left, rank);
    else if (leftSize < rank) return select(node.right, rank - leftSize - 1);
    else return node.key;
  }

  public void deleteMin() {
    root = deleteMin(root);
  }

  private Node deleteMin(Node node) {
    if (node.left == null) return node.right;
    node.left = deleteMin(node.left);
    node.count = 1 + size(node.left) + size(node.right);
    return node;
  }

  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node delete(Node node, Key key) {
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0) node.left = delete(node.left, key);
    else if (cmp > 0) node.right = delete(node.right, key);
    else {
      if (node.right == null) return node.left;
      if (node.left == null) return node.right;
      Node x = node;
      node = min(x.right);
      node.right = deleteMin(x.right);
      node.left = x.left;
    }
    node.count = size(node.left) + size(node.right) + 1;
    return node;
  }

  public Iterable<Key> keys() {
    if (isEmpty()) return new LinkedQueue<Key>();
    return keys(min(), max());
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    keys(root, queue, lo, hi);
    return queue;
  }

  private void keys(Node node, LinkedQueue<Key> queue, Key lo, Key hi) {
    if (node == null) return;
    int cmplo = lo.compareTo(node.key);
    int cmphi = hi.compareTo(node.key);
    if (cmplo < 0) keys(node.left, queue, lo, hi);
    if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
    if (cmphi > 0) keys(node.right, queue, lo, hi);
  }

  public Iterable<Key> iterator() {
    LinkedQueue<Key> queue = new LinkedQueue<>();
    inorder(root, queue);
    return queue;
  }

  private void inorder(Node node, LinkedQueue<Key> queue) {
    if (node == null) return;
    inorder(node.left, queue);
    queue.enqueue(node.key);
    inorder(node.right, queue);
  }
}
