package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition2Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC2 must be false if there are less than three points.", li.lic2());
    }

    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC2 must be false if there are no points.", li.lic2());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(-1, 0), new Point(0, 0), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic2());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(0, 1), new Point(0, 0), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic2());
    }

    @Test
    public void nonConsecutiveFailTest () {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        // a, b, c
        Point[] pointsT = { new Point(4, 0), new Point(-2, 1), new Point(0, 0) };
        li = new LaunchInterceptor(numPoints, pointsT, parameters, null, null);
        assertTrue(li.lic2());

        // a, c, b
        Point[] pointsF = { new Point(4, 0), new Point(0, 0), new Point(-2, 1) };
        li = new LaunchInterceptor(numPoints, pointsF, parameters, null, null);
        assertFalse(li.lic2());
    }

    @Test
    public void aCoincidesWithBFail() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic2());
    }

    @Test
    public void fourPointsConsecutiveTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 4;

        // a - b - c false, b - c - d true
        Point[] points = { new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(1, 1) };
        
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic2());
    }

}
