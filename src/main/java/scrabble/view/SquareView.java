package scrabble.view;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scrabble.model.Multiplier;
import scrabble.model.Position;
import scrabble.model.Square;
import scrabble.model.TileInstance;

public class SquareView {
	private Square square;

    public SquareView(Square square) {
        this.square = square;
    }

    public StackPane createSquare() {
        StackPane squarePane = new StackPane();
        
        Label multiplierLabel;
        TileInstance tile = square.getTile();
        
        
        
        squarePane.setMaxSize(60, 60);
        squarePane.setMinSize(60, 60);
        

        
        switch (square.getMultiplier()) {
        case DOUBLE_WORD:
            squarePane.setStyle("-fx-background-color: pink;"+ "-fx-background-radius: 10;");
            multiplierLabel = new Label("MD");
            break;
        case TRIPLE_WORD:
            squarePane.setStyle("-fx-background-color: red;"+ "-fx-background-radius: 10;");
            multiplierLabel = new Label("MT");
            break;
        case DOUBLE_LETTER:
            squarePane.setStyle("-fx-background-color: lightblue;"+ "-fx-background-radius: 10;");
            multiplierLabel = new Label("LD");
            break;
        case TRIPLE_LETTER:
            squarePane.setStyle("-fx-background-color: blue;"+ "-fx-background-radius: 10;");
            multiplierLabel = new Label("LT");
            break;
        default:
        	squarePane.setStyle( " -fx-background-color: lightgrey;"
                    + "-fx-background-radius: 10;");
        	multiplierLabel = new Label("");
            break;
        }
        multiplierLabel.setFont(new Font("Arial", 24)); 
        squarePane.getChildren().add(multiplierLabel);
        multiplierLabel.setStyle("-fx-font-weight: bold;"+ "-fx-text-fill:white;");
        
        if (square.getX()==7 && square.getY()==7) {
        	multiplierLabel.setText("");
        }
        if (tile != null) {
            squarePane.getChildren().add(TileView.createTile(tile));
        }
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0);
        innerShadow.setOffsetY(2.0);
        innerShadow.setColor(Color.GRAY);
        squarePane.setEffect(innerShadow);
        
        return squarePane;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}
