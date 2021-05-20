package mc322.lab06;

import mc322.lab06.entity.Entity;
import mc322.lab06.entity.IGeneratorEntity;

import java.util.HashMap;

/**
 * Cada objeto CaveRoom possui um mapa hash que mapeia (i) uma representação
 * String de uma Entity à (ii) própria Entity, permitindo não só adição
 * como também remoção em tempo constante. Funcional apenas assumindo que
 * há apenas um tipo de Entity por sala.
 */
public class CaveRoom {
    private final HashMap<String, Entity> entities;
    private boolean visited;

    public CaveRoom() {
        this.entities = new HashMap<>();
        this.visited = false;
    }

    /**
     * Adiciona em tempo constante objetos Entity ao HashMap da sala.
     * @param newEntity Ponteiro para um objeto Entity.
     * @return Retorna o HashMap de entidades da sala.
     */
    public Entity addEntity(Entity newEntity) {
        Entity result = this.entities.put(newEntity.toString(), newEntity);
        return result;
    }

    /**
     * Remove em tempo constante objetos Entity ao HashMap da sala.
     * @param entityType String contendo a representação de um objeto Entity.
     * @return Retorna o HashMap de entidades da sala.
     */
    public Entity removeEntity(String entityType) {
        return this.entities.remove(entityType);
    }

    /**
     * @return Informa se a sala não possui objetos Entity.
     */
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    /**
     * @return Informa se a sala já foi vistada pelo Herói.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Marca a sala como visitada pelo Herói.
     */
    public void setVisited() {
        this.visited = true;
    }

    /**
     * Recupera em tempo constante um objeto Entity com base em sua representação String.
     * @param entityType Representação String da entidade.
     * @return Retorna o objeto Entity almejado.
     */
    public Entity getEntity(String entityType) {
        return this.entities.get(entityType);
    }

    /**
     * @return Retorna um ponteiro para a entidade com maior prioridade de impressão na sala.
     */
    public Entity getMaxPrintPriorityEntity () {
        String[] searchKeys = new String[]{"O", "B", "W", "P", "f", "b"};
        Entity maxPriority = this.entities.get(searchKeys[0]);
        Entity current;

        for (String key : searchKeys) {
            current = this.entities.get(key);
            if (maxPriority == null) {
                maxPriority = current;
            } else if (current != null) {
                if (current.getPrintPriority() > maxPriority.getPrintPriority()) {
                    maxPriority = current;
                }
            }
        }

        return maxPriority;
    }

    /**
     * @return Retorna uma String informando o conteúdo da sala, levando em conta
     *         a ordem de prioridade das entidades ali contidas.
     */
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
