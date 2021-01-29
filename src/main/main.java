package Decide.src.main;

public class main {
    public static void main(String[] args) {
        

        int numpoints = 100;
        Point[] points = new Point[numpoints];
        Parameters parameters = new Parameters(2.0,2.0,2.0,2.0,1,1,1.0,1,1,1,1,1,1,1,1,1,1.0,1.0,1.0);
        Connector[][] LCM = new Connector[15][15];
        boolean[][] PUV = new boolean[15][15];
        LaunchInterceptor launch = new LaunchInterceptor();
        System.out.print(launch.decide());
        
    }
}
