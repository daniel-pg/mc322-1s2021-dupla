## Arquivos Java do Jogo

O caminho da classe contendo o método main() é mc322.lab06.AppMundoWumpus. Para executá-lo pela linha de comando, é necessário passar um argumento contendo o caminho do arquivo .csv especificando a configuração da caverna, conforme descrito na especificação da tarefa. Alguns exemplos desses arquivos CSV podem ser encontrados no diretório /data.
```
$ cd bin
$ java mc322.lab06.AppMundoWumpus <arquivo.csv>
```
Após executar o jogo, insira seu nome e execute os comandos digitando-os no terminal. O código-fonte de todas as classes pode ser encontrado [aqui](src/mc322/lab06).

## Destaques de Arquitetura

### Movimentação do Herói
```
this.cave.removeEntity(this.position, this.toString());
this.position = targetPos;
this.cave.addEntity(this.position, this);
this.cave.setVisited(this.position);
```
O código acima exibe os cinco primeiros atributos arquiteturais especificados no Lab. Nele, está contido parte do método move() da classe Hero, que é o ponto de acesso que a classe Game (controladora) possui para interagir com a caverna. É a classe Hero que interagirá com o objeto Cave, assim preservado a arquitetura proposta.

A classe Hero é responsável por alterar seu registro de posição, mas é a classe Cave que é responsável por realocar objetos Entity em suas salas e marcá-las como visitadas pelo Herói. Ao delegar tarefas para diferentes classes, isso evita a centralização de tarefas.

### Adição, remoção, e recuperação de Entidades
```
public Entity addEntity(Entity newEntity) {
    Entity result = this.entities.put(newEntity.toString(), newEntity);
    return result;
}

public Entity removeEntity(String entityType) {
    return this.entities.remove(entityType);
}

public Entity getEntity(String entityType) {
    return this.entities.get(entityType);
}
```
Vimos os métodos removeEntity() e addEntity() da classe Cave chamados no destaque anterior. Os métodos que vemos no trecho acima possuem o mesmo nome, mas são da classe CaveRoom. Os métodos homônimos da classe Cave chamam esses métodos da classe CaveRoom, com um pequeno wrapper.

É a classe CaveRoom que de fato implementará a remoção e adição de entidades dentro de si mesma, além de alterar sua propriedade "visited". Ao chamar as versões homônimas da classe Cave, a classe Herói nunca interage diretamente com a classe CaveRoom. Isso preserva o encapsulamento das classes.

Note, por fim, que addEntity possui como parâmetro objetos de classe Entity, explorando ao máximo o polimorfismo para os diferentes tipos de componente (Breeze, Gold, Hero, Hole, Stink, e Wumpus).

### Representação String das salas
```
public String toString() {
        Entity greatestPriority;
        if (!this.isVisited()) {
            return "-";
        } else if (this.isEmpty()) {
            return "#";
        } else {
            greatestPriority = getMaxPrintPriorityEntity();
            if (greatestPriority != null) {
                return greatestPriority.toString();
            }
        }
```
O código acima novamente exibe a modularização de nossa arquitetura. O método toString() acima é da classe CaveRoom e sobrescreve o método toString() da classe Object, da qual todas as classes herdam em Java.

Caso a sala tenha sido visitada e não esteja vazia, esse método visa retornar a representação String da entidade que ela contém. Se ela conter mais de uma entidade, deve retornar a representação String daquela com maior prioridade.

O grau de prioridade de cada entidade é acessível por meio do método getPrintPriority() da classe Entity, sendo sobrescrito por cada sub-classe. Esse método é chamado por getMaxPriorityEntity() da classe CaveRoom, mostrando encapsulamento.

Similarmente, a representação String de cada entidade é acessível por meio do método toString() da classe Entity, também sobrescrito por cada sub-classe.
