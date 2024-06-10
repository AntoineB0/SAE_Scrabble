package scrabble.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scrabble.model.Bag;
import scrabble.model.Board;
import scrabble.model.Game;
import scrabble.model.GameMaster;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;


public class ScrabbleMainView extends javafx.application.Application{
	public static void main(String[] args) {
		Application.launch(args);

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Rack rackP1 = new Rack();
		Rack rackP2 = new Rack();
		
		Board board = new Board();
		Bag bag = new Bag();
		
		Player player1 = new Player("James", rackP1);
		Player player2 = new Player("Rodrigo", rackP2);
		GameMaster gameMaster = new GameMaster(board);
		Game game = new Game(board, player1, player2,gameMaster, bag);
		
		GameView gameView = new GameView(game);
		BorderPane gameContainer = gameView.createGameView();
		
		Scene scene = new Scene(gameContainer);
		primaryStage.setFullScreen(true);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Scrabble");
	    primaryStage.show();
	}

}
