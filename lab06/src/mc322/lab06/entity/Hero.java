package mc322.lab06.entity;

import mc322.lab06.Cave;
import mc322.lab06.CaveRoom;

import java.util.Random;

public class Hero extends Entity implements IEntity {
    private int numberOfArrows;
    private boolean isDead;
    private boolean isBowEngaged;
    private boolean hasGold;
    private String[] eyeContent;
    private boolean hasWumpusBattle;
    private boolean hasKilledWumpus;

    Hero(Cave cave, int[] position) {
        super(cave, position);
        this.numberOfArrows = 1;
        this.isDead = false;
        this.isBowEngaged = false;
        this.hasGold = false;
        this.eyeContent = new String[]{"", "", ""};
        this.hasWumpusBattle = false;
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
     * Wrapper de move(int[]) que determina valores numéricos associados a direção de movimento
     * @param move Código do movimento
     * @return Valor inteiro informando o acréscimo ou decréscimo de pontuação associado ao movimento
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

    /**
     * Realiza a movimentação do Herói e suas interações automáticas com objetos da caverna
     * @param displacement Array binário informando movimento vertical e horizontal
     * @return Valor inteiro informando o acréscimo ou decréscimo de pontuação associado ao movimento
     */
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

        // Remove Herói da posição de origem
        this.cave.removeEntity(this.position, this.toString());

        // Adiciona Herói à posição de destino
        this.position = targetPos;
        this.cave.addEntity(this.position, this);
        this.cave.setVisited(this.position);

        // Interação com buracos
        if (this.cave.getEntity(this.position, "B") != null) {
            this.isDead = true;
            this.cave.removeEntity(this.position, this.toString());
            score -= 1000;
        }

        // Interação com Wumpus
        if (this.cave.getEntity(this.position, "W") != null) {
            this.hasWumpusBattle = true;
            Random rand = new Random();
            if (this.isBowEngaged && rand.nextInt(101) >= 50) {
                this.cave.removeEntity(this.position, "W");
                score += 500;
                this.hasKilledWumpus = true;
            } else {
                this.isDead = true;
                score -= 1000;
            }
        }

        // Atira quaisquer flechas equipadas
        if (this.fireBow()) {
            score -= 100;
        }

        this.setEyeContent();

        return score;
    }

    /**
     * Detecta os componentes da sala atual e preenche as lacunas de conteúdo
     * da visão do Herói, possivelmente uma String vazia. O primeiro conteúdo
     * corresponde ao conteúdo primário, o segundo à brisa, e o terceiro ao fedor.
     */
    public void setEyeContent() {
        CaveRoom currentRoom = this.cave.getRoom(this.position);

        // Detecta o componente primário da sala
        if (currentRoom.getEntity("O") != null) {
            this.eyeContent[0] = "Gold";
        } else if (currentRoom.getEntity("W") != null) {
            this.eyeContent[0] = "Wumpus";
        } else if (currentRoom.getEntity("B") != null) {
            this.eyeContent[0] = "Hole";
        } else {
            this.eyeContent[0] = "";
        }

        // Detecta a presença de brisa
        if (currentRoom.getEntity("b") != null) {
            this.eyeContent[1] = "Breeze";
        } else {
            this.eyeContent[1] = "";
        }

        // Detecta a presença de fedor
        if (currentRoom.getEntity("f") != null) {
            this.eyeContent[2] = "Stink";
        } else {
            this.eyeContent[2] = "";
        }
    }

    /**
     * @return String[] da forma {"A", "B", "C"} informando o componente primário
     *         e os dois componentes secundários que o Herói possivelmente vê na sala atual
     */
    public String[] getEyeContent() {
        return eyeContent;
    }

    public boolean isDead() {
        return isDead;
    }

    public void engageBow() {
        if (this.numberOfArrows > 0) {
            isBowEngaged = true;
        }
    }

    public boolean isBowEngaged() {
        return this.isBowEngaged;
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
        if (this.cave.getEntity(this.position, "O") != null) {
            this.hasGold = true;
            this.cave.removeEntity(this.position, "O");
        }
    }

    public boolean hasGold() {
        return hasGold;
    }

    public boolean hasWumpusBattle() {
        return this.hasWumpusBattle;
    }

    public void turnOffWumpusBattle() {
        this.hasWumpusBattle = false;
    }

    public boolean hasKilledWumpus() {
        return this.hasKilledWumpus;
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
