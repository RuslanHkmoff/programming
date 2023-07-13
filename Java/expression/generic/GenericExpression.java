package expression.generic;

public interface GenericExpression<T> extends expression.ToMiniString {
    T evaluate(T x);

    T evaluate(T x, T y, T z);
}
