package scrabble.application;

import scrabble.model.*;

public class ScrabbleConsoleApplication {

	public static void main(String[] args) {
		
		Rack rackP1 = new Rack();
		Rack rackP2 = new Rack();
		
		Board board = new Board();
		Bag bag = new Bag();
		
		Player player1 = new Player("James", rackP1);
		Player player2 = new Player("Rodrigo", rackP2);
		Game game = new Game(board, player1, player2, bag);
		
		
		
			
		System.out.println("-------------------------------------------------------");
		System.out.println("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		System.out.println("-------------------------------------------------------\n");
		while ( 10 < 100) {
			game.printGameStatus();
			game.playAction();
		}
		
		
	}
	
	public static void separator(String title){
        System.out.println();
        System.out.println(title);
        
    }
}
