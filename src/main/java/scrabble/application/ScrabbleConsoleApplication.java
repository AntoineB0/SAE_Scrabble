package scrabble.application;

import scrabble.model.*;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		
		Board board = new Board();
		
			
		System.out.println("-------------------------------------------------------");
		System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		System.out.println("-------------------------------------------------------\n");
		 
		
		board.printBoard();
		
		 
	}
}
