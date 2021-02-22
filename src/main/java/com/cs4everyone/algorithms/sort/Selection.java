package com.cs4everyone.algorithms.sort;

import java.util.Comparator;

public class Selection {

  public static void sort(Comparable[] array) {
    for (int i = 0; i < array.length; i++) {
      int min = i;
      for (int j = i + 1; j < array.length; j++) {
        if (less(array[j], array[min])) {
          min = j;
        }
      }
      swap(array, i, min);
    }
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static boolean less(Comparator comparator, Comparable a, Comparable b) {
    return comparator.compare(a, b) < 0;
  }

  private static void swap(Comparable[] array, int i, int j) {
    Comparable swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
