package mc322.lab06;

import mc322.lab06.entity.Hero;

import java.util.Scanner;

public class AppMundoWumpus {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Número de argumentos é insuficiente");
        }

        CaveFactory caveFactory = new CaveFactory(args[0]);
        Cave cave = caveFactory.getCave();
        Hero hero = caveFactory.getHero();

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.next();

        Game game = new Game(hero, playerName);
        game.executaJogo();
    }
}
