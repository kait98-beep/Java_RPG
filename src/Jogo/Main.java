package Jogo;

import Entidades.Heroi;

import java.util.Scanner;

import static Jogo.Jogo.jogadorPerdeu;

public class Main {

    /*A Variável heroi é static porque pertence à class Main e não às restantes
      É chamada noutras classes, mas só é utilizada no main*/
    static Heroi heroi = null;


    static void main() {

        Scanner scan = new Scanner(System.in);

        iniciarJogo(false);

        boolean continuarAJogar = true;

        while (continuarAJogar) {

            if (jogadorPerdeu) {
                System.out.println("Fim do jogo");
                System.out.println("Deseja jogar novamente? Escreva S para sim, N para não");

                String opcaoDerrota = scan.nextLine();

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

                } else {
                    System.out.println("Saiu do jogo");
                    continuarAJogar = false;
                }
            } else {
                //Jogador ganhou
                continuarAJogar = false;
            }
        }
    }

    //Cria a personagem e inicia um novo jogo ou inicia só um novo jogo, dependendo da decisão anterior do utilizador
    public static void iniciarJogo(boolean mesmaPersonagem){
        jogadorPerdeu = false;

        Jogo jogo = new Jogo();

        if (!mesmaPersonagem){
            heroi = jogo.criarPersonagem();
        } else if (heroi != null){
            heroi.reiniciarParaEstadoInicial();
        }

        if(heroi != null){
            jogo.aventuraDosGelados(heroi);
        }
    }
}