package gameserver;

public interface OutputListenable {
    public void addOutputListener(OutputListener listener);
    public void removeOutputListener(OutputListener listener);
    public void fireOutput();
}
