package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition0Test {
    private Decide.src.main.Parameters parameters;
    private Decide.src.main.LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        parameters = new Decide.src.main.Parameters();
        parameters.LENGTH1 = 5;

        int numPoints = 2;

        Decide.src.main.Point[] points = {};

        li = new Decide.src.main.LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC1 must be false if there are no points.", li.lic0());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(1, 1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic0());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(10, 10)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic0());
    }

    @Test
    public void pointsOnEdgeTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 10;

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(0, 10)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic0());
    }
}
