package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.sort.Knuth;

public class QuickSelect {

  public static Comparable select(Comparable[] array, int k) {
    Knuth.shuffle(array);
    int low = 0, high = array.length - 1;
    while (high > low) {
      int j = partition(array, low, high);
      if (j < k) low = j + 1;
      else if (j > k) high = j - 1;
      else return array[k];
    }
    return array[k];
  }

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

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static void swap(Comparable[] array, int i, int j) {
    Comparable swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
