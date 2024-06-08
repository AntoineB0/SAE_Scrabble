package scrabble.model;

public class Position {
    Integer x;
    Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Integer getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}