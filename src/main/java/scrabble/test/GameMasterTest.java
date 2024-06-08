package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.Board;
import scrabble.model.GameMaster;
import scrabble.model.Move;
import scrabble.model.Position;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMasterTest {

    @Test
    void testCalculateScore() {
        // Créer un plateau, des joueurs, un maître de jeu, un sac et un jeu
        Board board = new Board();
        GameMaster gameMaster = new GameMaster(board);

        // Créer un mouvement avec des tuiles et des positions
        TileInstance tile1 = new TileInstance(Tiles.A);
        TileInstance tile2 = new TileInstance(Tiles.B);
        Position pos1 = new Position(7, 7);
        Position pos2 = new Position(7, 8);
        Move move = new Move(
                new ArrayList<>(Arrays.asList(tile1, tile2)),
                new ArrayList<>(Arrays.asList(pos1, pos2)),
                null, // game
                null, // player
                WordDirection.HORIZONTAL,
                board,
                1
        );

        // Placer les tuiles sur le plateau
        board.getSquare(7, 7).setTile(tile1);
        board.getSquare(7, 8).setTile(tile2);

        // Calculer le score pour le mouvement
        int score = gameMaster.calculateScore(move);

        // Vérifier si le score calculé est correct
        assertEquals(3, score); // Le score attendu dépend des valeurs de tuile dans la classe Tiles
    }
    
    @Test
    void testCalculateAdjacentWordsScore() {
        // Créer un plateau, des joueurs, un maître de jeu, un sac et un jeu
        Board board = new Board();
        GameMaster gameMaster = new GameMaster(board);

        // Placer quelques tuiles sur le plateau
        TileInstance tile1 = new TileInstance(Tiles.A);
        TileInstance tile2 = new TileInstance(Tiles.B);
        TileInstance tile3 = new TileInstance(Tiles.C);
        board.getSquare(7, 7).setTile(tile1);
        board.getSquare(8, 7).setTile(tile2);
        board.getSquare(9, 7).setTile(tile3);

        // Créer un mouvement pour tester la méthode calculateAdjacentWordsScore
        Move move = new Move(
                new ArrayList<>(Arrays.asList(tile1, tile2, tile3)),
                new ArrayList<>(Arrays.asList(new Position(7, 7), new Position(8, 7), new Position(9, 7))),
                null, // game
                null, // player
                WordDirection.HORIZONTAL,
                board,
                1
        );

        // Calculer le score des mots adjacents
        int score = gameMaster.calculateAdjacentWordsScore(8, 7, 1, tile2, move);

        // Vérifier si le score calculé est correct
        assertEquals(3, score); // Le score attendu dépend des valeurs de tuile dans la classe Tiles
    }
    
    @Test
    void testCalculateVerticalWordScore() {
        // Créer un plateau, des joueurs, un maître de jeu, un sac et un jeu
        Board board = new Board();
        GameMaster gameMaster = new GameMaster(board);

        // Placer quelques tuiles sur le plateau
        TileInstance tile1 = new TileInstance(Tiles.A);
        TileInstance tile2 = new TileInstance(Tiles.B);
        TileInstance tile3 = new TileInstance(Tiles.C);
        board.getSquare(7, 7).setTile(tile1);
        board.getSquare(7, 8).setTile(tile2);
        board.getSquare(7, 9).setTile(tile3);

        // Créer un mouvement pour tester la méthode calculateVerticalWordScore
        Move move = new Move(
                new ArrayList<>(Arrays.asList(tile1, tile2, tile3)),
                new ArrayList<>(Arrays.asList(new Position(7, 7), new Position(7, 8), new Position(7, 9))),
                null, // game
                null, // player
                WordDirection.VERTICAL,
                board,
                1
        );

        // Calculer le score du mot vertical adjacent
        int score = gameMaster.calculateVerticalWordScore(7, 8, tile2);

        // Vérifier si le score calculé est correct
        assertEquals(3, score); // Le score attendu dépend des valeurs de tuile dans la classe Tiles
    }
    
    
}
