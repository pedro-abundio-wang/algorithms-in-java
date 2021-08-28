package com.cs4everyone.algorithms.sort;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class SortTest {

  @Test
  public void selectionTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Selection.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void insertionTest() {
    In in = new In("tiny.txt");
    String[] array = in.readAllStrings();
    Insertion.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void shellTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Shell.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void bubbleTest() {
    In in = new In("tiny.txt");
    String[] array = in.readAllStrings();
    Bubble.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void mergeTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Merge.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void mergeBUTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    MergeBU.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void quickTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Quick.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void quick3wayTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Quick3way.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void quickDualPivotTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    QuickDualPivot.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }

  @Test
  public void heapTest() {
    In in = new In("words3.txt");
    String[] array = in.readAllStrings();
    Heap.sort(array);
    for (int i = 0; i < array.length; i++) {
      StdOut.println(array[i]);
    }
  }
}
