package expression.generic.operation;

import expression.generic.GenericExpression;

public class Const<T> implements GenericExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x) {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
