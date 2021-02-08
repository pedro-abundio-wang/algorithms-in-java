package com.cs4everyone.algorithms.search;

public class BinarySearch {

  private BinarySearch() {}

  public static int indexOf(int[] sortedArray, int key) {
    int low = 0, high = sortedArray.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (key < sortedArray[mid]) {
        high = mid - 1;
      } else if (key > sortedArray[mid]) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
}
