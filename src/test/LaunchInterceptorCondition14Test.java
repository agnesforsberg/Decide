package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition14Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        parameters = new Parameters();

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC14 must be false if there are no points.", li.lic14());
    }

    @Test
    public void fewPointsTest() {
        parameters = new Parameters();
        parameters.E_PTS = 0;
        parameters.F_PTS = 0;
        parameters.AREA1 = 0.5;
        parameters.AREA2 = 2;
        int numPoints = 4;

        Point[] points = {  new Point(0,1), 
                            new Point(1,0), 
                            new Point(-1,0), 
                            new Point(1,1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("", li.lic14());
    }


    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.E_PTS = 0;
        parameters.F_PTS = 0;
        parameters.AREA1 = 1.2;
        parameters.AREA2 = 2;
        int numPoints = 5;

        Point[] points = {  new Point(0,1), 
                            new Point(1,0), 
                            new Point(-1,0), 
                            new Point(1,1),
                            new Point(1,1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("", li.lic14());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.E_PTS = 0;
        parameters.F_PTS = 0;
        parameters.AREA1 = 0.5;
        parameters.AREA2 = 2;
        int numPoints = 5;

        Point[] points = {  new Point(0,1), 
                            new Point(1,0), 
                            new Point(-1,0), 
                            new Point(1,1),
                            new Point(1,1)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue("", li.lic14());
    }
}