package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;

public class BagTest {

    private Bag bag;

    @BeforeEach
    public void setUp() {
        bag = new Bag();
    }

    @Test
    public void testInitializeBag() {
        int expectedTotalTiles = 102; // Ajustez ce nombre en fonction du nombre total de tuiles dans votre sac
        assertEquals(expectedTotalTiles, bag.getTileListLenght(), "Le sac doit contenir le nombre correct de tuiles après initialisation.");
    }

    @Test
    public void testDrawTile() {
        int initialSize = bag.getTileListLenght();
        TileInstance tile = bag.drawTile();
        assertNotNull(tile, "Tirer une tuile ne doit pas renvoyer null.");
        assertEquals(initialSize - 1, bag.getTileListLenght(), "La taille du sac doit diminuer d'une unité après avoir tiré une tuile.");
    }

    @Test
    public void testDrawTileFromEmptyBag() {
        while (bag.getTileListLenght() > 0) {
            bag.drawTile();
        }
        TileInstance tile = bag.drawTile();
        assertNull(tile, "Tirer une tuile d'un sac vide doit renvoyer null.");
    }

    @Test
    public void testAddTile() {
        TileInstance tile = new TileInstance(Tiles.A);
        int initialSize = bag.getTileListLenght();
        bag.addTile(tile);
        assertEquals(initialSize + 1, bag.getTileListLenght(), "La taille du sac doit augmenter d'une unité après avoir ajouté une tuile.");
        assertTrue(bag.getTileList().contains(tile), "Le sac doit contenir la tuile ajoutée.");
    }

    @Test
    public void testShuffleBag() {
        ArrayList<TileInstance> initialTiles = new ArrayList<>(bag.getTileList());
        bag.drawTile(); // Déclenche le mélange
        ArrayList<TileInstance> shuffledTiles = new ArrayList<>(bag.getTileList());
        assertNotEquals(initialTiles, shuffledTiles, "L'ordre des tuiles doit être différent après avoir tiré (mélangé) une tuile.");
    }
}
