package scrabble.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scrabble.model.TileInstance;

public class TileView {
	
	
	public static StackPane createTile(TileInstance tile) {
		Label letterLabel = new Label(tile.toString());
        letterLabel.setFont(new Font("Arial", 24)); 
        letterLabel.setTextAlignment(TextAlignment.CENTER);
        
        Label valueLabel = new Label(String.valueOf(tile.getValue()));
        valueLabel.setFont(new Font("Arial", 14));
        valueLabel.setTextAlignment(TextAlignment.RIGHT);

        

        
        StackPane tileContainer = new StackPane();
        tileContainer.getChildren().addAll(letterLabel, valueLabel);
        

        
        StackPane.setAlignment(letterLabel, Pos.CENTER);
        StackPane.setAlignment(valueLabel, Pos.BOTTOM_RIGHT);
        
        DropShadow dropShadow = new DropShadow();
        tileContainer.setEffect(dropShadow);
        tileContainer.setStyle("-fx-border-color: black;"
        		+ " -fx-border-width: 4px;"
        		+ " -fx-background-color: beige;"
        		+ "-fx-border-radius: 6px;"
        		+ "-fx-background-radius:9px;"
        		+ "-fx-font-weight:bold");
		
		return tileContainer;
	}
}
