package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition1Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;

        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC1 must be false if there are less than three points.", li.lic1());
    }

    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;

        int numPoints = 2;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC1 must be false if there are no points.", li.lic1());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 1), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic1());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 10;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 10), new Point(0, -11) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic1());
    }

    @Test
    public void pointsOnEdgeTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 3.5;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(3.5, 0), new Point(0, 3.5) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic1());
    }

    @Test
    public void manyPointsFailingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;

        int numPoints = 100;

        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble() * 0.8 - 0.4;
            double y = rand.nextDouble() * 0.8 - 0.4;
            points[i] = new Point(x, y);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic1());
    }

    @Test
    public void manyPointsPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic1());
    }
}
