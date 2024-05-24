package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Board;
import scrabble.model.Move;
import scrabble.model.Multiplier;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Square;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;

public class MultiplierTest {

    private Square square;

    @BeforeEach
    public void setUp() {
        // Set up a square with a tile and a multiplier
        square = new Square(Tiles.A, Multiplier.DOUBLE_LETTER, false, 0, 0);
    }

    @Test
    public void testMultiplierEnumValues() {
        // Test if the Multiplier enum contains the correct values
        Multiplier[] expected = {Multiplier.TRIPLE_LETTER, Multiplier.TRIPLE_WORD, Multiplier.DOUBLE_LETTER, Multiplier.DOUBLE_WORD};
        Multiplier[] actual = Multiplier.values();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSquareWithMultiplier() {
        // Test if the square correctly stores and retrieves the multiplier
        assertEquals(Multiplier.DOUBLE_LETTER, square.getMultiplier());
    }

    @Test
    public void testSetMultiplier() {
        // Test setting a new multiplier on the square
        square.setMultiplier(Multiplier.TRIPLE_WORD);
        assertEquals(Multiplier.TRIPLE_WORD, square.getMultiplier());
    }

    @Test
    public void testCalculateScoreWithMultiplier() {
        // Set up a move that uses the multiplier
        Board board = new Board();
        Player player = new Player("TestPlayer", new Rack());
        board.getSquare(0, 0).setTile(Tiles.A); // A is on DOUBLE_LETTER
        board.getSquare(0, 0).setMultiplier(Multiplier.DOUBLE_LETTER);
        Move move = new Move(player, board, "A", WordDirection.HORIZONTAL, 0, 0);
        
        // TODO add multiplier (v4)
        int score = move.calculateScore();
        assertEquals(Tiles.A.getValue(), score);
    }

    @Test
    public void testCalculateScoreWithWordMultiplier() {
        // Set up a move that uses the word multiplier
        Board board = new Board();
        Player player = new Player("TestPlayer", new Rack());
        board.getSquare(0, 0).setTile(Tiles.A); // A is on TRIPLE_WORD
        board.getSquare(0, 0).setMultiplier(Multiplier.TRIPLE_WORD);
        Move move = new Move(player, board, "A", WordDirection.HORIZONTAL, 0, 0);
        
        // TODO add multiplier (v4)
        int score = move.calculateScore();
        assertEquals(Tiles.A.getValue(), score); 
    }
}
