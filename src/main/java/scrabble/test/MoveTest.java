package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Bag;
import scrabble.model.Board;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Square;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;
import scrabble.model.Multiplier;


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
        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, , );
        System.out.println(player.getScore());
        assertEquals(9, move.calculateScore());
    }

    @Test
    public void testCalculateScoreWithExistingTiles() {
    	rack.printRack();

        board.getSquare(8, 9).setTile(Tiles.A); // Place 'A' at position (0,1)
        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, 8, 8);
        assertEquals(8, move.calculateScore());
    }

    @Test
    public void testToTiles() {
        List<Tiles> expected = Arrays.asList(Tiles.F, Tiles.A, Tiles.C, Tiles.E);
        assertEquals(expected, Move.toTiles("FACE"));
    }

    // New tests

    @Test
    public void testCanBePlacedOnExistingWord() {
        board.getSquare(7, 7).setTile(Tiles.H); // Place 'H' at the starting point
        Move move = new Move(player, board, "HELLO", WordDirection.HORIZONTAL, 7, 7);
        assertFalse(move.canBePlaced());
    }

    @Test
    public void testCannotBePlacedWithIncorrectTiles() {
        Move move = new Move(player, board, "XYZ", WordDirection.HORIZONTAL, 0, 0);
        assertFalse(move.hasRequiredTiles(Arrays.asList('X', 'Y', 'Z')));
    }

    @Test
    public void testCannotBePlacedIfNotConnected() {
        board.getSquare(7, 7).setTile(Tiles.H); // Place 'H' at the starting point
        Move move = new Move(player, board, "HELLO", WordDirection.HORIZONTAL, 5, 5);
        assertFalse(move.canBePlaced());
    }

    @Test
    public void testCalculateScoreWithMultipliers() {
        // Place 'F' on a double letter score, and 'A' on a double word score
        Square doubleLetterSquare = board.getSquare(8, 8);
        doubleLetterSquare.setMultiplier(Multiplier.DOUBLE_LETTER);

        Square doubleWordSquare = board.getSquare(8, 9);
        doubleWordSquare.setMultiplier(Multiplier.DOUBLE_WORD);

        Move move = new Move(player, board, "FACE", WordDirection.HORIZONTAL, 8, 8);
        assertEquals(26, move.calculateScore()); // F=4*2, A=1, C=3, E=1 -> 4*2 + 1 + 3 + 1 = 13 -> 13 * 2 = 26
    }

    @Test
    public void testCalculateAdjacentWordsScore() {
        board.getSquare(7, 7).setTile(Tiles.H); // Place 'H' at the starting point
        board.getSquare(7, 8).setTile(Tiles.E); // Place 'E' at (7, 8)
        board.getSquare(7, 9).setTile(Tiles.L); // Place 'L' at (7, 9)
        board.getSquare(7, 10).setTile(Tiles.L); // Place 'L' at (7, 10)
        board.getSquare(7, 11).setTile(Tiles.O); // Place 'O' at (7, 11)

        Move move = new Move(player, board, "HEAD", WordDirection.VERTICAL, 7, 7);
        assertEquals(18, move.calculateScore()); // H=4, E=1, A=1, D=2 + HEAD (word multiplier)
    }
}
