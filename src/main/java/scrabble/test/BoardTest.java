package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import scrabble.model.Square;
import scrabble.model.Board;
import scrabble.model.Tiles;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testInitializeBoard() {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                assertNull(board.getSquare(i, j).getTile());
            }
        }
    }

    @Test
    public void testGetSquare() {
        Square square = board.getSquare(0, 0);
        assertNotNull(square);
        assertEquals(0, square.getX());
        assertEquals(0, square.getY());
    }

    @Test
    public void testSetSquare() {
        Square square = new Square(null, null, false, 0, 0);
        board.setSquare(square);
        assertEquals(square, board.getSquare(0, 0));
    }

    @Test
    public void testCenterSquare() {
        Square centerSquare = board.getSquare(7, 7);
        assertNotNull(centerSquare);
        assertNull(centerSquare.getTile());
        assertEquals(7, centerSquare.getX());
        assertEquals(7, centerSquare.getY());
    }

    @Test
    public void testSetAndRetrieveTile() {
        Square square = board.getSquare(7, 7);
        square.setTile(Tiles.A);
        board.setSquare(square);
        assertEquals(Tiles.A, board.getSquare(7, 7).getTile());
    }


    @Test
        public void testPrintBoard() {
            // Create the board
            Board board = new Board();

            // Capture print output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            // Call the printBoard method
            board.printBoard();

            // Restore the original System.out
            System.setOut(originalOut);

            // Expected output
            String expectedOutput =
                "  |A|B|C|D|E|F|G|H|I|J|K|L|M|N|O\n" +
                " 1| | | | | | | | | | | | | | | |\n" +
                " 2| | | | | | | | | | | | | | | |\n" +
                " 3| | | | | | | | | | | | | | | |\n" +
                " 4| | | | | | | | | | | | | | | |\n" +
                " 5| | | | | | | | | | | | | | | |\n" +
                " 6| | | | | | | | | | | | | | | |\n" +
                " 7| | | | | | | | | | | | | | | |\n" +
                " 8| | | | | | | |*| | | | | | | |\n" +
                " 9| | | | | | | | | | | | | | | |\n" +
                "10| | | | | | | | | | | | | | | |\n" +
                "11| | | | | | | | | | | | | | | |\n" +
                "12| | | | | | | | | | | | | | | |\n" +
                "13| | | | | | | | | | | | | | | |\n" +
                "14| | | | | | | | | | | | | | | |\n" +
                "15| | | | | | | | | | | | | | | |\n";

            // Verify the output
            assertEquals(expectedOutput, outContent.toString());
    }
}
