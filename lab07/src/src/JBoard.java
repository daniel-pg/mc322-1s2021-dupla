import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JBoard extends JPanel {
    public JBoard(int n, int m) {
        super();
        this.setLayout(new GridLayout(n, m));
        for (int i = 0; i < n * m; i++) {
            this.add(new JButton("a"));
        }
    }
}
