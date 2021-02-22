package com.cs4everyone.algorithms.sort;

public class Quick {

  private static int partition(Comparable[] array, int low, int high) {
    int i = low;
    int j = high + 1;
    while (true) {
      while (less(array[++i], array[low])) if (i == high) break;
      while (less(array[low], array[--j])) if (j == low) break;
      if (i >= j) break;
      swap(array, i, j);
    }
    swap(array, low, j);
    return j;
  }

  public static void sort(Comparable[] array) {
    Knuth.shuffle(array);
    sort(array, 0, array.length - 1);
  }

  private static void sort(Comparable[] array, int low, int high) {
    if (high <= low) return;
    int j = partition(array, low, high);
    sort(array, low, j - 1);
    sort(array, j + 1, high);
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
