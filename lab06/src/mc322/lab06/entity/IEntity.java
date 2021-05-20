package mc322.lab06.entity;

/**
 * Interaface para objetos Entity
 */
public interface IEntity {
    public int getPrintPriority();

    public String toString();

    public int[] getPos();

    public void setPos(int[] position);
}
