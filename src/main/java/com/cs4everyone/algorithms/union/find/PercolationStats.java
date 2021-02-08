package com.cs4everyone.algorithms.union.find;

public class PercolationStats {

  private double[] meanStats;

  // perform T independent experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    meanStats = new double[T];
    for (int t = 0; t < T; t++) {
      Percolation percolation = new Percolation(N);
      int openCount = 0;
      while (!percolation.percolates()) {
        int i = (int) (Math.random() * N);
        int j = (int) (Math.random() * N);
        if (!percolation.isOpen(i, j)) {
          percolation.open(i, j);
          openCount++;
        }
      }
      double percentage = ((double) openCount) / (N * N);
      meanStats[t] = percentage;
      System.out.println("percolation experiment " + t + ", percentage = " + percentage);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    double sum = 0;
    double mean;
    for (int i = 0; i < meanStats.length; i++) {
      sum += meanStats[i];
    }
    mean = sum / meanStats.length;
    return mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    double sum = 0;
    double stddev;
    double mean = this.mean();
    for (int i = 0; i < meanStats.length; i++) {
      sum += (meanStats[i] - mean) * (meanStats[i] - mean);
    }
    stddev = sum / meanStats.length;
    return stddev;
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLow() {
    return this.mean() - 1.96 * Math.sqrt(this.stddev()) / Math.sqrt(meanStats.length);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHigh() {
    return this.mean() + 1.96 * Math.sqrt(this.stddev()) / Math.sqrt(meanStats.length);
  }
}
