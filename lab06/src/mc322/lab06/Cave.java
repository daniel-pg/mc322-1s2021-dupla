package mc322.lab06;

import mc322.lab06.entity.*;

public class Cave {
    private final CaveRoom[][] caveRooms;
    private final int height, width;
    public Hero hero;

    Cave() {
        this(4, 4);
    }

    Cave(int height, int width) {
        this.caveRooms = new CaveRoom[height][width];
        this.height = height;
        this.width = width;

        // Inicializa cada sala individual
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                caveRooms[i][j] = new CaveRoom();
            }
        }

        caveRooms[0][0].setVisited(); // Casa inicial sempre visível
    }

    /**
     * @param position Array informando a linha e a coluna da sala almejada.
     * @return Retorna um booleano informando se a sala está contida na caverna.
     */
    private boolean validRoom(int[] position) {
        int row = position[0];
        int col = position[1];
        if (row >= 0 && row < this.height && col >= 0 && col < this.width) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param position Array informando a linha e a coluna da sala almejada.
     * @return Retorna um ponteiro ao objeto CaveRoom a posição especificada, se houver.
     *         Se não houver, retorna um ponteiro nulo.
     */
    public CaveRoom getRoom(int[] position) {
        if (this.validRoom(position)) {
            return this.caveRooms[position[0]][position[1]];
        } else {
            return null;
        }
    }

    /**
     * @return Retorna uma representação serializada do conteúdo da caverna.
     */
    public String[] getCaveMap() {
        String[] lines = new String[height];
        StringBuilder newLine = new StringBuilder();

        CaveRoom currentRoom;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentRoom = this.caveRooms[i][j];
                newLine.append(currentRoom.toString());
            }
            lines[i] = newLine.toString();
            newLine.setLength(0);
        }

        return lines;
    }

    /**
     * @return Retorna um array informando a altura e a largura da caverna.
     */    public int[] getCaveDimensions() {
        return new int[]{height, width};
    }

    /**
     * @param hero: Objeto Hero contido na caverna.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * @return Retorna o objeto Hero contido na caverna.
     */
    public Hero getHero() {
        return this.hero;
    }

    /**
     * Adiciona em tempo um objeto Entity a uma sala.
     * @param position Especifica as coordenadas da sala.
     * @param newEntity Ponteiro para um objeto Entity.
     */
    public void addEntity(int[] position, Entity newEntity) {
        this.getRoom(position).addEntity(newEntity);
    }

    /**
     * Remove em tempo um objeto Entity de uma sala.
     * @param position Especifica as coordenadas da sala.
     * @param entityType String contendo a representação de um objeto Entity.
     */
    public void removeEntity(int[] position, String entityType) {
        this.getRoom(position).removeEntity(entityType);
    }

    /**
     * @param position Especifica as coordenadas da sala.
     * Marca a sala como visitada pelo Herói.
     */
    public void setVisited(int[] position) {
        this.getRoom(position).setVisited();
    }

    /**
     * Retorna um objeto Entity na sala com base em sua representação String.
     * @param position Especifica as coordenadas da sala.
     * @param entityType Representação String da entidade.
     * @return Retorna o objeto Entity almejado.
     */
    public Entity getEntity(int[] position, String entityType) {
        return this.getRoom(position).getEntity(entityType);
    }
}
