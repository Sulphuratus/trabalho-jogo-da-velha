package be.edu.iftm.JogodaVelha;

import java.util.Random;
import java.util.Scanner;

public class Velhaplus {

    char[][] cartela = new char[10][10];
    int jogador;
    int cont, n = 0, maxPosicao = 9;
    String nome;
    boolean checa, continuaJogo = true;
    Scanner leia = new Scanner(System.in);
    Jogador[] vetorJogador = new Jogador[2];
    Jogador daHora;
    Random aleatorio = new Random();

    public void iniciar() {

        cadastrarJogador();
        System.out.println("==== Início do Jogo: Todas Posições Vazias ===");
        desenharCartela(cartela);

        while (continuaJogo) {

            try {
                if (validarJogada(cartela, realizarJogada(leia, escolherJogador()))) {
                    System.out.println("Jogada realizada com sucesso!");
                } else {
                    System.out.println("Posição ocupada, tente novamente!");
                    while (validarJogada(cartela, realizarJogada(leia, daHora)) == false) {
                        // System.out.println("Jogada realizada com sucesso!");
                    }
                    System.out.println("Jogada realizada com sucesso!");
                }
                n++;
                System.out.println("Jogada contabilizada: " + n);
                desenharCartela(cartela);
                verificarFim();

            } catch (Exception e) {
                System.out.println("Erro encontrado: " + e.getMessage());
            }
        }
    }

