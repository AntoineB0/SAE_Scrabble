package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Move {
    private Player player ;
    private int score;
    private String word;
    private WordDirection wordDirection;
    private int startingPosX;
    private int startingPosY;
    private Board board;

    public Move(Player player,Board board, String word, WordDirection wordDirection, int startingPosX,int startingPosY) {
        this.player = player;
        this.board = board;
        this.word = word;
        this.score = 0;
        this.wordDirection = wordDirection;
        this.startingPosX = startingPosX;
        this.startingPosY = startingPosY;
    }
    
    public boolean hasRequiredTiles(List<Character> charList) {
        List<Tiles> rackLetterList = player.getRack().getTilesOnRack();


        for (Tiles tile : rackLetterList) {
            if ( charList.contains(tile.name().charAt(0)) ){
                charList.remove(tile.name().charAt(0));
            }
        }

        return (charList.size() == 0);
    }
    
    public boolean canBePlaced() {
        int x = startingPosX;
        int y = startingPosY;
        int length = word.length();
        List<Character> charList = new ArrayList<>();


        // Check if it fits in the board
        if (wordDirection == WordDirection.HORIZONTAL) {
            if (x + length > board.getColumns()) {
                return false;
            }
        } else {
            if (y + length > board.getRows()) {
                return false;
            }
        }
        
      //Check if some letters of the words are already placed or if there is a conflict
        for (int i = 0; i < length; i++) {
            Tiles boardTile;

            if (wordDirection == WordDirection.HORIZONTAL) {
                boardTile = board.getSquare(y, x + i).getTile();
            } else {
                boardTile = board.getSquare(y + i, x).getTile();
            }
            char wordChar = word.charAt(i);
            if (boardTile != null ) {
                if (boardTile.name().charAt(0) != wordChar) {
                    return false; 
                }
            }
            else {
            	charList.add(wordChar);
            }   
        }
        return hasRequiredTiles(charList);
    }

	public int calculateScore() {
        int totalScore = 0;
        int wordScore = 0;
        int wordMultiplier = 1;
        int x = startingPosX;
        int y = startingPosY;
        List<Tiles> tilesInWord = Move.toTiles(word);

        for (int i = 0; i < word.length(); i++) {
            int letterScore = tilesInWord.get(i).getValue();
            Square square;

            if (wordDirection == WordDirection.HORIZONTAL) {
                square = board.getSquare(y, x + i);
            } else {
                square = board.getSquare(y + i, x);
            }
            wordScore += letterScore; 

            
            if (square.getTile() == null) {  // The multiplier has to never been used before 
            	if (square.getMultiplier() != null) {
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
                wordScore += letterScore;
                int adjacentScore = calculateAdjacentWordsScore(x, y, i, square.getTile());
                totalScore += adjacentScore;
            } else {
                wordScore += letterScore;
            }
        }
        wordScore *= wordMultiplier;
        totalScore += wordScore;

        return totalScore;
    }
    

    public static List<Tiles> toTiles(String word) {
        List<Tiles> tilesList = new ArrayList<>();
        for (char letter : word.toCharArray()) {
            tilesList.add(Tiles.charToTile(letter));
        }
        return tilesList;
    }
    private int calculateAdjacentWordsScore(int x, int y, int i, Tiles tile) {
        if (wordDirection == WordDirection.HORIZONTAL) {
            return calculateVerticalWordScore(y, x + i, tile);
        } else {
        	return calculateHorizontalWordScore(y + i, x, tile);
        }
    }

    private int calculateVerticalWordScore(int row, int col, Tiles tile) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;
        int top = row - 1;
        int bottom = row + 1;
        // Top part of the word 
        while (top >= 0 && board.getSquare(top, col).getTile() != null) {
            isWord = true;
            score += board.getSquare(top, col).getTile().getValue();
            top--;
        }
        // Add the new letter
        Square currentSquare = board.getSquare(row, col);
        if (currentSquare.getMultiplier() != null) {
    	    switch (currentSquare.getMultiplier()) {
    	        case DOUBLE_LETTER:
    	        	score += 2*tile.getValue();
    	            break;
    	        case TRIPLE_LETTER:
    	        	score += 3*tile.getValue();
    	            break;
    	        case DOUBLE_WORD:
    	            wordMultiplier *= 2;
    	            break;
    	        case TRIPLE_WORD:
    	            wordMultiplier *= 3;
    	            break;
    	        default:
    	        	score += tile.getValue();
    	            break;
    	    }
        }
        // Bottom part of the word
        while (bottom < board.getRows() && board.getSquare(bottom, col).getTile() != null) { // Check getRows behavior same for getColunm in the horizontal
            isWord = true;
            score += board.getSquare(top, col).getTile().getValue();
            bottom++;
        }
        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }
        return score;
    }

    private int calculateHorizontalWordScore(int row, int col, Tiles tile) {
        int score = 0;
        int wordMultiplier = 1;
        boolean isWord = false;
        int left = col - 1;  
        int right = col + 1;
        // Left part of the word
        while (left >= 0 && board.getSquare(row, left).getTile() != null) {
            isWord = true;
            score += board.getSquare(row, left).getTile().getValue();
            left--;
        }
        // Add the new letter
        Square currentSquare = board.getSquare(row, col);
        if (currentSquare.getMultiplier() != null) {
    	    switch (currentSquare.getMultiplier()) {
    	        case DOUBLE_LETTER:
    	        	score += 2*tile.getValue();
    	            break;
    	        case TRIPLE_LETTER:
    	        	score += 3*tile.getValue();
    	            break;
    	        case DOUBLE_WORD:
    	            wordMultiplier *= 2;
    	            break;
    	        case TRIPLE_WORD:
    	            wordMultiplier *= 3;
    	            break;
    	        default:
    	        	score += tile.getValue();
    	            break;
    	    }
        }
        // Right part of the word
        while (right < board.getColumns() && board.getSquare(row, right).getTile() != null) {
            isWord = true;
            score +=board.getSquare(row, right).getTile().getValue();
            right++;
        }

        if (isWord) {
            score *= wordMultiplier;
        } else {
            score = 0;
        }

        return score;
    }
    
    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public WordDirection getWordDirection() {
		return wordDirection;
	}

	public void setWordDirection(WordDirection wordDirection) {
		this.wordDirection = wordDirection;
	}

	public int getStartingPosX() {
		return startingPosX;
	}

	public void setStartingPosX(int startingPosX) {
		this.startingPosX = startingPosX;
	}

	public int getStartingPosY() {
		return startingPosY;
	}

	public void setStartingPosY(int startingPosY) {
		this.startingPosY = startingPosY;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}