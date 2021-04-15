package com.cs4everyone.algorithms.search;

import com.cs4everyone.algorithms.queue.LinkedQueue;

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

  public void delete(Key key) {
    /* see next slides */
  }

  public Value min() {
    Node node = root;
    if (node == null) return null;
    while (node.left != null) {
      node = node.left;
    }
    return node.val;
  }

  public Value max() {
    Node node = root;
    if (node == null) return null;
    while (node.right != null) {
      node = node.right;
    }
    return node.val;
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
