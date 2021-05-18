package mc322.lab06.entity;

import mc322.lab06.Cave;

public class Hero extends Entity implements IEntity {
    private int numberOfArrows;
    private boolean isBowEngaged;

    Hero(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 3;
    }

    @Override
    public String toString() {
        return "P";
    }
}
