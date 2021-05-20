package mc322.lab06.entity;

import mc322.lab06.Cave;

/**
 * Classe construtora de objetos Entity para preencher a caverna
 */
public class EntityFactory {
    private Cave cave;

    public EntityFactory(Cave cave) {
        this.cave = cave;
    }

    /**
     * @param entityType Representação String da entidade a ser criada
     * @param position Array informando a linha e a coluna onde se localiza a entidade
     * @return Um objeto Entity criado
     */
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

    /**
     * @param cave Objeto Cave onde serão inseridos os objetos Entity criados
     */
    public void setCave(Cave cave) {
        this.cave = cave;
    }
}
