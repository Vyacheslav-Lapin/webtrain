package lab;

import lombok.experimental.FieldDefaults;

import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class FpExample {

    public static void main(String... __) {

        IntFunction<IntUnaryOperator> squareCarry = x -> y -> square(x, y);

        // calc x
        int x = 115;

//        IntUnaryOperator squereWithX = y -> square(x, y);
        IntUnaryOperator squareCarryY = squareCarry.apply(x);

        // calc y
        int y = 8;
        int z = squareCarryY.applyAsInt(y);

//        int z = squereWithX.applyAsInt(y);
//        int z = square(x, y);
    }

    static int square(int x, int y) {
        return x * y;
    }
}
