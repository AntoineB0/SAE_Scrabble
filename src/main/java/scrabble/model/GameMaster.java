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
        

        for (int i = 0; i < move.getPosList().size(); i++) {
            int letterScore = move.getTilesList().get(i).getValue();
            Square square;

            if (move.getWordDirection() == WordDirection.HORIZONTAL) {
                square = board.getSquare(x+i,y);
            } else {
                square = board.getSquare(x,y+1);
            }
            wordScore += letterScore; 

            totalScore += calculateAdjacentWordsScore(x, y, i, square.getTile(),move);
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
            score += board.getSquare(col, top).getTile().getValue();
            top--;
        }
        // Add the new letter
    	score += tile.getValue();
    	    
        // Bottom part of the word
        while (bottom < board.getRows() && board.getSquare(col, bottom).getTile() != null) { // Check getRows behavior same for getColunm in the horizontal
            isWord = true;
            score += board.getSquare(col,bottom).getTile().getValue();
            bottom++;
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
            score += board.getSquare(left, row).getTile().getValue();
            left--;
        }
        // Add the new letter
        
        score += tile.getValue();
        
        // Right part of the word
        while (right < board.getColumns() && board.getSquare(right, row).getTile() != null) {
            isWord = true;
            System.out.println(board.getSquare(right, row));
            score +=board.getSquare(right,row).getTile().getValue();
            right++;
        }

        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }

        return score;
    }
}