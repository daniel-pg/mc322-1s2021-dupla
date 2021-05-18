package mc322.lab06.entity;

import mc322.lab06.Cave;

public class EntityFactory {
    private Cave cave;

    public EntityFactory(Cave cave) {
        this.cave = cave;
    }

    public Entity getEntity(char entityType, int[] position) {
        return switch (entityType) {
            case 'b' -> new Breeze(cave, position);
            case 'O' -> new Gold(cave, position);
            case 'P' -> new Hero(cave, position);
            case 'B' -> new Hole(cave, position);
            case 'f' -> new Stink(cave, position);
            case 'W' -> new Wumpus(cave, position);
            default -> null;
        };
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }
}
