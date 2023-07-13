package expression.generic.type;

import expression.exceptions.*;

import java.math.BigInteger;

public class BigIntegerCalc implements Type<BigInteger> {
    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) throw new DivisionByZero("division by zero");
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger getConst(String value) {
        return new BigInteger(value);
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        if (x.compareTo(y) < 0) return x;
        return y;
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        if (min(x, y).equals(x)) return y;
        return x;
    }

    @Override
    public BigInteger abs(BigInteger x) {
        return x.compareTo(BigInteger.ZERO) < 0 ? BigInteger.ZERO.subtract(x) : x;
    }

    @Override
    public BigInteger mod(BigInteger x, BigInteger y) {
        return x.mod(y);
    }

    @Override
    public BigInteger square(BigInteger x) {
        return x.multiply(x);
    }

    @Override
    public BigInteger count(BigInteger x) {
        return BigInteger.valueOf(x.bitCount());
    }
}
