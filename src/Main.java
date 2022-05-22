import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Spring[] springs = {
//                new Spring(2),
//                new Spring(2),
//                new Spring(3)
//        };
        Spring spring = SpringArray.equivalentSpring("{[][]{[][][]}}");
        System.out.println(spring.getK());
//        Spring spring = new Spring(0.125);
//        final double t0 = 0;
//        final double t1 = 10;
//        final double dt = (t1 - t0) / 512d;
//        final double x0 = -4;
//        final double v0 = 1;
//        final double m = 2;
//        final double[] coords = spring.move(t0, t1, dt, x0, v0, m);
//        double a0over2 = 0;
//        for (int i = 0; i < coords.length; i++) {
//            a0over2 += coords[i];
//        }
//        a0over2 /= coords.length;
//        System.out.println(a0over2);
////        for(double t = t0; t <= t1; t += dt) {
////            System.out.print(t + ", ");
////        }
////        System.out.println();
////        System.out.println(spring.getK());
//        System.out.println(Arrays.toString(coords));
//        double[][] result = FT.transform(coords, t0, t1, dt);
//        System.out.println(Arrays.toString(result[0]));
//        System.out.println(Arrays.toString(result[1]));
    }
}
