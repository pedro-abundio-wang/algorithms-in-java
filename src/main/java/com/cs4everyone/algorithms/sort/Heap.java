package com.cs4everyone.algorithms.sort;

public class Heap {

  public static void sort(Comparable[] array) {

    // 1-based indexing
    int size = array.length;

    // heap construction
    for (int k = size / 2; k >= 1; k--) {
      sink(array, k, size);
    }

    // sort
    while (size > 1) {
      swap(array, 1, size);
      sink(array, 1, --size);
    }
  }

  private static void sink(Comparable[] array, int k, int size) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && less(array, j, j + 1)) j++;
      if (!less(array, k, j)) break;
      swap(array, k, j);
      k = j;
    }
  }

  /***************************************************************************
   * Helper functions for comparisons and swaps.
   * Indices are "off-by-one" to support 1-based indexing.
   ***************************************************************************/
  private static boolean less(Comparable[] array, int i, int j) {
    return array[i - 1].compareTo(array[j - 1]) < 0;
  }

  private static void swap(Object[] array, int i, int j) {
    Object swap = array[i - 1];
    array[i - 1] = array[j - 1];
    array[j - 1] = swap;
  }
}
