package expression.generic.type;

public interface Type<T> {
    T add(T x, T y);

    T subtract(T x, T y);

    T multiply(T x, T y);

    T divide(T x, T y);

    T negate(T x);

    T getConst(String value);

    T min(T x, T y);

    T max(T x, T y);

    T abs(T x);

    T mod(T x, T y);

    T square(T x);

    T count(T x);
}
