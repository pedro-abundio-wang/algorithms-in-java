package com.cs4everyone.algorithms.sort;

public class Shell {

  public static void sort(Comparable[] array) {

    int h = 1;
    while (h < array.length / 3) h = 3 * h + 1;

    while (h >= 1) {
      for (int i = h; i < array.length; i++) {
        for (int j = i; j >= h; j = j - h) {
          if (less(array[j], array[j - h])) {
            swap(array, j, j - h);
          }
        }
      }
      h = h / 3;
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
