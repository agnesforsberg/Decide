package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import java.util.Random;

import org.junit.After;
import org.junit.Test;

public class LaunchInterceptorCondition6Test {
    private Parameters parameters;
    private LaunchInterceptor li;
    private Random rand = new Random();

    @After
    public void tearDown() {
        li = null;
        parameters = null;
    }

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();

        int numPoints = 2;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC6 must be false if there are less than three points.", li.lic6());
    }

    @Test
    public void trivialThreePointsOnLineTest() {
        parameters = new Parameters();
        parameters.DIST = 10;
        parameters.N_PTS = 3;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(1, 0), new Point(2, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic6());
    }

    @Test
    public void trivialThreePointsSuccedingTest() {
        parameters = new Parameters();
        parameters.DIST = 1;
        parameters.N_PTS = 3;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0.5, 1.5), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic6());
    }

    @Test
    public void trivialSameThreePointsWithoutDistance() {
        parameters = new Parameters();
        parameters.DIST = 0;
        parameters.N_PTS = 3;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(0, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic6());
    }

    @Test
    public void trivialSameThreePointsWithDistance() {
        parameters = new Parameters();
        parameters.DIST = 1;
        parameters.N_PTS = 3;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(0, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic6());
    }

    @Test
    public void manyClusteredPointsWithTooHighDistance() {
        parameters = new Parameters();
        parameters.DIST = 100;
        parameters.N_PTS = 20;

        int numPoints = 1000;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble() * 20 - 10;
            double y = rand.nextDouble() * 20 - 10;
            points[i] = new Point(x, y);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic6());
    }

    @Test
    public void manyCoincidingPointsOneOtherOutsideDistance() {
        parameters = new Parameters();
        parameters.DIST = 10;
        parameters.N_PTS = 20;

        int numPoints = 1000;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(0, 0);
        }

        points[723] = new Point(10, 10);

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic6());
    }

    @Test
    public void manyClusteredPointsOneOtherOutsideDistance() {
        parameters = new Parameters();
        parameters.DIST = 10;
        parameters.N_PTS = 20;

        int numPoints = 1000;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble() * 100 - 50;
            double y = 0;
            points[i] = new Point(x, y);
        }

        points[723] = new Point(100, 100);

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic6());
    }
}
