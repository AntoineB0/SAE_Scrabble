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
            
        }

		return wordScore;
    }

    public static List<Tiles> toTiles(String word) {
        List<Tiles> tilesList = new ArrayList<>();
        for (char letter : word.toCharArray()) {
            tilesList.add(Tiles.charToTile(letter));
        }
        return tilesList;
    }
}