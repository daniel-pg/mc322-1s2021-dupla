package mc322.lab06;

import mc322.lab06.entity.Entity;

import java.util.HashSet;

public class CaveRoom {
    private HashSet<Entity> entities;
    private boolean visited;

    public CaveRoom() {
        this.entities = new HashSet<>();
        this.visited = false;
    }

    public void addEntity(Entity newEntity) {
        this.entities.add(newEntity);
    }

    public void removeEntity(Entity newEntity) {
        this.entities.remove(newEntity);
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
