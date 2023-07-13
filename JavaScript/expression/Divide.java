package expression;

public class Divide extends AbstractBinaryOperation {
    public Divide(AbstractExpression expr1, AbstractExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getOperation() {
        return "/";
    }

    @Override
    protected double makeDoubleOperation(double x, double y, String op) {

        return x / y;
    }

    @Override
    protected int makeIntOperation(int x, int y, String op) {
        return x / y;
    }


}
