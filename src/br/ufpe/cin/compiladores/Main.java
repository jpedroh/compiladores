package br.ufpe.cin.compiladores;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final Map<Character, Operacao> operacoes = new HashMap<>();

    public static void main(String[] args) {
        final Stack<Integer> pilha = new Stack<>();
        final Scanner in = new Scanner(System.in);

        operacoes.put('+', (x, y) -> x + y);
        operacoes.put('-', (x, y) -> x - y);
        operacoes.put('*', (x, y) -> x * y);
        operacoes.put('/', (x, y) -> x / y);

        while (in.hasNext()) {
            if (in.hasNextInt()) {
                int valor = in.nextInt();
                pilha.push(valor);
            } else {
                char operador = in.next().charAt(0);
                int x = pilha.pop();
                int y = pilha.pop();
                pilha.push(operacoes.get(operador).operar(x, y));
            }
        }

        System.out.println(pilha.pop());
    }

    @FunctionalInterface
    private interface Operacao {
        int operar(int x, int y);
    }
}
