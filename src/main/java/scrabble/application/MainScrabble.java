package scrabble.application;

import java.util.Scanner;

import scrabble.model.*;


public class MainScrabble {
	
	public static void main(String[] args) {
		
        Board board = new Board(); 
        
        board.printBoard();
        

        Bag bag = new Bag();
        Rack rack = new Rack();
        System.out.println(bag.getTileListLenght());
        rack.printRack();
        
        rack.addTiles(6, bag);
        rack.printRack();
        System.out.println(bag.getTileListLenght());
        
        rack.addTiles(2, bag);
        rack.printRack();
        System.out.println(bag.getTileListLenght());
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Jetons a supprimer : ");
        String rankTile = scanner.nextLine();
        int rank = Integer.parseInt(rankTile);
        rack.removeTile(rank, bag);
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
