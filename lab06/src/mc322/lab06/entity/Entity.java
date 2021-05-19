package mc322.lab06.entity;

import mc322.lab06.Cave;

public abstract class Entity implements IEntity {
    protected Cave cave;
    protected int[] position;

    Entity(Cave cave, int[] position) {
        this.cave = cave;
        this.position = position;
    }

    public int[] getPos() {
        return this.position;
    }

    public void setPos(int[] position) {
        this.position = position;
    }
}
