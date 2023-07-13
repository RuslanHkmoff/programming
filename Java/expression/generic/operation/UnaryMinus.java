package expression.generic.operation;

import expression.generic.GenericExpression;
import expression.generic.UnaryOperation;
import expression.generic.type.*;

public class UnaryMinus<T> extends UnaryOperation<T> {
    public UnaryMinus(GenericExpression<T> expr, Type<T> calcType) {
        super(expr, calcType);
    }

    @Override
    protected T makeOperation(T x) {
        return calcType.negate(x);
    }

    @Override
    public String toString() {
        return "-(" + expr.toString() + ")";
    }
}
