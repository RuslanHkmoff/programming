package expression.generic.operation;

import expression.generic.GenericExpression;
import expression.generic.UnaryOperation;
import expression.generic.type.Type;

public class Abs<T> extends UnaryOperation<T> {
    public Abs(GenericExpression<T> expr, Type<T> calcType) {
        super(expr, calcType);
    }

    @Override
    protected T makeOperation(T x) {
        return calcType.abs(x);
    }

    @Override
    public String toString() {
        return "abs(" + expr.toString() + ")";
    }
}
