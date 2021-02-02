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
     * based upon input radar tracking information.
     */
    public boolean decide() {
        return true;
    }

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


    /**
     * There exists at least one set of three consecutive data points that are the vertices of a triangle
     * with area greater than AREA1.
     * (0 ≤ AREA1)
     * 
     * @return whether the LIC3 condition holds or not.
     */
    public boolean lic3() {
        if (numPoints < 3)
            return false;
            
        for (int i = 0; i < numPoints - 2; i++) {
            Point a = points[i];
            Point b = points[i+1];
            Point c = points[i+2];
            /*
            * @see https://en.wikipedia.org/wiki/Triangle#Using_coordinates
            * Factoring on X
            */
            double area = Math.abs( (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);
            if(area > parameters.AREA1){
                return true;
            }
        }
        return false;


    }

    public boolean lic4() {

        //Ensure basic conditions are met
        //(2 ≤ Q PTS ≤ NUMPOINTS)
        if(2 > parameters.Q_PTS || numPoints < parameters.Q_PTS){ return false; }

        //(1 ≤ QUADS ≤ 3)
        if(4 < parameters.QUADS  || parameters.QUADS < 1){ return false;}

        
        //Iterate over all sets of censecutive set of Q_pts points. Each set is referred as the lowerbound index of the set.
        for(int i=0; i < numPoints-parameters.Q_PTS+1; i++){
            
            boolean[] usedQuads = new boolean[4];
            //Iterate over current point and the next Q_pts in the consecutive set of points
            for(int j=i; j < i+parameters.Q_PTS; j++){
                //Where there is ambiguity as to which quadrant contains a given point, 
                //priority of decision will be by quadrant number, i.e., I, II, III, I

                //Quadrant I
                if(points[j].x >= 0 && points[j].y >= 0){
                    usedQuads[0] = true;
                } 
                //Quadrant II
                else if(points[j].x < 0 && points[j].y >= 0){
                    usedQuads[1] = true;
                }
                //Quadrant III
                else if(points[j].x < 0 && points[j].y <= 0){
                    usedQuads[2] = true;
                }
                //Quadrant IV
                else if(points[j].x > 0 && points[j].y < 0){
                    usedQuads[3] = true;
                }
            }

            //Loop over the used quadrant flags
            int quadCounter = 0;
            for(boolean b : usedQuads){
                if(b) quadCounter++; 
            }

            if(quadCounter > parameters.QUADS) return true; 
        }

        return false;
    }

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
     * There exists at least one set of two data points separated by exactly K PTS consecutive
     * intervening points that are a distance greater than the length, LENGTH1, apart. The condition
     * is not met when NUMPOINTS < 3
     * @return whether the LIC7 condition holds or not.
     */
    public boolean lic7() {
        if (numPoints < 3)
            return false;

        for (int i = 0; i < numPoints - parameters.K_PTS - 1; i++) {
            Point a = points[i];
            Point b = points[i + parameters.K_PTS + 1];

            if (a.distanceTo(b) > parameters.LENGTH1 )
                return true;
        }

        return false;
    }


    /**
     * There exists at least one set of three data points separated by exactly A PTS and B PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
     * 1 ≤ A PTS, 1 ≤ B PTS
     * A PTS+B PTS ≤ (NUMPOINTS−3)
     * @return whether the LIC8 condition holds or not.
     */
    public boolean lic8() {
        if (numPoints < 5)
            return false;

        for (int i = 0; i < numPoints - parameters.A_PTS - parameters.B_PTS - 2; i++) {
            Point a = points[i];
            Point b = points[i + parameters.A_PTS + 1];
            Point c = points[i + parameters.A_PTS + 1 + parameters.B_PTS + 1];

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

    /**
     * There exists at least one set of three data points, separated by exactly A PTS
     * and B PTS consecutive intervening points, respectively, that cannot be contained
     * within or on a circle of radius RADIUS1. In addition, there exists at least one
     * set of three data points (which can be the same or different from the three data
     * points just mentioned) separated by exactly A PTS and B PTS consecutive intervening
     * points, respectively, that can be contained in or on a circle of radius RADIUS2.
     * Both parts must be true for the LIC to be true. 
     * The condition is not met when NUMPOINTS < 5.
     * 0 ≤ RADIUS2
     * A PTS+B PTS ≤ (NUMPOINTS−3)
     * 
     * @return whether the LIC13 condition holds or not.
     */
    public boolean lic13() {
        if (numPoints < 5)
            return false;

        /**
         * The first condition that checks if there are 3 points separated by A_PTS and B_PTS
         * that can not be contained in a circle with radius RADIUS1 is the same as LIC8
         * so if LIC8 is false then LIC13 will also be false.
         */
        if(!lic8()){
            return false;
        }

        for (int i = 0; i < numPoints - parameters.A_PTS - parameters.B_PTS - 2; i++) {
            Point a = points[i];
            Point b = points[i + parameters.A_PTS + 1];
            Point c = points[i + parameters.A_PTS + 1 + parameters.B_PTS + 1];

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
                System.out.println(oRadius);
            }

            /*
            * If the radius of the smallest possible encompassing circle is smaller or equal to
            * the set RADIUS2, then we've found three consecutive points which can be
            * contained within a circle of radius RADIUS2.
            */
            if (oRadius <= parameters.RADIUS2)
                return true;
        }
        return false;
    }

    boolean lic14() {
        return true;
    }

}
