public class Main {
    public static void main(String[] args) {
//        Spring spring = new Spring();
//        final double[] coords = spring.move(0,   8, 1.2, -4, 0);
//        for(int i = 0; i < coords.length; i++) {
//            System.out.println(coords[i]);
//        }
        Spring[] springs = {
                new Spring(2),
                new Spring(2),
                new Spring(3)
        };
        Spring spring = SpringArray.equivalentSpring("[[[][]][]]");
        System.out.println(spring.getK());
    }
}
