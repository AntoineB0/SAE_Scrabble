package scrabble.model;

public class TileInstance {
    private Tiles type;
    private int value;
    private boolean wasAJoker;

    public TileInstance(Tiles type) {
        this.type = type;
        this.value = type.getValue();
        this.wasAJoker = false;
    }

    public boolean isJoker() {
        return type.toChar() == 'j';
    }

    public Tiles getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean getWasAJoker() {
        return wasAJoker;
    }

    public void setWasAJoker(boolean wasAJoker) {
        this.wasAJoker = wasAJoker;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char toChar() {
        return type.toChar();
    }

    @Override
    public String toString() {
        return type.name();
    }
}