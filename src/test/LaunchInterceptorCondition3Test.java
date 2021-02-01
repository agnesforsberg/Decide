package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition3Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;

        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC3 must be false if there are less than three points.", li.lic3());
    }

    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;

        int numPoints = 2;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC3 must be false if there are no points.", li.lic3());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 1), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic3());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 10), new Point(10, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic3());
    }

    @Test
    public void manyPointsFailingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;

        int numPoints = 100;

        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            points[i] = new Point(x, y);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic3());
    }

    @Test
    public void manyPointsPassingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 50;

        int numPoints = 100;

        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble()+i;
            double y = rand.nextDouble()+i*(i%2);
            points[i] = new Point(x, y);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic3());
    }
}
