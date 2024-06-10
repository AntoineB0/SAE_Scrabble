package scrabble.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import scrabble.model.Game;
import scrabble.model.Rack;
import scrabble.model.TileInstance;

public class RackView{
	
	private Game game;
	private VBox rackContainer;
	
	public RackView(Game game) {
		this.game = game;
		this.rackContainer = createRack();	
	}
	
	public VBox createRack() {
		ArrayList<StackPane> tileList = new ArrayList<>(); 
		for (TileInstance tile : game.getCurrentPlayer().getRack().getTilesOnRack()) {
			tileList.add(TileView.createTile(tile));
		}
		
		VBox rackBox = new VBox(17);
		
		for (StackPane tile : tileList) {
			tile.setMaxSize(70, 70);
			tile.setMinSize(70, 70);
			rackBox.setAlignment(Pos.CENTER);
			rackBox.getChildren().add(tile);
		}
		rackBox.setStyle("-fx-border-color: grey;"
				+ " -fx-border-width: 3px;"
				+ " -fx-background-color: white;"
				+ "-fx-border-radius: 0 20 20 0;"
				+ "-fx-background-radius: 0 24 24 0;");
		rackBox.setMinWidth(130);   
		rackBox.setMinHeight(700);  
		rackBox.setMaxWidth(130);  
		rackBox.setMaxHeight(700);  
		InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0);
        innerShadow.setOffsetY(2.0);
        innerShadow.setColor(Color.GRAY);
        rackBox.setEffect(innerShadow);
        DropShadow dropShadow = new DropShadow();
        rackBox.setEffect(dropShadow);
        
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(e -> shuffleRack());
        rackBox.getChildren().add(shuffleButton);
		return rackBox;
	}
	
	public void shuffleRack() {
		game.getCurrentPlayer().getRack().shuffle(); 
	    refreshRackView(); 
	}
	
	public void refreshRackView() {
        rackContainer.getChildren().clear(); 
        
        
        ArrayList<StackPane> tileList = new ArrayList<>(); 
        for (TileInstance tile : game.getCurrentPlayer().getRack().getTilesOnRack()) {
            tileList.add(TileView.createTile(tile));
        }
        
        for (StackPane tile : tileList) {
            tile.setMaxSize(70, 70);
            tile.setMinSize(70, 70);
            rackContainer.setAlignment(Pos.CENTER);
            rackContainer.getChildren().add(tile);
        }
        
       
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(e -> shuffleRack());
        rackContainer.getChildren().add(shuffleButton);
    }
	
	public VBox getRackContainer() {
		return rackContainer;
	}

	public void setRackContainer(VBox rackContainer) {
		this.rackContainer = rackContainer;
	}

	
	
	
}
