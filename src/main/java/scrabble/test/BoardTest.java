package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Board;
import scrabble.model.Square;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        board = new Board();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testBoardInitialization() {
        assertNotNull(board);
        assertEquals(15, board.getRows());
        assertEquals(15, board.getColumns());
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
        Square square = new Square(null, null, 0, 0);
        board.setSquare(square);
        assertEquals(square, board.getSquare(0, 0));
    }
    


    @Test
    public void testGetRows() {
        int rows = board.getRows();
        assertEquals(15, rows);
    }

    @Test
    public void testGetColumns() {
        int columns = board.getColumns();
        assertEquals(15, columns);
    }
    
   

    @Test

    public void testPrintBoard() {
        System.setOut(new PrintStream(outputStreamCaptor));

        Board board = new Board();
        board.printBoard();

        // Define the expected output here based on your printBoard implementation
        String expectedOutput = "  |A|B|C|D|E|F|G|H|I|J|K|L|M|N|O\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

}
