package mc322.lab06;

import mc322.lab06.entity.Hero;

import java.util.Scanner;

/**
 * @param args Caminho para um arquivo .csv especificando a caverna.
 */
public class AppMundoWumpus {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Número de argumentos é insuficiente");
        }

        // Preenche a caverna a partir do arquivo .csv
        CaveFactory caveFactory = new CaveFactory(args[0]);
        Cave cave = caveFactory.getCave();
        Hero hero = cave.getHero();

        System.out.print("Insira o seu nome: ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.next();

        // Inicializa e chama o controlador do jogo
        Game game = new Game(hero, playerName);
        game.executaJogo();
    }
}
