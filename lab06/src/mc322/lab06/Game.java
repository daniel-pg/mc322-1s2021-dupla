package mc322.lab06;

import mc322.lab06.entity.Hero;

import java.util.Arrays;
import java.util.Scanner;

/** 
 * Recebe e executa os comandos do jogador, mantendo registro
 * da pontuação e verificando as condições de término de jogo.
 */
public class Game {
    private final String playerName;
    private final Hero hero;
    private GameStatus gameStatus;
    private int score;

    Game(Hero hero, String playerName) {
        this.hero = hero;
        this.playerName = playerName;
        this.gameStatus = GameStatus.ONGOING;
        this.score = 0;
    }

    public void executaJogo() {
        Scanner scanner = new Scanner(System.in);

        // Exibe o estado inicial da caverna, da perspectiva do Herói
        this.display();

        // Recebe novos comandos até que o jogo termine de alguma forma
        while (gameStatus == GameStatus.ONGOING) {
            System.out.print("\nInsira sua jogada: ");
            executeCommand(scanner.next().charAt(0));

            if (gameStatus == GameStatus.RESIGNED) {
                System.out.println("Volte sempre !");
                return;
            } else {
                // Exibe o estado atualizado da caverna, da perspectiva do Herói
                this.display();
            }
        }

        // Mensagem final de vitória ou derrota
        if (gameStatus == GameStatus.WIN) {
            System.out.println("Voce ganhou =D !!!");
        } else if (gameStatus == GameStatus.DEFEAT) {
            System.out.println("Voce perdeu =(...");
        }
    }

    /**
     * Executa os comandos passados pelo usuário através do teclado
     * @param cmd Caracter-código do comando
     */
    private void executeCommand(char cmd) {
        switch (cmd) {
            case 'w', 'a', 's', 'd' -> {
                // Movimentação e interação com a caverna são implementados pelo Herói
                this.score += hero.move(cmd);

                // Herói morreu
                if (hero.isDead()) {
                    this.gameStatus = GameStatus.DEFEAT;
                }

                // Herói tem ouro e também chegou na saída da caverna
                if (Arrays.equals(hero.getPos(), new int[]{0, 0}) && hero.hasGold()) {
                    this.score += 1000;
                    this.gameStatus = GameStatus.WIN;
                }
            }

            case 'k' -> hero.engageBow();

            // A captura de ouro também é implementada pelo Herói
            case 'c' -> hero.captureGold();

            case 'q' -> gameStatus = GameStatus.RESIGNED;

            default -> System.err.println("É uma anta quadrada mesmo! Não sabe ler a lista de comandos do jogo???");
        }
    }

    private void display() {
        this.hero.displayMap();
        System.out.println("Player: " + this.playerName);
        System.out.println("Score: " + this.score);
        System.out.println("Obtained gold: " + this.hero.hasGold());
        System.out.println("Killed Wumpus: " + this.hero.hasKilledWumpus());
        System.out.println("Engaged bow: " + this.hero.isBowEngaged());

        System.out.print("Current room content: ");
        String[] eyeContent = this.hero.getEyeContent();
        for (String content : eyeContent) {
            if (!content.equals("")) {
                System.out.print(content + " ");
            }
        }
        System.out.print("\n");

        if (this.hero.hasWumpusBattle()) {
            System.out.println("Batalha Wumpus!");
            this.hero.turnOffWumpusBattle();
        }
    }
}
