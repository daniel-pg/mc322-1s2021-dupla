package gameserver;

import java.util.ArrayList;

public class Options {
    int players;
    ArrayList<String> names;
    int m;
    int n;
    int k;
    boolean gravity;

    public Options() {
        this.players = 2;
        this.names = new ArrayList<String>();
        this.m = 3;
        this.n = 3;
        this.k = 3;
        this.gravity = false;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public void addName(String name) {
        this.names.add(name);
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public String getName(int index) {
        return this.names.get(index);
    }

    public int getPlayers() {
        return this.players;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getK() {
        return this.k;
    }

    public boolean getGravity() {
        return this.gravity;
    }
}
