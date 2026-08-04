package Entidades;

public abstract class Entidade {
    private String nome;
    private int maxHP = 0;
    private int hp = 0;
    private int forca;

    //Constructor
    public Entidade(String nome, int maxHP, int hp, int forca){
        this.nome = nome;
        this.maxHP = maxHP;
        this.hp = hp;
        this.forca = forca;
    }

    //Mostrar a informação da Entidade na consola
    public void mostrarDetalhes(){
        System.out.println("Nome: " + this.nome + " | HP Máximo: " + this.maxHP + " | Hp: " + this.hp +
                " | Força: " + this.forca);
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public int getForca() {
        return forca;
    }

    //Setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

}
