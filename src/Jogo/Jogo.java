package Jogo;

import Entidades.*;
import Itens.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

    Scanner scan = new Scanner(System.in);

    //Está limitada a classe Jogo
    public static boolean jogadorPerdeu = false;

    //Ir buscar o inimigo pelo nome e não pelo index, através do key (key - value)
    HashMap<String, NPC> listInimigos = new HashMap<>();

    //Instanciar o vendedor
    Vendedor vendedor = new Vendedor();

    int pontosCriacao = 0, ouro = 0, vida = 0, forca = 0;

    //Criação da Personagem
    public Heroi criarPersonagem() {

        //O tipo de Heroi
        System.out.println("Qual o Heroi que pretende escolher?");
        System.out.println("1 - Cavaleiro");
        System.out.println("2 - Feiticeiro");
        System.out.println("3 - Arqueiro");

        int heroiEscolhido = scan.nextInt();

        if (heroiEscolhido == 1) {
            dificuldadeEPontos();
            return new Cavaleiro("Carlos", vida, vida, forca, 1, ouro);
        } else if (heroiEscolhido == 2) {
            dificuldadeEPontos();
            return new Feiticeiro("Carlos", vida, vida, forca, 1, ouro);
        } else if (heroiEscolhido == 3) {
            dificuldadeEPontos();
            return new Arqueiro("Carlos", vida, vida, forca, 1, ouro);
        } else {
            System.out.println("Número escolhido não existe!");
            System.out.println("Criado o Cavaleiro por defeito");
            dificuldadeEPontos();
            return new Cavaleiro("Carlos", vida, vida, forca, 1, ouro);
        }
    }

    public void aventuraDosGelados(Heroi heroiEscolhido) {
        int contador = 1;

        //Criação dos inimigos
        inimigosCriados();

        //Produtos na loja do Vendedor
        consumiveisNaLoja();

        //Descrição inicial do objetivo do jogo
        System.out.println("Há vários anos, num universo paralelo, existia o Planeta dos Gelados. Um mundo repleto de \n" +
                "sabores e cones de diferentes figuras. Infelizmente, após a chegada dos inimigos, os gelados \n" +
                "começaram a derreter, porque eles eram incapazes de controlar a temperatura exageradamente grande que \n" +
                "lhes teria sido concebido. E além disso, comiam os gelados todos e não restava nada para ninguém. \n" +
                "Foi então criado os herois dos gelados para colocar um travão nestes inimigos e restaurar a frescura \n" +
                "e a delícia do Planeta dos Gelados! \n");

        while (!jogadorPerdeu) {

            /**
             *
             * Vendedor
             *
             */

            //Imprimir 10 produtos aleatórios dos 14 existentes
            ArrayList<ItemHeroi> list = vendedor.imprimirLoja();

            //Compra dos produtos
            vendedor.processoDeVenda(heroiEscolhido, list);

            switch (contador) {
                case 1:

                    /**
                     *
                     * 1ª Tomada de Decisão
                     *
                     */

                    System.out.println("Quando começa a viagem, tem 2 caminhos possiveis: ");
                    System.out.println("1 - Vale dos Gelados");
                    System.out.println("2 - Montanha das Bolachas");

                    int escolhaCaminho1 = scan.nextInt();

                    //Primeira luta
                    if (escolhaCaminho1 == 1) {
                        heroiEscolhido.combateAtacar(listInimigos.get("dragao"));
                    } else if (escolhaCaminho1 == 2) {
                        heroiEscolhido.combateAtacar(listInimigos.get("ladraoFogo"));
                    } else {
                        System.out.println("Esse caminho não existe!");
                    }
                    break;

                case 2:

                    /**
                     *
                     * 2ª Tomada de Decisão
                     *
                     */

                    //Segunda luta
                    System.out.println("Após a derrota do inimigo, voltamos a encontrar um entroncamento: ");
                    System.out.println("1 - Casa de Gelo");
                    System.out.println("2 - Monte do frio");

                    int escolhaCaminho2 = scan.nextInt();

                    Random rd = new Random();

                    if (escolhaCaminho2 == 1) {
                        System.out.println("Pelos caminhos, encontrou uma casa que parece congelada. Dentro das paredes, " +
                                "parece que há algo dourado e que brilha. Optas por tentar partir com a tua arma");

                        //50% chance de encontrar ouro
                        if(rd.nextBoolean()){
                            //if true (1)
                            System.out.println("Ao partir, encontrou ouro! Adicionado 40 moedas");
                            heroiEscolhido.setOuro(heroiEscolhido.getOuro() + 40);

                        } else {
                            //if false (0)
                            System.out.println("Ao partir, descobriu que era só trigo para alimentar os animais que lá " +
                                    "viviam anteriormente.");
                        }

                    } else if (escolhaCaminho2 == 2){
                        int randomNumber = rd.nextInt(50000);
                        if(randomNumber > 2000 && randomNumber < 4000){
                            System.out.println("Ao passar o monte, parece-lhe ver uma toca perdida no meio da neve. " +
                                    "Por curiosidade, opta por aproximar-se e ver se há algo dentro dessa toca. Ao" +
                                    "colocar a mão, é mordido por um animal e perde 20 de vida");
                            heroiEscolhido.setHp(heroiEscolhido.getHp() - 20);

                            if(heroiEscolhido.getHp() < 0){
                                System.out.println("Morreu derivado ao dano provocado pelo animal!");
                                jogadorPerdeu = true;
                            }
                        } else {
                            System.out.println("Ao passar o monte, parece-lhe ver uma toca numa arvore. " +
                                    "Por curiosidade, opta por aproximar-se e ver se há algo dentro dessa toca." +
                                    "Procura e parece-lhe encontrar um saco, que na realidade são só folhas secas.");
                        }

                    } else {
                        System.out.println("Esse caminho não existe!");
                    }
                    break;

                case 3:

                    /**
                     *
                     * 3ª Tomada de Decisão
                     *
                     */
                    //Terceira luta
                    System.out.println("Após um descanso merecido, o caminho fica curto e começamos a ficar só com uma opção: ");
                    System.out.println("1 - Caverna da Baunilha");

                    int escolhaCaminho3 = scan.nextInt();

                    if (escolhaCaminho3 == 1) {
                        heroiEscolhido.combateAtacar(listInimigos.get("slimeFogo"));
                    } else {
                        System.out.println("Esse caminho não existe!");
                    }
                    break;

                case 4:

                    /**
                     *
                     * 4ª Tomada de Decisão
                     *
                     */
                    //Quarta luta
                    System.out.println("O inimigo, sem grande problema de derrota, permite o seguimento da aventura: ");
                    System.out.println("1 - Buraco do Morango");

                    int escolhaCaminho4 = scan.nextInt();

                    if (escolhaCaminho4 == 1) {
                        heroiEscolhido.combateAtacar(listInimigos.get("formigaFogo"));
                    } else {
                        System.out.println("Esse caminho não existe!");
                    }
                    break;

                case 5:

                    /**
                     *
                     * 5ª Tomada de Decisão
                     *
                     */
                    //Quinta luta
                    System.out.println("Chegando ao fim desta caverna, e com o último inimigo por derrotar, é agora," +
                            "finalmente, que os gelados ficam a salvo: ");
                    System.out.println("1 - Cova do Inferno");
                    System.out.println("2 - Cova do gelo");

                    int escolhaCaminho5 = scan.nextInt();

                    if (escolhaCaminho5 == 1) {
                        heroiEscolhido.combateAtacar(listInimigos.get("jocaFire"));
                    } else if (escolhaCaminho5 == 2) {
                        heroiEscolhido.combateAtacar(listInimigos.get("jocaIce"));
                    } else {
                        System.out.println("Esse caminho não existe!");
                    }
                    break;

                default:
                    contador = 99;
                    break;
            }

            if (!jogadorPerdeu) {
                /**
                 *
                 * Inventário --> Poções
                 *
                 */

                //Após a luta, a possibilidade de utilizar uma poção
                System.out.println("Quer utilizar alguma poção do inventário? \n");

                heroiEscolhido.usarPocao();

                if (contador != 99) {
                    contador++;
                } else {
                    System.out.println("Ganhou!");
                    break;
                }
            }
        }
    }

    public void dificuldadeEPontos() {
        //A dificuldade do jogo
        System.out.println("Qual a dificuldade que pretende?");
        System.out.println("1 - Fácil");
        System.out.println("2 - Difícil");

        boolean dificuldadeValida = false;
        int dificuldadeEscolhida = scan.nextInt();

        do{
            switch (dificuldadeEscolhida) {
                case 1:
                    pontosCriacao = 300;
                    ouro = 20;
                    System.out.println("Tem " + pontosCriacao + " para utilizar");
                    System.out.println("Tem " + ouro + " de ouro \n");
                    dificuldadeValida = true;
                    break;
                case 2:
                    pontosCriacao = 220;
                    ouro = 15;
                    System.out.println("Tem " + pontosCriacao + " para utilizar");
                    System.out.println("Tem " + ouro + " de ouro \n");
                    dificuldadeValida = true;
                    break;
                default:
                    System.out.println("Introduzido numero errado! Introduza outro");
                    dificuldadeEscolhida = scan.nextInt();
                    break;
            }
        }
        //Valida se a condição é true
        while(!dificuldadeValida);

        //Distribuição dos pontos entre vida e força
        System.out.println("Distribuição dos pontos de criação: ");
        System.out.println("1 - 1x Vida --> custa 1 ponto de criação");
        System.out.println("2 - 5x Vida --> Custa 5 pontos de criação");
        System.out.println("3 - 10x Vida --> Custa 10 pontos de criação \n");

        System.out.println("4 - 1x Força --> custa 5 pontos de criação");
        System.out.println("5 - 5x Forca --> Custa 25 pontos de criação");
        System.out.println("6 - 10x Força --> Custa 50 pontos de criação \n");

        while (pontosCriacao > 0) {

            int escolhaPontos = scan.nextInt();
            int vidaPontos = 0;
            int pontoTirar = 0;
            int forcaPontos = 0;

            switch(escolhaPontos){

                case 1:
                    vidaPontos = 1;
                    pontoTirar = 1;
                    break;
                case 2:
                    vidaPontos = 5;
                    pontoTirar = 5;
                    break;
                case 3:
                    vidaPontos = 10;
                    pontoTirar = 10;
                    break;
                case 4:
                    forcaPontos = 1;
                    pontoTirar = 5;
                    break;
                case 5:
                    forcaPontos = 5;
                    pontoTirar = 25;
                    break;
                case 6:
                    forcaPontos = 10;
                    pontoTirar = 50;
                    break;
                default:
                    System.out.println("Numero escolhido não existe!");
            }

            if (pontosCriacao >= pontoTirar){
                vida += vidaPontos;
                forca += forcaPontos;
                pontosCriacao -= pontoTirar;

                System.out.println("Sobram: " + pontosCriacao);

            } else {
                System.out.println("Valor devolvido negativo! Introduza outro valor possível");
                System.out.println("Sobram: " + pontosCriacao);
            }
        }
    }

    public void consumiveisNaLoja() {
        //Criação dos itens
        Pocao curarMinima = new Pocao("Minimo de cura", 5, 5, 0);
        Pocao curarMedium = new Pocao("Medium de cura", 10, 15, 0);
        Pocao curarMaximus = new Pocao("Máximo de cura", 15, 25, 0);
        Pocao curarInfinito = new Pocao("Cura o máximo possível", 20, 40, 0);

        Pocao forcaMinima = new Pocao("Aumento minimo da Força", 5, 0, 10);
        Pocao forcaMedium = new Pocao("Aumento medium de força", 10, 0, 15);
        Pocao forcaMaximus = new Pocao("Aumento máximo da força", 15, 0, 20);
        Pocao forcaInfinita = new Pocao("Aumento de força máximo possível", 20, 0, 40);

        ConsumivelCombate pernaDePau = new ConsumivelCombate("Perna de Pau", 9, 5);
        ConsumivelCombate magnumDeAtaque = new ConsumivelCombate("Magnumitude", 10, 6);
        ConsumivelCombate oreoSandwich = new ConsumivelCombate("Sandwich de Oreo", 20, 15);

        ArmaPrincipal arcoEFlecha = new ArmaPrincipal("Arco e Flecha", 18, 17, 20);
        ArmaPrincipal espadaFogo = new ArmaPrincipal("Espada de Fogo", 20, 20, 26);
        ArmaPrincipal bastaoFerro = new ArmaPrincipal("Bastão de Ferro", 20, 17, 20);

        /*Especificar os herois.
          Os não especificados, significa que qualquer heroi pode utilizar*/
        arcoEFlecha.getHeroisPermitidos().add("Arqueiro");
        espadaFogo.getHeroisPermitidos().add("Cavaleiro");
        bastaoFerro.getHeroisPermitidos().add("Feiticeiro");

        //Adicionar as poções à loja do vendedor
        vendedor.getLoja().add(curarMinima);
        vendedor.getLoja().add(curarMedium);
        vendedor.getLoja().add(curarMaximus);
        vendedor.getLoja().add(curarInfinito);
        vendedor.getLoja().add(forcaMinima);
        vendedor.getLoja().add(forcaMedium);
        vendedor.getLoja().add(forcaMaximus);
        vendedor.getLoja().add(forcaInfinita);
        vendedor.getLoja().add(pernaDePau);
        vendedor.getLoja().add(magnumDeAtaque);
        vendedor.getLoja().add(oreoSandwich);
        vendedor.getLoja().add(arcoEFlecha);
        vendedor.getLoja().add(espadaFogo);
        vendedor.getLoja().add(bastaoFerro);
    }

    public HashMap<String, NPC> inimigosCriados() {

        //Instanciar os inimigos
        NPC jocaIce = new NPC("Gnomo do Gelo", 50, 50, 50, 100);
        NPC jocaFire = new NPC("Gnomo do Inferno", 50, 50, 50, 100);
        NPC dragao = new NPC("Dragão", 100, 100, 28, 40);
        NPC ladraoFogo = new NPC("Ladrão de Fogo", 25, 25, 10, 200);
        NPC slimeFogo = new NPC("Slime de Fogo", 10, 10, 5, 5);
        NPC formigaFogo = new NPC("Formiga de Fogo", 5, 5, 2, 0);

        //Colocar dentro do HashMap
        listInimigos.put("jocaIce", jocaIce);
        listInimigos.put("jocaFire", jocaFire);
        listInimigos.put("dragao", dragao);
        listInimigos.put("ladraoFogo", ladraoFogo);
        listInimigos.put("slimeFogo", slimeFogo);
        listInimigos.put("formigaFogo", formigaFogo);

        return listInimigos;
    }
}
