public class Spring {
    private double k;

    public Spring() {
        this(1);
    }

    public Spring (double k) {
        this.k = k;
    }

    public Spring (Spring spring) {
        this.k = spring.k;
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
        final int amount = (int) ((t1 - t0) / dt) + 1;
        final double[] coords = new double[amount];

        // constant
        final double omega = Math.sqrt(k / m);
        final double A = v0 / omega;
        final double B = x0;
        double t = t0;
        for(int i = 0; t <= t1; i++) {
            final double coord = A * Math.sin(omega * t) + B * Math.cos(omega * t);
            coords[i] = coord;
            t += dt;
        }
        return coords;
    }

    public Spring inSeries (Spring spring) {
        final double k1 = k;
        final double k2 = spring.k;
        final double newK = (k1 * k2) / (k1 + k2);
        return new Spring(newK);
    }

    public static Spring inSeries (Spring[] springs, int size) {
        double inverseKSum = 0;
        for (int i = 0; i < size; i++) {
            inverseKSum += 1 / springs[i].getK();
        }
        return new Spring(1 / inverseKSum);
    }

    public Spring inParallel (Spring spring) {
        final double k1 = k;
        final double k2 = spring.k;
        final double newK = k1 + k2;
        return new Spring(newK);
    }

    public static Spring inParallel (Spring[] springs, int size) {
        double kSum = 0;
        for (int i = 0; i < size; i++) {
            kSum += springs[i].getK();
        }
        return new Spring(kSum);
    }

    public double getK () {
        return k;
    }

    private void setK (double k) {
        this.k = k;
    }
}
