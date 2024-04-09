package scrabble.application;

import scrabble.model.*;


public class MainScrabble {
	
	public static void main(String[] args) {
		
        Board board = new Board(); 
        Board emptyBoard = new Board();
        
        
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Tiles tile = getRandomTile(); 
                board.setSquare( new Square(tile, null, false, i, j));
            }
        }
        
        
        
        emptyBoard.printBoard();
        
	}
        
        
 
    
    public static Tiles getRandomTile() {
        Tiles[] tiles = Tiles.values();
        return tiles[(int) (Math.random() * tiles.length)];
    }
    
    public static void separator(String title){
        System.out.println();
        System.out.println(title);
        System.out.println();
    }
}
