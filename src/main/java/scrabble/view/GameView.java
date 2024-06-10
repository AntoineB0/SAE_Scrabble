package scrabble.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import scrabble.model.Game;


public class GameView {
	private Game game;
    private BoardView boardView;
    private RackView rackView;
	
	public GameView(Game game) {
		this.game = game;
        this.boardView = new BoardView(game.getBoard());
        this.rackView = new RackView(game);
        
    }

    public BorderPane createGameView() {
    	BorderPane gamePane = new BorderPane();

        StackPane boardContainer = new StackPane();
        boardContainer.getChildren().add(boardView.getBoardPane());
        StackPane.setAlignment(boardView.getBoardPane(), Pos.CENTER);

        StackPane rackContainer = new StackPane();
        rackContainer.getChildren().add(rackView.getRackContainer());
        StackPane.setAlignment(rackView.getRackContainer(), Pos.CENTER);

        InteractionPanel interactionPanel = new InteractionPanel(game, boardView, rackView);
        VBox interactionPanelContainer = interactionPanel.createInteractionPanel();
        
        
        gamePane.setCenter(boardContainer);
        gamePane.setLeft(rackContainer);
        gamePane.setRight(interactionPanelContainer);
        	
        
        
        gamePane.setStyle("-fx-background-color:lightblue");
        return gamePane;
    }
	
	
}
