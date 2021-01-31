package Decide.src.main;

import java.lang.Math;
import java.util.stream.DoubleStream;

public class LaunchInterceptor {

    // The global input

    /**
     * The number of points present in points.
     */
    int numPoints;

    /**
     * Planar data points (X,Y): an input set of up to numPoints planar data points
     * representing radar echoes
     */
    Point[] points;

    /**
     * Contains input parameters necessary for calculating the LICs.
     */
    Parameters parameters;

    /**
     * Logical Connector Matrix (LCM): The LCM describes how individual LICs should
     * be logically combined.
     */
    Connector[][] lcm;

    /**
     * Preliminary Unlocking Vectore (PUV): The input PUV indicates whether the
     * corresponding LIC is to be considered as a factor in signaling interceptor
     * launch. If PUV[i] is set to false, then the i:th LIC should NOT hold back
     * Launch.
     */
    boolean[] puv;

    // The intermediate global output.

    /**
     * Conditions Met Vector (CMV): The fifteen elements of a Conditions Met Vector
     * (CMV) will be assigned boolean values true or false; each element of the CMV
     * corresponds to one LIC’s condition.
     */
    boolean[] cmv;

    /**
     * Preliminary Unlocking Matrix (PUM): The values of the CMV joined index-wise
     * through the connectors available in the LCM.
     */
    boolean[][] pum;

    /**
     * Final Unlocking Vector (FUM): A positive launch decision can only be made if
     * all elements of this array are true. An index i is only set to true if PUV[i]
     * is false, or if PUV[i] is true and all elements in PUM[i, j] is true for all
     * j != i, 0 <= j <= 14.
     */
    boolean[] fuv;

    public LaunchInterceptor(int numPoints, Point[] points, Parameters parameters, Connector[][] lcm, boolean[] puv) {
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

    /**
     * The funal launch decision as defined in the project specification.
     * 
     * @return the signal controlling whether an interceptor should be launched
     *         based upon input radar tracking information.
     */
    public boolean decide() {
        return true;
    }

    public boolean lic0() {
        for (int i = 1; i < numPoints; i++) {
            if (points[i - 1].distanceTo(points[i]) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there exists at least one set of three consecutive data
     * points that cannot all be contained within or on a circle of radius RADIUS1.
     * (0 ≤ RADIUS1)
     * 
     * @return whether the LIC1 condition holds or not.
     */
    public boolean lic1() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - 2; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            Point c = points[i + 2];

            double oRadius;

            /*
             * if the points make an obtuse triangle, then the longest distance between any
             * two points make the diameter of the smallest encompassing circle.
             */
            if (a.angleBetween(b, c) > Math.PI / 2 || b.angleBetween(a, c) > Math.PI / 2
                    || c.angleBetween(a, b) > Math.PI / 2) {
                oRadius = DoubleStream.of(a.distanceTo(b), b.distanceTo(c), c.distanceTo(a)).max().getAsDouble() / 2;
            } else {
                /*
                 * otherwise, we'll have to use the law of sines.
                 * 
                 * @see https://en.wikipedia.org/wiki/Law_of_sines
                 */
                double angle = a.angleBetween(b, c);
                double len = b.distanceTo(c);
                oRadius = (len / Math.sin(angle)) / 2;
            }

            /*
             * If the radius of the smallest possible encompassing circle is larger than the
             * set RADIUS1, then we've found three consecutive points which can not be
             * contained within a circle of radius RADIUS1.
             */
            if (oRadius > parameters.RADIUS1)
                return true;
        }

        return false;
    }

    boolean lic2() {
        return true;
    }

    boolean lic3() {
        return true;
    }

    boolean lic4() {
        return true;
    }

    boolean lic5() {
        return true;
    }

    /**
     * There exists at least one set of N PTS consecutive data points such that at
     * least one of the points lies a distance greater than DIST from the line
     * joining the first and last of these N PTS points. If the first and last
     * points of these N PTS are identical, then the calculated distance to compare
     * with DIST will be the distance from the coincident point to all other points
     * of the N PTS consecutive points. The condition is not met when NUMPOINTS < 3.
     * (3 ≤ N PTS ≤ NUMPOINTS), (0 ≤ DIST)
     * 
     * @return whether the LIC6 condition holds or not.
     */
    public boolean lic6() {
        if (numPoints < 3)
            return false;

        Point start, end, intermediate;

        for (int starti = 0; starti < numPoints - parameters.N_PTS + 1; starti++) {
            int endi = starti + parameters.N_PTS - 1;

            start = points[starti];
            end = points[endi];

            boolean coincidentPoint = false;

            /*
             * If start and end coincide they make a coincident point.
             */
            if (start.equals(end))
                coincidentPoint = true;

            for (int intermediatei = starti + 1; intermediatei < endi; intermediatei++) {
                intermediate = points[intermediatei];
                double distance;

                /*
                 * If the endpoints make a coincident point, distance is to be calculated to
                 * that point. Otherwise, it is to be calculated to the line through those two
                 * points.
                 */
                if (coincidentPoint)
                    distance = intermediate.distanceTo(start);
                else
                    distance = intermediate.distanceToLineThrough(start, end);

                if (distance > parameters.DIST)
                    return true;
            }

        }

        return false;
    }

    boolean lic7() {
        return true;
    }

    boolean lic8() {
        return true;
    }

    boolean lic9() {
        return true;
    }

    boolean lic10() {
        return true;
    }

    /**
     * There exists at least one set of two data points, (X[i],Y[i]) and
     * (X[j],Y[j]), separated by exactly G_PTS consecutive intervening points, such
     * that X[j] - X[i] < 0. (where i < j ) The condition is not met when NUMPOINTS
     * < 3. 1 ≤ G PTS ≤ NUMPOINTS−2
     * 
     * @return whether the LIC11 condition holds or not.
     */
    public boolean lic11() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - parameters.G_PTS - 1; i++) {
            int j = i + parameters.G_PTS + 1;

            if (points[j].x - points[i].x < 0)
                return true;
        }

        return false;
    }

    boolean lic12() {
        return true;
    }

    boolean lic13() {
        return true;
    }

    boolean lic14() {
        return true;
    }

}
