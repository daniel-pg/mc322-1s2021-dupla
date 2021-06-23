package gameclient;

import gameserver.Cell;

import java.awt.*;
import javax.swing.*;

public class GUIBoard extends JPanel implements ICommandReceiver, ICommandProducer {
    ICommandReceiver listener;
    GUICell[][] matrix;
    int rows, cols;

    public GUIBoard(int rows, int cols, int width, int height) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(rows, cols, 3, 3));
    }

    public boolean insideBoard(Point pos) {
        boolean rowInside = (pos.x >= 0 && pos.x < this.rows);
        boolean colInside = (pos.y >= 0 && pos.y < this.cols);
        return rowInside && colInside;
    }

    public boolean isEmpty(Point pos) {
        return this.insideBoard(pos) && (this.matrix[pos.x][pos.y] == null);
    }

    public void setCell(Point pos, GUICell cell) {
        if (isEmpty(pos)) {
            cell.addCommandReceiver(this);
            this.matrix[pos.x][pos.y] = cell;
            this.add(cell);
        }
    }

    @Override
    public void addCommandListener(ICommandReceiver listener) {
        this.listener = listener;
    }

    @Override
    public void listenCommand(Point pos) {
        this.fireCommand(pos);
    }

    @Override
    public void fireCommand(Point pos) {
        this.listener.listenCommand(pos);
    }


}
