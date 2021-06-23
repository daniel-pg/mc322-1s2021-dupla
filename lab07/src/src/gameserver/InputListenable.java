package gameserver;

public interface InputListenable {
    public void addInputListener(InputListener listener);
    public void fireInput(Input input);
}
