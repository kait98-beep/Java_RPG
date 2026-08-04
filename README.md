## SuperClasses:
- **Entidade:** É do tipo abstract, pois só serve de molde para as duas classes Heroi e NPC;
- **ItemHeroi:** Os items que o Heroi poderá ter no inventário. É do tipo abstract, pois só serve de molde para ArmaPrincipal e Consumivel;
- **Consumivel:** É do tipo abstract, pois só serve de molde para a classe ConsumivelCombate e para a classe Pocao;
- **Vendedor:** Tem uma loja, através de um arraylist de ItemHeroi, para o heroi poder comprar items.

***Vendedor:*** O objetivo é ter uma loja de onde o heroi poderá comprar items, se assim quiser. Estes items podem ser
    do tipo Pocao, Consumivel, ConsumivelCombate ou ArmaPrincipal. De métodos, tem o imprimirLoja(), que vai imprimir
    aleatóriamente 10 items daqueles já instanciados, podendo repetir o mesmo item mais do que uma vez. O processoDeVenda(),
    a receber dois parâmetros (o heroi e a lista criada no imprimirLoja()), permite que o heroi possa decidir o que quer
    comprar e quando quer parar de comprar. A compra é realizada com sucesso quando é adicionado ao inventário e é retirado
    o respetivo ouro através do método vender que incorporei no processoDeVenda(). Tem um Getter utilizado no Jogo para
    permite adicionar instancias de consumiveis ao ArrayList loja.


## SubClasses:
- **Heroi e NPC:** SubClasses de Entidade.


***Heroi:*** Uma classe abstrata que serve de molde para Arqueiro, Cavaleiro e Feiticeiro, permitindo que estes herdem
    os seus atributos, incluindo o armaPrincipal, que é definido no construtor de cada subclasse através do setArmaPrincipal().
    Tem o método combateAtacar(), do tipo abstract, porque vai ser executado de forma diferente em cada classe. O ciclo de
    combate repete-se enquanto tanto o heroi como o inimigo tiverem vida (hp) superior a zero, verificando o valor atual através
    do getHp(), e termina assim que um dos dois for derrotado. Continuando no combate, tem os métodos heroiAtacar()
    (mecanismo de ataque do heroi), que calcula o dano consoante o tipo de ataque escolhido (normal, especial ou consumível)
    e atualiza diretamente o hp do NPC através do setHp(). No ataque normal, existe uma chance aleatória de crítico, gerada
    através da classe Random (10% de probabilidade, se nextInt(100) < 10, que adiciona um bónus de +5 de dano ao ataque).
    Tem ainda o método loot, responsável pelos ganhos ou perdas do pós-combate. Tem o método usarPocao() que permite que
    qualquer heroi possa utilizar poções quando necessário, sendo a ação escolhida (curar ou aumentar a força) aplicada a
    todas as poções presentes no inventário do herói nesse momento cujo atributo correspondente (curar ou aumentoForca)
    seja superior a zero, evitando assim aplicar efeitos nulos de poções que não sirvam para essa finalidade.
    Se a cura for superior à quantidade de vida que nos resta, deve avisar o jogador da quantidade de excesso e
    apresentar pergunta de confirmação. Termina com getters e setters utilizados pelas restantes classes.

***NPC:*** Tem como objetivo ser os inimigos no combate contra o heroi. De métodos tem o mostrarDetalhes(), para mostrar
    a informação sobre o inimigo e o respetivo getter e setter.

<hr>

- **Arqueiro, Cavaleiro, Feiticeiro:** SubClasses de Heroi.


***Arqueiro, Cavaleiro, Feiticeiro:*** Herdam as informações do constructor do Heroi, incluindo o atributo armaPrincipal,
que cada subclasse inicializa no seu próprio construtor (através do setArmaPrincipal()) com a arma inicial correspondente,
podendo esta ser substituída futuramente ao comprar na loja. Como herdam de Heroi, por obrigatoridade, têm o método
combateAtacar() com uma especificidade muito simples:

