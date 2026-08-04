package Entidades;

public class NPC extends Entidade {

    private int ouro;

    //Constructor
    public NPC(String nome, int maxHP, int hp, int forca, int ouro) {
        super(nome, maxHP, hp, forca);

        this.ouro = ouro;
    }

    //Mostrar a informação do NPC na consola
    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.print(" | Ouro: " + this.ouro);
    }

    //Getters
    public int getOuro() {
        return ouro;
    }

    //Setters
    public void setOuro(int ouro) {
        this.ouro = ouro;
    }
}
