package Decide.src.main;

import java.lang.Math;
import Decide.src.main.Connector;

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
    public boolean[] cmv;

    /**
     * Preliminary Unlocking Matrix (PUM): The values of the CMV joined index-wise
     * through the connectors available in the LCM.
     */
    public boolean[][] pum;

    /**
     * Final Unlocking Vector (FUM): A positive launch decision can only be made if
     * all elements of this array are true. An index i is only set to true if PUV[i]
     * is false, or if PUV[i] is true and all elements in PUM[i, j] is true for all
     * j != i, 0 <= j <= 14.
     */
    public boolean[] fuv;

    public LaunchInterceptor(int numPoints, Point[] points, Parameters parameters, Connector[][] lcm, boolean[] puv) {
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
        // calculateCMV only works with all tests, but the rest will be disregarded later if numTests < 15
        cmv = new boolean[15];
        pum = new boolean[15][15];
        fuv = new boolean[15];
    }

    public LaunchInterceptor(int numPoints, Point[] points, Parameters parameters, Connector[][] lcm, boolean[] puv, int numTests) {
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
        // calculateCMV only works with all tests, but the rest will be disregarded later if numTests < 15
        cmv = new boolean[15];
        pum = new boolean[numTests][numTests];
        fuv = new boolean[numTests];
    }

    /**
     * The funal launch decision as defined in the project specification.
     *
     * @return the signal controlling whether an interceptor should be launched
     *         based upon input radar tracking information.
     */
    public String decide() {
        calculateCMV();
        calculatePUM();
        calculateFUV();
        boolean launch = calculateLaunch();
        return launch ? "YES" : "NO";
    }

    public void calculateCMV() {
        cmv[0] = lic0();
        cmv[1] = lic1();
        cmv[2] = lic2();
        cmv[3] = lic3();
        cmv[4] = lic4();
        cmv[5] = lic5();
        cmv[6] = lic6();
        cmv[7] = lic7();
        cmv[8] = lic8();
        cmv[9] = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();
    }

    public void calculatePUM() {
        for (int i = 0; i < pum.length; i++) {
            for (int j = 0; j < pum[i].length; j++) {
                /*
                 * The diagonal is ignored according to specification.
                 */
                if (i == j) {
                    continue;
                }
                /*
                 * Since the matrix is symmetric, we can skip the lower half triangle.
                 */
                if (j < i) {
                    pum[i][j] = pum[j][i];
                    continue;
                }

                boolean pumij = false;

                switch (lcm[i][j]) {
                    case ANDD: {
                        pumij = cmv[i] && cmv[j];
                        break;
                    }
                    case ORR: {
                        pumij = cmv[i] || cmv[j];
                        break;
                    }
                    case NOTUSED: {
                        pumij = true;
                        break;
                    }
                    default:
                        break;
                }

                pum[i][j] = pumij;
            }
        }
    }

    public void calculateFUV() {
        for (int i = 0; i < fuv.length; i++) {
            fuv[i] = true;
            if (puv[i]) {
                for (int j = 0; j < pum[i].length; j++) {
                    /*
                     * PUM diagonal is not to be considered as defined in the specification.
                     * 
                     * @see 2.3.3
                     */
                    if (i == j)
                        continue;

                    if (!pum[i][j])
                        fuv[i] = false;
                }
            }
        }
    }

    public boolean calculateLaunch() {
        for (int i = 0; i < fuv.length; i++) {
            if (!fuv[i])
                return false;
        }
        return true;
    }

    /**
     * There exists at least one set of two consecutive data points
     * that are a distance greater than the length, LENGTH1, apart.
     */
    public boolean lic0() {
        if (numPoints < 2) {
            return false;
        }
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

            double oRadius = Point.smallestCircle(a, b, c);

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

    /**
     * There exists at least one set of three consecutive data points which form an angle such that:
     * angle < (PI−EPSILON)
     * or
     * angle > (PI+EPSILON)
     * The second of the three consecutive points is always the vertex of the angle. If either the first
     * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
     * is not satisfied by those three points.
     * @return whether the LIC2 condition holds or not.
     */
    public boolean lic2() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - 2; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            Point c = points[i + 2];

            // If either endpoint coincides with the vertex return false
            if (a.equals(b) || c.equals(b))
                return false;

            // abcAngle is the same as cbaAngle
            // b is always the vertex of the angle
            double abcAngle = b.angleBetween(a, c);

            double piMinusEp = Math.PI - parameters.EPSILON;
            double piPlusEp = Math.PI + parameters.EPSILON;

            if (abcAngle < piMinusEp || abcAngle > piPlusEp) {
                return true;
            }

        }

        return false;
    }

    /**
     * There exists at least one set of three consecutive data points that are the
     * vertices of a triangle with area greater than AREA1. (0 ≤ AREA1)
     * 
     * @return whether the LIC3 condition holds or not.
     */
    public boolean lic3() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - 2; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            Point c = points[i + 2];
            /*
             * @see https://en.wikipedia.org/wiki/Triangle#Using_coordinates Factoring on X
             */
            double area = Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);
            if (area > parameters.AREA1) {
                return true;
            }
        }
        return false;

    }

    public boolean lic4() {

        // Ensure basic conditions are met
        // (2 ≤ Q PTS ≤ NUMPOINTS)
        if (2 > parameters.Q_PTS || numPoints < parameters.Q_PTS) {
            return false;
        }

        // (1 ≤ QUADS ≤ 3)
        if (4 < parameters.QUADS || parameters.QUADS < 1) {
            return false;
        }

        // Iterate over all sets of censecutive set of Q_pts points. Each set is
        // referred as the lowerbound index of the set.
        for (int i = 0; i < numPoints - parameters.Q_PTS + 1; i++) {

            boolean[] usedQuads = new boolean[4];
            // Iterate over current point and the next Q_pts in the consecutive set of
            // points
            for (int j = i; j < i + parameters.Q_PTS; j++) {
                // Where there is ambiguity as to which quadrant contains a given point,
                // priority of decision will be by quadrant number, i.e., I, II, III, I

                // Quadrant I
                if (points[j].x >= 0 && points[j].y >= 0) {
                    usedQuads[0] = true;
                }
                // Quadrant II
                else if (points[j].x < 0 && points[j].y >= 0) {
                    usedQuads[1] = true;
                }
                // Quadrant III
                else if (points[j].x < 0 && points[j].y <= 0) {
                    usedQuads[2] = true;
                }
                // Quadrant IV
                else if (points[j].x > 0 && points[j].y < 0) {
                    usedQuads[3] = true;
                }
            }

            // Loop over the used quadrant flags
            int quadCounter = 0;
            for (boolean b : usedQuads) {
                if (b)
                    quadCounter++;
            }

            if (quadCounter > parameters.QUADS)
                return true;
        }

        return false;
    }

    /**
     * There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]),
     * such that X[j] - X[i] < 0. (where i = j - 1)
     */
    public boolean lic5() {
        if (numPoints < 2) {
            return false;
        }
        for (int i = 1; i < numPoints; i++) {
            if (points[i].x - points[i - 1].x < 0) {
                return true;
            }
        }
        return false;
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

    /**
     * There exists at least one set of two data points separated by exactly K PTS
     * consecutive intervening points that are a distance greater than the length,
     * LENGTH1, apart. The condition is not met when NUMPOINTS < 3
     * 
     * @return whether the LIC7 condition holds or not.
     */
    public boolean lic7() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - parameters.K_PTS - 1; i++) {
            Point a = points[i];
            Point b = points[i + parameters.K_PTS + 1];

            if (a.distanceTo(b) > parameters.LENGTH1)
                return true;
        }

        return false;
    }

    /**
     * There exists at least one set of three data points separated by exactly A PTS
     * and B PTS consecutive intervening points, respectively, that cannot be
     * contained within or on a circle of radius RADIUS1. The condition is not met
     * when NUMPOINTS < 5. 1 ≤ A PTS, 1 ≤ B PTS A PTS+B PTS ≤ (NUMPOINTS−3)
     * 
     * @return whether the LIC8 condition holds or not.
     */
    public boolean lic8() {
        if (numPoints < 5)
            return false;

        for (int i = 0; i < numPoints - parameters.A_PTS - parameters.B_PTS - 2; i++) {
            Point a = points[i];
            Point b = points[i + parameters.A_PTS + 1];
            Point c = points[i + parameters.A_PTS + 1 + parameters.B_PTS + 1];

            double oRadius = Point.smallestCircle(a, b, c);

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

    public boolean lic9() {
        // Ensure basic conditions are met
        // 1 ≤ C PTS, 1 ≤ D PTS
        if (1 > parameters.C_PTS || 1 > parameters.D_PTS) {
            return false;
        }

        // C PTS+D PTS ≤ NUMPOINTS−3
        if (numPoints - 3 < parameters.C_PTS + parameters.D_PTS) {
            return false;
        }

        //5 ≤ NUMPOINTS
        if (numPoints < 5) {
            return false;
        }

        // Iterate over all set of thre points seperated by C_PTS and D_PTS. +2 to
        // include the 2nd and 3rd point in the limit
        for (int i = 0; i < numPoints - (parameters.C_PTS + parameters.D_PTS + 2); i++) {

            Point a = points[i];
            Point b = points[i + parameters.C_PTS + 1];
            Point c = points[i + parameters.C_PTS + parameters.D_PTS + 2];

            // angle < (PI−EPSILON) or angle > (PI+EPSILON)
            if (b.angleBetween(a, c) < (Math.PI - parameters.EPSILON)
                    || b.angleBetween(a, c) > (Math.PI + parameters.EPSILON)) {
                return true;
            }
        }
        return false;
    }

    /**
     * There exists at least one set of three data points separated by exactly E_PTS and F_PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1. The condition is not met when NUMPOINTS < 5.
     * 1 < E PTS, 1 < F PTS
     * E_PTS+F_PTS <= NUMPOINTS - 3
     */
    public boolean lic10() {
        if (numPoints < 5) {
            return false;
        }

        if (parameters.E_PTS < 1 || parameters.F_PTS < 1) {
            return false;
        }

        if (parameters.E_PTS + parameters.F_PTS <= numPoints - 3) {
            for (int i = 0; i + parameters.E_PTS + parameters.F_PTS + 2 < numPoints; i++) {
                Point a = points[i];
                Point b = points[i + 1 + parameters.E_PTS];
                Point c = points[i + 2 + parameters.E_PTS + parameters.F_PTS];
                double area = Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);
                if (area > parameters.AREA1) {
                    return true;
                }
            }
        }

        return false;
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

    /**
     * There exists at least one set of two data points, separated by exactly K PTS
     * consecutive intervening points, which are a distance greater than the length,
     * LENGTH1, apart. In addition, there exists at least one set of two data points
     * (which can be the same or different from the two data points just mentioned),
     * separated by exactly K PTS consecutive intervening points, that are a
     * distance less than the length, LENGTH2, apart. Both parts must be true for
     * the LIC to be true. The condition is not met when NUMPOINTS < 3.
     * 
     * @return whether the LIC12 condition holds or not.
     */
    public boolean lic12() {
        if (!lic7())
            return false;

        for (int i = 0; i < numPoints - parameters.K_PTS - 1; i++) {
            Point a = points[i];
            Point b = points[i + parameters.K_PTS + 1];

            if (a.distanceTo(b) < parameters.LENGTH2)
                return true;
        }
        return false;
    }

    /**
     * There exists at least one set of three data points, separated by exactly A
     * PTS and B PTS consecutive intervening points, respectively, that cannot be
     * contained within or on a circle of radius RADIUS1. In addition, there exists
     * at least one set of three data points (which can be the same or different
     * from the three data points just mentioned) separated by exactly A PTS and B
     * PTS consecutive intervening points, respectively, that can be contained in or
     * on a circle of radius RADIUS2. Both parts must be true for the LIC to be
     * true. The condition is not met when NUMPOINTS < 5. 0 ≤ RADIUS2 A PTS+B PTS ≤
     * (NUMPOINTS−3)
     * 
     * @return whether the LIC13 condition holds or not.
     */
    public boolean lic13() {
        if (numPoints < 5)
            return false;

        /**
         * The first condition that checks if there are 3 points separated by A_PTS and
         * B_PTS that can not be contained in a circle with radius RADIUS1 is the same
         * as LIC8 so if LIC8 is false then LIC13 will also be false.
         */
        if (!lic8()) {
            return false;
        }

        for (int i = 0; i < numPoints - parameters.A_PTS - parameters.B_PTS - 2; i++) {
            Point a = points[i];
            Point b = points[i + parameters.A_PTS + 1];
            Point c = points[i + parameters.A_PTS + 1 + parameters.B_PTS + 1];

            double oRadius = Point.smallestCircle(a, b, c);

            /*
             * If the radius of the smallest possible encompassing circle is smaller or
             * equal to the set RADIUS2, then we've found three consecutive points which can
             * be contained within a circle of radius RADIUS2.
             */
            if (oRadius <= parameters.RADIUS2)
                return true;
        }
        return false;
    }

    public boolean lic14() {
        // Ensure basic conditions are met
        // 0 ≤ AREA2
        if (0 > parameters.AREA2) {
            return false;
        }
        // 5 ≤ NUMPOINTS
        if (5 > numPoints) {
            return false;
        }

        boolean condition1 = false;
        boolean condition2 = false;

        // Iterate over all set of three points seperated by E_PTS and F_PTS. +2 to
        // include the 2nd and 3rd point in the limit
        for (int i = 0; i < parameters.E_PTS + parameters.F_PTS + 2; i++) {
            Point a = points[i];
            Point b = points[i + parameters.E_PTS + 1];
            Point c = points[i + parameters.F_PTS + 2];
            /*
             * @see https://en.wikipedia.org/wiki/Triangle#Using_coordinates Factoring on X
             */
            double area = Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);

            if (area > parameters.AREA1) {
                condition1 = true;
            }
            if (area < parameters.AREA2) {
                condition2 = true;
            }
        }
        if (condition1 && condition2) {
            return true;
        }

        return false;
    }

}
