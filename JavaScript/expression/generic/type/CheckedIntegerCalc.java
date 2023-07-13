package expression.generic.type;

import expression.exceptions.*;

public class CheckedIntegerCalc implements Type<Integer> {
    private  boolean flag;



    @Override
    public Integer add(Integer x, Integer y) {
        if (((x > 0 && y > 0 && Integer.MAX_VALUE - x < y)
                || x < 0 && y < 0 && Integer.MIN_VALUE - x > y)) {
            throw new OverflowExceptions("overflow");
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (((x == 0 && y == Integer.MIN_VALUE)
                || (y > 0 && Integer.MIN_VALUE + y > x)
                || y < 0 && Integer.MAX_VALUE + y < x)) {
            throw new OverflowExceptions("overflow");
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (((x > 0 && y > 0 && x > Integer.MAX_VALUE / y)
                || (x < 0 && y < 0 && x < Integer.MAX_VALUE / y)
                || (x < 0 && y > 0 && x < Integer.MIN_VALUE / y)
                || (x > 0 && y < 0 && y < Integer.MIN_VALUE / x))) {
            throw new OverflowExceptions("overflow");
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        if (x == Integer.MIN_VALUE && y == -1) throw new OverflowExceptions("overflow");
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) throw new OverflowExceptions("overflow");
        return -x;
    }

    @Override
    public Integer getConst(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Math.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Math.max(x, y);
    }

    @Override
    public Integer abs(Integer x) {
        if (x < 0) return negate(x);
        return x;
    }

    @Override
    public Integer mod(Integer x, Integer y) {
        if (y==0) throw new DivisionByZero("division by zero");
        return x % y;
    }

    @Override
    public Integer square(Integer x) {
        if (abs(x) > 46341) throw new OverflowExceptions("overflow");
        return x * x;
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }
}
