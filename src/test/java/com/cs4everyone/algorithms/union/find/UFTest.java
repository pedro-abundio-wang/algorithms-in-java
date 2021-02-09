package com.cs4everyone.algorithms.union.find;

import com.cs4everyone.algorithms.utils.In;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;

public class UFTest {

  private static final String tinyUF = "tinyUF.txt";
  private static final String mediumUF = "mediumUF.txt";
  private static final String largeUF = "largeUF.txt";

  @Test
  public void quickFindUFTest() throws IOException {
    In in = new In(mediumUF);
    int n = in.readInt();
    QuickFindUF uf = new QuickFindUF(n);
    while (!in.isEmpty()) {
      int p = in.readInt();
      int q = in.readInt();
      uf.union(p, q);
      System.out.println(p + " " + q);
    }
    System.out.println(uf.count() + " components");
  }

  @Test
  public void quickUnionUFTest() throws IOException {
    In in = new In(mediumUF);
    int n = in.readInt();
    QuickUnionUF uf = new QuickUnionUF(n);
    while (!in.isEmpty()) {
      int p = in.readInt();
      int q = in.readInt();
      uf.union(p, q);
      System.out.println(p + " " + q);
    }
    System.out.println(uf.count() + " components");
  }

  @Test
  public void weightedQuickUnionUFTest() throws IOException {
    In in = new In(mediumUF);
    int n = in.readInt();
    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
    while (!in.isEmpty()) {
      int p = in.readInt();
      int q = in.readInt();
      uf.union(p, q);
      System.out.println(p + " " + q);
    }
    System.out.println(uf.count() + " components");
  }

  @Test
  public void weightedPathHalvingUFTest() throws IOException {
    In in = new In(mediumUF);
    int n = in.readInt();
    WeightedPathHalvingUF uf = new WeightedPathHalvingUF(n);
    while (!in.isEmpty()) {
      int p = in.readInt();
      int q = in.readInt();
      uf.union(p, q);
      System.out.println(p + " " + q);
    }
    System.out.println(uf.count() + " components");
  }

  @Test
  public void weightedPathCompressionUFTest() throws IOException {
    In in = new In(mediumUF);
    int n = in.readInt();
    WeightedPathCompressionUF uf = new WeightedPathCompressionUF(n);
    while (!in.isEmpty()) {
      int p = in.readInt();
      int q = in.readInt();
      uf.union(p, q);
      System.out.println(p + " " + q);
    }
    System.out.println(uf.count() + " components");
  }
}
