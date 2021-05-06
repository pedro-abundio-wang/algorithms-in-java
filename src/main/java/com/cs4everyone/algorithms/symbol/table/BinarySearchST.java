package com.cs4everyone.algorithms.symbol.table;

import com.cs4everyone.algorithms.queue.LinkedQueue;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

  private static final int INIT_CAPACITY = 8;

  private Key[] keys;

  private Value[] values;

  private int size = 0;

  public BinarySearchST() {
    this(INIT_CAPACITY);
  }

  public BinarySearchST(int capacity) {
    keys = (Key[]) new Comparable[capacity];
    values = (Value[]) new Object[capacity];
  }

  private void resize(int capacity) {
    assert capacity >= size;
    Key[] copyKeys = (Key[]) new Comparable[capacity];
    Value[] copyValues = (Value[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      copyKeys[i] = keys[i];
      copyValues[i] = values[i];
    }
    values = copyValues;
    keys = copyKeys;
  }

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
    if (isEmpty()) return null;
    int i = rank(key);
    if (i < size && keys[i].compareTo(key) == 0) return values[i];
    return null;
  }

  /**
   * Returns the number of keys in this symbol table strictly less than {@code key}.
   *
   * @param key the key
   * @return the number of keys in the symbol table strictly less than {@code key}
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public int rank(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");
    int lo = 0, hi = size - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = key.compareTo(keys[mid]);
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return lo;
  }

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("argument to put() is null");

    if (val == null) {
      delete(key);
      return;
    }

    int i = rank(key);

    // key is already in table
    if (i < size && keys[i].compareTo(key) == 0) {
      values[i] = val;
      return;
    }

    // insert new key-value pair
    if (size == keys.length) resize(2 * keys.length);

    for (int j = size; j > i; j--) {
      keys[j] = keys[j - 1];
      values[j] = values[j - 1];
    }
    keys[i] = key;
    values[i] = val;
    size++;

    assert check();
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    if (isEmpty()) return;

    // compute rank
    int i = rank(key);

    // key not in table
    if (i == size || keys[i].compareTo(key) != 0) {
      return;
    }

    for (int j = i; j < size - 1; j++) {
      keys[j] = keys[j + 1];
      values[j] = values[j + 1];
    }

    size--;
    // to avoid loitering
    keys[size] = null;
    values[size] = null;

    // resize if 1/4 full
    if (size > 0 && size == keys.length / 4) resize(keys.length / 2);

    assert check();
  }

  public void deleteMin() {
    if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
    delete(min());
  }

  public void deleteMax() {
    if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
    delete(max());
  }

  /***************************************************************************
   *  Ordered symbol table methods.
   ***************************************************************************/

  public Key min() {
    if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
    return keys[0];
  }

  public Key max() {
    if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
    return keys[size - 1];
  }

  public Key select(int k) {
    if (k < 0 || k >= size()) {
      throw new IllegalArgumentException("called select() with invalid argument: " + k);
    }
    return keys[k];
  }

  public Key floor(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to floor() is null");
    int i = rank(key);
    if (i < size && key.compareTo(keys[i]) == 0) return keys[i];
    if (i == 0) throw new NoSuchElementException("argument to floor() is too small");
    else return keys[i - 1];
  }

  public Key ceil(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
    int i = rank(key);
    if (i == size) throw new NoSuchElementException("argument to ceiling() is too large");
    else return keys[i];
  }

  public int size(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("argument to size() is null");
    if (hi == null) throw new IllegalArgumentException("argument to size() is null");

    if (lo.compareTo(hi) > 0) return 0;
    if (contains(hi)) return rank(hi) - rank(lo) + 1;
    else return rank(hi) - rank(lo);
  }

  public Iterable<Key> keys() {
    return keys(min(), max());
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("argument to keys() is null");
    if (hi == null) throw new IllegalArgumentException("argument to keys() is null");

    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    if (lo.compareTo(hi) > 0) return queue;
    for (int i = rank(lo); i < rank(hi); i++) queue.enqueue(keys[i]);
    if (contains(hi)) queue.enqueue(keys[rank(hi)]);
    return queue;
  }

  /***************************************************************************
   *  Check internal invariants.
   ***************************************************************************/

  private boolean check() {
    return isSorted() && rankCheck();
  }

  // are the items in the array in ascending order?
  private boolean isSorted() {
    for (int i = 1; i < size(); i++)
      if (keys[i].compareTo(keys[i - 1]) < 0)
        return false;
    return true;
  }

  // check that rank(select(i)) = i
  private boolean rankCheck() {
    for (int i = 0; i < size(); i++)
      if (i != rank(select(i)))
        return false;
    for (int i = 0; i < size(); i++)
      if (keys[i].compareTo(select(rank(keys[i]))) != 0)
        return false;
    return true;
  }
}
