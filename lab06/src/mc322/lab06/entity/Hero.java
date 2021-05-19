package mc322.lab06.entity;

import mc322.lab06.Cave;

public class Hero extends Entity implements IEntity {
    private int numberOfArrows;
    private boolean isDead;
    private boolean isBowEngaged;
    private boolean hasGold;
    private boolean hasKilledWumpus;

    Hero(Cave cave, int[] position) {
        super(cave, position);
        this.numberOfArrows = 1;
        this.isDead = false;
        this.isBowEngaged = false;
        this.hasGold = false;
        this.hasKilledWumpus = false;
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
    public boolean move(char move) {
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

    private boolean move(int[] displacement) {
        if (displacement == null) {
            return false;
        }

        /* TODO: Tentar mover o herói se o movimento for válido. Também verifica se o herói caiu num buraco ou encontrou
            o Wumpus (e se tem flecha equipada ou não). Se ele morrer no processo, atualiza estado. */
        return false;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isBowEngaged() {
        return isBowEngaged;
    }

    public void engageBow() {
        isBowEngaged = true;
    }

    private void fireBow() {
        if (numberOfArrows > 0) {
            numberOfArrows--;
            isBowEngaged = false;
        }
    }

    public void captureGold() {
        if (this.cave.getRoom(this.position).removeEntity("O") != null) {
            this.hasGold = true;
        }
    }

    public boolean hasGold() {
        return hasGold;
    }

    public boolean hasKilledWumpus() {
        return hasKilledWumpus;
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
            System.out.println("");
        }

        // Imprimir numeração das colunas na parte debaixo do tabuleiro.
        System.out.println(" ");
        for (int i = 0; i < width; i++) {
            System.out.println(" " + (i + 1));
        }
    }
}
