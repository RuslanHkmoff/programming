package expression.generic.operation;

import expression.generic.BinaryOperation;
import expression.generic.GenericExpression;
import expression.generic.type.Type;

public class Divide<T> extends BinaryOperation<T> {

    public Divide(GenericExpression<T> expr1, GenericExpression<T> expr2, Type<T> calcType) {
        super(expr1, expr2, calcType);
    }

    @Override
    protected String getOperationSymbol() {
        return "/";
    }

    @Override
    protected T makeOperation(T x, T y) {
        return calcType.divide(x, y);
    }
}
