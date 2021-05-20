package mc322.lab06;

import mc322.lab06.entity.*;

/**
 * Classe responsável por gerar objetos Cave válidos a partir de uma matriz de instruções,
 * em que cada instrução possui a forma {"X:Y", "Z"} especificando coordenadas numéricas X, Y
 * e um tipo String de entidade Z.
 */
public class CaveFactory {
    private final String[][] commands;
    private final int height, width;

    CaveFactory(String csvPath) {
        this(csvPath, 4, 4);
    }

    CaveFactory(String csvPath, int height, int width) {
        CSVHandling csv = new CSVHandling();
        csv.setDataSource(csvPath);

        this.commands = csv.requestCommands();
        this.height = height;
        this.width = width;
    }

    /**
     * @param entityCount Array informando a quantidade de certos objetos Entidade.
     * @return Retorna um booleano informando se a caverna possui as quantidades apropriadas.
     */
    private static boolean validateEntityCount(int[] entityCount) {
        int minHoleCount = 2;
        int maxHoleCount = 3;
        int wumpusCount = 1;
        int goldCount = 1;
        int heroCount = 1;
        return entityCount[0] >= minHoleCount && entityCount[0] <= maxHoleCount &&
                entityCount[1] == wumpusCount &&
                entityCount[2] == goldCount &&
                entityCount[3] == heroCount;
    }
    
    /**
     * Constrói um objeto Cave válido a partir do artibuto 'commands' que especifica a caverna.
     * @return Retorna o objeto Cave construído.
     */
    public Cave getCave() {
        Cave newCave = new Cave(height, width);
        EntityFactory entityFactory = new EntityFactory(newCave);

        int[] entityCount = new int[]{0,0,0,0}; // Buracos, Wumpus, Ouro, Herói

        Entity newEntity;
        char entityType;
        int[] newEntityCoord;

        // Para cada entrada da especificação da caverna ('commands'), cria e aloca objetos Entity.
        for (String[] command : commands) {
            newEntityCoord = decodeCoordinates(command[0]);
            entityType = command[1].charAt(0);
            newEntity = entityFactory.getEntity(entityType, newEntityCoord);

            // Objetos Hole e Wumpus geram objetos Breeze e Stink ao seu redor.
            switch (entityType) {
                case 'B' -> {
                    ((Hole) newEntity).generate();
                    entityCount[0]++;
                }
                case 'W' -> {
                    ((Wumpus) newEntity).generate();
                    entityCount[1]++;
                }
                case 'O' -> entityCount[2]++;
                case 'P' -> {
                    newCave.setHero((Hero) newEntity);
                    entityCount[3]++;
                }
            }

            if (newEntity != null) {
                newCave.getRoom(newEntityCoord).addEntity(newEntity);
            }
        }

        if (validateEntityCount(entityCount)) {
            return newCave;
        } else {
            System.err.println("Arquivo de entrada inválido!");
            return null;
        }
    }

    /**
     * @param encodedCoordinates Vetor da forma {"X:Y"}. 
     * @return Retorna um array de inteiros {X,Y} especificando coordenadas dentro de uma caverna.
     */
    private int[] decodeCoordinates(String encodedCoordinates) {
        String[] splitCoordinates = encodedCoordinates.split(":");

        int[] coordinates = new int[2];
        coordinates[0] = Integer.parseInt(splitCoordinates[0]) - 1;
        coordinates[1] = Integer.parseInt(splitCoordinates[1]) - 1;
        return coordinates;
    }
}
