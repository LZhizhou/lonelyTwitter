package ca.ualberta.cs.lonelytwitter.models;

public abstract class GraphObject {

    private int x,y;

    GraphObject(){
        this.x = 0;
        this.y = 0;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
