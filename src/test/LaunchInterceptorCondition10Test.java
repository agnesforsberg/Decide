package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import java.util.Random;

public class LaunchInterceptorCondition10Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;

        int numPoints = 2;

        Point[] points = {new Point(0, 0), new Point(1, 1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC10 must be false if there are less than three points.", li.lic10());
    }

    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC10 must be false if there are no points.", li.lic10());
    }

    @Test
    public void lowEPTSPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;
        parameters.E_PTS = 0;
        parameters.F_PTS = 1;

        int numPoints = 5;

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic10());
    }

    @Test
    public void lowFPTSPointsTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;
        parameters.E_PTS = 1;
        parameters.F_PTS = 0;

        int numPoints = 5;

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic10());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 5;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;

        int numPoints = 5;

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(1, 0), new Point(3, 3), new Point(0, 1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic10());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.AREA1 = 2;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;

        int numPoints = 5;

        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(5, 0), new Point(3, 3), new Point(0, 5)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic10());
    }
}
