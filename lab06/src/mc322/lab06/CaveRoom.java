package mc322.lab06;

import mc322.lab06.entity.Entity;

import java.util.HashMap;

public class CaveRoom {
    private final HashMap<String, Entity> entities;
    private boolean visited;

    public CaveRoom() {
        this.entities = new HashMap<>();
        this.visited = false;
    }

    public Entity addEntity(Entity newEntity) {
        return this.entities.put(newEntity.toString(), newEntity);
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
}
