package expression.generic.type;

import expression.exceptions.DivisionByZero;

public class LongCalc implements Type<Long> {
    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        return x / y;
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }

    @Override
    public Long getConst(String value) {
        return Long.parseLong(value);
    }

    @Override
    public Long min(Long x, Long y) {
        return Math.min(x, y);
    }

    @Override
    public Long max(Long x, Long y) {
        return Math.max(x, y);
    }

    @Override
    public Long abs(Long x) {
        return Math.abs(x);
    }

    @Override
    public Long mod(Long x, Long y) {
        return x % y;
    }

    @Override
    public Long square(Long x) {
        return x * x;
    }

    @Override
    public Long count(Long x) {
        return (long) Long.bitCount(x);
    }
}
