package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.search.BinarySearch;
import com.cs4everyone.algorithms.sort.Merge;
import com.cs4everyone.algorithms.utils.In;
import org.apache.commons.lang3.ArrayUtils;

public class Sum {

  // sort, binary search
  public static int searchCount(int[] array) {

    Integer[] boxedArray = ArrayUtils.toObject(array);
    Merge.sort(boxedArray);
    int[] primitiveArray = ArrayUtils.toPrimitive(boxedArray);

    int count = 0;
    int size = boxedArray.length;
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        int search = -(boxedArray[i] + boxedArray[j]);
        int index = BinarySearch.indexOf(primitiveArray, search);
        if (index != -1) {
          if (index > j) {
            count++;
          }
        }
      }
    }
    return count;
  }

  // Brute force
  public static int forceCount(int[] array) {
    int size = array.length;
    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {
        for (int k = j + 1; k < size; k++) {
          if (array[i] + array[j] + array[k] == 0) {
            count++;
          }
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String filename = "8Kints.txt";
    In in = new In(filename);
    int[] array = in.readAllInts();
    System.out.println("sum count = " + forceCount(array));
  }
}
