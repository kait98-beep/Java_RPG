package Entidades;

import Itens.ArmaPrincipal;
import Jogo.Jogo;

public class Arqueiro extends Heroi {

    //Constructor
    public Arqueiro(String nome, int maxHP, int hp, int forca, int nivel, int ouro) {
        super(nome, maxHP, hp, forca, nivel, ouro);

        setArmaPrincipal(new ArmaPrincipal("Arco", 5,  3, 6));
    }

    //Combate do Arqueiro
    @Override
    public void combateAtacar(NPC npc) {

        int forcaTotalInimigo = npc.getForca();

        //Turnos
        while (getHp() > 0 && npc.getHp() > 0) {

            //Heroi ataca o inimigo primeiro
            heroiAtacar(npc);

            if (npc.getHp() <= 0) {
                break;
            }

            //Inimigo ataca em segundo: o dano é aumentado em 10%
            setHp(getHp() - ((int) (forcaTotalInimigo * 1.1)));
            System.out.println("O inimigo atacou, ficou com: " + getHp());
        }

        //Ganhos finais ou perderam
        boolean outcome = loot(npc);

        //Reiniciar o jogo
        if (!outcome) {
            Jogo.jogadorPerdeu = true;
        }
    }
}
