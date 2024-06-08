package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scrabble.model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Move move;
    private Player player;
    private Game game;
    private Board board;
    private Bag bag;

    @BeforeEach
    public void setUp() {
        board = new Board();
        player = new Player("Player1", 0, new Rack());
        game = new Game(board, player, new Player("Player2", 0, new Rack()), new GameMaster(board), new Bag());
        bag = new Bag();
        ArrayList<TileInstance> tilesList = new ArrayList<>();
        tilesList.add(new TileInstance(Tiles.A));
        tilesList.add(new TileInstance(Tiles.B));
        tilesList.add(new TileInstance(Tiles.C));
        ArrayList<Position> posList = new ArrayList<>();
        posList.add(new Position(7, 7));
        posList.add(new Position(8, 7));
        posList.add(new Position(9, 7));
        move = new Move(tilesList, posList, game, player, WordDirection.HORIZONTAL, board, 1);
    }

    @Test
    public void testHasRequiredTiles() {
        assertTrue(move.hasRequiredTiles());
    }

    @Test
    public void testCheckFirstMove() {
        assertTrue(move.checkFirstMove());
    }

    @Test
    public void testCanBePlaced() {
        assertTrue(move.canBePlaced());
    }

    @Test
    public void testCheckSiColler() {
        assertTrue(move.checkSiColler());
    }

    @Test
    public void testCheckForConflict() {
        assertTrue(move.checkForConflict());
    }

    @Test
    public void testCheckAdjacentTiles() {
        assertTrue(move.checkAdjacentTiles());
    }

    // Add more tests as needed
}
