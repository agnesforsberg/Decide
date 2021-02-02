package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class launchInterceptorCondition9Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        parameters = new Parameters();

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC9 must be false if there are no points.", li.lic9());
    }

    @Test
    public void trivialFailingTest() {

    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.C_PTS = 0;
        parameters.C_PTS = 0;
        parameters.EPSILON = Math.PI*0.66;
        int numPoints = 5;

        Point[] points = {new Point(0,1), new Point(0,0),
            new Point(1,0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertTrue(" "+points[1].angleBetween(points[0], points[2]), li.lic9());
    }

    @Test
    public void pointsOnEdgeTest() {

    }
}
