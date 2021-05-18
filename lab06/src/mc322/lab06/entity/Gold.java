package mc322.lab06.entity;

import mc322.lab06.Cave;

public class Gold extends Entity implements IEntity {

    Gold(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 4;
    }

    @Override
    public String toString() {
        return "O";
    }
}
