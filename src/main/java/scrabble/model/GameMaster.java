package scrabble.model;

public class GameMaster {
	private Board board;
	
	
	public GameMaster(Board board) {
		this.board = board;
	}

	public int calculateScore(Move move) {
        int totalScore = 0;
        int wordScore = 0;
        int wordMultiplier = 1;
        Integer x = move.getPosList().get(0).getX();
        Integer y = move.getPosList().get(0).getY();
        Integer i = 0;
        if (move.getWordDirection() == WordDirection.HORIZONTAL) {
            // Left part and center
        	while (board.getSquare(x+i,y).getTile() != null){
        		Square square = board.getSquare(x+i,y);
        		wordScore += square.getTile().getValue(); 
                totalScore += calculateAdjacentWordsScore(x, y, i, square.getTile(),move);
                i++;
        	}
        	
        	i = 1;
        	//Right part
        	while (board.getSquare(x-i,y).getTile() != null){
        		Square square = board.getSquare(x+i,y);
        		wordScore += square.getTile().getValue(); 
                totalScore += calculateAdjacentWordsScore(x, y, i, square.getTile(),move);
                i++;
        		
        	}
        	
        } else {
        	// Top part and center
        	while (board.getSquare(x,y+i).getTile() != null){
        		Square square = board.getSquare(x+i,y);
        		
        		wordScore += square.getTile().getValue(); 

                totalScore += calculateAdjacentWordsScore(x, y, i, square.getTile(),move);
                i++;
        	}
        	
        	i = 1;
        	// Bottom part
        	while (board.getSquare(x,y-i).getTile() != null){
        		Square square = board.getSquare(x+i,y);
        		wordScore += square.getTile().getValue(); 
                totalScore += calculateAdjacentWordsScore(x, y, i, square.getTile(),move);
        		i++;
        	}
        }
        

        wordScore *= wordMultiplier;
        totalScore += wordScore;

        return totalScore;
    }
   
    private int calculateAdjacentWordsScore(int x, int y, int i, TileInstance tile,Move move) {
        if (move.getWordDirection() == WordDirection.HORIZONTAL) {
            return calculateVerticalWordScore(x + i,y , tile);
        } else {
        	return calculateHorizontalWordScore(x,y + i , tile);
        }
    }

    private int calculateVerticalWordScore(int col,int row,  TileInstance tile) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;
        int top = row - 1;
        int bottom = row + 1;

        // Top part of the word 
        while (top >= 0 && board.getSquare(col, top).getTile() != null) {
            isWord = true;
            Square square = board.getSquare(col, top);
            if (square != null && square.getTile() != null) {
                isWord = true;
                score += square.getTile().getValue();
                top--;
            }
        }
        // Add the new letter
    	score += tile.getValue();
    	    
        // Bottom part of the word
        while (bottom < board.getRows() && board.getSquare(col, bottom).getTile() != null) { // Check getRows behavior same for getColunm in the horizontal
            isWord = true;
            Square bottomSquare = board.getSquare(col, bottom);
            if (bottomSquare != null && bottomSquare.getTile() != null) {
                isWord = true;
                score += bottomSquare.getTile().getValue();
                bottom++;
            }
           
        }

        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }
        return score;
    }

    private int calculateHorizontalWordScore(int col, int row, TileInstance tile) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;
        int left = col - 1;  
        int right = col + 1;
        System.out.println("Tile.getValue"+tile.getValue());
        // Left part of the word
        while (left >= 0 && board.getSquare(left, row ).getTile() != null) {
            isWord = true;
            Square leftSquare = board.getSquare(left, row);
            if (leftSquare != null && leftSquare.getTile() != null) {
                score += leftSquare.getTile().getValue();
                left--;
            }
            
        }
        // Add the new letter
        
        score += tile.getValue();
        
        // Right part of the word
        while (right < board.getColumns() && board.getSquare(right, row).getTile() != null) {
            isWord = true;
            System.out.println(board.getSquare(right, row));
            Square rightSquare = board.getSquare(right, row);
            if (rightSquare != null && rightSquare.getTile() != null) {
                score += rightSquare.getTile().getValue();
                right++;
            }
            
        }

        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }

        return score;
    }
}
