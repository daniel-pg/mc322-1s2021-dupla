# Projeto _Taalt!_

## Descrição Resumida

_Taalt!_, como foi batizado pela nossa equipe, é um jogo inspirado em uma classe abstrata de jogos de tabuleiros, conhecida como _Jogo ❬m, n, k❭_. Trata-se de uma generalização matemática do famoso jogo da velha (ou Tic-Tac-Toe), de modo que os parâmetros da partida podem ser variados.

De forma resumida, um jogo ❬m, n, k❭ possui um tabuleiro celular com as dimensões m × n, cujas células podem ser preenchidas por uma única peça cada, que por sua vez tem cores ou símbolos diferentes para cada um dos jogadores envolvidos. A condição de vitória é que um dos jogadores coloque *k* peças suas em sequência, seja na horizontal, na vertical, ou na diagonal.

Além dessas características, Taalt! ainda possui alguns elementos que distinguem sua jogabilidade. Dentre tais elementos, incluem-se a possibilidade de jogar com ou sem gravidade (que faz as peças caírem para a parte de baixo do tabuleiro), o número de jogadores (dois a quatro), as direções válidas para alinhar peças (com ou sem diagonais, por exemplo) e o tipo de visualização do tabuleiro (pela linha de comando ou interface gráfica).



## Equipe
* Daniel Credico de Coimbra
* Daniel Paulo Garcia



## Vídeos do Projeto
* [Vídeo da Prévia](assets/Prévia_Vídeo.mp4)



## Slides do Projeto
* [Slides da Prévia](assets/Prévia_Slides.pdf)



## Documentação dos Componentes

### Diagramas

#### Diagrama Geral do Projeto
Tentamos empregar o estilo arquitetural Model-View-Controller. Existem elementos controladores dentro dos componentes Window (responsável pela visualização) e GameController (responsável pelo modelo). Este segundo componente é projetado para que funcione de maneira completamente independente de um componente gráfico, sendo portanto acoplável a qualquer outro modo de visualização.

![Diagrama Geral do Projeto](assets/Arquitetura_Geral.png)

#### Diagrama dos Componentes de GameController
Modelo e controle da partida.

![Diagrama Geral do Projeto](assets/Arquitetura_Jogo.png)

#### Diagrama dos Componentes de Window
Visualização da partida, interface de input, e controlador de janelas.

![Diagrama Geral do Projeto](assets/Arquitetura_GUI.png)



## Componentes

### Componente `GameController`
Este componente possui um controlador principal GameController que administra a criação do componente Board, por meio da classe GameBuilder, e também gerencia algumas operações de I/O. Um sistema externo qualquer pode realizar entrada de inputs pela interface IGameActon, tal como pode requerer informações do jogo via IGameView. O componente GameController admite de observadores (listeners) que serão avisados automaticamente de quaisquer mudanças no estado interno do jogo, via UpdateView. Por fim, GameController é criado por um sistema externo via IGameBuilder.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `lab07.src.bin.GameController`
Autores | `Grupo Daniel x Daniel`
Interfaces | `IGameControl, IGameView, IGameBuild, IRBoardAction`

**Interfaces associadas**
![Diagrama Interfaces](assets/imagem)
Interface agregadora do componente em Java:
~~~java
public interface IExemplo3 extends IExemplo1, IExemplo2 {
}
~~~


### Componente `Window`
Este componente possui um controlador principal WindowManager que administra a criação e exibição das janelas MainMenu, onde o usuário insere opções e pode inicializar o jogo, e GameScreen, onde uma partida é exibida e na qual se pode interagir com o tabuleiro. WindowManager serve como ponto de entrada de nosso programa, e por meio de sua interface é possível criar um jogo via IGameBuilder. Inputs serão inseridos via IGameAction e atualizações sobre o tabuleiro serão sinalizadas via UpdateView e obtidas via IGameView.
![Exemplo 2](assets/imagem)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `lab07.src.bin.Exemplo2`
Autores | `Grupo Daniel x Daniel`
Interfaces | `IExemplo4, IExemplo5, IExemplo6`

**Interfaces associadas**
![Diagrama Interfaces](assets/imagem)
Interface agregadora do componente em Java:
~~~java
public interface IExemplo4 extends IExemplo5, IExemplo6 {
}
~~~



## Detalhamento das Interfaces
### Interface `IExemplo1`
Resumo do papel da interface.
~~~java
public interface IExemplo1 {
  int método1();
  boolean método2();
}
~~~
Método | Objetivo
-------| --------
`método1` | `Objetivos e parâmetros`
`método2` | `Objetivos e parâmetros`

### Interface `IExemplo2`
Resumo do papel da interface.
~~~java
public interface IExemplo1 {
  String[] método3();
  String[][] método4();
}
~~~
Método | Objetivo
-------| --------
`método3` | `Objetivos e parâmetros`
`método4` | `Objetivos e parâmetros`



## Plano de Exceções

### Diagrama da hierarquia de exceções
![Hierarquia Exceções](assets/imagem)

### Descrição das classes de exceção
Classe | Descrição
----- | -----
Exceção1 | Engloba tal coisa.
Exceção2 | Indica tal coisa.
Exceção3 | Cobre tal caso.
