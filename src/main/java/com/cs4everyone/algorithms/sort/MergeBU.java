package com.cs4everyone.algorithms.sort;

public class MergeBU {

  public static void sort(Comparable[] array) {
    Comparable[] aux = new Comparable[array.length];
    for (int sz = 1; sz < array.length; sz = sz + sz)
      for (int low = 0; low < array.length - sz; low += sz + sz)
        merge(array, aux, low, low + sz - 1, Math.min(low + sz + sz - 1, array.length - 1));
  }

  private static void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high) {
    for (int i = low; i <= high; i++) {
      aux[i] = array[i];
    }
    int i = low;
    int j = mid + 1;
    for (int k = low; k <= high; k++) {
      if (i > mid) {
        array[k] = aux[j++];
      } else if (j > high) {
        array[k] = aux[i++];
      } else if (less(aux[i], aux[j])) {
        array[k] = aux[i++];
      } else {
        array[k] = aux[j++];
      }
    }
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }
}
