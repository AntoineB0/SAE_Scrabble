package scrabble.view;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import scrabble.model.Board;



public class BoardView  {
	private Board board;
	private GridPane boardPane;

    public BoardView(Board board) {
        this.board = board;
        this.boardPane = createBoard();
    }
    
    

    public GridPane createBoard() {
        GridPane boardPane = new GridPane();
        boardPane.setHgap(6);
        boardPane.setVgap(6);
        boardPane.setGridLinesVisible(false);
        
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                SquareView squareView = new SquareView(board.getSquare(row, col));
                StackPane squarePane = squareView.createSquare();
                
                
                boardPane.add(squarePane, col, row);
            
		        if (col == 7 && row == 7) {
		        	Label starLabel = new Label("🌟");
		        	starLabel.setFont(new Font("Arial", 32));
		            squarePane.getChildren().add(starLabel);
	                
		        }
            }
        }
        
        boardPane.setStyle("-fx-border-color: white;"
                + " -fx-border-width: 10px;"
                + " -fx-background-color: white;"
                + "-fx-border-radius: 10;"
                + "-fx-background-radius: 10;");
        boardPane.setMaxSize(1000, 1000);
        boardPane.setMinSize(1000, 1000);
        boardPane.setAlignment(Pos.CENTER);
        
        DropShadow dropShadow = new DropShadow();
        boardPane.setEffect(dropShadow);
        return boardPane;
    }
    public void refreshBoard() {
        boardPane.getChildren().clear(); 
        
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                SquareView squareView = new SquareView(board.getSquare(col, row));
                StackPane squarePane = squareView.createSquare();
                
                boardPane.add(squarePane, col, row);
            
                if (col == 7 && row == 7) {
                    
                }
            }
        }
    }
    public GridPane getBoardPane() {
		return boardPane;
	}

	public void setBoardPane(GridPane boardPane) {
		this.boardPane = boardPane;
	}

	
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
