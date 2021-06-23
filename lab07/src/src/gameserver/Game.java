package gameserver;

import java.awt.*;
import java.util.ArrayList;

public class Game implements IGame {
    ArrayList<OutputListener> outputListenerList;
    GameStatus status;
    Options options;
    Board board;

    public Game(Options options) {
        outputListenerList = new ArrayList<OutputListener>();
        this.status = new GameStatus(options);
        this.options = options;

        Dimension boardDimension = new Dimension(options.getM(), options.getN());
        this.board = new Board(this.options.getK(), boardDimension);
    }

    @Override
    public IContentProducer getCell(Point pos) {
        return this.board.getCell(pos);
    }

    @Override
    public void listenInput(Input input) {
        Point pos = input.getInput();
        Player currentPlayer = this.status.getCurrentPlayer();
        this.board.fillCell(pos, currentPlayer);
        boolean end = this.board.hasWon(pos, currentPlayer);
        if (end) {
           this.status.endGame();
        } else {
            this.status.nextRound();
        }
        this.fireOutput();
    }

    @Override
    public void fireOutput() {
        Output output = this.status.getOutput();
        for (OutputListener listener: this.outputListenerList) {
            listener.listenOutput(output);
        }
    }

    @Override
    public void addOutputListener(OutputListener listener) {
        this.outputListenerList.add(listener);
    }

    @Override
    public void removeOutputListener(OutputListener listener) {
        this.outputListenerList.remove(listener);
    }
}
