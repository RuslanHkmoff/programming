package expression.generic;

import expression.generic.type.*;

import java.util.Objects;

public abstract class UnaryOperation<T> implements GenericExpression<T> {
    protected GenericExpression<T> expr;
    protected Type<T> calcType;

    public UnaryOperation(GenericExpression<T> expr, Type<T> calcType) {
        this.expr = expr;
        this.calcType = calcType;
    }

    @Override
    public T evaluate(T x) {
        return null;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return makeOperation(expr.evaluate(x, y, z));
    }

    protected abstract T makeOperation(T x);

    @Override
    public boolean equals(Object ob) {
        if (ob == this) return true;
        if (ob == null || ob.getClass() != this.getClass()) return false;
        return expr.equals(((UnaryOperation<?>) ob).expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), expr) * 31;
    }
}
