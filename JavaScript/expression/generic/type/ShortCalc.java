package expression.generic.type;

import expression.exceptions.DivisionByZero;

public class ShortCalc implements Type<Short> {
    @Override
    public Short add(Short x, Short y) {
        return (short) (x + y);
    }

    @Override
    public Short subtract(Short x, Short y) {
        return (short) (x - y);
    }

    @Override
    public Short multiply(Short x, Short y) {
        return (short) (x * y);
    }

    @Override
    public Short divide(Short x, Short y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        return (short) (x / y);
    }

    @Override
    public Short negate(Short x) {
        return (short) (-x);
    }

    @Override
    public Short getConst(String value) {
        return (short) Long.parseLong(value);
    }

    @Override
    public Short min(Short x, Short y) {
        return (short) Math.min(x, y);
    }

    @Override
    public Short max(Short x, Short y) {
        return (short) Math.max(x, y);
    }

    @Override
    public Short abs(Short x) {
        return (short) Math.abs(x);
    }

    @Override
    public Short mod(Short x, Short y) {
        if (y == 0) throw new DivisionByZero("division by zero");
        return (short) (x % y);
    }

    @Override
    public Short square(Short x) {
        return (short) (x * x);
    }

    @Override
    public Short count(Short x) {
        return (short) Integer.bitCount(x);
    }
}
