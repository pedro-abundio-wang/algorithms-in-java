package com.cs4everyone.algorithms.sort;

public class QuickDualPivot {

  // quicksort the array array[] using dual-pivot quicksort
  public static void sort(Comparable[] array) {
    Knuth.shuffle(array);
    sort(array, 0, array.length - 1);
  }

  // quicksort the subarray array[low .. high] using dual-pivot quicksort
  private static void sort(Comparable[] array, int low, int high) {
    if (high <= low) return;

    // make sure array[low] <= array[high]
    if (less(array[high], array[low])) swap(array, low, high);

    int lt = low + 1, gt = high - 1;
    int i = low + 1;
    while (i <= gt) {
      if (less(array[i], array[low])) swap(array, lt++, i++);
      else if (less(array[high], array[i])) swap(array, i, gt--);
      else i++;
    }
    swap(array, low, --lt);
    swap(array, high, ++gt);

    // recursively sort three subarrays
    sort(array, low, lt - 1);
    if (less(array[lt], array[gt])) sort(array, lt + 1, gt - 1);
    sort(array, gt + 1, high);
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
