package expression.generic.operation;

import expression.generic.*;
import expression.generic.type.Type;

public class Count<T> extends UnaryOperation<T> {
    public Count(GenericExpression<T> expr, Type<T> calcType) {
        super(expr, calcType);
    }

    @Override
    protected T makeOperation(T x) {
        return calcType.count(x);
    }

    @Override
    public String toString() {
        return "count(" + expr.toString() + ")";
    }
}
