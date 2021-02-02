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

    @Test
    public void kptsLargeTrueTest() {
        parameters = new Parameters();
        parameters.LENGTH1 = 5;
        parameters.K_PTS = 100;

        int numPoints = 102;

        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        for (int i = 0; i < numPoints - 1; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        points[numPoints - 1] = new Point(5,0);
        li = new LaunchInterceptor(numPoints, points, parameters, null, null);
        assertTrue(li.lic7());


    }

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
