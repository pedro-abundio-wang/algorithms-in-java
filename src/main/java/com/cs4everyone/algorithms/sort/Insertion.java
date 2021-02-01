package com.cs4everyone.algorithms.sort;

public class Insertion {

  public static void sort(Comparable[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j > 0; j--) {
        if (less(array[j], array[j - 1])) {
          swap(array, j, j - 1);
        } else {
          break;
        }
      }
    }
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static void swap(Comparable[] array, int i, int j) {
    Comparable swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
