package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ConsumivelCombate;
import Itens.Pocao;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private ArmaPrincipal armaPrincipal;
    private ArrayList<Consumivel> inventario;
    private final int maxHPInicial;
    private final int forcaInicial;
    private final int ouroInicial;

    Scanner scan = new Scanner(System.in);
    boolean ataqueEspecialUsado = false;

    //Constructor
    public Heroi(String nome, int maxHP, int hp, int forca, int nivel, int ouro) {
        super(nome, maxHP, hp, forca);

        this.nivel = nivel;
        this.ouro = ouro;
        this.inventario = new ArrayList<>();

        this.maxHPInicial = maxHP;
        this.forcaInicial = forca;
        this.ouroInicial = ouro;
    }

    //Stats inicialmente criados, para o jogar poder manter stats se quiser manter a pesonagem criada inicialmente
    public void reiniciarParaEstadoInicial(){
        setMaxHP(maxHPInicial);
        setHp(maxHPInicial);
        setForca(forcaInicial);
        setOuro(ouroInicial);
        setNivel(1);
    }

    /*Vai ter uma implementação diferente em cada subclasse
      Finalidade: confrontar o herói com um NPC, numa luta, até que um fique sem vida*/
    public abstract void combateAtacar(NPC npc);


    /*Imprime o inventário das poções e o utilizador escolhe aquela que quer utilizar;
      Aumenta a vida ou força, do momento, dependendo da escolha. Não aumenta o MaxHp*/
    public void usarPocao() {

        boolean temPocao = false;

        if (!inventario.isEmpty()) {

            System.out.println("Inventário de poções do Herói:");

            for (Consumivel consumivel : this.inventario) {
                if (consumivel instanceof Pocao) {
                    System.out.println(consumivel.getNome());
                    temPocao = true;
                }
            }

            if (!temPocao) {
                System.out.println("Não tem poções! \n");
            }

            if (temPocao) {

                System.out.println("Qual a ação que quer?");
                System.out.println("1 - Curar");
                System.out.println("2 - Aumentar a Força");
                System.out.println("3 - Fechar Inventário");

                int escolha = scan.nextInt();
                //Consome o \n deixado pelo scan.nextInt
                scan.nextLine();

            /*Para validar a escolha antes de entrar no for. Sem este while, caso alguem se engane logo ao início, há
              a possibilidade de se perder alguma poção*/
                while (escolha != 1 && escolha != 2 && escolha != 3) {
                    System.out.println("Introduziu um numero errado! Introduza um numero de 1 a 3");
                    escolha = scan.nextInt();

                    scan.nextLine();
                }

                while (escolha != 3) {

                    ArrayList<Consumivel> paraRemover = new ArrayList<>();

                    for (Consumivel pocao : this.inventario) {

                        boolean removerPocao = false;

                        if (pocao instanceof Pocao p) {
                            switch (escolha) {
                                case 1:
                                    int maxHp = getMaxHP();
                                    int hp = getHp();
                                    int curaPossivel = maxHp - hp;

                                    if (p.getCurar() > 0) {
                                        //Se a quantidade que a poção curar for inferior à quantidade que podemos curar, simplesmente cura
                                        if (p.getCurar() <= curaPossivel) {
                                            setHp(getHp() + p.getCurar());
                                            System.out.println("Foi curado " + p.getCurar() + " da vida!");
                                            removerPocao = true;

                                        } else {

                                            /*Se a quantidade que a poção curar for superior, mostra na consola a quantidade
                                            a mais e pergunta se o utilizador quer mesmo utilizar a poção*/
                                            int sobra = p.getCurar() - curaPossivel;
                                            System.out.println("Queres mesmo utilizar a poção? Vai sobrar: " + sobra);
                                            System.out.println("Se sim, escreve S, se não, introduza outro caracter diferente.");

                                            String opcaoPocao = scan.nextLine();

                                            if (opcaoPocao.equalsIgnoreCase("S")) {
                                                setHp(maxHp);
                                                System.out.println("Curou a vida toda! Sobrou: " + sobra);
                                                removerPocao = true;
                                            }
                                        }
                                    } else {
                                        System.out.println("A poção não é de cura! Escolhe outra opção");
                                    }
                                    break;

                                case 2:
                                    if (p.getAumentoForca() > 0) {
                                        setForca(getForca() + p.getAumentoForca());
                                        System.out.println("Foi aumentado " + p.getAumentoForca() + " de força!");
                                        removerPocao = true;
                                    } else {
                                        System.out.println("A poção não é de força! Escolhe outra opção");
                                    }
                                    break;
                            }
                        }

                        //Introduzir as poções que quero remover num array
                        if (removerPocao) {
                            paraRemover.add(pocao);
                        }
                    }

                    //Eliminar essas poções do meu inventário
                    this.inventario.removeAll(paraRemover);

                    //Limpar o arraylist paraRemover
                    paraRemover.clear();

                    System.out.println("Qual a ação que quer?");
                    System.out.println("1 - Curar");
                    System.out.println("2 - Aumentar a Força");
                    System.out.println("3 - Fechar Inventário");

                    escolha = scan.nextInt();
                    scan.nextLine();

                    while (escolha != 1 && escolha != 2 && escolha != 3) {
                        System.out.println("Introduziu um numero errado! Introduza um numero de 1 a 3");
                        escolha = scan.nextInt();
                        scan.nextLine();
                    }
                }
            }
            System.out.println("Inventário fechado \n");

        } else {
            System.out.println("Inventário vazio! \n");
        }
    }

    //O Ataque do Heroi
    public void heroiAtacar(NPC npc) {
        int vidaInimigo = npc.getHp();

        int menu = 0;

        System.out.println("Qual o tipo de ataque que pretende?: ");
        System.out.println("1: Ataque Normal");
        System.out.println("2: Ataque Especial");
        System.out.println("3: Ataque Consumível");

        int armaEscolhida = scan.nextInt();
        while (menu == 0) {
            switch (armaEscolhida) {
                case 1:
                    //Random para dar um crítico (+5 dano)
                    Random rd = new Random();
                    int randomNumber = rd.nextInt(100);
                    int bonusAtaque = 0;

                    if (randomNumber < 10) {
                        bonusAtaque = 5;
                    }
                    vidaInimigo -= (getForca() + getArmaPrincipal().getAtaque() + bonusAtaque);
                    npc.setHp(vidaInimigo);
                    menu = 1;

                    break;

                case 2:
                    if (ataqueEspecialUsado) {
                        System.out.println("Ataque especial já utilizado neste combate, escolhe outra opção");
                        armaEscolhida = scan.nextInt();
                    } else {
                        vidaInimigo -= (getForca() + getArmaPrincipal().getAtaqueEspecial());
                        npc.setHp(vidaInimigo);
                        ataqueEspecialUsado = true;
                        menu = 1;
                    }
                    break;

                case 3:
                    System.out.println("Lista de Consumíveis de Combate: ");

                    ArrayList<ConsumivelCombate> consumiveisCombate = new ArrayList<>();

                    for (Consumivel consumivel : getInventario()) {
                        if (consumivel instanceof ConsumivelCombate cc) {
                            consumiveisCombate.add(cc);
                        }
                    }

                    if (!consumiveisCombate.isEmpty()) {
                        int i = 1;
                        for (ConsumivelCombate consumivel : consumiveisCombate) {
                            System.out.println(i + " - " + consumivel.getNome());
                            i++;
                        }
                        System.out.println("Qual dos consumíveis quer usar? Escolhe o numero associado");

                        int consumivelEscolhido = scan.nextInt();

                        if (consumivelEscolhido >= 1 && consumivelEscolhido <= consumiveisCombate.size()) {

                            ConsumivelCombate escolhido = consumiveisCombate.get(consumivelEscolhido - 1);

                            vidaInimigo -= (getForca() + escolhido.getAtaqueInstantaneo());
                            npc.setHp(vidaInimigo);
                            getInventario().remove(escolhido);
                            System.out.println("Consumivel utilizado e removido do inventário");
                            menu = 1;

                        } else {
                            System.out.println("Escolha inválida");
                            armaEscolhida = scan.nextInt();
                        }
                    } else {
                        System.out.println("Não tem consumíveis de combate!");
                        armaEscolhida = scan.nextInt();
                    }
                    break;
                default:
                    System.out.println("Numero escolhido inválido");
                    armaEscolhida = scan.nextInt();
                    break;
            }
        }
    }

    //Dependendo de quem ganha (vida <= 0), vai haver um resultado diferente
    public boolean loot(NPC npc) {

        if (npc.getHp() <= 0) {
            setNivel(getNivel() + 1);
            setMaxHP(getMaxHP() + 10);
            setHp(getMaxHP());
            setForca(getForca() + 1);
            setOuro(getOuro() + npc.getOuro());
            npc.setOuro(0);
            ataqueEspecialUsado = false;

            //Definir a variável do ataque especial false
            System.out.println("Ganhou o combate! Stats: ");
            System.out.println("Nivel: " + getNivel());
            System.out.println("Max Hp: " + getMaxHP());
            System.out.println("Hp: " + getHp());
            System.out.println("Força: " + getForca());
            System.out.println("Ouro: " + getOuro() + "\n");
        } else {
            System.out.println("O Jogador morreu, o NPC ganhou");
            return false;
        }
        return true;
    }

    //Getters
    public int getNivel() {
        return this.nivel;
    }

    public int getOuro() {
        return this.ouro;
    }

    public ArmaPrincipal getArmaPrincipal() {
        return armaPrincipal;
    }

    public ArrayList<Consumivel> getInventario() {
        return inventario;
    }

    //Setters
    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }
}