package Itens;

public class Pocao extends Consumivel{

    private int curar;
    private int aumentoForca;

    //Constructor
    public Pocao(String nome, int preco, int curar, int aumentoForca) {
        super(nome, preco);

        this.curar = curar;
        this.aumentoForca = aumentoForca;
    }

    //Mostrar informação da poção na consola
    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.print(" | Curar: " + this.curar + " | Aumentar a Força: " + this.aumentoForca);
    }

    //Getters
    public int getCurar() {
        return curar;
    }

    public int getAumentoForca() {
        return aumentoForca;
    }
}
