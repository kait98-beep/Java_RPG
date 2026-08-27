package Jogo;

import Entidades.Heroi;

import java.util.Scanner;

import static Jogo.Jogo.jogadorPerdeu;
//testing github
public class Main {

    /*A Variável heroi é static porque pertence à class Main e não às restantes
      É chamada noutras classes, mas só é utilizada no main*/
    static Heroi heroi = null;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String opcaoDerrota = "";

        while (!opcaoDerrota.equalsIgnoreCase("N")) {
            iniciarJogo(false);

            if (jogadorPerdeu) {
                System.out.println("Fim do jogo");
                System.out.println("Deseja jogar novamente? Escreva S para sim, N para não");

                opcaoDerrota = scan.nextLine();

                if (opcaoDerrota.equalsIgnoreCase("S")) {
                    System.out.println("Se sim: ");
                    System.out.println("1 - Quer jogar com a mesma personagem?");
                    System.out.println("2 - Quer criar uma nova personagem?");

                    int opcaoContinuar = scan.nextInt();
                    //consome o \n deixado pelo nextInt()
                    scan.nextLine();

                    if (opcaoContinuar == 1) {
                        iniciarJogo(true);
                    } else {
                        iniciarJogo(false);
                    }

                } else if (opcaoDerrota.equalsIgnoreCase("N")) {
                    System.out.println("Saiu do jogo");
                }
            }
        }
    }

    //Cria a personagem e inicia um novo jogo ou inicia só um novo jogo, dependendo da decisão anterior do utilizador
    public static void iniciarJogo(boolean novoJogo){
        jogadorPerdeu = false;

        Jogo jogo = new Jogo();

        if (!novoJogo){
            heroi = jogo.criarPersonagem();
        }

        if(heroi != null){
            jogo.aventuraDosGelados(heroi);
        }
    }
}