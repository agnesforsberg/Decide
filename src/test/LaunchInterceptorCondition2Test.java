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

    /**
     * Tests that LIC2 returns false when there are less than 3 points.
     */
    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 2;

        Point[] points = { new Point(0, 0), new Point(1, 1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC2 must be false if there are less than three points.", li.lic2());
    }

    /**
     * Tests that LIC2 returns false when there are no points.
     */
    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC2 must be false if there are no points.", li.lic2());
    }

    /**
     * Tests a trivial failing test when the points form a line.
     * Since EPSILON = 1, a failing test is when the lines create an angle that satisfies 2.14 < angle < 4.14
     * A line has the angle PI so this test will fail.
     */
    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(-1, 0), new Point(0, 0), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic2());
    }

    /**
     * Tests a trivial passing test when the points form a 90 degree angle (PI/2 radians).
     * For the test to pass (based on EPSILON = 1) the angle must satify angle < 2.14 OR angle > 4.14
     * This angle is PI/2 and so this test should pass.
     */
    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(0, 1), new Point(0, 0), new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic2());
    }

    /**
     * Tests that points need to be in consectutive order.
     * The points a, b, c form a narrow, passing angle when having b as the vertex.
     * When c is the vertex the angle is close to being a line, and the angle should not pass LIC2.
     */
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

    /**
     * Tests that if any endpoint of the angle coincides with the vertex LIC2 should fail.
     * In this test all point coincide.
     */
    @Test
    public void aCoincidesWithBFail() {
        parameters = new Parameters();
        parameters.EPSILON = 1;

        int numPoints = 3;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(0, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic2());
    }

    /**
     * Tests that it does not need to be the first 3 points that form the passing angle.
     * abc form a line, while bcd form a 90 degree angle.
     */
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
