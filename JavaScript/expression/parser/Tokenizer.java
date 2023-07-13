package expression.parser;


import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public enum TokenType {
        OPEN_BRACKET, CLOSE_BRACKET,
        ADD, SUB, MUL, DIV,
        VAR,
        NUMBER, SET,
        CLEAR, MOD,
        SQUARE, ABS,
        EOF
    }

    public static class Token {
        public TokenType type;
        public String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }


        public Token(TokenType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }
    }

    public static class TokenBuffer {
        private int pos;

        public List<Token> tokens;

        public TokenBuffer(List<Token> tokens) {
            this.tokens = tokens;
        }

        public Token next() {
            return tokens.get(pos++);
        }

        public void back() {
            pos--;
        }
    }


    public static List<Token> getTokens(String expText) {
        ArrayList<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }
            if (c == 'a') {
                tokens.add(new Token(TokenType.ABS, "abs"));
                pos += 3;
                continue;
            }
            if (pos < expText.length() - 2 && c == 's' && expText.charAt(pos + 1) == 'q') {
                tokens.add(new Token(TokenType.SQUARE, "square"));
                pos += 6;
                continue;
            }
            if (c == 'm') {
                tokens.add(new Token(TokenType.MOD, "mod"));
                pos += 3;
            }
            if (c == 's') {
                tokens.add(new Token(TokenType.SET, "set"));
                pos += 3;
                continue;
            } else if (c == 'c') {
                tokens.add(new Token(TokenType.CLEAR, "clear"));
                pos++;
            } else if (c == 'x' || c == 'y' || c == 'z') {
                tokens.add(new Token(TokenType.VAR, c));
                pos++;
                continue;
            } else if (c == '(') {
                tokens.add(new Token(TokenType.OPEN_BRACKET, c));
                pos++;
                continue;
            } else if (c == ')') {
                tokens.add(new Token(TokenType.CLOSE_BRACKET, c));
                pos++;
                continue;
            } else if (c == '+') {
                tokens.add(new Token(TokenType.ADD, c));
                pos++;
                continue;
            } else if (c == '-') {
                tokens.add(new Token(TokenType.SUB, c));
                pos++;
                continue;
            } else if (c == '*') {
                tokens.add(new Token(TokenType.MUL, c));
                pos++;
                continue;
            } else if (c == '/') {
                tokens.add(new Token(TokenType.DIV, c));
                pos++;
                continue;
            } else if (c <= '9' && c >= '0') {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c);
                    pos++;
                    if (pos >= expText.length()) {
                        break;
                    }
                    c = expText.charAt(pos);
                } while (c <= '9' && c >= '0');
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));

                continue;
            }
            pos++;
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

}
