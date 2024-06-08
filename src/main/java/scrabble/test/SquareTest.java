package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Multiplier;
import scrabble.model.Square;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;

public class SquareTest {

    private Square square;

    @BeforeEach
    public void setUp() {
        square = new Square(0, 0);
    }

    @Test
    public void testEmptySquare() {
        assertTrue(square.isEmpty());
    }

    @Test
    public void testSetTile() {
        TileInstance tile = new TileInstance(Tiles.A);
        square.setTile(tile);
        assertEquals(tile, square.getTile());
    }

    @Test
    public void testSetMultiplier() {
        Multiplier multiplier = Multiplier.DOUBLE_LETTER;
        square.setMultiplier(multiplier);
        assertEquals(multiplier, square.getMultiplier());
    }

    @Test
    public void testToString() {
        TileInstance tile = new TileInstance(Tiles.A);
        Multiplier multiplier = Multiplier.DOUBLE_LETTER;
        square.setTile(tile);
        square.setMultiplier(multiplier);
        String expectedString = "Square [tile=" + tile.toString() + ", multiplier=" + multiplier.toString() + ", x=0, y=0]";
        assertEquals(expectedString, square.toString());
    }
    
    @Test
    public void testSetX() {
        square.setX(5);
        assertEquals(5, square.getX());
    }

    @Test
    public void testSetY() {
        square.setY(7);
        assertEquals(7, square.getY());
    }

}
