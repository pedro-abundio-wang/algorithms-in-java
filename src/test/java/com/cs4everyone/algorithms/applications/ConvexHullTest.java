package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.applications.convex.hull.GrahamScan;
import com.cs4everyone.algorithms.applications.convex.hull.Point2D;
import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class ConvexHullTest {

    @Test
    public void test(){
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

}
