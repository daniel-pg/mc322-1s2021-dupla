package gameclient;

import gameserver.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: Consider JOptionPane for error messages, end game messages, and replay queries.
public class GUIGameScreen extends JPanel implements ICommandReceiver, IClient {
    public static final int MENU_SIZE = 50;
    public static final int STATUS_SIZE = 50;
    GUIController parentScreen;
    IGame gameServer;
    GUIBoard board;
    Output output;
    GUIGameStatus status;
    int width, height;
    int rows, cols;

    ArrayList<ActionListener> listenerList;

    public GUIGameScreen(int width, int height, int rows, int cols, GUIController parentScreen) {
        super();
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.cols = cols;
        this.parentScreen = parentScreen;
        this.setLayout(new BorderLayout());
        this.setSize(width, height - MENU_SIZE - STATUS_SIZE);
    }

    public void build() {
        GUIGameMenu menu = new GUIGameMenu(width, MENU_SIZE);
        this.add(menu, BorderLayout.PAGE_START);

        this.status = new GUIGameStatus(width, STATUS_SIZE);
        this.add(status, BorderLayout.PAGE_END);

        GUIBoard board = new GUIBoard(this.rows, this.cols, width, height);
        this.add(board, BorderLayout.CENTER);
        this.board = board;
        this.board.addCommandListener(this);

        GUICell guiCell;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Point pos = new Point(row, col);
                guiCell = new GUICell(pos);
                this.board.setCell(pos, guiCell);

                IContentProducer contentProducer = gameServer.getContentProducer(pos);
                assert contentProducer != null;
                cell.addCellListener(guiCell);
                guiCell.setCell(cell);
            }
        }
    }

    @Override
    public void listenCommand(Point pos) {
        Input input = new Input(pos);
        this.fireInput(input);
    }

    @Override
    public void addInputListener(InputListener gameServer) {
        this.gameServer = (IGame) gameServer;
    }

    @Override
    public void fireInput(Input input) {
        this.gameServer.listenInput(input);
    }

    @Override
    public void listenOutput(Output output) {
        this.output = output;
        this.status.update(output);
        if (output.getGameCondition() == GameCondition.END) {
            this.parentScreen.end();
        }
    }
}
