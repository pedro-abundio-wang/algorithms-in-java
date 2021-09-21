package com.cs4everyone.algorithms.applications.convex.hull;

import com.cs4everyone.algorithms.stack.LinkedStack;
import com.cs4everyone.algorithms.utils.StdIn;
import com.cs4everyone.algorithms.utils.StdOut;

import java.util.Arrays;

/**
 * The {@code GrahamScan} data type provides methods for computing the convex hull of a set of
 * <em>n</em> points in the plane.
 *
 * <p>The implementation uses the Graham-Scan convex hull algorithm. It runs in O(<em>n</em> log
 * <em>n</em>) time in the worst case and uses O(<em>n</em>) extra memory.
 */
public class GrahamScan {

  private LinkedStack<Point2D> hull = new LinkedStack<Point2D>();

  /**
   * Computes the convex hull of the specified array of points.
   *
   * @param points the array of points
   * @throws IllegalArgumentException if {@code points} is {@code null}
   * @throws IllegalArgumentException if any entry in {@code points[]} is {@code null}
   * @throws IllegalArgumentException if {@code points.length} is {@code 0}
   */
  public GrahamScan(Point2D[] points) {
    if (points == null) throw new IllegalArgumentException("argument is null");
    if (points.length == 0) throw new IllegalArgumentException("array is of length 0");

    // defensive copy
    int n = points.length;
    Point2D[] copyOfPoints = new Point2D[n];
    for (int i = 0; i < n; i++) {
      if (points[i] == null) throw new IllegalArgumentException("points[" + i + "] is null");
      copyOfPoints[i] = points[i];
    }

    // preprocess so that copyOfPoints[0] has lowest y-coordinate; break ties by x-coordinate
    // copyOfPoints[0] is an extreme point of the convex hull
    // (alternatively, could do easily in linear time)
    Arrays.sort(copyOfPoints);

    // sort by polar angle with respect to base point copyOfPoints[0],
    // breaking ties by distance to copyOfPoints[0]
    Arrays.sort(copyOfPoints, 1, n, copyOfPoints[0].polarOrder());

    hull.push(copyOfPoints[0]); // copyOfPoints[0] is first extreme point

    // find index k1 of first point not equal to copyOfPoints[0]
    int k1;
    for (k1 = 1; k1 < n; k1++) if (!copyOfPoints[0].equals(copyOfPoints[k1])) break;
    if (k1 == n) return; // all points equal

    // find index k2 of first point not collinear with copyOfPoints[0] and copyOfPoints[k1]
    int k2;
    for (k2 = k1 + 1; k2 < n; k2++) if (Point2D.ccw(copyOfPoints[0], copyOfPoints[k1], copyOfPoints[k2]) != 0) break;
    hull.push(copyOfPoints[k2 - 1]); // copyOfPoints[k2-1] is second extreme point

    // Graham scan; note that copyOfPoints[n-1] is extreme point different from copyOfPoints[0]
    for (int i = k2; i < n; i++) {
      Point2D top = hull.pop();
      while (Point2D.ccw(hull.peek(), top, copyOfPoints[i]) <= 0) {
        top = hull.pop();
      }
      hull.push(top);
      hull.push(copyOfPoints[i]);
    }

    assert isConvex();
  }

  /**
   * Returns the extreme points on the convex hull in counterclockwise order.
   *
   * @return the extreme points on the convex hull in counterclockwise order
   */
  public Iterable<Point2D> hull() {
    LinkedStack<Point2D> stack = new LinkedStack<Point2D>();
    for (Point2D p : hull) stack.push(p);
    return stack;
  }

  // check that boundary of hull is strictly convex
  private boolean isConvex() {
    int n = hull.size();
    if (n <= 2) return true;

    Point2D[] points = new Point2D[n];
    int k = 0;
    for (Point2D p : hull()) {
      points[k++] = p;
    }

    for (int i = 0; i < n; i++) {
      if (Point2D.ccw(points[i], points[(i + 1) % n], points[(i + 2) % n]) <= 0) {
        return false;
      }
    }
    return true;
  }
}
