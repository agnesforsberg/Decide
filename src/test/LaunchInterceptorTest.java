package Decide.src.test;

import org.junit.Test;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Parameters;
import Decide.src.main.Point;
import Decide.src.main.Connector;

import static org.junit.Assert.*;

public class LaunchInterceptorTest {
    private Parameters parameters;
    private LaunchInterceptor li;

    /**
     * Tests the main program using only LIC0, LIC1, LIC2
     * The PUV is all true and LCM all ANDD
     * Tests that decide outputs "YES"
     */
    @Test
    public void topLevelLicsTruePUVAllTrueOutputYES() {
        // All parameters must be set and valid for CMV to be computed.
        int length1 = 6;
        int radius1 = 2;
        int epsilon = 1;
        parameters = new Parameters(length1, radius1, epsilon, 1, 2, 2, 1, 3,
                1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0);

        int numPoints = 3;
        Point[] points = { new Point(0, 4), new Point(0, 0), new Point(7, 0)};

        Connector[][] lcm = new Connector[3][3];
        boolean[] puv = new boolean[3];

        for (int i = 0; i < 3; i++){
            puv[i] = true;
            for (int j = 0; j < 3; j++){
                lcm[i][j] = Connector.ANDD;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, lcm, puv, 3);
        assertTrue(li.lic0());
        assertTrue(li.lic1());
        assertTrue(li.lic2());
        assertEquals("YES", li.decide());
    }

    /**
     * Tests that output is YES even if all LICS (0, 1, 2) return false IF PUV is all false.
     */
    @Test
    public void toplevelLicsFalsePUVAllFalseOutputYES() {
        // All parameters must be set and valid for CMV to be computed.
        int length1 = 20;
        int radius1 = 10;
        int epsilon = 4;
        parameters = new Parameters(length1, radius1, epsilon, 1, 2, 2, 1, 3,
                1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0);

        int numPoints = 3;
        Point[] points = { new Point(0, 4), new Point(0, 0), new Point(7, 0)};

        Connector[][] lcm = new Connector[3][3];
        // default false
        boolean[] puv = new boolean[3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                lcm[i][j] = Connector.ANDD;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, lcm, puv, 3);
        assertFalse(li.lic0());
        assertFalse(li.lic1());
        assertFalse(li.lic2());
        assertEquals("YES", li.decide());
    }

    /**
     * Tests that when all Lics (0, 1, 2) output false and PUV is all true output will be "NO".
     */
    @Test
    public void toplevelLicsFalsePUVAllTrueOutputNO() {
        // All parameters must be set and valid for CMV to be computed.
        int length1 = 20;
        int radius1 = 10;
        int epsilon = 4;
        parameters = new Parameters(length1, radius1, epsilon, 1, 2, 2, 1, 3,
                1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0);

        int numPoints = 3;
        Point[] points = { new Point(0, 4), new Point(0, 0), new Point(7, 0)};

        Connector[][] lcm = new Connector[3][3];
        boolean[] puv = new boolean[3];

        for (int i = 0; i < 3; i++){
            puv[i] = true;
            for (int j = 0; j < 3; j++){
                lcm[i][j] = Connector.ANDD;
            }
        }

        li = new LaunchInterceptor(numPoints, points, parameters, lcm, puv, 3);
        assertFalse(li.lic0());
        assertFalse(li.lic1());
        assertFalse(li.lic2());
        assertEquals("NO", li.decide());
    }

}
