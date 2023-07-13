package expression.generic;

import expression.AbstractBinaryOperation;
import expression.generic.type.*;

import java.util.*;

public abstract class BinaryOperation<T> implements GenericExpression<T> {
    protected GenericExpression<T> expr1;
    protected GenericExpression<T> expr2;
    protected Type<T> calcType;

    public BinaryOperation(GenericExpression<T> expr1, GenericExpression<T> expr2, Type<T> calcType) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.calcType = calcType;
    }

    @Override
    public String toString() {
        return "(" + expr1.toString() + " " + getOperationSymbol() + " " + expr2.toString() + ")";
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return makeOperation(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }

    @Override
    public T evaluate(T x) {
        return makeOperation(expr1.evaluate(x), expr2.evaluate(x));
    }

    protected abstract String getOperationSymbol();

    protected abstract T makeOperation(T x, T y);

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;
        return expr1.equals(((BinaryOperation<?>) ob).expr1)
                && expr2.equals(((BinaryOperation<?>) ob).expr2)
                && this.getOperationSymbol().equals(getOperationSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), expr1, expr2) * 31;
    }

}
