package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ItemHeroi;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Vendedor {

    Scanner scan = new Scanner(System.in);

    //Representa os itens que o Heroi poderá comprar durante o jogo (armas, consumiveis, poções)
    private ArrayList<ItemHeroi> loja;

    //Constructor
    public Vendedor() {
        this.loja = new ArrayList<>();
    }


    //Mostra 10 itens randoms que existam na loja e retorna essa listaTemporaria para poder ser utilizado no escolhaUtilizador()
    public ArrayList<ItemHeroi> imprimirLoja() {
        Random rd = new Random();
        ArrayList<ItemHeroi> listTemporaria = new ArrayList<>();

        if (!this.loja.isEmpty()) {

            System.out.println("Itens para venda na Loja: ");

            for (int i = 0; i < 10; i++) {
                int indexStock = rd.nextInt(loja.size());
                ItemHeroi itemRandom = loja.get(indexStock);

                listTemporaria.add(itemRandom);
                System.out.print(i + " ");
                itemRandom.mostrarDetalhes();
                System.out.println("\n");
            }
        } else {
            System.out.println("Não existe nada na loja!");
        }
        return listTemporaria;
    }

    //As escolhas do utilizador das 10 opções disponibilizadas pela listaTemporaria do imprimirLoja()
    public void processoDeVenda(Heroi heroi, ArrayList<ItemHeroi> list) {

        boolean cancelar = false;

        System.out.println("Qual item que vai comprar?");
        System.out.println("Introduza um numero de 0 a 9, sendo 0 o primeiro item e 9 o último apresentado: ");
        System.out.println("Quando quiser sair da loja, ou se não quiser comprar nada, pressione c");

        while (!cancelar) {

            //hasNextInt: se for um número, entra no nextInt(); se não for número, vai para o else
            if (scan.hasNextInt()) {
                int escolhaProduto = scan.nextInt();
                //Consome o \n deixado pelo nextInt()
                scan.nextLine();

                if (escolhaProduto < 0 || escolhaProduto >= list.size()) {
                    System.out.println("Número fora do intervalo. Introduza outro valor");
                    continue;
                }

                System.out.println("Tem a certeza que quer comprar esse artigo? ");
                System.out.println("Nome: " + list.get(escolhaProduto).getNome());
                System.out.println("S para sim e N para não");

                String escolhaOpcao = scan.nextLine();

                if (escolhaOpcao.equalsIgnoreCase("S")) {
                    vender(heroi, list.get(escolhaProduto));
                    list.remove(escolhaProduto);
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(i + " ");
                        list.get(i).mostrarDetalhes();
                        System.out.println("\n");
                    }

                } else if (escolhaOpcao.equalsIgnoreCase("N")) {
                    System.out.println("Introduza o próximo artigo que quer");

                } else if (escolhaOpcao.equalsIgnoreCase("C")) {
                    System.out.println("Saiu do Menu");
                    cancelar = true;

                } else {
                    System.out.println("Caracter inválido");
                }

            } else {
                String input = scan.nextLine();

                if (input.equalsIgnoreCase("C")) {
                    System.out.println("Saiu do Menu");
                    cancelar = true;
                } else {
                    System.out.println("Valor inválido. Introduza um número de 0 a 9 ou C para sair");
                }
            }
        }
    }

    //Verifica se a compra pode ser efetuada e atualiza o inventário e o ouro
    public void vender(Heroi heroi, ItemHeroi item) {
        if (heroi.getOuro() >= item.getPreco()) {
            boolean tirarOuro = false;

            if (item instanceof Consumivel && item.getHeroisPermitidos().isEmpty()) {
                heroi.getInventario().add((Consumivel) item);
                System.out.println("Adicionado item com sucesso");
                this.loja.remove(item);
                tirarOuro = true;

                //getClass() -- Retorna a classe do objeto
                //getSimpleName() -- Vai buscar o nome da classe
            } else if (item instanceof ArmaPrincipal && item != heroi.getArmaPrincipal() &&
                    item.getHeroisPermitidos().contains(heroi.getClass().getSimpleName())) {
                heroi.setArmaPrincipal((ArmaPrincipal) item);
                System.out.println("Trocado arma com sucesso");
                this.loja.remove(item);
                tirarOuro = true;

            } else {
                System.out.println("Já tem esta arma principal!");
            }

            if (tirarOuro) {
                heroi.setOuro(heroi.getOuro() - item.getPreco());
            }

        } else {
            System.out.println("Não tem dinheiro suficiente");
        }
        System.out.println("Ouro restante: " + heroi.getOuro());
    }

    //Getter
    public ArrayList<ItemHeroi> getLoja() {
        return loja;
    }
}
