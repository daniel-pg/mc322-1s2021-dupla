package mc322.lab06;

import mc322.lab06.entity.*;

public class CaveFactory {
    private final String[][] commands;
    private final int height, width;
    private Hero hero;

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

    private static boolean validateEntityCount(int[] entityCount) {
        return entityCount[0] >= 2 && entityCount[0] <= 3 &&
                entityCount[1] == 1 &&
                entityCount[2] == 1 &&
                entityCount[3] == 1;
    }

    public Cave getCave() {
        Cave newCave = new Cave(height, width);
        EntityFactory entityFactory = new EntityFactory(newCave);

        int[] entityCount = new int[]{0,0,0,0}; // Buracos, Wumpus, Ouro, Herói

        Entity newEntity;
        char entityType;
        int[] newEntityCoord;

        for (String[] command : commands) {
            newEntityCoord = decodeCoordinates(command[0]);
            entityType = command[1].charAt(0);
            newEntity = entityFactory.getEntity(entityType, newEntityCoord);

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
                    this.hero = (Hero) newEntity;
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

    public Hero getHero() {
        return this.hero;
    }

    private int[] decodeCoordinates(String encodedCoordinates) {
        String[] splitCoordinates = encodedCoordinates.split(":");

        int[] coordinates = new int[2];
        coordinates[0] = Integer.parseInt(splitCoordinates[0]) - 1;
        coordinates[1] = Integer.parseInt(splitCoordinates[1]) - 1;
        return coordinates;
    }
}
