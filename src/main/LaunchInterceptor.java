package Decide.src.main;

public class LaunchInterceptor {

    //The global data
    int numPoints;
    /** planar data points (X,Y)
     *  an input set of up to 100 planar data points representing radar echoes
     */
    Point[] points;

    /** Conditions Met Vector (CMV) 
     * The fifteen elements of a Conditions Met Vector (CMV) will be assigned boolean values true or false;
     * each element of the CMV corresponds to one LIC’s condition.
    */
    boolean[] cmv;
    /**
     * 
     */
    Connector[][] lcm;
    boolean[][] pum;
    boolean[] puv;
    boolean[] fuv;

    public LaunchInterceptor(){

    }

    boolean decide(){
        return true;
    }

    boolean lic0() {return true;} 
    boolean lic1() {return true;} 
    boolean lic2() {return true;} 
    boolean lic3() {return true;} 
    boolean lic4() {return true;} 
    boolean lic5() {return true;} 
    boolean lic6() {return true;} 
    boolean lic7() {return true;} 
    boolean lic8() {return true;} 
    boolean lic9() {return true;} 
    boolean lic10() {return true;} 
    boolean lic11() {return true;} 
    boolean lic12() {return true;} 
    boolean lic13() {return true;} 
    boolean lic14() {return true;} 

    public static void main(String[] args) {
        System.out.println("asd");
    }
}
