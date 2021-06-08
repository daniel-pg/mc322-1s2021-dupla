import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JGameMenu extends JPanel {
    public JGameMenu() {
        super();
        JTextArea nameField = new JTextArea("What is your name?");
        this.add(nameField);
    }
}
