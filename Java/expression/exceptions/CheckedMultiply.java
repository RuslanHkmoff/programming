package expression.exceptions;

import expression.AbstractExpression;

public class CheckedMultiply extends AbstractBinaryOperationChecked {
    public CheckedMultiply(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }


    @Override
    protected String getOperation() {
        return "*";
    }


    @Override
    protected int makeIntOperation(int x, int y) {
        if ((x > 0 && y > 0 && x <= Integer.MAX_VALUE / y)
                || (x > 0 && y < 0 && y >= Integer.MIN_VALUE / x)
                || (x < 0 && y > 0 && x >= Integer.MIN_VALUE / y)
                || (x < 0 && y < 0 && y >= Integer.MAX_VALUE / x)
                || (x == 0 || y == 0)) {
            return x * y;
        }
        throw new OverflowExceptions("overflow");
    }
}
