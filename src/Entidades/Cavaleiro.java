package Entidades;

import Itens.ArmaPrincipal;
import Jogo.Jogo;

public class Cavaleiro extends Heroi {

    //Constructor
    public Cavaleiro(String nome, int maxHP, int hp, int forca, int nivel, int ouro) {
        super(nome, maxHP, hp, forca, nivel, ouro);

        setArmaPrincipal(new ArmaPrincipal("Espada", 10,  2, 5));
    }

    //Combate do Cavaleiro
    @Override
    public void combateAtacar(NPC npc) {

        int forcaTotalInimigo = npc.getForca();

        //Turnos
        while (getHp() > 0 && npc.getHp() > 0) {

            //Inimigo ataca primeiro: o dano é reduzido para 80% da força original
            setHp(getHp() - ((int) (forcaTotalInimigo * 0.8)));
            System.out.println("O inimigo atacou, ficaste com: " + getHp());

            if(getHp() <= 0){
                break;
            }

            //Heroi ataca o inimigo
            heroiAtacar(npc);
        }

        //Ganhos finais ou perderam
        boolean outcome = loot(npc);

        if (!outcome){
            Jogo.jogadorPerdeu = true;
        }
    }
}