>> Arqueiro: A força total do inimigo é aumentado em 10% por o arqueiro não ter armadura;

>> Cavaleiro: A força total do inimigo é reduzida para 80% do valor original (ou seja, perde 20% de eficácia), devido à armadura do cavaleiro;

>> Feiticeiro: O combate é normal, sem alterações nos valores.

<hr>

- **ArmaPrincipal e Consumivel:** SubClasses do ItemHeroi com específicações da arma principal e dos consumiveis utilizados
    em combate ou poções para curar a vida ou aumentar a força.


***ArmaPrincipal:*** Tem os atributos ataque e ataqueEspecial, que vão ser selecionados no menu da vez do heroi atacar.
    De métodos, tem o mostrarDetalhes() para mostrar a informação sobre estes atributos e os respetivos Getters utilizados no
    heroiAtacar() presente na classe Heroi e no combateAtacar() presente nas subclasses do Heroi (Cavaleiro, Feiticeiro, Arqueiro).

***Consumivel:*** Serve para fazer a ponte entre ItemHeroi e ConsumivelCombate e entre ItemHeroi e Pocao, permitindo juntar
    instancias destas duas classes num ArrayList na classe Vendedor, a loja.

<hr>

- **ConsumivelCombate e Pocao:** SubClasse de Consumivel.


***ConsumivelCombate:*** Tem o atributo ataqueInstantaneo, que faz parte do menu de combate do heroi, que utiliza um
    consumivel de combate para aumentar o dano infligido.

***Pocao:*** Tem o atributo curar e aumentoForca, dependendo daquilo que o heroi pretender. Estas poções são instanciadas
    no Jogo e colocadas na loja do vendedor, permitindo à compra das mesmas. De métodos, tem o mostrarDetalhes(), para mostrar
    a informação, e os respetivos getters, utilizados na classe Heroi e nas subclasses do mesmo (Cavaleiro, Arqueiro, Feiticeiro).


## Classes:

- **Jogo:** Faz a ligação das classes todas, anteriormente descritas, para colocar o jogo a funcionar;
- **Main:** Inicializar um novo jogo e decidir o que acontece caso o heroi morra ou chegue ao fim.


***Jogo:*** Tem o método para criarPersonagem() (com a dificuldade e os respetivos pontos de criação decididos pelo utilizador)
    e de como o jogo vai funcionar através do método aventuraDosGelados(), que recebe como parametro o heroi construido no
    criarPersonagem(). Através de um ciclo while, permite percorrer o código do jogo até o heroi chegar ao fim ou até morrer.
    Nesta classe, também temos os consumiveis e inimigos instanciados. Os inimigos foram guardados no HashMap geral, assim permite
    utilizar ao longo da classe e permite procurar o value através da key e não através do index, como seria com um get num ArrayList.
    Na 2ª Tomada de Decisão do aventuraDosGelados(), foram introduzido eventos aleatórios, também através da classe Random:
    no caminho "Casa de Gelo", existe 50% de hipótese (nextBoolean()) de o heroi encontrar ouro e ganhar 40 moedas; no caminho
    "Monte do Frio", é gerado um número aleatório entre 0 e 50000 (nextInt(50000)) e, se este calhar entre 2000 e 4000, o heroi
    é mordido por um animal e perde 20 de vida, podendo inclusive morrer e perder o jogo, caso a vida fique negativa.
    Esta hipótese é propositadamente baixa (cera de 4%), representando um risco raro, mas presente neste caminho.

***Main:*** Onde vamos ver tudo a funcionar. Caso o jogador perca ou chegue ao fim da aventuraDosGelados(),
    é perguntado se pretende jogar novamente e é dado as respetivos opções caso queira, permitindo que simplesmente volte a
    jogar com a mesma personagem já criada anteriormente, ou de criar uma personagem nova e de inicializar um novo jogo.
    Isto tudo a partir de um boolean.
