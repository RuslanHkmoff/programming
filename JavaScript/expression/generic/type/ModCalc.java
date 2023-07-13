package expression.generic.type;

import expression.exceptions.DivisionByZero;

public class ModCalc implements Type<Integer> {
    private final int module;

    public ModCalc(int module) {
        this.module = module;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        int a = getConst("" + x) % module + module;
        return ((x + y) % module + module) % module;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return ((x - y) % module + module) % module;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return ((x * y) % module + module) % module;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        y = y % module;
        int inv = -1;
        for (int i = 1; i < module; i++) {
            if ((y * i) % module == 1) inv = i;
        }
        return (x * inv) % module;
    }

    public int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // modular inverse does not exist
    }

    @Override
    public Integer negate(Integer x) {
        return (module - x) % module;
    }

    @Override
    public Integer getConst(String value) {
        return (Integer.parseInt(value) % module + module) % module;
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return (Math.min(x, y) % module + module) % module;
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return (Math.max(x, y) % module + module) % module;
    }

    @Override
    public Integer abs(Integer x) {
        return x < 0 ? negate(x) : x % module;
    }

    @Override
    public Integer mod(Integer x, Integer y) {
        return ((x % y) % module + module) % module;
    }

    @Override
    public Integer square(Integer x) {
        return ((x * x) % module + module) % module;
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x) % module;
    }
}
