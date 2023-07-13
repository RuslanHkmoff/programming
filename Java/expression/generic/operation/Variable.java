package expression.generic.operation;

import expression.generic.GenericExpression;

public class Variable<T> implements GenericExpression<T> {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x) {
        return x;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (value) {
            case "x" -> {
                return x;
            }
            case "y" -> {
                return y;
            }
            case "z" -> {
                return z;
            }
            default -> throw new RuntimeException();
        }
    }
}
