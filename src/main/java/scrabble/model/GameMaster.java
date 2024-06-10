package scrabble.model;

public class GameMaster {
	private Board board;
	
	
	public GameMaster(Board board) {
		this.board = board;
	}
	public void givePoint(Move move) { 
		 int score = calculateScore(move); 
		 if (move.getPosList().size()== 7) {
			 score += 50;
		 }
		 move.getPlayer().addScore(score); 
		  
	}
	
	public boolean wasPlayedThisTurn(int x,int y,Move move) {
		for (Position pos : move.getPosList()) {
			if ((pos.getX()==x)&&(pos.getY()==y)) {
				return true;
			}
		}
		return false;
	}
	
	public int calculateScore(Move move) {
        int totalScore = 0;
        int wordScore = 0;
        int wordMultiplier = 1;
        Integer x = move.getPosList().get(0).getX();
        Integer y = move.getPosList().get(0).getY();
        Integer i = 0;
        int letterScore;
        Square square;
        if (move.getWordDirection() == WordDirection.HORIZONTAL) {
            // Left part and center
        	while (board.getSquare(x+i,y).getTile() != null){
        		square = board.getSquare(x+i,y);
        		letterScore = square.getTile().getValue();
        		if (square.getMultiplier() != null) {
        			if (wasPlayedThisTurn(x+i,y,move)) {
	    	            switch (square.getMultiplier()) {
	    	                    case DOUBLE_LETTER:
	    	                        letterScore *= 2;
	    	                        break;
	    	                    case TRIPLE_LETTER:
	    	                        letterScore *= 3;
	    	                        break;
	    	                    case DOUBLE_WORD:
	    	                        wordMultiplier *= 2;
	    	                        break;
	    	                    case TRIPLE_WORD:
	    	                        wordMultiplier *= 3;
	    	                        break;
	    	                    default:
	    	                        break;
	    	            }	
        			}
                }   
                wordScore += letterScore;
                
                int adjacentScore = calculateAdjacentWordsScore(x, y, i,move);
                totalScore += adjacentScore;        	
                i++;
        	}
        	
        	i = 1;
        	//Right part
        	while (board.getSquare(x-i,y).getTile() != null){
        		square = board.getSquare(x-i,y);
        		letterScore = square.getTile().getValue();
        		if (square.getMultiplier() != null) {
        			if (wasPlayedThisTurn(x-i,y,move)) {
	    	            switch (square.getMultiplier()) {
	    	                    case DOUBLE_LETTER:
	    	                        letterScore *= 2;
	    	                        break;
	    	                    case TRIPLE_LETTER:
	    	                        letterScore *= 3;
	    	                        break;
	    	                    case DOUBLE_WORD:
	    	                        wordMultiplier *= 2;
	    	                        break;
	    	                    case TRIPLE_WORD:
	    	                        wordMultiplier *= 3;
	    	                        break;
	    	                    default:
	    	                        break;
	    	            }	
        			}
                }   
                wordScore += letterScore;
                
                int adjacentScore = calculateAdjacentWordsScore(x, y, i,move);
                totalScore += adjacentScore;        	
                i++;
        	}
		}else {
        	// Top part and center
        	while (board.getSquare(x,y+i).getTile() != null){
        		square = board.getSquare(x,y+i);
        		letterScore = square.getTile().getValue();
        		if (square.getMultiplier() != null) {
        			if (wasPlayedThisTurn(x,y+i,move)) {
	    	            switch (square.getMultiplier()) {
	    	                    case DOUBLE_LETTER:
	    	                        letterScore *= 2;
	    	                        break;
	    	                    case TRIPLE_LETTER:
	    	                        letterScore *= 3;
	    	                        break;
	    	                    case DOUBLE_WORD:
	    	                        wordMultiplier *= 2;
	    	                        break;
	    	                    case TRIPLE_WORD:
	    	                        wordMultiplier *= 3;
	    	                        break;
	    	                    default:
	    	                        break;
	    	            }	
        			}
                }   
                wordScore += letterScore;
                
                int adjacentScore = calculateAdjacentWordsScore(x, y, i,move);
                totalScore += adjacentScore;        	
                i++;
        	}
        	
        	i = 1;
        	// Bottom part
        	while (board.getSquare(x,y-i).getTile() != null){
        		square = board.getSquare(x,y-i);
        		letterScore = square.getTile().getValue();
        		if (square.getMultiplier() != null) {
        			if (wasPlayedThisTurn(x,y-i,move)) {
	    	            switch (square.getMultiplier()) {
	    	                    case DOUBLE_LETTER:
	    	                        letterScore *= 2;
	    	                        break;
	    	                    case TRIPLE_LETTER:
	    	                        letterScore *= 3;
	    	                        break;
	    	                    case DOUBLE_WORD:
	    	                        wordMultiplier *= 2;
	    	                        break;
	    	                    case TRIPLE_WORD:
	    	                        wordMultiplier *= 3;
	    	                        break;
	    	                    default:
	    	                        break;
	    	            }	
        			}
                }   
                wordScore += letterScore;
                
                int adjacentScore = calculateAdjacentWordsScore(x, y, i,move);
                totalScore += adjacentScore;        	
                i++;
        	}
        }
        
        
        wordScore *= wordMultiplier;
        totalScore += wordScore;

        return totalScore;
    }
   
    private int calculateAdjacentWordsScore(int x, int y, int i,Move move) {
        if (move.getWordDirection() == WordDirection.HORIZONTAL) {
            return calculateVerticalWordScore(x + i,y);
        } else {
        	return calculateHorizontalWordScore(x,y + i);
        }
    }

    private int calculateVerticalWordScore(int x, int y) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;

        // Traverse upwards
        for (int i = y - 1; i >= 0 && board.getSquare(x, i).getTile() != null; i--) {
            isWord = true;
            score += board.getSquare(x, i).getTile().getValue();
        }

        // Traverse downwards
        for (int i = y + 1; i < board.getRows() && board.getSquare(x, i).getTile() != null; i++) {
            isWord = true;
            score += board.getSquare(x, i).getTile().getValue();
        }

        // Check the current square multiplier
        Square currentSquare = board.getSquare(x, y);
        if (currentSquare.getMultiplier() != null) {
            switch (currentSquare.getMultiplier()) {
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                    break;
            }
        }

        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }

        return score;
    }

    private int calculateHorizontalWordScore(int x, int y) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;

        // Traverse left
        for (int i = x - 1; i >= 0 && board.getSquare(i, y).getTile() != null; i--) {
            isWord = true;
            score += board.getSquare(i, y).getTile().getValue();
        }

        // Traverse right
        for (int i = x + 1; i < board.getColumns() && board.getSquare(i, y).getTile() != null; i++) {
            isWord = true;
            score += board.getSquare(i, y).getTile().getValue();
        }

        // Check the current square multiplier
        Square currentSquare = board.getSquare(x, y);
        if (currentSquare.getMultiplier() != null) {
            switch (currentSquare.getMultiplier()) {
                case DOUBLE_WORD:
                    wordMultiplier *= 2;
                    break;
                case TRIPLE_WORD:
                    wordMultiplier *= 3;
                    break;
                default:
                    break;
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
