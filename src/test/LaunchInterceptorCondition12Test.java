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
     * Lic7 has already been tested, so the requirements that are equal to Lic7 will not be tested here.
     * Those tests can be seen in LaunchInterceptorConditionTest7.java
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

    @Test
    public void notCloseEnoughTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.LENGTH2 = 1;
        parameters.K_PTS = 1;

        int numPoints = 3;

        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic12());
    }

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
