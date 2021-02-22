package com.cs4everyone.algorithms.sort;

public class Quick3way {

  public static void sort(Comparable[] array) {
    Knuth.shuffle(array);
    sort(array, 0, array.length - 1);
  }

  private static void sort(Comparable[] array, int low, int high) {
    if (high <= low) return;
    int lt = low, gt = high;
    Comparable partition = array[low];
    int i = low + 1;
    while (i <= gt) {
      int cmp = array[i].compareTo(partition);
      if (cmp < 0) swap(array, lt++, i++);
      else if (cmp > 0) swap(array, i, gt--);
      else i++;
    }
    sort(array, low, lt - 1);
    sort(array, gt + 1, high);
  }

  private static void swap(Comparable[] array, int i, int j) {
    Comparable swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
