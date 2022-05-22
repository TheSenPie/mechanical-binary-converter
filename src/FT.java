public class FT {
    public static double[][] transform(double[] coords, double t0, double t1, double dt) {
        double[] a = new double[coords.length];
        double[] b = new double[coords.length];

        double T = (t1 - t0);
        double omega0 = 2 * Math.PI / T;
        System.out.println(omega0);
        for (int k = 0; k < coords.length; k++) {
            double omegak = k * omega0;

            double ak = 0;
            for (int i = 0; i < coords.length; i++) {
                ak += coords[i] * Math.cos(omegak * (t1 + i * dt));
            }
            ak *= (2d * dt) / T;
            a[k] = ak;

            double bk = 0;
            for (int i = 0; i < coords.length; i++) {
                bk += coords[i] * Math.sin(omegak * (t1 + i * dt));
            }
            bk *= (2d * dt) / T;
            b[k] = bk;
        }

        double[][] result = {a, b};
        return result;
    }
}