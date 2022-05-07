import java.util.ArrayDeque;
import java.util.Deque;

public class SpringArray {
    /**
     * push - addFirst
     * pop - removeFirst
     * peek - peekFirst
     */
    private static Deque<Character> expressionStack = new ArrayDeque<>();

    public static Spring equivalentSpring(String stringExpr) {
        // there's gonna be at most n / 2 springs
        Spring[] springs = new Spring[stringExpr.length() / 2];
        for (int i = 0; i < springs.length; i++) {
            springs[i] = new Spring();
        }
        return equivalentSpring(stringExpr, springs);
    }

    public static Spring equivalentSpring(String stringExp, Spring[] springs) {
        expressionStack.clear();

        int ci = 0; // char index
        int si = 0; // spring index

        Spring[] stc = new Spring[1000]; // springs to connect
        int stcs = 0; // springs to connect size

        int mode = 0;
        while(ci < stringExp.length()) {
            final char curr = stringExp.charAt(ci);

            if (curr == '[' || curr == '{') {
                mode = 1;
                expressionStack.push(curr);
            } else if (curr == ']' || curr == '}') {
                final char prev = expressionStack.pop();

                if (prev == '[' && curr != ']' || prev == '{' && curr != '}') {
                    throw new Error("Illegal sequence");
                }

                mode -= 1;
                if (curr == ']') {
                    if (mode < 0) {
                        stc[0] = Spring.inParallel(stc, stcs);
                        stcs = 1;
                    } else {
                        final Spring spring = new Spring(springs[si]);
                        si += 1;
                        stc[stcs] = spring;
                        stcs += 1;
                    }
                } else if (curr == '}') {
                    if (mode < 0) {
                        stc[0] = Spring.inSeries(stc, stcs);
                        stcs = 1;
                    }
                }
            } else {
                throw new Error("Invalid character");
            }

            ci += 1;
        }

        return stc[0];
    }
}
