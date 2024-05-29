package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.application.MainScrabble.*;
import scrabble.model.Bag;
import scrabble.model.Board;
import scrabble.model.Game;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Board board;
    private Bag bag;
    private Player player1;
    private Player player2;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
        bag = new Bag();
        player1 = new Player("James", new Rack());
        player2 = new Player("Rodrigo", new Rack());
        game = new Game(board, player1, player2, bag);
        
    }

    @Test
    public void testInitializeGame() {
        assertEquals(7, player1.getRack().getTilesOnRack().size());
        assertEquals(7, player2.getRack().getTilesOnRack().size());
        assertEquals(player2, game.getActualPlayer());
    }
    
    @Test
    public void testDrawTileFromBag() {
        int initialBagSize = bag.getTileListLenght();
        Tiles tile = bag.drawTile();
        assertNotNull(tile);
        assertEquals(initialBagSize - 1, bag.getTileListLenght());
    }

    @Test
    public void testAddTileToRack() {
        Rack rack = new Rack();
        rack.addTile(bag);
        assertEquals(rack.RackSize, rack.getTilesOnRack().size());
    }

    @Test
    public void testRemoveTileFromRack() {
        Rack rack = new Rack();
        rack.addTile(bag);
        int initialRackSize = rack.getTilesOnRack().size();
        Tiles tile = rack.getTilesOnRack().get(0);
        rack.removeTile(tile, bag);
        assertEquals(initialRackSize - 1, rack.getTilesOnRack().size());
    }

    @Test
    public void testSwapTileInRack() {
        Rack rack = new Rack();
        rack.addTile(bag);
        int initialRackSize = rack.getTilesOnRack().size();
        Tiles tile = rack.getTilesOnRack().get(0);
        rack.swapTile(0, bag);
        assertEquals(initialRackSize, rack.getTilesOnRack().size());
        assertFalse(rack.getTilesOnRack().contains(tile));
    }
    
    @Test
    public void testPlayMove() {
        Rack rack = player1.getRack();
        rack.getTilesOnRack().clear(); // Vide le rack

        rack.addSpecificTile(Tiles.O, bag);
        rack.addSpecificTile(Tiles.U, bag);
        rack.addSpecificTile(Tiles.I, bag);
        rack.addSpecificTile(Tiles.O, bag);
        rack.addSpecificTile(Tiles.U, bag);
        rack.addSpecificTile(Tiles.I, bag);

        Move move = new Move(player1, board, "OUI", WordDirection.HORIZONTAL, 7, 7);
        rack.printRack();
        assertTrue(move.canBePlaced());
        
        game.playMove(move);
        
        assertEquals('O', board.getSquare(7, 7).getTile().toChar());
        assertEquals('U', board.getSquare(7, 8).getTile().toChar());
        assertEquals('I', board.getSquare(7, 9).getTile().toChar());

    }


    @Test
    public void testCalculateScore() {
        Rack rack = player1.getRack();
        rack.addSpecificTile(Tiles.O, bag);
        rack.addSpecificTile(Tiles.U, bag);
        rack.addSpecificTile(Tiles.I, bag);
        

        Move move = new Move(player1, board, "OUI", WordDirection.HORIZONTAL, 7, 7);
        game.playMove(move);
        int score = move.calculateScore();
        assertEquals(3, score); // Assuming no multipliers, A=1, P=3, P=3, L=1, E=1
    }
}
