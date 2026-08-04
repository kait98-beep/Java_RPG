package Itens;

public class ConsumivelCombate extends Consumivel{

    private int ataqueInstantaneo;

    //Constructor
    public ConsumivelCombate(String nome, int preco, int ataqueInstantaneo) {
        super(nome, preco);

        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    //Mostrar informação dos Consumiveis de Ataque na consola
    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.print(" | Ataque Instantaneo: " + this.ataqueInstantaneo);
    }

    //Getters
    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }
}
