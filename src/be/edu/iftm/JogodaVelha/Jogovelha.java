package be.edu.iftm.JogodaVelha;

import java.util.Random;
import java.util.Scanner;

public class Jogovelha {

    char[][] tabuleiro = new char[3][3];
    int jogador, cont, maxPosicao = 3, n = 0;
    String nome;
    boolean checa, continuaJogo = true;
    Scanner leia = new Scanner(System.in);
    Jogador[] vetorJogador = new Jogador[2];
    Jogador daHora;
    Random aleatorio = new Random();

    public void iniciar() {

        cadastrarJogador();
        System.out.println("==== Início do Jogo: Todas Posições Vazias ===");
        desenharTabuleiro(tabuleiro);

        while (continuaJogo) {

            // desenharTabuleiro(tabuleiro);
            // escolherJogador();

            try {
                if (validarJogada(tabuleiro, realizarJogada(leia, escolherJogador()))) {
                    System.out.println("\n *** Jogada realizada com sucesso!");
                } else {
                    System.out.println("\n << Posição ocupada, tente novamente! >>");
                    while (validarJogada(tabuleiro, realizarJogada(leia, daHora)) == false) {
                        // validarJogada(tabuleiro, realizarJogada(leia, daHora));
                    }

                    System.out.println("Jogada realizada com sucesso!");
                    // n++;
                }
                n++;
                System.out.println("\n *** Jogada contabilizada: " + n);
                desenharTabuleiro(tabuleiro);
                verificarFim();

            } catch (Exception e) {
                System.out.println("\n << Erro encontrado: -> " + e.getMessage());
            }
        }
    }

    public void desenharTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if ((tabuleiro[i][k]) == 0) {
                    tabuleiro[i][k] = '_';
                    System.out.print("|" + tabuleiro[i][k] + "|");
                } else {
                    System.out.print("|" + tabuleiro[i][k] + "|");
                }
                // System.out.println("");
            }
            System.out.println("");
        }
    }

    public Jogador[] cadastrarJogador() {

        System.out.println("\n *** Digite o nome do jogador humano: #8-D");
        nome = leia.nextLine();
        Humano h = new Humano(nome, 'X');
        vetorJogador[0] = h;

        System.out.println("\n *** Digite o nome do jogador Computador: {:-]");
        nome = leia.nextLine();
        Computador c = new Computador(nome, 'O');
        vetorJogador[1] = c;

        return vetorJogador;

    }

    public Jogador escolherJogador() {

        while (checa = true) {
            System.out.println("\n *** Escolha o jogador: Digite 1 para Humano ou 2 para Computador");
            jogador = leia.nextInt();

            if (cont == 0) {
                if (jogador == 1) {
                    daHora = vetorJogador[0];
                    return daHora;

                } else if (jogador == 2) {
                    daHora = vetorJogador[1];
                    return daHora;

                } else {
                    System.out.println("\n << Opção inválida, tente novamente! >>");
                    checa = false;
                }

            } else {
                if (jogador == 1 || jogador == 2) {
                    if (jogador == 1 && daHora.getSimbolo() == 'X') {
                        System.out.println("\n << Jogador humano já jogou, indique jogador computador! >>");
                        // daHora = vetorJogador[1];
                        // return daHora;

                    } else if (jogador == 2 && daHora.getSimbolo() == 'O') {
                        System.out.println("\n << Jogador computador já jogou, indique o jogador humano! >>");
                        // daHora = vetorJogador[0];
                        // return daHora;

                    } else {
                        if (jogador == 1) {
                            daHora = vetorJogador[0];
                            // return daHora;

                        } else if (jogador == 2) {
                            daHora = vetorJogador[1];
                            // return daHora;
                        }
                        System.out.println("\n *** Jogador escolhido válido, " + daHora.getNome() + " prossiga! **");
                        checa = false;
                        return daHora;
                    }
                } else {
                    System.out.println("\n << Opção inválida, tente novamente! >>");
                    checa = false;
                }
            }

        }
        return null;

    }

    public boolean validarJogada(char[][] tabuleiro, int posicao[]) {
        if (tabuleiro[posicao[0]][posicao[1]] == '_') {
            tabuleiro[posicao[0]][posicao[1]] = daHora.getSimbolo();
            return true;
        } else {
            // while(tabuleiro[posicao[0]][posicao[1]] != '_'){
            // realizarJogada(leia, daHora);
            // }
            return false;
        }
    }

    public int[] realizarJogada(Scanner leia, Jogador daHora) {

        int[] posicao = new int[2];
        System.out.println("\n *** Jogo da velha 3x3: Jogador << " + daHora.getNome() +
                " >> escolha a posição a jogar:");

        if (daHora.getSimbolo() == 'O') {
            posicao[0] = aleatorio.nextInt(maxPosicao);
            posicao[1] = aleatorio.nextInt(maxPosicao);
            System.out.println("\n -> Tentativas: " + posicao[0] + ", " + posicao[1]);
        } else if (daHora.getSimbolo() == 'X') {
            System.out.println("\n *** Digite a linha");
            posicao[0] = leia.nextInt();
            System.out.println("\n *** Digite a coluna");
            posicao[1] = leia.nextInt();
        }
        cont++;

        return posicao;
    }

    public void verificarFim() {

        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] != '_' || tabuleiro[i][1] != '_' || tabuleiro[i][2] != '_') {
                boolean linha = tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2];
                boolean coluna = tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i];

                boolean diagonalPrincipal = tabuleiro[0][0] == tabuleiro[1][1]
                        && tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[i][i] != '_';

                boolean diagonalSecundaria = tabuleiro[2][0] == tabuleiro[1][1] &&
                        tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[2 - i][i] != '_';

                if (linha) {
                    if (tabuleiro[i][0] == 'O') {
                        System.out.println("\n **** Vencedor " + tabuleiro[i][0] + " é o Computador " +
                                daHora.getNome());
                        continuaJogo = false;
                    } else if (tabuleiro[i][0] == 'X') {
                        System.out.println("\n **** Vencedor " + tabuleiro[i][0] + " é o Humano " +
                                daHora.getNome());
                        continuaJogo = false;
                    }
                } else if (coluna) {
                    if (tabuleiro[0][i] == 'O') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][i] + " é o Computador " +
                                daHora.getNome());
                        continuaJogo = false;
                    } else if (tabuleiro[0][i] == 'X') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][i] + " é o Humano " +
                                daHora.getNome());
                        continuaJogo = false;
                    }
                } else if (diagonalPrincipal) {
                    if (tabuleiro[0][0] == 'O') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][0] + " é o Computador " +
                                daHora.getNome());
                        continuaJogo = false;
                        break;
                    } else if (tabuleiro[0][0] == 'X') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][0] + " é o Humano " +
                                daHora.getNome());
                        continuaJogo = false;
                        break;
                    }
                } else if (diagonalSecundaria) {
                    if (tabuleiro[0][2] == 'O') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][2] + " é o Computador " +
                                daHora.getNome());
                        continuaJogo = false;
                        break;
                    } else if (tabuleiro[0][2] == 'X') {
                        System.out.println("\n **** Vencedor " + tabuleiro[0][2] + " é o Humano " +
                                daHora.getNome());
                        continuaJogo = false;
                        break;
                    }
                } else if (n == 9 && continuaJogo) {
                    System.out.println("\n *** Jogo empatado");
                    continuaJogo = false;
                    break;
                }
            }
        }
    }
    // Fechamento da classe
}
