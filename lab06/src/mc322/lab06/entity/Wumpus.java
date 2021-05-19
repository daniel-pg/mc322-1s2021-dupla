package mc322.lab06.entity;

import mc322.lab06.Cave;
import mc322.lab06.CaveRoom;

public class Wumpus extends Entity implements IGeneratorEntity {

    Wumpus(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 4;
    }

    @Override
    public String toString() {
        return "W";
    }

    @Override
    public void generate() {
        int[][] displacement = new int[][]{
                {-1 ,  0},
                {+1 ,  0},
                { 0 , -1},
                { 0 , +1}
        };

        CaveRoom newRoom;
        int[] newPosition = new int[2];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                newPosition[j] = this.position[j] + displacement[i][j];
            }
            newRoom = this.cave.getRoom(newPosition);
            if (newRoom != null) {
                newRoom.addEntity(new Stink(this.cave, newPosition));
            }
        }

    }
}
