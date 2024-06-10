package scrabble.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Move {
	ArrayList<Character> letterList = new ArrayList<>();
	ArrayList<TileInstance> tilesList = new ArrayList<>();
	ArrayList<Position> posList = new ArrayList<>();
	private Game game;
    private Player player;
    private int score;
    private WordDirection wordDirection;
    private Board board;
    private int currentTurn;
    
    
    public Move(ArrayList<TileInstance> tilesList, ArrayList<Position> posList, Game game,Player player,WordDirection wordDirection, Board board, int currentTurn) {
		this.letterList = toLetterList(tilesList);
		this.tilesList = tilesList;
		this.posList = posList;
		this.game = game;
		this.player = player;
		this.score = 0;
		this.wordDirection = wordDirection;
		this.board = board;
		this.currentTurn = currentTurn;
	}
    
   
    private ArrayList<Character> toLetterList(ArrayList<TileInstance> tileList) {
        ArrayList<Character> lettersList = new ArrayList<>();
        for (TileInstance tile : tileList) {
            lettersList.add(tile.toChar());
        }
        return lettersList;
    }


	public boolean hasRequiredTiles() { 
        List<TileInstance> rackLetterList = player.getRack().getTilesOnRack(); 
        List<Character> letterListCopy = new ArrayList<>(letterList); 
        int jokerCount = 0;
        int jokerPlayed = 0;
        
        for (TileInstance tile : rackLetterList) { 
            if (tile.isJoker()) { 
                jokerCount++; 
            }
        }
        for (TileInstance tile : rackLetterList) { 
            if (tile.getWasAJoker()) { 
                jokerPlayed++; 
            }
        }
        for (TileInstance tile : rackLetterList) { 
            Character tileChar = tile.getType().toChar(); 
            if (letterListCopy.contains(tileChar)) { 
                letterListCopy.remove(tileChar);  
            } 
        }
        
        while (!letterListCopy.isEmpty() && jokerCount > 0) {
            letterListCopy.remove(0); 
            jokerCount--;
        }
        return letterListCopy.isEmpty(); 
    } 
    
    
    public boolean canBePlaced() {
    	if (checkFirstMove()) {
    	    if ((posList.size() < 2)&&(currentTurn==1)) {
    	    	System.out.println("Erreur : Un tuile unique ne peut pas former de mot");
    	    	return false;
    	    }
    	} else {
    	    System.out.println("Erreur : Pas la bonne case pour first move");
    	    return false;
    	}
    	
		if (!checkSiColler()) {
		    System.out.println("Erreur : Tuiles pas adjacentes.");
		    return false;
		}
		
		if (!checkForConflict()) {
		    System.out.println("Erreur : Conflit de tuiles.");
		    return false;
		}
		
		if (!hasRequiredTiles()) {
		    System.out.println("Erreur : Les tuiles requises ne sont pas présentes.");
		    return false;
		}
		
		
		if ((!checkAdjacentTiles()) && (currentTurn != 1) ) {
            System.out.println("Erreur : Le mot doit être adjacent à au moins une tuile existante.");
            return false;
        }
		return true;
    }

    private boolean checkAdjacentTiles() {
        for (Position pos : posList) {
            if (isAdjacentToExistingTile(pos)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdjacentToExistingTile(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return (board.getSquare(x - 1, y) != null && board.getSquare(x - 1, y).getTile() != null) ||
               (board.getSquare(x + 1, y) != null && board.getSquare(x + 1, y).getTile() != null) ||
               (board.getSquare(x, y - 1) != null && board.getSquare(x, y - 1).getTile() != null) ||
               (board.getSquare(x, y + 1) != null && board.getSquare(x, y + 1).getTile() != null);
    }
    
	private boolean checkForConflict() {
    	TileInstance boardTile;
    	
    	for (int i=0;i<posList.size();i++) {
            boardTile = board.getSquare(posList.get(i).getX(),posList.get(i).getY()).getTile();
            
            if (boardTile != null) {
            	return false;
            }
        }
    	return true;
    }
    
	
    private boolean checkSiColler() {
    	ArrayList<Position> posListCopy = posList;
        if (wordDirection == WordDirection.HORIZONTAL) {
        	posListCopy.sort(Comparator.comparingInt(Position::getX));
        	System.out.println(posListCopy);
            for (int i=0; i < posListCopy.size()-1; i++) {
            	if (((posListCopy.get(i+1).getX()) != posListCopy.get(i).getX()+1) || ((posListCopy.get(i+1).getY()) != posListCopy.get(i).getY())) {
            		System.out.println(i);
            		return false;
            	}
            }
        } 
        else {
        	posList.sort(Comparator.comparingInt(Position::getY));
            for (int i=0; i < posList.size()-1; i++) {
            	if (((posListCopy.get(i+1).getY()) != posListCopy.get(i).getY()+1) || ((posListCopy.get(i+1).getX()) != posListCopy.get(i).getX())) {
            		return false;
            	}
            }
        }
        return true;
    }
    
    
    private boolean checkFirstMove(){
    	boolean containsCenterPos = true;
    	if (currentTurn == 0 ) {
    		containsCenterPos = false;
        	for (Position position : posList) {
                if (position.getX() == 7 && position.getY() == 7) {
                    containsCenterPos = true;
                }
            }
        }
    	return containsCenterPos;
    }

    ///
    ///     Getters And Setters 
    ///
    
	public ArrayList<Character> getLetterList() {
		return letterList;
	}


	public ArrayList<TileInstance> getTilesList() {
		return tilesList;
	}


	public ArrayList<Position> getPosList() {
		return posList;
	}


	public Game getGame() {
		return game;
	}


	public Player getPlayer() {
		return player;
	}


	public int getScore() {
		return score;
	}


	public WordDirection getWordDirection() {
		return wordDirection;
	}


	public Board getBoard() {
		return board;
	}


	public int getCurrentTurn() {
		return currentTurn;
	}


	public void setLetterList(ArrayList<Character> letterList) {
		this.letterList = letterList;
	}


	public void setTilesList(ArrayList<TileInstance> tilesList) {
		this.tilesList = tilesList;
	}


	public void setPosList(ArrayList<Position> posList) {
		this.posList = posList;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public void setWordDirection(WordDirection wordDirection) {
		this.wordDirection = wordDirection;
	}


	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
}
