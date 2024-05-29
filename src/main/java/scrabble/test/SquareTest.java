package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.Square;
import scrabble.model.Tiles;
import scrabble.model.Multiplier;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    public void testSquareInitializationWithTileAndMultiplier() {
        Square square = new Square(Tiles.A, Multiplier.DOUBLE_WORD, false, 1, 1);

        assertEquals(Tiles.A, square.getTile());
        assertEquals(Multiplier.DOUBLE_WORD, square.getMultiplier());
        assertFalse(square.isEmpty());
        assertEquals(1, square.getX());
        assertEquals(1, square.getY());
    }

    @Test
    public void testSquareInitializationWithoutTileAndMultiplier() {
        Square square = new Square(2, 2);

        assertNull(square.getTile());
        assertNull(square.getMultiplier());
        assertTrue(square.isEmpty());
        assertEquals(2, square.getX());
        assertEquals(2, square.getY());
    }

    @Test
    public void testSetTile() {
        Square square = new Square(3, 3);
        square.setTile(Tiles.B);

        assertEquals(Tiles.B, square.getTile());
        assertFalse(square.isEmpty());
    }

    @Test
    public void testSetMultiplier() {
        Square square = new Square(4, 4);
        square.setMultiplier(Multiplier.TRIPLE_LETTER);

        assertEquals(Multiplier.TRIPLE_LETTER, square.getMultiplier());
    }

    @Test
    public void testSetEmpty() {
        Square square = new Square(5, 5);
        square.setEmpty(true);

        assertTrue(square.isEmpty());
    }

    @Test
    public void testToString() {
        Square square = new Square(Tiles.C, Multiplier.TRIPLE_WORD, false, 6, 6);
        String expectedString = "Square [tile=C, multiplier=TRIPLE_WORD, isEmpty=false, x=6, y=6]";

        assertEquals(expectedString, square.toString());
    }
}
