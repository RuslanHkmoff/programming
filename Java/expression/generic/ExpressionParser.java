package expression.generic;


import java.util.List;

import expression.generic.operation.*;
import expression.generic.type.*;

import static expression.parser.Tokenizer.*;

public class ExpressionParser<T> {
    private Type<T> typeCalc;

    public GenericExpression<T> parse(String expression, Type<T> typeCalc) {
        this.typeCalc = typeCalc;
        List<Token> tokensList = getTokens(expression);
        TokenBuffer tokens = new TokenBuffer(tokensList);
        return parseExpression(tokens);

    }

    private GenericExpression<T> parseExpression(TokenBuffer tokens) {
        Token curr = tokens.next();
        if (curr.type == TokenType.EOF) {
            return null;
        }
        tokens.back();
        return parseAddSub(tokens);
    }

    private GenericExpression<T> parseAddSub(TokenBuffer tokens) {
        GenericExpression<T> value = parseMulDiv(tokens);
        while (true) {
            Token curr = tokens.next();
            switch (curr.type) {
                case ADD -> value = new Add<>(value, parseMulDiv(tokens), typeCalc);
                case SUB -> value = new Subtract<>(value, parseMulDiv(tokens), typeCalc);
                default -> {
                    tokens.back();
                    return value;
                }
            }
        }
    }

    private GenericExpression<T> parseMulDiv(TokenBuffer tokens) {
        GenericExpression<T> value = parseConstVar(tokens);
        while (true) {
            Token curr = tokens.next();
            switch (curr.type) {
                case MOD -> value = new Mod<>(value, parseMulDiv(tokens), typeCalc);
                case MUL -> value = new Multiply<>(value, parseConstVar(tokens), typeCalc);
                case DIV -> value = new Divide<>(value, parseConstVar(tokens), typeCalc);
                default -> {
                    tokens.back();
                    return value;
                }
            }
        }
    }

    private GenericExpression<T> parseConstVar(TokenBuffer tokens) {
        GenericExpression<T> res;
        Token curr = tokens.next();
        switch (curr.type) {
            case NUMBER -> {
                res = new Const<>(typeCalc.getConst(curr.value));
                return res;
            }
            case OPEN_BRACKET -> {
                GenericExpression<T> value = parseAddSub(tokens);
                curr = tokens.next();
                return value;
            }
            case SUB -> {
                curr = tokens.next();
                if (curr.type == TokenType.NUMBER) {
                    return new Const<>(typeCalc.getConst("-" + curr.value));
                } else {
                    tokens.back();
                    return new UnaryMinus<>(parseConstVar(tokens), typeCalc);
                }
            }
            case SQUARE -> {
                return new Square<>(parseConstVar(tokens), typeCalc);
            }
            case ABS -> {
                return new Abs<>(parseConstVar(tokens), typeCalc);
            }
            case VAR -> {
                if (curr.value.equals("x") || curr.value.equals("y") || curr.value.equals("z")) {
                    res = new Variable<>(curr.value + "");
                    return res;
                }
            }
        }
        return null;
    }
}
