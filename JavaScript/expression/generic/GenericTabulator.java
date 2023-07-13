package expression.generic;

import expression.generic.type.*;

public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode,
                                 String expression,
                                 int x1, int x2, int y1,
                                 int y2, int z1, int z2) throws Exception {
        Object[][][] arr = new Object[(x2 - x1) + 1][(y2 - y1) + 1][(z2 - z1) + 1];
        Type<?> typeMode = getMode(mode);
        fill(arr, typeMode, expression, x1, x2, y1, y2, z1, z2);
        return arr;
    }

    public <T> void fill(Object[][][] arr, Type<T> typeMode,
                         String expression, int x1, int x2,
                         int y1, int y2, int z1, int z2) {
        ExpressionParser<T> parser = new ExpressionParser<>();
        GenericExpression<T> result = parser.parse(expression, typeMode);
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        arr[i][j][k] = result.evaluate(typeMode.getConst("" + (x1 + i)),
                                typeMode.getConst("" + (y1 + j)), typeMode.getConst("" + (z1 + k)));
                    } catch (ArithmeticException e) {
                        arr[i][j][k] = null;
                    }
                }
            }
        }
    }

    public static void print(Object[][][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result[j].length; k++) {
                    System.out.println("x = " + (i - 2) + " y = " + (j - 2) + " z = " + (k - 2) + " res = " + result[i][j][k]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String mode = args[0];
        String expression = args[1];
        GenericTabulator tabulator = new GenericTabulator();
        print(tabulator.tabulate(mode, expression, -2, 2, -2, 2, -2, 2));
    }

    public Type<?> getMode(String mode) {
        switch (mode) {
            case "i" -> {
                return new CheckedIntegerCalc();
            }
            case "u" -> {
                return new UncheckedIntegerCalc();
            }
            case "d" -> {

                return new DoubleCalc();
            }
            case "bi" -> {
                return new BigIntegerCalc();
            }
            case "l" -> {
                return new LongCalc();
            }
            case "f" -> {
                return new FloatCalc();
            }
            case "p" -> {
                return new ModCalc(10079);
            }
            case "s" -> {
                return new ShortCalc();
            }
            default -> throw new IllegalArgumentException("illegal mode: " + mode);
        }
    }
}
