package be.edu.iftm.JogodaVelha;

import java.util.Scanner;

public class TesteJogo {
    public static void main(String[] args) {
        
        Jogovelha j = new Jogovelha();
        Velhaplus p = new Velhaplus();

        try (Scanner leia = new Scanner(System.in)) {
            System.out.println("Escolha qual jogo deseja jogar:");
            System.out.println("Digite 1 = Jogo da Velha ou 2 = Jogo da Velha Plus");
            int escolha = leia.nextInt();
            
            if (escolha == 1){
                j.iniciar();
            }else if(escolha ==2){
                p.iniciar();
            }else{
                System.out.println("Escolha inválida; programa encerrado");
            }
        }

    
        /*try {
            j.realizarJogada();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro de estouro de limite!" + e.getMessage());
        }*/

        //j.realizarJogada();
    
    }
}
