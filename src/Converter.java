public class Converter {

    private Spring spring;
    private double[] coords;
    private double maxAmplitudeFrequency;
    private static final int N = 2048;

    public void binaryToSpring(String bitSeq) {
        StringBuilder seq = new StringBuilder();
        seq.append('[');
        for (int i = 7; i >= 0; i--) {
            char bit = bitSeq.charAt(i);
            if (bit == '1') {
                int n = (int) Math.pow(2, 8 - i - 1);
                for (int j = 0; j < n; j++) {
                    seq.append("[]");
                }
            }
        }
        seq.append(']');
        spring =  SpringArray.equivalentSpring(seq.toString());
    }

    public void compute () {
        final double t0 = 0;
        final double t1 = 32;
        final double dt = (t1 - t0) / N;
        final double x0 = 32;
        final double v0 = 0;
        final double m = 1;
        if (spring != null) {
            coords = spring.move(t0, t1, dt, x0, v0, m);
        }
    }

    public  void FT () {
        int n = N;
        Complex[] x = new Complex[n];
        for (int i = 0; i < n; i++) {
            x[i] = new Complex(coords[i], 0);
        }
        // FFT of original data
        Complex[] y = SimpleFFT.fft(x);

        double max = Double.NEGATIVE_INFINITY;
        double index = -1;

        for (int i = 0; i < N / 2; i++) {
            if (y[i].abs() > max) {
                max = y[i].abs();
                index = i;
            }
        }

        final double t0 = 0;
        final double t1 = 32;
        final double dt = (t1 - t0) / N;
        maxAmplitudeFrequency = ((index / N) / dt) * Math.PI * 2;
    }

    public double decimal () {
        double omega = maxAmplitudeFrequency;
        double m = 1d;
        double k = Math.pow(omega, 2) * m;
        return k;
    }
}
