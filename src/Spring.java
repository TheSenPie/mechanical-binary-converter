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
        final int amount = (int) ((t1 - t0) / dt);
        final double[] coords = new double[amount];

        // constant
        final double omega = Math.sqrt(k / m);
        final double A = x0;
        final double B = v0 / omega;
        double t = t0;
        for (int i = 0; i < amount; i++) {
            final double coord = A * Math.cos(omega * t) + B * Math.sin(omega * t);
            coords[i] = coord;
            t += dt;
        }
        return coords;
    }

    public Spring inSeries (Spring spring) {
        final double k1 = k;
        final double k2 = spring.k;
        if (k1 == 0 && k2 == 0) {
            return new Spring(0);
        }

        double oneOverK1;
        if (k1 == 0) {
            oneOverK1 = 0;
        } else {
            oneOverK1 = 1 / k1;
        }

        double oneOverK2;
        if (k2 == 0) {
            oneOverK2 = 0;
        } else {
            oneOverK2 = 1 / k2;
        }

        final double newK = 1 / ( oneOverK1 + oneOverK2);
        return new Spring(newK);
    }

    public Spring inParallel (Spring spring) {
        final double k1 = k;
        final double k2 = spring.k;
        final double newK = k1 + k2;
        return new Spring(newK);
    }

    public double getK () {
        return k;
    }

    private void setK (double k) {
        this.k = k;
    }
}
