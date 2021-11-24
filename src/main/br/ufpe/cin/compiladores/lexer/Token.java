package br.ufpe.cin.compiladores.lexer;

public class Token {
    public final TokenType type;
    public final String lexeme;

    public Token (TokenType type, String value) {
        this.type = type;
        this.lexeme = value;
    }

    @Override
    public String toString() {
        return "Token [type=" + this.type + ", lexeme=" + this.lexeme + "]";
    }
}
