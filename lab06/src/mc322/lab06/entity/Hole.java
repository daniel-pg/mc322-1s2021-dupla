package mc322.lab06.entity;

import mc322.lab06.Cave;
import mc322.lab06.CaveRoom;

public class Hole extends Entity implements IGeneratorEntity {

    Hole(Cave cave, int[] position) {
        super(cave, position);
    }

    @Override
    public int getPrintPriority() {
        return 4;
    }

    @Override
    public String toString() {
        return "B";
    }

    /**
     * Se possível, gera quatro objetos Breeze nas quatro direções cardinais adjacentes na caverna
     */
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
                newRoom.addEntity(new Breeze(this.cave, newPosition));
            }
        }

    }
}
