package Entidades;

import Itens.ArmaPrincipal;
import Jogo.Jogo;

public class Feiticeiro extends Heroi {

    //Constructor
    public Feiticeiro(String nome, int maxHP, int hp, int forca, int nivel, int ouro) {
        super(nome, maxHP, hp, forca, nivel, ouro);

        setArmaPrincipal(new ArmaPrincipal("Pau de Carvalho", 3, 2, 3));
    }

    //Combate do Feiticeiro
    @Override
    public void combateAtacar(NPC npc) {

        int forcaTotalInimigo = npc.getForca();

        //Turnos
        while (getHp() > 0 && npc.getHp() > 0) {

            //Heroi ataca primeiro
            heroiAtacar(npc);

            if (npc.getHp() <= 0) {
                break;
            }

            //Inimigo ataca em segundo
            setHp(getHp() - forcaTotalInimigo);
            System.out.println("O inimigo atacou, ficaste com: " + getHp());
        }

        //Ganhos finais ou perderam
        boolean outcome = loot(npc);

        if (!outcome) {
            Jogo.jogadorPerdeu = true;
        }
    }
}
