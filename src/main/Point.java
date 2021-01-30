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
}
