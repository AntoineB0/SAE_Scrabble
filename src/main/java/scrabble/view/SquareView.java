package scrabble.view;

import javafx.beans.property.IntegerProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        
        
        TileInstance tile = square.getTile();
        if (tile != null) {
            squarePane.getChildren().add(TileView.createTile(tile));
        }
        
        
        squarePane.setMaxSize(60, 60);
        squarePane.setMinSize(60, 60);
        squarePane.setStyle( " -fx-background-color: lightgrey;"
                + "-fx-background-radius: 10;");

        
        switch (square.getMultiplier()) {
        case VOID:
        	break;
        case DOUBLE_WORD:
            squarePane.setStyle("-fx-background-color: pink;");
            break;
        case TRIPLE_WORD:
            squarePane.setStyle("-fx-background-color: red;");
            break;
        case DOUBLE_LETTER:
            squarePane.setStyle("-fx-background-color: lightblue;");
            break;
        case TRIPLE_LETTER:
            squarePane.setStyle("-fx-background-color: blue;");
            break;
        default:
            break;
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
