package mc322.lab06.entity;

import mc322.lab06.Cave;

public class EntityFactory {
    private Cave cave;

    public Entity getEntity(String entityType, int[] position) {
        if (entityType == null) {
            return null;
        }
        else if (entityType.equalsIgnoreCase("breeze")) {
            return new Breeze(cave, position);
        }
        else if (entityType.equalsIgnoreCase("gold")) {
            return new Gold(cave, position);
        }
        else if (entityType.equalsIgnoreCase("hero")) {
            return new Hero(cave, position);
        }
        else if (entityType.equalsIgnoreCase("hole")) {
            return new Hole(cave, position);
        }
        else if (entityType.equalsIgnoreCase("stink")) {
            return new Stink(cave, position);
        }
        else if (entityType.equalsIgnoreCase("wumpus")) {
            return new Wumpus(cave, position);
        }

        return null;
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }
}
