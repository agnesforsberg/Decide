package Decide.src.test;

public class LaunchInterceptorTest {
    
    /** LIC0 Descriptor
     * There exists at least one set of two consecutive data points
     * that are a distance greater than the length, LENGTH1, apart.
     * 
     * (0 ≤ LENGTH1)
     */
    void lic0Test(){}

    /** LIC1 Descriptor
     * There exists at least one set of three consecutive data points
     *  points that cannot all be contained within or on a circle of radius RADIUS1.
     * 
     * (0 ≤ RADIUS1)
     */
    void lic1Test(){}

    /** LIC2 Descriptor
     * There exists at least one set of three consecutive data points which form an angle such that:
     * angle < (PI−EPSILON) or angle > (PI+EPSILON)
     * The second of the three consecutive points is always the vertex of the angle.
     * If either the first point or the last point (or both) coincides with the vertex, 
     * the angle is undefined and the LIC is not satisfied by those three points.
     * 
     * (0 ≤ EPSILON < PI)
     */
    void lic2Test(){}

    /** LIC3 Descriptor
     * There exists at least one set of three consecutive data points
     * that are the vertices of a triangle with area greater than AREA1.
     * 
     * (0 ≤ AREA1)
     */
    void lic3Test(){}

    /** LIC4 Descripitor
     * There exists at least one set of Q PTS consecutive data points that lie in more than QUADS quadrants.
     * Where there is ambiguity as to which quadrant contains a given point, priority 
     * of decision will be by quadrant number, i.e., I, II, III, IV. 
     * For example, the data point (0,0) is in quadrant I,
     * the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III,
     * the point (0,1) is in quadrant I and the point (1,0) is in quadrant I.
     * 
     * (2 ≤ Q PTS ≤ NUMPOINTS), (1 ≤ QUADS ≤ 3)
     */
    void lic4Test(){}

    /** LIC5 Descriptor
     * There exists at least one set of two consecutive data points,
     *  (X[i],Y[i]) and (X[j],Y[j]), 
     * such that X[j] - X[i] < 0. (where i = j-1)
     */
    void lic5Test(){}

    /** LIC6 Descriptor
     * There exists at least one set of N PTS consecutive data points
     * such that at least one of the points lies a distance greater than
     * DIST from the line joining the first and last of these N PTS points.
     * If the first and last points of these N PTS are identical, then the calculated distance
     * to compare with DIST will be the distance from the coincident point to all other points of
     * the N PTS consecutive points. The condition is not met when NUMPOINTS < 3
     * 
     * (3 ≤ N PTS ≤ NUMPOINTS), (0 ≤ DIST)
     */
    void lic6Test(){}

    /** LIC7 Descriptor
     * There exists at least one set of two data points separated by exactly K PTS
     * consecutive intervening points that are a distance greater than the length, LENGTH1, apart.
     * The condition is not met when NUMPOINTS < 3.
     * 
     * 1 ≤ K PTS ≤ (NUMPOINTS−2)
     */
    void lic7Test(){}

    /** LIC8 Descriptor
     * There exists at least one set of three data points separated by exactly A PTS and B PTS
     * consecutive intervening points, respectively,
     * that cannot be contained within or on a circle of radius RADIUS1.
     * The condition is not met when NUMPOINTS < 5.
     * 
     * 1 ≤ A PTS, 1 ≤ B PTS
     * A PTS+B PTS ≤ (NUMPOINTS−3)
     */
    void lic8Test(){}

    /** LIC9 Descriptor
     * There exists at least one set of three data points separated by exactly C PTS and D PTS
     * consecutive intervening points, respectively, that form an angle such that:
     * 
     * angle < (PI−EPSILON) or angle > (PI+EPSILON)
     * 
     * The second point of the set of three points is always the vertex of the angle.
     * If either the first point or the last point (or both) coincide with the vertex,
     * the angle is undefined and the LIC is not satisfied by those three points.
     * When NUMPOINTS < 5, the condition is not met.
     * 
     * 1 ≤ C PTS, 1 ≤ D PTS
     * C PTS+D PTS ≤ NUMPOINTS−3
     */
    void lic9Test(){}

    /** LIC10 Descriptor
     * There exists at least one set of three data points separated by exactly
     * E PTS and F PTS consecutive intervening points, respectively,
     * that are the vertices of a triangle with area greater than AREA1. 
     * The condition is not met when NUMPOINTS < 5.
     * 
     * 1 ≤ E PTS, 1 ≤ F PTS
     * E PTS+F PTS ≤ NUMPOINTS−3
     */
    void lic10Test(){}

    /** LIC11 Descriptor
     * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), 
     * separated by exactly G PTS consecutive intervening points, 
     * such that X[j] - X[i] < 0. (where i < j ) 
     * The condition is not met when NUMPOINTS < 3.
     * 
     * 1 ≤ G PTS ≤ NUMPOINTS−2
     */
    void lic11Test(){}

    /** LIC12 Descriptor
     * There exists at least one set of two data points, separated by exactly K PTS consecutive
     * intervening points, which are a distance greater than the length, LENGTH1, apart.
     * 
     * In addition, there exists at least one set of two data points
     * (which can be the same or different fromthe two data points just mentioned),
     * separated by exactly K PTS consecutive intervening points, 
     * that are a distance less than the length, LENGTH2, apart.
     * 
     * Both parts must be true for the LIC to be true.
     * The condition is not met when NUMPOINTS < 3.
     * 
     * 0 ≤ LENGTH2
     */
    void lic12Test(){}

    /** LIC13 Descriptor
     * There exists at least one set of three data points, separated by exactly
     * A PTS and B PTS consecutive intervening points, respectively, 
     * that cannot be contained within or on a circle of radius RADIUS1.
     * 
     * In addition, there exists at least one set of three data points 
     * (which can be the same or different from the three data points just mentioned)
     * separated by exactly A PTS and B PTS consecutive intervening points, respectively,
     *  that can be contained in or on a circle of radius RADIUS2. 
     * 
     * Both parts must be true for the LIC to be true. 
     * The condition is not met when NUMPOINTS < 5.
     * 
     * 0 ≤ RADIUS2
     */
    void lic13Test(){}

    /** LIC14 Descriptor
     * There exists at least one set of three data points, separated by exactly 
     * E PTS and F PTS consecutive intervening points, respectively,
     * that are the vertices of a triangle with area greater than AREA1.
     * 
     * In addition, there exist three data points 
     * (which can be the same or different from the three data points just mentioned)
     * separated by exactly E PTS and F PTS consecutive intervening points, respectively,
     * that are the vertices of a triangle with area less than AREA2.
     * 
     * Both parts must be true for the LIC to be true. 
     * The condition is not met when NUMPOINTS < 5.
     * 
     * 0 ≤ AREA2
     */
    void lic14Test(){}
}
