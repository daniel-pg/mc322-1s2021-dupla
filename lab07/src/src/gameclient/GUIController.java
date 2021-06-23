package gameclient;

import gameserver.Game;
import gameserver.Options;

import java.awt.*;
import javax.swing.*;

// TODO (1): Consider using JFrame.pack() method to dynamically adjust screen size.
// TODO (2): Decide whether the game is to be run in a single window or, instead, if gameclient.GUIMainMenu and gameclient.GUIGameScreen are different windows altered using JFrame.dispose().
// TODO (2 cont.): In the former case, gameclient.GUIController is the unifying window. In the latter case, gameclient.GUIController is a non-GUI operation.
public class GUIController extends JFrame implements IOptionsReceiver {
    public final static int WIDTH = 500;
    public final static int HEIGHT = 600;
    GUIMainMenu menuScreen;
    GUIGameScreen gameScreen;
    Options options;
    Game server;

    public GUIController() throws InterruptedException {
        // Initializes JFrame and adds favicon
        this.setTitle("Taalt! – The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        ImageIcon logoIcon = new ImageIcon("assets/logo32x32.png");
        this.setIconImage(logoIcon.getImage());
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);

        // Initializes main menu
        this.menuScreen = new GUIMainMenu();
        menuScreen.addOptionsListener(this);
        this.getContentPane().add(this.menuScreen);

        // Sets the JFrame to visible
        this.setVisible(true);
    }

    public void listenOptions(Options options) {
        this.options = options;
        this.start();
    }

    public void start() {
        this.gameScreen = new GUIGameScreen(WIDTH, HEIGHT, options.getM(), options.getN(), this);
        this.getContentPane().add(this.gameScreen);

        this.menuScreen.setVisible(false);

        this.server = new Game(this.options);
        this.gameScreen.addInputListener(this.server);
        this.server.addOutputListener(this.gameScreen);

        this.gameScreen.build();
        this.server.fireOutput();
    }

    public void end() {
        assert this.server != null;
        //this.server.removeOutputListener(this.gameScreen); // TODO: Esse comando gera ConcurrentModificationException porque tenta alterar o ArrayList enquanto Game.fireOutput() itera sobre ele
        this.getContentPane().remove(this.gameScreen);
        this.gameScreen = null;
        this.menuScreen.setVisible(true);
    }
}

