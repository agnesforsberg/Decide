package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition5Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        parameters = new Parameters();

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC5 must be false if there are no points.", li.lic5());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(1, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic5());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        int numPoints = 2;

        Point[] points = {new Point(1, 0), new Point(0, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic5());
    }

    @Test
    public void pointsOnEdgeTest() {
        parameters = new Parameters();

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(0, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic5());
    }
}
