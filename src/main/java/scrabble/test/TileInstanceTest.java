package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.TileInstance;
import scrabble.model.Tiles;

import static org.junit.jupiter.api.Assertions.*;

public class TileInstanceTest {

    @Test
    public void testConstructor() {
        Tiles type = Tiles.A;  // Assurez-vous que Tiles.A est une valeur valide
        TileInstance tile = new TileInstance(type);

        assertEquals(type, tile.getType());
        assertEquals(type.getValue(), tile.getValue());
        assertFalse(tile.getWasAJoker());
    }

    @Test
    public void testIsJoker() {
        Tiles jokerTile = Tiles.j;  // Assurez-vous que Tiles.JOKER est une valeur valide et représente un joker
        TileInstance joker = new TileInstance(jokerTile);

        assertTrue(joker.isJoker());

        Tiles nonJokerTile = Tiles.A;  // Assurez-vous que Tiles.A est une valeur valide et ne représente pas un joker
        TileInstance nonJoker = new TileInstance(nonJokerTile);

        assertFalse(nonJoker.isJoker());
    }

    @Test
    public void testGetType() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        assertEquals(type, tile.getType());
    }

    @Test
    public void testGetValue() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        assertEquals(type.getValue(), tile.getValue());
    }

    @Test
    public void testGetWasAJoker() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        assertFalse(tile.getWasAJoker());
    }

    @Test
    public void testSetWasAJoker() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        tile.setWasAJoker(true);
        assertTrue(tile.getWasAJoker());

        tile.setWasAJoker(false);
        assertFalse(tile.getWasAJoker());
    }

    @Test
    public void testSetValue() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        tile.setValue(10);
        assertEquals(10, tile.getValue());

        tile.setValue(1);
        assertEquals(1, tile.getValue());
    }

    @Test
    public void testToChar() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        assertEquals(type.toChar(), tile.toChar());
    }

    @Test
    public void testToString() {
        Tiles type = Tiles.A;
        TileInstance tile = new TileInstance(type);

        assertEquals(type.name(), tile.toString());
    }
}
