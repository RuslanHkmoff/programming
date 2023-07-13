package expression.generic.type;

public class FloatCalc implements Type<Float> {
    @Override
    public Float add(Float x, Float y) {
        return x + y;
    }

    @Override
    public Float subtract(Float x, Float y) {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) {
        return x / y;
    }

    @Override
    public Float negate(Float x) {
        return -x;
    }

    @Override
    public Float getConst(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public Float min(Float x, Float y) {
        return Math.min(x, y);
    }

    @Override
    public Float max(Float x, Float y) {
        return Math.max(x, y);
    }

    @Override
    public Float abs(Float x) {
        return Math.abs(x);
    }

    @Override
    public Float mod(Float x, Float y) {
        return x % y;
    }

    @Override
    public Float square(Float x) {
        return x * x;
    }

    @Override
    public Float count(Float x) {
        return (float) Integer.bitCount(Float.floatToIntBits(x));
    }
}
