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

    private boolean validRoom(int[] position) {
        int row = position[0],
                col = position[1];
        if (row >= 0 && row < this.height && col >= 0 && col < this.width) {
            return true;
        } else {
            return false;
        }
    }

    public CaveRoom getRoom(int[] position) {
        if (this.validRoom(position)) {
            return this.caveRooms[position[0]][position[1]];
        } else {
            return null;
        }
    }

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

    public int[] getCaveDimensions() {
        return new int[]{height, width};
    }
}
