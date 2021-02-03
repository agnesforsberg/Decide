package Decide.src.test;

import org.junit.Test;

import java.awt.*;
import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition7Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    /**
     * Tests that LIC7 returns false when there are no points.
     */
    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 2;

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC7 must be false if there are no points.", li.lic7());
    }

    /**
     * Tests that LIC7 returns false when there are less than 3 points.
     */
    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 2;

        int numPoints = 2;

        Point[] points = { new Point(0, 1), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC7 must be false if there are less than 3 points.", li.lic7());
    }

    /**
     * Tests that LIC7 returns false when the points separated by K_PTS points are less than LENGTH1 apart.
     * Here points[0] and points[2] are 1 point apart, but only 2 in length.
     */
    @Test
    public void notFarEnoughApartTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 1;

        int numPoints = 3;

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC7 must be false if the two points are not LENGTH1 apart.", li.lic7());
    }

    /**
     * Tests that LIC7 returns true when the 2 points separated by K_PTS points are more than LENGTH1 apart.
     * LENGTH1 = 5 and their distance is 6.
     */
    @Test
    public void farEnoughApartTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 1;

        int numPoints = 3;

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(6, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic7());
    }

    /**
     * Tests that LIC7 can handle large K_PTS and does not care where those points are in the plane.
     */
    @Test
    public void kptsLargeTrueTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 100;

        int numPoints = 102;

        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        for (int i = 1; i < numPoints - 1; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        points[numPoints - 1] = new Point(6,0);
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertTrue(li.lic7());


    }

    /**
     * Tests that LIC7 can detect false input with large K_PTS.
     */
    @Test
    public void kptsLargeFalseTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 100;

        int numPoints = 102;

        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        for (int i = 1; i < numPoints - 1; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        points[numPoints - 1] = new Point(1, 0);
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertFalse(li.lic7());
    }


}
