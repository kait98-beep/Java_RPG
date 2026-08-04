package Itens;

public class ArmaPrincipal extends ItemHeroi{

    private int ataque;
    private int ataqueEspecial;


    //Constructor
    public ArmaPrincipal(String nome, int preco, int ataque, int ataqueEspecial) {
        super(nome, preco);

        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;

    }

    //Mostrar informação da Arma Principal na consola
    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.print(" | Ataque: " + this.ataque + " | Ataque Especial: " + this.ataqueEspecial);
    }

    //Getters
    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
}
