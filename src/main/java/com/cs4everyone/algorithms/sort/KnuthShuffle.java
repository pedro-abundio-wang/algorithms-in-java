package com.cs4everyone.algorithms.sort;

import java.util.Random;

public class KnuthShuffle {

  // Knuth shuffle

  public static void shuffle(Comparable[] array) {
    for (int i = 0; i < array.length; i++) {
      // choose index uniformly in [0, i]
      int r = (int) (Math.random() * (i + 1));
      swap(array, i, r);
    }
  }

  private static void swap(Comparable[] array, int i, int j) {
    Comparable swap = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
