package mc322.lab06.entity;

import mc322.lab06.Cave;
import mc322.lab06.CaveRoom;

import java.util.Random;

public class Hero extends Entity implements IEntity {
    private int numberOfArrows;
    private boolean isDead;
    private boolean isBowEngaged;
    private boolean hasGold;

    Hero(Cave cave, int[] position) {
        super(cave, position);
        this.numberOfArrows = 1;
        this.isDead = false;
        this.isBowEngaged = false;
        this.hasGold = false;
    }

    @Override
    public int getPrintPriority() {
        return 3;
    }

    @Override
    public String toString() {
        return "P";
    }

    /**
     * Move o herói pela caverna.
     *
     * @param move Código do movimento
     * @return Verdadeiro se o movimento é válido; Falso caso contrário
     */
    public int move(char move) {
        int[] displacement;

        switch (move) {
            case 'w' -> displacement = new int[] {-1,  0};
            case 'a' -> displacement = new int[] { 0, -1};
            case 's' -> displacement = new int[] {+1,  0};
            case 'd' -> displacement = new int[] { 0, +1};
            default -> displacement = null;
        }

        return move(displacement);
    }

    private int move(int[] displacement) {
        int score = 0;
        if (displacement == null) {
            return score;
        }

        int[] targetPos = new int[2];
        for (int i = 0; i < 2; i++) {
            targetPos[i] = this.position[i] + displacement[i];
        }

        // Checa se o destino do herói é válido
        if (this.cave.getRoom(targetPos) == null) {
            return score;
        }

        // Penalidade correspondente ao movimento bem-sucedido
        score -= 15;

        // Remove herói da posição de origem
        CaveRoom currentRoom = this.cave.getRoom(this.position);
        currentRoom.removeEntity(this.toString());

        // Adiciona herói à posição de destino
        this.position = targetPos;
        currentRoom = this.cave.getRoom(this.position);
        currentRoom.addEntity(this);
        currentRoom.setVisited();

        // Interação com buracos
        if (currentRoom.getEntity("B") != null) {
            this.isDead = true;
            currentRoom.removeEntity(this.toString());
            score -= 1000;
        }

        // Interação com Wumpus
        if (currentRoom.getEntity("W") != null) {
            Random rand = new Random();
            if (this.isBowEngaged && rand.nextInt(101) >= 50) {
                currentRoom.removeEntity("W");
                score += 500;
            } else {
                this.isDead = true;
                score -= 1000;
            }
        }

        // Atira quaisquer flechas equipadas
        if (this.fireBow()) {
            score -= 100;
        }

        return score;
    }

    public boolean isDead() {
        return isDead;
    }

    public void engageBow() {
        if (this.numberOfArrows > 0) {
            isBowEngaged = true;
        }
    }

    private boolean fireBow() {
        if (numberOfArrows > 0 && this.isBowEngaged) {
            numberOfArrows--;
            isBowEngaged = false;
            return true;
        }

        return false;
    }

    public void captureGold() {
        if (this.cave.getRoom(this.position).removeEntity("O") != null) {
            this.hasGold = true;
        }
    }

    public boolean hasGold() {
        return hasGold;
    }

    public void displayMap() {
        String[] caveMap = this.cave.getCaveMap();
        int[] caveDimensions = this.cave.getCaveDimensions();
        int height = caveDimensions[0],
            width = caveDimensions[1];

        // Imprime a representação da caverna
        for (int i = 0; i < height; i++) {
            System.out.print(i + 1); // Numeração das linhas

            for (int j = 0; j < width; j++) {
                System.out.print(" " + caveMap[i].charAt(j));
            }
            System.out.println();
        }

        // Imprimir numeração das colunas na parte debaixo do tabuleiro.
        System.out.print(" ");
        for (int i = 0; i < width; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
    }
}
