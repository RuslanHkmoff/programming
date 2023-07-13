package expression.generic.type;

import expression.exceptions.DivisionByZero;

public class DoubleCalc implements Type<Double> {

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double getConst(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public Double min(Double x, Double y) {
        return Math.min(x, y);
    }

    @Override
    public Double max(Double x, Double y) {
        return Math.max(x, y);
    }

    @Override
    public Double abs(Double x) {
        return Math.abs(x);
    }

    @Override
    public Double mod(Double x, Double y) {
        return x % y;
    }

    @Override
    public Double square(Double x) {
        return x * x;
    }

    @Override
    public Double count(Double x) {
        return (double) Long.bitCount(Double.doubleToRawLongBits(x));
    }
}
