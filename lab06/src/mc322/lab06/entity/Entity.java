package mc322.lab06.entity;

import mc322.lab06.Cave;

/**
 * Classe-mãe das entidades que povoam cavernas.
 */
public abstract class Entity implements IEntity {
    protected Cave cave;
    protected int[] position;

    Entity(Cave cave, int[] position) {
        this.cave = cave;
        this.position = position;
    }

    /**
     * @return Retorna a posição da entidade na caverna. 
     */
    public int[] getPos() {
        return this.position;
    }

    /**
     * @param position: Array informando a linha e a coluna em que a entidade se encontra.
     */
    public void setPos(int[] position) {
        this.position = position;
    }
}
