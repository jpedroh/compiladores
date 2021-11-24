package br.ufpe.cin.compiladores;

import br.ufpe.cin.compiladores.core.Operacao;
import br.ufpe.cin.compiladores.lexer.InvalidTokenException;
import br.ufpe.cin.compiladores.lexer.Token;
import br.ufpe.cin.compiladores.lexer.TokenFactory;
import br.ufpe.cin.compiladores.lexer.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final Map<TokenType, Operacao> operacoes = new HashMap<>();

    private static InputStream fromStdIn() {
        return System.in;
    }

    private static File fromResourceFile() {
        URL url = Main.class.getClassLoader().getResource("Expressao.stk");
        assert url != null;
        return new File(url.getPath());
    }

    public static void main(String[] args) throws FileNotFoundException {
        final Stack<Token> pilha = new Stack<>();
        final Scanner in = new Scanner(fromResourceFile());

        operacoes.put(TokenType.PLUS, (x, y) -> x + y);
        operacoes.put(TokenType.MINUS, (x, y) -> x - y);
        operacoes.put(TokenType.STAR, (x, y) -> x * y);
        operacoes.put(TokenType.SLASH, (x, y) -> x / y);

        while (in.hasNext()) {
            try {
                String lexeme = in.next();
                Token token = TokenFactory.buildToken(lexeme);
                System.out.println(token);

                if (token.type == TokenType.NUM) {
                    pilha.push(token);
                } else {
                    int x = Integer.parseInt(pilha.pop().lexeme);
                    int y = Integer.parseInt(pilha.pop().lexeme);
                    Integer resultado = operacoes.get(token.type).operar(x, y);
                    Token tokenResultado = new Token(TokenType.NUM, resultado.toString());
                    pilha.push(tokenResultado);
                }
            } catch (InvalidTokenException exception) {
                System.out.println(exception);
                return;
            }
        }

        System.out.println(pilha.pop().lexeme);
    }
}