    public void desenharCartela(char[][] cartela) {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if ((cartela[i][k]) == 0) {
                    cartela[i][k] = '_';
                    System.out.print("|" + cartela[i][k] + "|");
                } else {
                    System.out.print("|" + cartela[i][k] + "|");
                }
                // System.out.println("");
            }
            System.out.println("");
        }
    }

    public Jogador[] cadastrarJogador() {

        System.out.println("Digite o nome do jogador humano: #8-)");
        nome = leia.nextLine();
        Humano h = new Humano(nome, 'X');
        vetorJogador[0] = h;

        System.out.println("Digite o nome do jogador Computador: {:-] ");
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
                        System.out.println("\n<< Jogador humano já jogou, indique jogador computador! >>");
                        // daHora = vetorJogador[1];
                        // return daHora;

                    } else if (jogador == 2 && daHora.getSimbolo() == 'O') {
                        System.out.println("\n<< Jogador computador já jogou, indique o jogador humano! >>");
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
                        System.out.println("\n *** Jogador escolhido válido, " + daHora.getNome() + " prossiga! ***");
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

    public boolean validarJogada(char[][] cartela, int posicao[]) {

        if (cartela[posicao[0]][posicao[1]] == '_') {
            cartela[posicao[0]][posicao[1]] = daHora.getSimbolo();
            System.out.println("\n *** Jogada Inicial, marcação na linha" + posicao[0] + " ***");
            return true;
        } else {
            int contaLinha = 0;
            for (int x = 0; x < 10; x++) {
                if (cartela[x][posicao[1]] != '_') {
                    contaLinha++;
                }
            }
            if (contaLinha < 10) {
                posicao[0] = contaLinha;
                cartela[posicao[0]][posicao[1]] = daHora.getSimbolo();
                System.out.println("\n << Linha ocupada, próxima linha é: " + posicao[0] + " >>");
                return true;
            } else {
                System.out.println("\n << Número máximo de linhas atingido, tente outra coluna. >>");
                return false;
            }
        }

    }

    public int[] realizarJogada(Scanner leia, Jogador daHora) {

        int[] posicao = new int[9];
        System.out.println("\n *** Jogo da velha 10x10: Jogador << " + daHora.getNome() +
                " >> escolha uma posição de 0 a 9 para jogar:");

        // System.out.println("Digite a linha");

        posicao[0] = 0;

        if (daHora.getSimbolo() == 'O') {
            posicao[1] = aleatorio.nextInt(maxPosicao);
            System.out.println("\n -> Tentativas: coluna" + posicao[1]);
        } else if (daHora.getSimbolo() == 'X') {
            System.out.println("\n *** Digite a coluna desejada");
            posicao[1] = leia.nextInt();
        }

        // implementar um loop para varrer as linhas e descobrir em que linha parou
        // naquela coluna

        cont++;

        return posicao;
    }

    public void verificarFim() {

        for (int i = 0; i < 10; i++) {
            // for (int v = 0; v < 10; v++) {
            if (cartela[0][i] != '_' || cartela[1][i] != '_' || cartela[2][i] != '_' || cartela[3][i] != '_' ||
                    cartela[4][i] != '_' || cartela[5][i] != '_' || cartela[6][i] != '_' ||
                    cartela[7][i] != '_' || cartela[8][i] != '_' || cartela[9][i] != '_') {

                boolean linha = cartela[i][0] == cartela[i][1] && cartela[i][0] == cartela[i][2]
                        && cartela[i][0] == cartela[i][3] && cartela[i][0] == cartela[i][4]
                        && cartela[i][0] != '_' ||

                        cartela[i][1] == cartela[i][2] && cartela[i][1] == cartela[i][3]
                                && cartela[i][1] == cartela[i][4] && cartela[i][1] == cartela[i][5]
                                && cartela[i][1] != '_'
                        ||

                        cartela[i][2] == cartela[i][3] && cartela[i][2] == cartela[i][4]
                                && cartela[i][2] == cartela[i][5] && cartela[i][2] == cartela[i][6]
                                && cartela[i][2] != '_'
                        ||

                        cartela[i][3] == cartela[i][4] && cartela[i][3] == cartela[i][5]
                                && cartela[i][3] == cartela[i][6] && cartela[i][3] == cartela[i][7]
                                && cartela[i][3] != '_'
                        ||

                        cartela[i][4] == cartela[i][5] && cartela[i][4] == cartela[i][6]
                                && cartela[i][4] == cartela[i][7] && cartela[i][4] == cartela[i][8]
                                && cartela[i][4] != '_'
                        ||

                        cartela[i][5] == cartela[i][6] && cartela[i][5] == cartela[i][7]
                                && cartela[i][5] == cartela[i][8] && cartela[i][5] == cartela[i][9]
                                && cartela[i][5] != '_';

                boolean coluna = cartela[0][i] == cartela[1][i] && cartela[0][i] == cartela[2][i]
                        && cartela[0][i] == cartela[3][i] && cartela[0][i] == cartela[4][i]
                        && cartela[0][i] != '_' ||

                        cartela[1][i] == cartela[2][i] && cartela[1][i] == cartela[3][i]
                                && cartela[1][i] == cartela[4][i] && cartela[1][i] == cartela[5][i]
                                && cartela[1][i] != '_'
                        ||

                        cartela[2][i] == cartela[3][i] && cartela[2][i] == cartela[4][i]
                                && cartela[2][i] == cartela[5][i] && cartela[2][i] == cartela[6][i]
                                && cartela[2][i] != '_'
                        ||

                        cartela[3][i] == cartela[4][i] && cartela[3][i] == cartela[5][i]
                                && cartela[3][i] == cartela[6][i] && cartela[3][i] == cartela[7][i]
                                && cartela[3][i] != '_'
                        ||

                        cartela[4][i] == cartela[5][i] && cartela[4][i] == cartela[6][i]
                                && cartela[4][i] == cartela[7][i] && cartela[4][i] == cartela[8][i]
                                && cartela[4][i] != '_'
                        ||

                        cartela[5][i] == cartela[6][i] && cartela[5][i] == cartela[7][i]
                                && cartela[5][i] == cartela[8][i] && cartela[5][i] == cartela[9][i]
                                && cartela[5][i] != '_';

                //if ( i < 5 ){
                boolean diagonalPrincipal = cartela[i][i] == cartela[Math.abs(5-i)][Math.abs(5-i)] 
                        && cartela[i][i] == cartela[Math.abs(4-i)][Math.abs(4-i)] 
                        && cartela[i][i] == cartela[Math.abs(3-i)][Math.abs(3-i)] 
                        && cartela[i][i] == cartela[Math.abs(2-i)][Math.abs(2-i)] 
                        && cartela[i][i] != '_';
                //}else{
                //    boolean diagonalPrincipal = cartela[i][i] == cartela[9-i][9-i] && cartela[i][i] == cartela[8-i][8-i]
                //        && cartela[i][i] == cartela[7-i][7-i] && cartela[i][i] == cartela[6-i][6-i] && cartela[i][i] != '_';
                //}
                boolean diagonalSecundaria = cartela[2][0] == cartela[1][1] &&
                        cartela[0][2] == cartela[1][1] && cartela[9 - i][i] != '_';

                if (linha) {
                    for (int y = 0; y < 9; y++) {
                    if (cartela[i][y] == 'O' && cartela[i][Math.abs(5-y)] == 'O') {
                        System.out.println("\n **** Vencedor simbolo " + cartela[i][y] + " por linha é Computador");
                        continuaJogo = false;
                        break;
                    } else if (cartela[i][y] == 'X' && cartela[i][Math.abs(5-y)] == 'X') {
                        System.out.println("\n **** Vencedor símbolo " + cartela[i][y] + " por linha é Humano");
                        continuaJogo = false;
                        break;
                    }
                    }
                } else if (coluna) {
                    for (int y = 0; y < 9; y++) {
                        if (cartela[y][i] == 'O' && cartela[Math.abs(5-y)][i] == 'O') {
                            System.out.println("\n **** Vencedor simbolo " + cartela[y][i]+" por coluna é Computador");
                            continuaJogo = false;
                            break;
                        } else if (cartela[y][i] == 'X' && cartela[Math.abs(5-y)][i] == 'X') {
                            System.out.println("\n **** Vencedor simbolo " + cartela[y][i]+" por coluna é Humano");
                            continuaJogo = false;
                            break;
                        }
                    }
                } else if (diagonalPrincipal) {
                    if (cartela[0][0] == 'O') {
                        System.out.println("\n **** Vencedor diagonal p " + cartela[0][0] + " é Computador");
                        continuaJogo = false;
                        break;
                    } else if (cartela[0][0] == 'X') {
                        System.out.println("\n **** Vencedor diagonal p " + cartela[0][0] + " é Humano");
                        continuaJogo = false;
                        break;
                    }
                } else if (diagonalSecundaria) {
                    if (cartela[0][2] == 'O') {
                        System.out.println("\n **** Vencedor diagonal s " + cartela[0][2] + " é Computador");
                        continuaJogo = false;
                        break;
                    } else if (cartela[0][2] == 'X') {
                        System.out.println("\n **** Vencedor diagonal s " + cartela[0][2] + " é Humano");
                        continuaJogo = false;
                        break;
                    }
                } else {
                    if (n == 99 && continuaJogo) {
                        System.out.println("\n **** Jogo empatado");
                        continuaJogo = false;
                    }
                }

            }
            // }
        }
    }
}
