package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.applications.convex.hull.GrahamScan;
import com.cs4everyone.algorithms.applications.convex.hull.Point2D;
import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import com.cs4everyone.algorithms.utils.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConvexHullTest {

  @Test
  public void test() {
    String filename = "input19.txt";
    In in = new In(filename);
    int n = in.readInt();
    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point2D(x, y);
    }
    GrahamScan graham = new GrahamScan(points);
    for (Point2D p : graham.hull()) StdOut.println(p);
  }

  @Test
  public void convexHullMaxDistance() {
    int n = 100000;
    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      int x = StdRandom.uniform(1000);
      int y = StdRandom.uniform(1000);
      points[i] = new Point2D(x, y);
    }
    long now = System.currentTimeMillis();
    List<Point2D> hullPoints = new ArrayList<>();
    GrahamScan graham = new GrahamScan(points);
    for (Point2D p : graham.hull()) hullPoints.add(p);
    double maxDistance = 0.0;
    int maxI = 0;
    int maxJ = 0;
    double distance;
    for (int i = 0; i < hullPoints.size(); i++) {
      for (int j = 0; j < hullPoints.size(); j++) {
        distance = hullPoints.get(i).distanceTo(hullPoints.get(j));
        if (distance > maxDistance) {
          maxDistance = distance;
          maxI = i;
          maxJ = j;
        }
      }
    }
    long period = System.currentTimeMillis() - now;
    StdOut.println("maxDistance = " + maxDistance);
    StdOut.println("maxI = " + maxI);
    StdOut.println("maxJ = " + maxJ);
    StdOut.println("period = " + period);
  }

  @Test
  public void rawMaxDistance() {
    int n = 100000;
    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      int x = StdRandom.uniform(1000);
      int y = StdRandom.uniform(1000);
      points[i] = new Point2D(x, y);
    }
    long now = System.currentTimeMillis();
    double maxDistance = 0.0;
    int maxI = 0;
    int maxJ = 0;
    double distance;
    for (int i = 0; i < points.length; i++) {
      for (int j = 0; j < points.length; j++) {
        distance = points[i].distanceTo(points[j]);
        if (distance > maxDistance) {
          maxDistance = distance;
          maxI = i;
          maxJ = j;
        }
      }
    }
    long period = System.currentTimeMillis() - now;
    StdOut.println("maxDistance = " + maxDistance);
    StdOut.println("maxI = " + maxI);
    StdOut.println("maxJ = " + maxJ);
    StdOut.println("period = " + period);
  }
}
