package Decide.src.main;

import java.lang.Math;

/**
 * A class representing a point in 2-D space with double accuracy.
 */
public class Point {

    // X and Y coordinates
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            if (this == obj)
                return true;

            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Point(%f, %f)", this.x, this.y);
    }

    /**
     * Returns the distance between two Points.
     * 
     * @param other another Point to measure the distance to.
     * @return the Euclidean distance between this Point and the other Point.
     */
    public double distanceTo(Point other) {
        double xDifference = this.x - other.x;
        double yDifference = this.y - other.y;

        return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
    }

    /**
     * Returns the angle between three points. AthisB
     * 
     * @param a the angle starting point.
     * @param b the angle ending point.
     * @return the angle between three points-
     */
    public double angleBetween(Point a, Point b) {
        Point oa = new Point(a.x - this.x, a.y - this.y);
        Point ob = new Point(b.x - this.x, b.y - this.y);

        double dotProduct = oa.x * ob.x + oa.y * ob.y;
        double lenOA = this.distanceTo(a);
        double lenOB = this.distanceTo(b);

        return Math.acos(dotProduct / (lenOA * lenOB));
    }
}
