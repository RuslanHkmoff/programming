package expression;

public class Count extends UnaryOperation {
    public Count(AbstractExpression expr) {
        super(expr);
    }


    @Override
    public String toString() {
        return "count(" + expr.toString() + ")";
    }

    @Override
    protected int makeOperation(int x) {
        return Integer.bitCount(x);
    }
}
