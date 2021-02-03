package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition12Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    /**
     * LIC12 has some requirements equal to LIC7.
     * Lic7 has already been tested, so the requirements that are equal to Lic7 will not be tested here.
     * Those tests can be seen in LaunchInterceptorConditionTest7.java
     * All tests in this class will pass LIC7, and therefore only LIC12 is tested.
     */

    /**
     * Tests that LIC12 returns false when there are less than 3 points.
     */
    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 7;
        parameters.K_PTS = 1;

        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC12 must be false if there are less than three points.", li.lic12());
    }

    /**
     * Tests that LIC12 returns false when there are no points.
     */
    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 7;
        parameters.K_PTS = 1;

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC12 must be false if there are no points.", li.lic12());
    }

    /**
     * Tests that LIC12 returns false when the 2 points separated by K_PTS are more than LENGTH2 apart.
     */
    @Test
    public void notCloseEnoughTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 1;
        parameters.K_PTS = 1;

        int numPoints = 3;

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(6, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic12());
    }

    /**
     * Tests that LIC12 returns true when the 2 points are less than LENGTH2 apart but more than LENGTH1 apart.
     */
    @Test
    public void closeEnoughTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 7;
        parameters.K_PTS = 1;

        int numPoints = 3;

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(6, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic12());
    }

    /**
     * Tests that LIC12 can handle large values in K_PTS for a true input.
     */
    @Test
    public void kptsLargeTrueTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 7;
        parameters.K_PTS = 100;

        int numPoints = 102;

        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        for (int i = 1; i < numPoints - 1; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        points[numPoints - 1] = new Point(6,0);
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertTrue(li.lic12());


    }

    /**
     * Tests that LIC12 can handle large values in K_PTS for a false input.
     */
    @Test
    public void kptsLargeFalseTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 1;
        parameters.K_PTS = 100;

        int numPoints = 102;

        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        for (int i = 1; i < numPoints - 1; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        points[numPoints - 1] = new Point(2, 0);
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertFalse(li.lic12());
    }

}
