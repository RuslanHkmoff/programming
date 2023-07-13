package expression.generic.type;

import expression.exceptions.DivisionByZero;

public class UncheckedIntegerCalc implements Type<Integer> {
    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        return -1 * x;
    }

    @Override
    public Integer getConst(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return x <= y ? x : y;
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return x >= y ? x : y;
    }

    @Override
    public Integer abs(Integer x) {
        return x < 0 ? negate(x) : x;
    }

    @Override
    public Integer mod(Integer x, Integer y) {
        return x % y;
    }

    @Override
    public Integer square(Integer x) {
        return x * x;
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }
}
