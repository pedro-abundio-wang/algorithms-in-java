package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.applications.percolation.PercolationStats;
import org.junit.Test;

public class PercolationStatsTest {

    @Test
    public void percolationStatsTest(){
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("confidenceLow = " + percolationStats.confidenceLow());
        System.out.println("confidenceHigh = " + percolationStats.confidenceHigh());
    }

}
