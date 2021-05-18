package mc322.lab06.entity;

import mc322.lab06.Cave;

public class Breeze extends Entity implements IEntity {

    Breeze(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 1;
    }

    @Override
    public String toString() {
        return "b";
    }
}
