package mc322.lab06.entity;

import mc322.lab06.Cave;
/**
 * Entidade gerada por objetos Wumpus
 */
public class Stink extends Entity implements IEntity {

    Stink(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 2;
    }

    @Override
    public String toString() {
        return "f";
    }
}
