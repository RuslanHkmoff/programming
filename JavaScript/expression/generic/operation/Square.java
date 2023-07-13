package expression.generic.operation;

import expression.generic.GenericExpression;
import expression.generic.UnaryOperation;
import expression.generic.type.Type;

public class Square<T> extends UnaryOperation<T> {
    public Square(GenericExpression<T> expr, Type<T> calcType) {
        super(expr, calcType);
    }

    @Override
    protected T makeOperation(T x) {
        return calcType.square(x);
    }

    @Override
    public String toString() {
        return "square(" + expr.toString() + ")";
    }
}
