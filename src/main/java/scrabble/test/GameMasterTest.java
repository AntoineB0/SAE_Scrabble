package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMasterTest {

    @Test
    public void testCalculateScore() {
        // Créer une instance de GameMaster
        Board board = new Board();
        GameMaster gameMaster = new GameMaster(board);

        // Créer une instance de Player
        Rack rack = new Rack();
        Player player1 = new Player("John Doe", rack);
        Player player2 = new Player("John Doe", rack);
        
        Bag bag = new Bag();

        Game game = new Game(board, player1, player2, gameMaster, bag);        

        // Créer une liste de TileInstance
        ArrayList<TileInstance> tiles = new ArrayList<>();
        tiles.add(new TileInstance(Tiles.C));
        tiles.add(new TileInstance(Tiles.A));
        tiles.add(new TileInstance(Tiles.T));

        // Créer une liste de Position
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(7, 7));
        positions.add(new Position(8, 7));
        positions.add(new Position(9, 7));

        // Créer une instance de Move
        int currentTurn = 1;
        Move move = new Move(tiles, positions, game, player1, WordDirection.HORIZONTAL, board, currentTurn);
        game.playMove(move);
        // Vérifier que la méthode calculateScore() renvoie le bon score
        
        int expectedScore = 10 ; 
        
        int actualScore = gameMaster.calculateScore(move);
        assertEquals(expectedScore, actualScore);
        
    }
    @Test
    public void testCalculateHorizontalWordScore() {
        // Créer une instance de GameMaster
        Board board = new Board();
        GameMaster gameMaster = new GameMaster(board);

        // Créer une instance de Player
        Rack rack = new Rack();
        Player player1 = new Player("John Doe", rack);
        Player player2 = new Player("John Doe", rack);
        
        Bag bag = new Bag();

        Game game = new Game(board, player1, player2, gameMaster, bag);        

        // Créer une liste de TileInstance
        ArrayList<TileInstance> tiles = new ArrayList<>();
        tiles.add(new TileInstance(Tiles.C));
        tiles.add(new TileInstance(Tiles.A));
        tiles.add(new TileInstance(Tiles.T));

        // Créer une liste de Position
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(5, 5));
        positions.add(new Position(6, 5));
        positions.add(new Position(7, 5));
        
        board.getSquare(5, 5).setMultiplier(Multiplier.TRIPLE_WORD);
        board.getSquare(6, 5).setMultiplier(Multiplier.TRIPLE_LETTER);
        // Créer une instance de Move
        int currentTurn = 1;
        Move move = new Move(tiles, positions, game, player1, WordDirection.HORIZONTAL, board, currentTurn);
        game.playMove(move);
        // Vérifier que la méthode calculateScore() renvoie le bon score
        
        int expectedScore = 21 ; 
        
        int actualScore = gameMaster.calculateScore(move);
        assertEquals(expectedScore, actualScore);
    }
}
