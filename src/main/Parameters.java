package Decide.src.main;

/**
 * Encompasses the parameters defined project specification.
 * 
 * @see "Launch Interceptor Program: Requirements Specification."
 */
public class Parameters {
    public double LENGTH1; // Length in LICs 0, 7, 12
    public double RADIUS1; // Radius in LICs 1, 8, 13
    public double EPSILON; // Deviation from PI in LICs 2, 9
    public double AREA1; // Area in LICs 3, 10, 14
    public int Q_PTS; // No. of consecutive points in LIC 4
    public int QUADS; // No. of quadrants in LIC 4
    public double DIST; // Distance in LIC 6
    public int N_PTS; // No. of consecutive pts. in LIC 6
    public int K_PTS; // No. of int. pts. in LICs 7, 12
    public int A_PTS; // No. of int. pts. in LICs 8, 13
    public int B_PTS; // No. of int. pts. in LICs 8, 13
    public int C_PTS; // No. of int. pts. in LIC 9
    public int D_PTS; // No. of int. pts. in LIC 9
    public int E_PTS; // No. of int. pts. in LICs 10, 14
    public int F_PTS; // No. of int. pts. in LICs 10, 14
    public int G_PTS; // No. of int. pts. in LIC 11
    public double LENGTH2; // Maximum length in LIC 12
    public double RADIUS2; // Maximum radius in LIC 13
    public double AREA2; // Maximum area in LIC 14

    public Parameters() {
    }

    public Parameters(double LENGTH1, double RADIUS1, double EPSILON, double AREA1, int Q_PTS, int QUADS, double DIST,
            int N_PTS, int K_PTS, int A_PTS, int B_PTS, int C_PTS, int D_PTS, int E_PTS, int F_PTS, int G_PTS,
            double LENGTH2, double RADIUS2, double AREA2) {
        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.G_PTS = G_PTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }

    public double getLENGTH1() {
        return this.LENGTH1;
    }

    public void setLENGTH1(double LENGTH1) {
        this.LENGTH1 = LENGTH1;
    }

    public double getRADIUS1() {
        return this.RADIUS1;
    }

    public void setRADIUS1(double RADIUS1) {
        this.RADIUS1 = RADIUS1;
    }

    public double getEPSILON() {
        return this.EPSILON;
    }

    public void setEPSILON(double EPSILON) {
        this.EPSILON = EPSILON;
    }

    public double getAREA1() {
        return this.AREA1;
    }

    public void setAREA1(double AREA1) {
        this.AREA1 = AREA1;
    }

    public int getQ_PTS() {
        return this.Q_PTS;
    }

    public void setQ_PTS(int Q_PTS) {
        this.Q_PTS = Q_PTS;
    }

    public int getQUADS() {
        return this.QUADS;
    }

    public void setQUADS(int QUADS) {
        this.QUADS = QUADS;
    }

    public double getDIST() {
        return this.DIST;
    }

    public void setDIST(double DIST) {
        this.DIST = DIST;
    }

    public int getN_PTS() {
        return this.N_PTS;
    }

    public void setN_PTS(int N_PTS) {
        this.N_PTS = N_PTS;
    }

    public int getK_PTS() {
        return this.K_PTS;
    }

    public void setK_PTS(int K_PTS) {
        this.K_PTS = K_PTS;
    }

    public int getA_PTS() {
        return this.A_PTS;
    }

    public void setA_PTS(int A_PTS) {
        this.A_PTS = A_PTS;
    }

    public int getB_PTS() {
        return this.B_PTS;
    }

    public void setB_PTS(int B_PTS) {
        this.B_PTS = B_PTS;
    }

    public int getC_PTS() {
        return this.C_PTS;
    }

    public void setC_PTS(int C_PTS) {
        this.C_PTS = C_PTS;
    }

    public int getD_PTS() {
        return this.D_PTS;
    }

    public void setD_PTS(int D_PTS) {
        this.D_PTS = D_PTS;
    }

    public int getE_PTS() {
        return this.E_PTS;
    }

    public void setE_PTS(int E_PTS) {
        this.E_PTS = E_PTS;
    }

    public int getF_PTS() {
        return this.F_PTS;
    }

    public void setF_PTS(int F_PTS) {
        this.F_PTS = F_PTS;
    }

    public int getG_PTS() {
        return this.G_PTS;
    }

    public void setG_PTS(int G_PTS) {
        this.G_PTS = G_PTS;
    }

    public double getLENGTH2() {
        return this.LENGTH2;
    }

    public void setLENGTH2(double LENGTH2) {
        this.LENGTH2 = LENGTH2;
    }

    public double getRADIUS2() {
        return this.RADIUS2;
    }

    public void setRADIUS2(double RADIUS2) {
        this.RADIUS2 = RADIUS2;
    }

    public double getAREA2() {
        return this.AREA2;
    }

    public void setAREA2(double AREA2) {
        this.AREA2 = AREA2;
    }
}
