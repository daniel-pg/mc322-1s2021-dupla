package mc322.lab06.entity;

public class EntityFactory {
    public Entity getEntity(String entityType) {
        if (entityType == null) {
            return null;
        }
        else if (entityType.equalsIgnoreCase("breeze")) {
            return new Breeze();
        }
        else if (entityType.equalsIgnoreCase("gold")) {
            return new Gold();
        }
        else if (entityType.equalsIgnoreCase("hero")) {
            return new Hero();
        }
        else if (entityType.equalsIgnoreCase("hole")) {
            return new Hole();
        }
        else if (entityType.equalsIgnoreCase("stink")) {
            return new Stink();
        }
        else if (entityType.equalsIgnoreCase("wumpus")) {
            return new Wumpus();
        }

        return null;
    }
}
