package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import com.cs4everyone.algorithms.utils.Stopwatch;
import org.junit.Test;

public class SumTest {

    @Test
    public void _1KTest(){
        String filename = "1Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.forceCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

    @Test
    public void _2KTest(){
        String filename = "2Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.forceCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

    @Test
    public void _4KTest(){
        String filename = "4Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.forceCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

    @Test
    public void _8KTest(){
        String filename = "8Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.forceCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

    @Test
    public void _4KSearchTest(){
        String filename = "4Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.searchCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

    @Test
    public void _8KSearchTest(){
        String filename = "8Kints.txt";
        In in = new In(filename);
        int[] array = in.readAllInts();
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println("sum count = " + Sum.searchCount(array));
        double time = stopwatch.elapsedTime();
        StdOut.println("elapsed time " + time);
    }

}
