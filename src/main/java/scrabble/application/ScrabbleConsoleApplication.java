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
		GameMaster gameMaster = new GameMaster(board);
		Game game = new Game(board, player1, player2,gameMaster, bag);
		
		
		
		separator("-- Bienvenue dans notre magnifique jeu de scrabble ! --");
		
		while ((game.getBag().getTileListLenght() != 0) && ((!player1.getRack().getTilesOnRack().isEmpty() ) || (!player2.getRack().getTilesOnRack().isEmpty() ))) {
			game.printGameStatus();
			game.playAction();
		}
		System.out.println();
		separator(player1 +"\n"+ player2);
	}
	
	public static void separator(String title){
        System.out.println("-------------------------------------------------------");
        System.out.println(title);
        System.out.println("-------------------------------------------------------\n");
        
    }
}
