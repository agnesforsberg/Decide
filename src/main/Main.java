package Decide.src.main;

public class Main {
    public static void main(String[] args) {

        int numpoints = 100;
        Point[] points = new Point[numpoints];
        // Add data to points...

        Parameters parameters = new Parameters(2.0, 2.0, 2.0, 2.0, 1, 1, 1.0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1.0, 1.0, 1.0);

        /**
         * For testing purposes not all LICS must be part of the result.
         * For all LICS: numtests = 15, PUV.length = 15, LCM = [15][15]
         * For all non-tests, a constructor without the numTests parameter exists.
         */

        Connector[][] LCM = new Connector[15][15];
        // Add data to LCM

        boolean[] PUV = new boolean[15];
        // Add data to PUV

        int numTests = 15;

        LaunchInterceptor launch = new LaunchInterceptor(numpoints, points, parameters, LCM, PUV);
        System.out.print(launch.decide());

    }
}
