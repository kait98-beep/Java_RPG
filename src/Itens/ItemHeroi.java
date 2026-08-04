package Itens;

import java.util.ArrayList;

public abstract class ItemHeroi {
    private String nome;

    //Em moedas de Ouro (Final porque o preço original não muda)
    private final int preco;

    //Guarda o tipo de herois que sabem usar este item
    private ArrayList<String> heroisPermitidos;

    //Constructor
    public ItemHeroi(String nome, int preco){
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = new ArrayList<>();
    }

    //Mostrar informação dos Items do Heroi na consola
    public void mostrarDetalhes(){
        System.out.println("Nome: " + this.nome + " | Preço: " + this.preco + " | Herois Permitidos: " + this.heroisPermitidos);
    }

    //Getters
    public String getNome(){
        return this.nome;
    }

    public int getPreco(){
        return this.preco;
    }

    public ArrayList<String> getHeroisPermitidos(){
        return this.heroisPermitidos;
    }

}
