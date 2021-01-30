package Decide.src.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import Decide.src.main.Point;

public class PointTest {

    private final double expectedAccuracy = 1E-10;

    @Test
    public void constructorTest() {
        Point p = new Point(10, 20);
        assertEquals(10, p.x, expectedAccuracy);
        assertEquals(20, p.y, expectedAccuracy);
    }

    /**
     * Creates two points along the Y-axis and calculates the distance between them.
     */
    @Test
    public void distanceTestSimple1() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 10);

        assertEquals(10, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(p2.distanceTo(p1), 10, expectedAccuracy);
    }

    /**
     * Creates two points along the X-axis and calculates the distance between them.
     */
    @Test
    public void distanceTestSimple2() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);

        assertEquals(10, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(p2.distanceTo(p1), 10, expectedAccuracy);
    }

    /**
     * Creates two points along the Y-axis and calculates the distance between them.
     */
    @Test
    public void distanceTestComplex() {
        Point p1 = new Point(0, 10);
        Point p2 = new Point(10, 0);

        assertEquals(14.1421356237, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(p2.distanceTo(p1), 14.1421356237, expectedAccuracy);
    }

    /**
     * Creates two points in an upwards slash-like trajectory and calculates the
     * distance between them.
     */
    @Test
    public void distanceTestComplexSlash() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);

        assertEquals(14.1421356237, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(p2.distanceTo(p1), 14.1421356237, expectedAccuracy);
    }

    /**
     * Creates two points in a downwards backslash-like trajectory and calculates
     * the distance between them.
     */
    @Test
    public void distanceTestComplexBackSlash() {
        Point p1 = new Point(0, 10);
        Point p2 = new Point(10, 0);

        assertEquals(14.1421356237, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(p2.distanceTo(p1), 14.1421356237, expectedAccuracy);
    }

}
