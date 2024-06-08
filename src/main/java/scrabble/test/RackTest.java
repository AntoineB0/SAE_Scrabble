package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RackTest {

    private Rack rack;
    private Bag bag;

    @BeforeEach
    public void setUp() {
        rack = new Rack();
        bag = new Bag();
    }

    @Test
    public void testAddTile() {
        rack.addTile(bag);
        assertEquals(Rack.RackSize, rack.getTilesOnRack().size());
    }

    @Test
    public void testAddTileOnRank() {
        rack.addTile(bag);
        int initialSize = rack.getTilesOnRack().size();

        rack.addTileOnRank(1, bag); // Utilisation correcte de la méthode
        assertEquals(initialSize + 1, rack.getTilesOnRack().size());
    }


    @Test
    public void testSwapTileOnRank() {
        rack.addTile(bag);
        rack.addTile(bag);

        TileInstance tileToSwap = rack.getTilesOnRack().get(0);
        int initialSize = rack.getTilesOnRack().size();

        rack.swapTileOnRank(0, bag);
        assertEquals(initialSize, rack.getTilesOnRack().size()); // Size remains the same
        assertFalse(rack.getTilesOnRack().contains(tileToSwap)); // Tile is removed from the rack
    }

    @Test
    public void testAddSpecificTile() {
        rack.addSpecificTile(Tiles.A, bag);
        assertEquals(1, rack.getTilesOnRack().size());
    }

    @Test
    public void testRemoveTile() {
        rack.addTile(bag);
        int initialSize = rack.getTilesOnRack().size();

        TileInstance tileToRemove = rack.getTilesOnRack().get(0);
        rack.removeTile(tileToRemove);
        assertEquals(initialSize - 1, rack.getTilesOnRack().size());
    }

    @Test
    public void testRemoveTileWithBag() {
        rack.addTile(bag);
        int initialSize = rack.getTilesOnRack().size();

        TileInstance tileToRemove = rack.getTilesOnRack().get(0);
        rack.removeTile(tileToRemove, bag);
        assertEquals(initialSize - 1, rack.getTilesOnRack().size()); // La taille doit diminuer après la suppression
        assertFalse(rack.getTilesOnRack().contains(tileToRemove)); // La tuile ne doit plus être présente sur le chevalet
        assertTrue(bag.getTileList().contains(tileToRemove)); // La tuile doit être ajoutée au sac
    }

    @Test
    public void testFindJoker() {
        rack.addSpecificTile(Tiles.j, bag); // Ajoute un joker sur le chevalet
        TileInstance jokerTile = rack.findJoker();
        assertNotNull(jokerTile); // Le joker doit être trouvé sur le chevalet
        assertTrue(jokerTile.isJoker()); // L'objet retourné doit être un joker
    }

    
    @Test
    public void testRemoveExistingTile() {
        rack.addTile(bag);
        int initialSize = rack.getTilesOnRack().size();

        TileInstance tileToRemove = rack.getTilesOnRack().get(0);
        rack.removeTile(tileToRemove);
        assertEquals(initialSize - 1, rack.getTilesOnRack().size()); // La taille doit diminuer après la suppression
        assertFalse(rack.getTilesOnRack().contains(tileToRemove)); // La tuile ne doit plus être présente sur le chevalet
    }

    @Test
    public void testRemoveNonExistingTile() {
        int initialSize = rack.getTilesOnRack().size();

        TileInstance nonExistingTile = new TileInstance(Tiles.A); // Crée une tuile qui n'est pas sur le chevalet
        rack.removeTile(nonExistingTile);
        assertEquals(initialSize, rack.getTilesOnRack().size()); // La taille ne doit pas changer
        // Vous pouvez également vérifier si le message d'erreur est correctement renvoyé
    }

    @Test
    public void testPrintRack() {
        // Crée un chevalet avec quelques tuiles
        rack.addSpecificTile(Tiles.A, bag);
        rack.addSpecificTile(Tiles.B, bag);
        rack.addSpecificTile(Tiles.C, bag);

        // Redirige la sortie standard vers un flux de sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Appelle la méthode à tester
        rack.printRack();

        // Vérifie que la sortie correspond à ce qui est attendu
        String expectedOutput = "Jetons sur le chevalet : A B C \n";
        assertEquals(expectedOutput, outContent.toString());
    }


    // You can add more test cases as needed

}
