package com.cs4everyone.algorithms.applications.percolation;

import com.cs4everyone.algorithms.union.find.WeightedPathHalvingUF;

public class Percolation {

  private static final int BLOCKED_SITE = 0;
  private static final int OPEN_SITE = 1;

  private int[][] sites;
  private int size;

  private int top;
  private int bottom;

  private WeightedPathHalvingUF uf;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    if (N <= 0) {
      throw new IllegalArgumentException("N must > 0");
    }
    size = N;
    sites = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        sites[i][j] = BLOCKED_SITE;
      }
    }
    top = size * size;
    bottom = size * size + 1;
    uf = new WeightedPathHalvingUF(size * size + 2);
  }

  // open site (row i, column j) if it is not open already
  public void open(int i, int j) {
    if (sites[i][j] == BLOCKED_SITE) {
      sites[i][j] = OPEN_SITE;
      int siteUFIndex = i * size + j;
      int neighborUFIndex;
      if (i > 0) {
        if (sites[i - 1][j] == OPEN_SITE) {
          neighborUFIndex = (i - 1) * size + j;
          uf.union(siteUFIndex, neighborUFIndex);
        }
      }

      if (i < size - 1) {
        if (sites[i + 1][j] == OPEN_SITE) {
          neighborUFIndex = (i + 1) * size + j;
          uf.union(siteUFIndex, neighborUFIndex);
        }
      }

      if (j > 0) {
        if (sites[i][j - 1] == OPEN_SITE) {
          neighborUFIndex = i * size + j - 1;
          uf.union(siteUFIndex, neighborUFIndex);
        }
      }

      if (j < size - 1) {
        if (sites[i][j + 1] == OPEN_SITE) {
          neighborUFIndex = i * size + j + 1;
          uf.union(siteUFIndex, neighborUFIndex);
        }
      }

      if (i == 0) {
        uf.union(siteUFIndex, top);
      }

      if (i == size - 1) {
        uf.union(siteUFIndex, bottom);
      }
    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int i, int j) {
    return sites[i][j] == OPEN_SITE;
  }

  // is site (row i, column j) full?
  public boolean isFull(int i, int j) {
    int siteUFIndex = i * size + j;
    return uf.isConnected(siteUFIndex, top);
  }

  // does the system percolate?
  public boolean percolates() {
    return uf.isConnected(top, bottom);
  }
}
