package gameclient;

import java.awt.*;

public interface ICommandProducer {
    public void addCommandReceiver(ICommandReceiver commandReceiver);
    public void fireCommand(Point pos);
}
