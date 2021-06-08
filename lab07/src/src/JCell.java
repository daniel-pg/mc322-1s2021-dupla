import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class JCell extends JButton {
    public JCell() {
        super();
        this.setBackground(Color.GRAY);
        this.setBorder(new EtchedBorder(0));
    }
}
