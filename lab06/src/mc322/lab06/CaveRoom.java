package mc322.lab06;

import mc322.lab06.entity.Entity;
import mc322.lab06.entity.IGeneratorEntity;

import java.util.HashMap;

public class CaveRoom {
    private final HashMap<String, Entity> entities;
    private boolean visited;

    public CaveRoom() {
        this.entities = new HashMap<>();
        this.visited = false;
    }

    public Entity addEntity(Entity newEntity) {
        Entity result = this.entities.put(newEntity.toString(), newEntity);
        return result;
    }

    public Entity removeEntity(String entityType) {
        return this.entities.remove(entityType);
    }

    public boolean isEmpty() {
        return entities.isEmpty();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public Entity getEntity(String entityType) {
        return this.entities.get(entityType);
    }

    public Entity getMaxPrintPriorityEntity () {
        String[] searchKeys = new String[]{"O", "B", "W", "P", "f", "b"};
        Entity result;

        for (String key : searchKeys) {
            result = this.entities.get(key);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

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

        return "?"; // Algo de errado não está certo...
    }
}
