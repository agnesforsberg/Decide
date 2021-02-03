package Decide.src.test;

import org.junit.Test;

import java.util.Random;

import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LaunchInterceptorCondition9Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void noPointsTest() {
        //LIC9 should fail if there are no points
        parameters = new Parameters();

        int numPoints = 0;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC9 must be false if there are no points.", li.lic9());
    }

    @Test
    public void trivialFailingTest() {
        //LIC9 should fail when there is an angle (PI/2) between 3 points and epsilon is greater than (PI/2)
        parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = Math.PI*0.51;
        int numPoints = 5;

        Point[] points = {  new Point(0,1), 
                            new Point(0,0),
                            new Point(0,0),
                            new Point(0,0),
                            new Point(1,0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertFalse("LIC9 shuld be true when the angle PI/2 is used with an epsilon greater than pi/2", li.lic9());
    }

    @Test
    public void trivialPassingTest() {
        //LIC9 should pass when there is an angle (PI/2) between 3 points and epsilon is less than (PI/2)
        parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = Math.PI*0.33;
        int numPoints = 5;

        Point[] points = {  new Point(0,1), 
                            new Point(0,0),
                            new Point(0,0),
                            new Point(0,0),
                            new Point(1,0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertTrue("LIC9 shuld be true when the angle PI/2 is used with an epsilon less than pi/2", li.lic9());
    }

    @Test
    public void tooFewPointsTest() {
        //LIC9 should fail when there are less than 5 points
        parameters = new Parameters();
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.EPSILON = Math.PI*0.33;
        int numPoints = 4;

        Point[] points = {  new Point(0,1), 
                            new Point(0,0),
                            new Point(0,0),
                            new Point(1,0)};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertFalse(li.lic9());
    }
}
