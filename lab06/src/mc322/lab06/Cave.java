package mc322.lab06;

public class Cave {
    private CaveRoom[][] caveRooms;
    private final int width, height;

    Cave() {
        this(4, 4);
    }

    Cave(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
