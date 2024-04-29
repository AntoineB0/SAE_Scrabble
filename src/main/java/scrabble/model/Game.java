package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public final Board board;
	public Player player1;
	public Player player2;
	public Integer turn;
	public final Bag bag;
	private final Scanner scanner;
	
	
	public Game(Board board, Player player1, Player player2, Bag bag) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.turn = 1;
		this.bag = bag;
		this.scanner = new Scanner(System.in);
		initializeRack();
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	
	private void initializeRack() {
		this.player1.rack.addTiles(7, bag);
		this.player2.rack.addTiles(7, bag);
	}
	
	public void printGameStatus() {
		// Make a cleaner version with a current player variable
		if(turn % 2 == 0) {
			System.out.println(this.player1.toString()+"\n"); 
			this.board.printBoard();
			this.player1.getRack().printRack();
		}
		else {
			System.out.println(this.player2.toString()+"\n");
			this.board.printBoard();
			this.player2.getRack().printRack();
		}
	}
	
	public int askAction(){
		
		System.out.println("\n Choisissez une action :");
        System.out.println(" 1 . Jouer un mot \n 2 . Échanger \n 3 . Passer son tour");

        return scanner.nextInt();
    }
	
	public void playAction() {
		int choice = askAction();
		switch (choice) {
		case 1: {
			
			// TODO Make a move
			break;
		}
		case 2:{
			askSwap();
			printGameStatus();
			break;
		}
		case 3: 
			// TODO Skip 
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		this.turn ++;
	}
	
	public void askSwap() {
		System.out.print("Nombre de jetons à supprimer : ");
        int numberOfSwap = scanner.nextInt();
        while (numberOfSwap < 1 || numberOfSwap > 7) {
        	numberOfSwap = scanner.nextInt();
        	System.out.print("Saisir une valeur comprise entre 1 et 7 : ");
        }
        
        for (int i = 0; i < numberOfSwap; i++) {
            System.out.println("Rang de la tuile n°" + (i+1) + " à retirer :");
            int tileRank = scanner.nextInt();
            ArrayList lastInput = new ArrayList<>();
            lastInput.add(tileRank);
            if (lastInput.contains(tileRank)) {
            	System.out.println("Veuillez choisir une tuile non sélectionnée ! ");
            	numberOfSwap++; //An iteration was wasted when you ask for on already selected tile so add an iteration !
            }
            
            if (turn % 2 == 0) {
                player1.rack.swapTile((tileRank-1), bag);
            } else {
                player2.rack.swapTile((tileRank-1), bag);
            }
        }
        
        scanner.close();
	}
}
