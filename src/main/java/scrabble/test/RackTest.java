package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.Rack;
import scrabble.model.Tiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

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
        Tiles tile = Tiles.A;
        int initialSize = rack.getTilesOnRack().size();
        rack.addSpecificTile(tile, bag);
        assertEquals(initialSize + 1, rack.getTilesOnRack().size());
        assertTrue(rack.getTilesOnRack().contains(tile));
    }

    @Test
    public void testAddSpecificTile() {
        Tiles tile = Tiles.A;
        rack.addSpecificTile(tile, bag);
        assertEquals(1, rack.getTilesOnRack().size());
        assertTrue(rack.getTilesOnRack().contains(tile));
    }

    @Test
    public void testSwapTile() {
        // Ajouter quelques tuiles au chevalet
        rack.addSpecificTile(Tiles.A, bag);
        rack.addSpecificTile(Tiles.B, bag);
        rack.addSpecificTile(Tiles.C, bag);

        // Vérifier le nombre de tuiles avant l'échange
        int initialSize = rack.getTilesOnRack().size();

        // Echanger une tuile avec une nouvelle tuile du sac
        rack.swapTile(1, bag);

        // Vérifier que la taille reste la même et que la tuile a été remplacée
        assertEquals(initialSize, rack.getTilesOnRack().size());
        assertFalse(rack.getTilesOnRack().contains(Tiles.B));
    }

    @Test
    public void testRemoveTileByTile() {
        // Ajouter quelques tuiles au chevalet
        rack.addSpecificTile(Tiles.A, bag);
        rack.addSpecificTile(Tiles.B, bag);
        rack.addSpecificTile(Tiles.C, bag);

        // Vérifier le nombre de tuiles avant la suppression
        int initialSize = rack.getTilesOnRack().size();

        // Supprimer une tuile spécifique
        rack.removeTile(Tiles.B, bag);

        // Vérifier que la taille a diminué et que la tuile a été supprimée
        assertEquals(initialSize - 1, rack.getTilesOnRack().size());
        assertFalse(rack.getTilesOnRack().contains(Tiles.B));
    }
    
    @Test
    public void testPrintRack() {
        // Rediriger la sortie standard vers un flux de sortie pour la vérification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Ajouter quelques tuiles au chevalet
        rack.addSpecificTile(Tiles.A, bag);
        rack.addSpecificTile(Tiles.B, bag);
        rack.addSpecificTile(Tiles.C, bag);

        // Appeler la méthode printRack
        rack.printRack();

        // Récupérer la sortie
        String printedRack = outputStream.toString().trim();

        // Vérifier que le chevalet a été correctement imprimé
        assertEquals("Jetons sur le chevalet :\nA B C", printedRack);
    }

    @Test
    public void testAddTileNoArgs() {
    	int initialSize = rack.getTilesOnRack().size();

        rack.addTile(bag);

        assertEquals(initialSize + 1, rack.getTilesOnRack().size());
    }
}
