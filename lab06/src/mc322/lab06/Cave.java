package mc322.lab06;

import mc322.lab06.entity.Entity;

public class Cave {
    private final CaveRoom[][] caveRooms;
    private final int height, width;

    Cave() {
        this(4, 4);
    }

    Cave(int height, int width) {
        this.caveRooms = new CaveRoom[height][width];
        this.height = height;
        this.width = width;
    }

    public void addEntityToRoom(Entity newEntity) {
        int[] position = newEntity.getPos();
        this.caveRooms[position[0]][position[1]].addEntity(newEntity);
    }

    public void display() {
        CaveRoom currentRoom;

        for (int i = 0; i < height; i++) {
            System.out.println(i + 1); // Numeração das linhas

            for (int j = 0; j < width; j++) {
                currentRoom = this.caveRooms[i][j];
                System.out.println(" "); // Separador de símbolos

                if (!currentRoom.isVisited()) {
                    System.out.println("-");
                }
                else if (currentRoom.isEmpty()) {
                    System.out.println("#");
                }
                else {
                    System.out.println(this.caveRooms[i][j].toString());
                }
            }
        }

        System.out.println(" ");

        for (int i = 0; i < width; i++) {
            System.out.println(" " + (i + 1));
        }
    }
}
