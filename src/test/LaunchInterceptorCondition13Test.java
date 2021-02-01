package Decide.src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import Decide.src.main.Point;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import java.util.Random;

public class LaunchInterceptorCondition13Test {
    private Parameters parameters;
    private LaunchInterceptor li;

    private Random rand = new Random();

    @Test
    public void notEnoughPointsTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;
        parameters.RADIUS2 = 5;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int numPoints = 4;

        Point[] points = { new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC13 must be false if there are less than five points.", li.lic13());
    }

    @Test
    public void noPointsTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;
        parameters.RADIUS2 = 5;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int numPoints = 4;

        Point[] points = {};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse("LIC13 must be false if there are no points.", li.lic13());
    }

    @Test
    public void trivialFailingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;
        parameters.RADIUS2 = 5;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int numPoints = 5;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(0, 10), new Point(0, 0), new Point(0, -10) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic13());
    }

    @Test
    public void trivialPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 1;
        parameters.RADIUS2 = 15;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        

        int numPoints = 5;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(0, 10), new Point(0, 0), new Point(0, -10),};

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void pointsOnEdgeTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 4;
        parameters.RADIUS2 = 3.5;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int numPoints = 5;

        Point[] points = { new Point(0, 0), new Point(0, 0), new Point(3.5, 0), new Point(0, 0), new Point(0, 3.5) };

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void manyPointsFailingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 5;
        parameters.RADIUS2 = 1E-10;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int numPoints = 100;

        Point[] points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double x = rand.nextDouble() * 0.8 - 0.4;
            double y = rand.nextDouble() * 0.8 - 0.4;
            points[i] = new Point(x, y);
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic13());
    }

    @Test
    public void manyPointsPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;
        parameters.RADIUS2 = 50;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void manyPointsLargeAPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;
        parameters.RADIUS2 = 50;
        parameters.A_PTS = 50;
        parameters.B_PTS = 1;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void manyPointsLargeBPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;
        parameters.RADIUS2 = 50;
        parameters.A_PTS = 1;
        parameters.B_PTS = 50;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void manyPointsLargeAAndBPassingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;
        parameters.RADIUS2 = 50;
        parameters.A_PTS = 48;
        parameters.B_PTS = 49;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertTrue(li.lic13());
    }

    @Test
    public void manyPointsTooLargeAAndBFailingTest() {
        parameters = new Parameters();
        parameters.RADIUS1 = 0.5;
        parameters.RADIUS1 = 50;
        parameters.A_PTS = 50;
        parameters.B_PTS = 50;

        int gridLength = 10;
        int numPoints = gridLength * gridLength;

        Point[] points = new Point[numPoints];

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                Point p = new Point(i, j);
                points[i * gridLength + j] = p;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, null, null);

        assertFalse(li.lic13());
    }
}
