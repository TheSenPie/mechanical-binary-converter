import java.util.ArrayDeque;
import java.util.Deque;

public class SpringArray {
    private static Deque<Spring> springStack = new ArrayDeque<>();
    private static Deque<Integer> connectSize = new ArrayDeque<>();

    public static Spring equivalentSpring(String stringExpr) {
        // there's gonna be at most n / 2 springs
        Spring[] springs = new Spring[stringExpr.length() / 2];
        for (int i = 0; i < springs.length; i++) {
            springs[i] = new Spring();
        }
        return equivalentSpring(stringExpr, springs);
    }

    public static Spring equivalentSpring(String stringExp, Spring[] springs) {
        if (stringExp.length() < 2) {
            throw new Error("Invalid Expression");
        }

        if (stringExp.equals("[]")) {
            return new Spring(springs[0]);
        } else if (stringExp.equals("{}")) {
            return null;
        }

        springStack.clear();
        connectSize.clear();

        int ci = 0; // character index
        int si = 0; // spring index

        while (ci < stringExp.length()) {
            char curr = stringExp.charAt(ci);
            boolean isOpening = curr == '[' || curr == '{';
            if (isOpening) {
                try {
                    char next = stringExp.charAt(ci + 1);

                    if (curr == '[' && next == ']') {
                        springStack.push(springs[si++]);
                        connectSize.push(connectSize.pop() + 1);
                        ci++;;
                    } else {
                        connectSize.push(0);
                    }
                } catch (IndexOutOfBoundsException exception) {
                    throw new Error("Invalid expression");
                }
            } else {
                int sz = connectSize.pop();
                while (sz > 1) {
                    Spring first = springStack.pop();
                    Spring second = springStack.pop();
                    if (curr == ']') {
                        springStack.push(first.inParallel(second));
                    } else if (curr == '}') {
                        springStack.push(first.inSeries(second));
                    } else {
                        throw new Error("Bad expression!");
                    }
                    sz--;
                }
                if (!connectSize.isEmpty()) {
                    connectSize.push(connectSize.pop() + 1);
                }
            }
            ci++;
        }

        if (!springStack.isEmpty()) {
            return springStack.pop();
        } else {
            return null;
        }
    }

}
