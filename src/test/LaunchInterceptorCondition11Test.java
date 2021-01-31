package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition11Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Before
    public void init() {
        parameters = new Parameters();
    }

    @After
    public void tearDown() {
        parameters = null;
        li = null;
    }

    @Test
    public void lic11_falseIfNotEnoughPoints() {
        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC11 must be false if there are less than three points.", li.lic11());
    }

    @Test
    public void lic11_falseIfAllPointsCoincide() {
        parameters.G_PTS = 10;

        int numPoints = 1000;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(10, 10);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("x-distance can't be < 0 if all points coincide.", li.lic11());
    }

    @Test
    public void lic11_trueTrivialCase() {
        parameters.G_PTS = 1;

        int numPoints = 3;

        Point[] points = { new Point(3, 0), null, new Point(0, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic11());
    }

    @Test
    public void lic11_falseTrivialCase() {
        parameters.G_PTS = 1;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), null, new Point(3, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic11());
    }

    @Test
    public void lic11_trueIfCorrectOnceInLargeSample() {
        parameters.G_PTS = 50;

        int numPoints = 1000;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(i, 0);
        }

        Point tmp = points[875];
        points[875] = points[875 + parameters.G_PTS + 1];
        points[875 + parameters.G_PTS + 1] = tmp;

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic11());
    }
}
