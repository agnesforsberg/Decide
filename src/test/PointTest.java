package Decide.src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        assertEquals(10, p2.distanceTo(p1), expectedAccuracy);
    }

    /**
     * Creates two points along the X-axis and calculates the distance between them.
     */
    @Test
    public void distanceTestSimple2() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);

        assertEquals(10, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(10, p2.distanceTo(p1), expectedAccuracy);
    }

    /**
     * Creates two points along the Y-axis and calculates the distance between them.
     */
    @Test
    public void distanceTestComplex() {
        Point p1 = new Point(0, 10);
        Point p2 = new Point(10, 0);

        assertEquals(14.1421356237, p1.distanceTo(p2), expectedAccuracy);
        assertEquals(14.1421356237, p2.distanceTo(p1), expectedAccuracy);
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
        assertEquals(14.1421356237, p2.distanceTo(p1), expectedAccuracy);
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
        assertEquals(14.1421356237, p2.distanceTo(p1), expectedAccuracy);
    }

    /**
     * Tests all four grid-alligned perpendicular angles.
     */
    @Test
    public void angleTrivialTest() {
        Point o = new Point(0, 0);

        Point p1, p2;

        p1 = new Point(0, 10);
        p2 = new Point(10, 0);

        assertEquals(Math.PI / 2, o.angleBetween(p1, p2), expectedAccuracy);
        assertEquals(Math.PI / 2, o.angleBetween(p2, p1), expectedAccuracy);

        p1 = new Point(0, -10);
        p2 = new Point(10, 0);

        assertEquals(Math.PI / 2, o.angleBetween(p1, p2), expectedAccuracy);
        assertEquals(Math.PI / 2, o.angleBetween(p2, p1), expectedAccuracy);

        p1 = new Point(0, -10);
        p2 = new Point(-10, 0);

        assertEquals(Math.PI / 2, o.angleBetween(p1, p2), expectedAccuracy);
        assertEquals(Math.PI / 2, o.angleBetween(p2, p1), expectedAccuracy);

        p1 = new Point(0, 10);
        p2 = new Point(-10, 0);

        assertEquals(Math.PI / 2, o.angleBetween(p1, p2), expectedAccuracy);
        assertEquals(Math.PI / 2, o.angleBetween(p2, p1), expectedAccuracy);
    }

    /**
     * Tests the common use-case of a right triangle.
     */
    @Test
    public void rightTriangleTest() {
        Point o = new Point(0, 0);

        Point p1 = new Point(10, 10);
        Point p2 = new Point(10, 0);

        assertEquals(Math.PI / 4, o.angleBetween(p1, p2), expectedAccuracy);
        assertEquals(Math.PI / 4, o.angleBetween(p2, p1), expectedAccuracy);
    }

    @Test
    public void equalsTest() {
        Point p = new Point(0, 0);

        // A point should be equal to itself.
        assertTrue(p.equals(p));

        // A point should be equal to a point at the same position.
        assertTrue(p.equals(new Point(0, 0)));
    }

    @Test
    public void notEqualsTest() {
        Point p = new Point(0, 0);

        assertFalse(p.equals(new Point(0, 10)));
        assertFalse(p.equals(new Point(10, 0)));
    }

    @Test
    public void distanceWhenPointOnLineTest() {
        Point p = new Point(10, 10);

        Point l1 = new Point(5, 5);
        Point l2 = new Point(15, 15);

        assertEquals(0, p.distanceToLineThrough(l1, l2), expectedAccuracy);
    }

    @Test
    public void distanceWhenPointsFormAcuteTriangle() {
        Point p = new Point(0, -3);

        Point l1 = new Point(-3, 0);
        Point l2 = new Point(3, 0);

        assertEquals(3, p.distanceToLineThrough(l1, l2), expectedAccuracy);
    }

    @Test
    public void distanceWhenPointsFormObtuseTriangle() {
        Point p = new Point(-100, -5);

        Point l1 = new Point(5, 0);
        Point l2 = new Point(10, 0);

        assertEquals(5, p.distanceToLineThrough(l1, l2), expectedAccuracy);
    }

    @Test
    public void radiusWithPointsOnLine() {
        Point a = new Point(-10, 0);
        Point b = new Point(0, 0);
        Point c = new Point(10, 0);

        assertEquals(10, Point.smallestCircle(a, b, c), expectedAccuracy);
    }

    @Test
    public void radiusWithStackedPoints() {
        Point a = new Point(-10, 0);
        Point b = new Point(10, 0);
        Point c = new Point(10, 0);

        assertEquals(10, Point.smallestCircle(a, b, c), expectedAccuracy);
    }

    @Test
    public void radiusWithObtusePoints() {
        Point a = new Point(-10, 0);
        Point b = new Point(5, 0);
        Point c = new Point(10, 0);

        assertEquals(10, Point.smallestCircle(a, b, c), expectedAccuracy);
    }

    @Test
    public void radiusWithNonObtusePoints() {
        Point a = new Point(-10, 0);
        Point b = new Point(0, 10);
        Point c = new Point(10, 0);

        assertEquals(10, Point.smallestCircle(a, b, c), expectedAccuracy);
    }
}
