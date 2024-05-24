package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.Board;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveTest {

    private Board board;
    private Player player;
    private Bag bag;
    private Rack rack;

    @BeforeEach
    public void setUp() {
        board = new Board();
        rack = new Rack();
        bag = new Bag();
        player = new Player("TestPlayer", rack);
        initializeRack();
    }
    
    private void initializeRack() {
        // Fill the rack with specific tiles for testing
        rack.addSpecificTile(Tiles.A, bag);
        rack.addSpecificTile(Tiles.B, bag);
        rack.addSpecificTile(Tiles.C, bag);
        rack.addSpecificTile(Tiles.D, bag);
        rack.addSpecificTile(Tiles.E, bag);
        rack.addSpecificTile(Tiles.F, bag);
        rack.addSpecificTile(Tiles.G, bag);
    }

    @Test
    public void testHasRequiredTiles() {
        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, 0, 0);
        List<Character> charList = new ArrayList<>(Arrays.asList('F', 'A', 'C', 'E'));
        assertTrue(move.hasRequiredTiles(charList));
    }

    @Test
    public void testHasRequiredTilesInsufficientTiles() {
        Move move = new Move(player, board, "FACED", WordDirection.HORIZONTAL, 0, 0);
        List<Character> charList = new ArrayList<>(Arrays.asList('F', 'A', 'C', 'E', 'D'));
        assertFalse(move.hasRequiredTiles(charList));
    }

    @Test
    public void testCanBePlacedHorizontal() {
        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, 0, 0);
        assertTrue(move.canBePlaced());
    }

    @Test
    public void testCanBePlacedVertical() {
        Move move = new Move(player, board, "FACE", WordDirection.VERTICAL, 0, 0);
        assertTrue(move.canBePlaced());
    }

    @Test
    public void testCannotBePlacedOutOfBounds() {
        Move move = new Move(player, board, "LONGWORDTHATISOUTOFBOUNDS", WordDirection.HORIZONTAL, 0, 0);
        assertFalse(move.canBePlaced());
    }

    @Test
    public void testCannotBePlacedWithConflict() {
        board.getSquare(0, 1).setTile(Tiles.B); // Place 'B' at position (0,1)
        Move move = new Move(player, board, "ABC", WordDirection.HORIZONTAL, 0, 0);
        assertFalse(move.canBePlaced());
    }

    @Test
    public void testCalculateScore() {
        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, 0, 0);
        assertEquals(9, move.calculateScore());
    }

    @Test
    public void testCalculateScoreWithExistingTiles() {
        board.getSquare(0, 1).setTile(Tiles.A); // Place 'A' at position (0,1)
        Move move = new Move(player, board, "FCE", WordDirection.HORIZONTAL, 0, 0);
        assertEquals(8, move.calculateScore());
    }

    @Test
    public void testToTiles() {
        List<Tiles> expected = Arrays.asList(Tiles.F, Tiles.A, Tiles.C, Tiles.E);
        assertEquals(expected, Move.toTiles("FACE"));
    }
}
