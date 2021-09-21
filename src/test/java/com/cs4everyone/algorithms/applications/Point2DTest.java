package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.applications.convex.hull.Point2D;
import com.cs4everyone.algorithms.utils.StdDraw;
import com.cs4everyone.algorithms.utils.StdRandom;
import org.junit.Test;

import java.util.Arrays;

public class Point2DTest {

  @Test
  public void drawTest() {
    int x0 = 50;
    int y0 = 50;
    int n = 100;

    StdDraw.setCanvasSize(800, 800);
    StdDraw.setXscale(0, 100);
    StdDraw.setYscale(0, 100);
    StdDraw.setPenRadius(0.005);
    StdDraw.enableDoubleBuffering();

    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++) {
      int x = StdRandom.uniform(100);
      int y = StdRandom.uniform(100);
      points[i] = new Point2D(x, y);
      points[i].draw();
    }

    // draw p = (x0, x1) in red
    Point2D p = new Point2D(x0, y0);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.setPenRadius(0.02);
    p.draw();

    // draw line segments from p to each point, one at a time, in polar order
    StdDraw.setPenRadius();
    StdDraw.setPenColor(StdDraw.BLUE);
    Arrays.sort(points, p.polarOrder());
    for (int i = 0; i < n; i++) {
      p.drawTo(points[i]);
      StdDraw.show();
      StdDraw.pause(100);
    }
  }
}
