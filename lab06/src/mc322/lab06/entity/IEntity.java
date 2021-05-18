package mc322.lab06.entity;

public interface IEntity {
    public int getPrintPriority();

    public String toString();

    public int[] getPos();

    public void setPos(int[] position);
}
