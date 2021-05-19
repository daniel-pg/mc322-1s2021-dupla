package mc322.lab06;

import mc322.lab06.entity.Entity;
import mc322.lab06.entity.EntityFactory;
import mc322.lab06.entity.Hero;

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

    public Cave getCave() {
        Cave newCave = new Cave(height, width);
        EntityFactory entityFactory = new EntityFactory(newCave);

        Entity newEntity;
        char entityType;
        int[] newEntityCoord;

        for (String[] command : commands) {
            newEntityCoord = decodeCoordinates(command[0]);
            entityType = command[1].charAt(0);
            newEntity = entityFactory.getEntity(entityType, newEntityCoord);

            if (entityType == 'P') {
                this.hero = (Hero) newEntity;
            }

            newCave.getRoom(newEntityCoord).addEntity(newEntity);
        }

        return newCave;
    }

    public Hero getHero() {
        return this.hero;
    }

    private int[] decodeCoordinates(String encodedCoordinates) {
        String[] splitCoordinates = encodedCoordinates.split(":");

        int[] coordinates = new int[2];
        coordinates[0] = Integer.getInteger(splitCoordinates[0]) - 1;
        coordinates[1] = Integer.getInteger(splitCoordinates[1]) - 1;
        return coordinates;
    }
}
