import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JController {
    JController() throws InterruptedException {
        // Initializes JFrame and adds favicon
        JFrame window = new JFrame("Taalt! – The Game");
        window.setSize(new Dimension(500, 500));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setIconImage(tk.getImage("../assets/icon32x32.png"));

        // Initializes menu
        JGameMenu menu = new JGameMenu();
        window.getContentPane().add(menu);
        menu.setVisible(true);

        // Initializes board
        JBoard board = new JBoard(4, 4);
        window.getContentPane().add(board);
        board.setVisible(true);


        /*
         * JButton button = new JButton("Change size");
         * button.setSize(100, 100);
         * window.getContentPane().add(BorderLayout.CENTER, button);
         * MouseListener listener = new MouseListener(window);
         * button.addActionListener(listener);
         */

        // Shows everything
        window.setVisible(true);


    }

    class MouseListener implements ActionListener {
        JFrame screen;

        MouseListener(JFrame screen) {
            this.screen = screen;
        }

        public void actionPerformed(ActionEvent event) {
            for (int i = 100; i <= 500; i += 100) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                screen.setSize(new Dimension(i, i));
        }
        }
    }
}

