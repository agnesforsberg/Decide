package Decide.src.main;

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

    boolean lic0() {
        return true;
    }

    boolean lic1() {
        return true;
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

    boolean lic6() {
        return true;
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

    boolean lic11() {
        return true;
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
