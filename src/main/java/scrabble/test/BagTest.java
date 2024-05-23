package scrabble.test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.Tiles;

import java.util.ArrayList;
import java.util.Collections;

public class BagTest {
    private Bag bag;

    @BeforeEach
    public void setUp() {
        bag = new Bag();
    }

    @Test
    public void testInitializeTiles() {
        ArrayList<Tiles> tileList = bag.getTileList();
        int totalTiles = 0;

        for (Tiles tile : Tiles.values()) {
            int occurrences = Collections.frequency(tileList, tile);
            assertEquals(tile.getNumberInBag(), occurrences);
            totalTiles += occurrences;
        }

        assertEquals(102, totalTiles, "The total number of tiles should be 102.");
    }

    @Test
    public void testDrawTile() {
        int initialSize = bag.getTileListLenght();
        Tiles drawnTile = bag.drawTile();
        assertNotNull(drawnTile);
        assertEquals(initialSize - 1, bag.getTileListLenght());
    }

    @Test
    public void testDrawTileEmptyBag() {
        bag.getTileList().clear();
        assertEquals(0, bag.getTileListLenght());
        Tiles drawnTile = bag.drawTile();
        assertNull(drawnTile, "Drawing from an empty bag should return null.");
    }


    @Test
    public void testAddTile() {
        Tiles tile = Tiles.A;
        int initialSize = bag.getTileListLenght();
        bag.addTile(tile);
        assertEquals(initialSize + 1, bag.getTileListLenght());
        assertTrue(bag.getTileList().contains(tile));
    }

    @Test
    public void testGetTileList() {
        ArrayList<Tiles> tileList = bag.getTileList();
        assertNotNull(tileList);
        assertEquals(102, tileList.size());
    }

    @Test
    public void testGetTileListLength() {
        assertEquals(102, bag.getTileListLenght());
    }
}
