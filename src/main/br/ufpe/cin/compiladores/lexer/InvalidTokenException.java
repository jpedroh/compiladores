package br.ufpe.cin.compiladores.lexer;

public class InvalidTokenException extends RuntimeException {
    private final String character;

    public InvalidTokenException(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Error: Unexpected character: " + this.character;
    }
}
