package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition4Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        parameters = new Parameters();

        parameters.Q_PTS = 2;
        parameters.QUADS = 2;
        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC4 must be false if there are no points.", li.lic4());
    }

    @Test
    public void trivialFailingTest(){
        parameters = new Parameters();

        parameters.Q_PTS = 2;
        parameters.QUADS = 2;
        int numPoints = 2;

        Point[] points = {new Point(1, 1),new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC4 must be false if QUADS=2 and the points are only in quadrant I.", li.lic4());
    }

    @Test
    public void tooManyQuadsTest(){
        parameters = new Parameters();

        parameters.Q_PTS = 2;
        parameters.QUADS = 4;
        int numPoints = 2;

        Point[] points = {new Point(1, 1),new Point(1, 0) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC4 must be false if QUADS=4 since there cant be more quadrants covered than 4", li.lic4());
    }

    @Test
    public void trivialPassingTest(){
        parameters = new Parameters();

        parameters.Q_PTS = 2;
        parameters.QUADS = 1;
        int numPoints = 2;

        Point[] points = {new Point(1, 1), new Point(-1, -1) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue("LIC4 must true since there are a set of consecutive points that cover quadrant I and III", li.lic4());
    }


}
