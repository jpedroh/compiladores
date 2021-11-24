package br.ufpe.cin.compiladores.lexer;

public class TokenFactory {
    private static final String NUM_MATCH = "\\d";
    private static final String NUM_MINUS = "\\-";
    private static final String NUM_PLUS = "\\+";
    private static final String NUM_SLASH = "\\/";
    private static final String NUM_STAR = "\\*";

    public static Token buildToken(String token) {
        if (token.matches(NUM_MATCH)) {
            return new Token(TokenType.NUM, token);
        } else if (token.matches(NUM_MINUS)) {
            return new Token(TokenType.MINUS, token);
        } else if (token.matches(NUM_PLUS)) {
            return new Token(TokenType.PLUS, token);
        } else if (token.matches(NUM_SLASH)) {
            return new Token(TokenType.SLASH, token);
        } else if (token.matches(NUM_STAR)) {
            return new Token(TokenType.STAR, token);
        }
        throw new InvalidTokenException(token);
    }
}
