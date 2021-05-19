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

        // Inicializa cada sala individual
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                caveRooms[i][j] = new CaveRoom();
            }
        }

        caveRooms[0][0].setVisited(); // Casa inicial sempre visível
    }

    public CaveRoom getRoom(int[] position) {
        return this.caveRooms[position[0]][position[1]];
    }

    public String[] getCaveMap() {
        String[] lines = new String[height];
        StringBuilder newLine = new StringBuilder();

        CaveRoom currentRoom;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentRoom = this.caveRooms[i][j];

                if (!currentRoom.isVisited()) {
                    newLine.append("-");
                } else if (currentRoom.isEmpty()) {
                    newLine.append("#");
                } else {
                    newLine.append(currentRoom.toString());
                }
            }
            lines[i] = newLine.toString();
            newLine.setLength(0);
        }

        return lines;
    }

    public int[] getCaveDimensions() {
        return new int[]{height, width};
    }
}
