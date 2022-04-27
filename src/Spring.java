public class Spring {
    private double k;

    public Spring() {
        this(1);
    }

    public Spring (double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0) {
        return move(0, t, dt, x0, 0, 1);
    }

    public double[] move(double t, double dt, double x0, double v0) {
        return move(0, t, dt, x0, v0, 1);
    }


    public double[] move(double t0, double t1, double dt, double x0,  double v0) {
        return move(t0, t1, dt, x0, v0, 1);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        return null;
    }

    public double getK () {
        return k;
    }

    private void setK (double k) {
        this.k = k;
    }
}
