package mc322.lab06;

import mc322.lab06.entity.Hero;

import java.util.Scanner;

public class Game {
    private final String playerName;
    private final Hero hero;
    private int score;

    Game(Hero hero, String playerName) {
        this.hero = hero;
        this.playerName = playerName;
        this.score = 0;
    }

    public void executaJogo() {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;

        while (!gameEnd) {
            gameEnd = executeMove(scanner.next().charAt(0));
        }
    }

    private boolean executeMove(char move) {
        return true; // TODO
    }
}
