package com.cs4everyone.algorithms.sort;

public class Insertion {

    public static void sort(Comparable[] array) {
        
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
