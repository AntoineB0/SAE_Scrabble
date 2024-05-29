package scrabble.application;

import java.util.Scanner;

import scrabble.model.*;


public class MainScrabble {
	
	public static void main(String[] args) {
	
		Board board = new Board(); 
		Bag bag = new Bag(); 
		Rack rackP1 = new Rack(); 
		Rack rackP2 = new Rack(); 
	       Player player1 = new Player("James", rackP1); 
		Player player2 = new Player("Rodrigo", rackP2); 
		Game game = new Game(board, player1, player2, bag); 
			 
		System.out.println(game.getActualPlayer()); 
		game.setTurn(game.getTurn()+1); 
		System.out.println(game.getActualPlayer()); 
	          
	         
	    board.printBoard(); 
	         
	 
	        
        Rack rack = new Rack();
        System.out.println(bag.getTileListLenght());
        rack.printRack();
        
        
        rack.printRack();
        System.out.println(bag.getTileListLenght());
        
        
        rack.printRack();
        System.out.println(bag.getTileListLenght());
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Jetons a supprimer : ");
        String rankTile = scanner.nextLine();
        int rank = Integer.parseInt(rankTile);
        scanner.close();
        
        rack.printRack();
        System.out.println(bag.getTileListLenght());
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
