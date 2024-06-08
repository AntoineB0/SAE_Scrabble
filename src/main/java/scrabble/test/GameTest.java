package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTest {

    @Test
    public void testPrintGameStatus() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create game components
        Board board = new Board();
        Bag bag = new Bag();
        Player player1 = new Player("Player 1", 0, new Rack());
        Player player2 = new Player("Player 2", 0, new Rack());
        GameMaster gameMaster = new GameMaster(board);
        Game game = new Game(board, player1, player2, gameMaster, bag);

        // Set up the expected output
        String expectedOutput = "Player 1\n" +
                "  | | | | | | | | | | | | | | | | | | | | | | | | | | | | |\n" +
                "Jetons sur le chevalet :\n" +
                "\n";

        game.printGameStatus();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testAskAction() {
        // Simulate user input
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Create game components
        Board board = new Board();
        Bag bag = new Bag();
        Player player1 = new Player("Player 1", 0, new Rack());
        Player player2 = new Player("Player 2", 0, new Rack());
        GameMaster gameMaster = new GameMaster(board);
        Game game = new Game(board, player1, player2, gameMaster, bag);

        assertEquals(1, game.askAction());
    }
    
    @Test
    public void testGettersAndSetters() {
        // Create game components
        Board board = new Board();
        Bag bag = new Bag();
        Player player1 = new Player("Player 1", 0, new Rack());
        Player player2 = new Player("Player 2", 0, new Rack());
        GameMaster gameMaster = new GameMaster(board);
        Game game = new Game(board, player1, player2, gameMaster, bag);

        // Test getters
        assertEquals(board, game.getBoard());
        assertEquals(bag, game.getBag());
        assertEquals(1, game.getTurn());
        assertEquals(player1, game.getPlayer1());
        assertEquals(player2, game.getPlayer2());

        // Test setters
        game.setTurn(5);
        assertEquals(5, game.getTurn());

    }

    
    @Test
    public void testAskSwap() {
        String input = "1\n1\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Board board = new Board();
        Player player1 = new Player("James", new Rack());
        Player player2 = new Player("Rodrigo", new Rack());
        GameMaster gameMaster = new GameMaster(board);
        Bag bag = new Bag();
        Game game = new Game(board, player1, player2, gameMaster, bag);
        game.askSwap();
        assertEquals(Rack.RackSize, player1.getRack().getTilesOnRack().size());
    }
    
    @Test
    public void testPlayMove() {
        // Création d'un plateau de jeu
        Board board = new Board();
        // Création de joueurs
        Player player1 = new Player("James", new Rack());
        Player player2 = new Player("Rodrigo", new Rack());
        // Création d'un GameMaster
        GameMaster gameMaster = new GameMaster(board);
        // Création d'un sac de tuiles
        Bag bag = new Bag();
        // Création du jeu
        Game game = new Game(board, player1, player2, gameMaster, bag);

        // Création d'un mouvement (simulé)
        ArrayList<TileInstance> tilesList = new ArrayList<>();
        tilesList.add(new TileInstance(Tiles.A));
        tilesList.add(new TileInstance(Tiles.B));
        ArrayList<Position> posList = new ArrayList<>();
        posList.add(new Position(7, 7)); // Central position on the board
        posList.add(new Position(7, 8));
        Move move = new Move(tilesList, posList, game, player1, WordDirection.HORIZONTAL, board, 1);

        // Execute the method to be tested
        game.playMove(move);

        // Verify if tiles were correctly placed on the board
        assertEquals(Tiles.A, board.getSquare(7, 7).getTile());
        assertEquals(Tiles.B, board.getSquare(7, 8).getTile());


    }
    @Test
    public void testGivePoint() {
        // Create a game board
        Board board = new Board();
        // Create players
        Player player1 = new Player("James", new Rack());
        Player player2 = new Player("Rodrigo", new Rack());
        // Create a GameMaster
        GameMaster gameMaster = new GameMaster(board);
        // Create a bag of tiles
        Bag bag = new Bag();
        // Create the game
        Game game = new Game(board, player1, player2, gameMaster, bag);

        // Create a simulated move
        // Let's say this move scores 10 points
        int expectedScore = 10;
		player1.addScore(10); 

        // Execute the method to be tested

        // Verify if the player's score was correctly updated
        assertEquals(expectedScore, player1.getScore());
    }
    
    @Test
    public void testAskTiles() {
        Game game = new Game(null, null, null, null, null);
        // Simuler une entrée utilisateur pour une tuile quelconque
        char expectedLetter = 'A'; // Supposez que l'utilisateur entre 'A'
        TileInstance tileInstance = new TileInstance(Tiles.charToTile(expectedLetter));
        TileInstance actualTileInstance = game.askTiles();
        assertEquals(tileInstance.toString(), actualTileInstance.toString());
    }
}
