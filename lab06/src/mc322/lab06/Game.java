package mc322.lab06;

import mc322.lab06.entity.Hero;

import java.util.Arrays;
import java.util.Scanner;

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

        // Estado inicial do tabuleiro
        hero.displayMap();
        System.out.println("Player: " + playerName);
        System.out.println("Score: " + score);

        while (gameStatus == GameStatus.ONGOING) {
            executeCommand(scanner.next().charAt(0));

            if (gameStatus == GameStatus.RESIGNED) {
                System.out.println("Você já vai? Poxa... até a próxima!");
                return;
            }

            hero.displayMap();
            System.out.println("Player: " + playerName);
            System.out.println("Score: " + score);
        }

        // Mensagem final de derrota/vitória
        if (gameStatus == GameStatus.WIN) {
            System.out.println("“Voce ganhou =D !!!");
        } else if (gameStatus == GameStatus.DEFEAT) {
            System.out.println("“Voce perdeu =(...");
        }
    }

    /**
     * Executa os comandos passados pelo usuário através do teclado.
     *
     * @param cmd Código/letra do comando
     */
    private void executeCommand(char cmd) {
        switch (cmd) {
            case 'w', 'a', 's', 'd':
                this.score += hero.move(cmd);

                // Herói morreu
                if (hero.isDead()) {
                    this.gameStatus = GameStatus.DEFEAT;
                }

                // Herói tem ouro E chegou na saída da caverna.
                if (Arrays.equals(hero.getPos(), new int[]{0, 0}) && hero.hasGold()) {
                    this.score += 1000;
                    this.gameStatus = GameStatus.WIN;
                }
                break;

            case 'k':
                hero.engageBow();
                break;

            case 'c':
                hero.captureGold();
                break;

            case 'q':
                gameStatus = GameStatus.RESIGNED;
                break;
            default:
                System.err.println("É uma anta quadrada mesmo! Não sabe ler a lista de comandos do jogo???");
        }
    }
}
