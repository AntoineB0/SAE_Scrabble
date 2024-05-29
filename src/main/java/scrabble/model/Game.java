package scrabble.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public final Board board;
	public final Player player1;
	public final Player player2;
	public Player actualPlayer;
	public Integer turn;
	public final Bag bag;
	private final Scanner scanner;
	
	
	
	public Game(Board board, Player player1, Player player2, Bag bag) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.turn = 1;
		this.actualPlayer = getActualPlayer();
		this.bag = bag;
		this.scanner = new Scanner(System.in);
		initializeRack();
	}

	public Player getActualPlayer() {
		return turn % 2 == 0 ? player1 : player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	
	private void initializeRack() {
		this.player1.rack.addTile(bag);
		this.player2.rack.addTile(bag);
	}
	
	public void printGameStatus() {	
		System.out.println(this.actualPlayer.toString()+"\n"); 
		this.board.printBoard();
		this.player1.getRack().printRack();
		this.player2.getRack().printRack();
		System.out.println();
	}
	
	public int askAction(){
		
		System.out.println("\n Choisissez une action :");
        System.out.println(" 1 . Jouer un mot \n 2 . Échanger \n 3 . Passer son tour");

        return scanner.nextInt();
    }
	
	public void playAction() { 
		int choice = askAction(); 
		Move move; 
		switch (choice) { 
		case 1: { 
			move = askForAMove(actualPlayer); 
			playMove(move); 
			givePoint(move); 
			actualPlayer.getRack().addTile(bag); 
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
	
	public void askSwap(){
		ArrayList<Integer> lastInput = new ArrayList<>();
		System.out.print("Nombre de jetons à supprimer : ");
        int numberOfSwap = scanner.nextInt();
        while (numberOfSwap < 1 || numberOfSwap > Rack.RackSize) {
        	numberOfSwap = scanner.nextInt();
        	System.out.print("Saisir une valeur comprise entre 1 et 7 : ");
        }
        
        for (int i = 0; i < numberOfSwap; i++) {
            System.out.println("Rang de la tuile n°" + (i+1) + " à retirer :");
            int tileRank = scanner.nextInt();
            if (tileRank > Rack.RackSize || tileRank < 1) {
            	System.out.println("Chiffre compris entre 1 et 7 UNIQUEMENT !!!");
            	i--;
            }
            else if (lastInput.contains(tileRank)) {
            	System.out.println("Veuillez choisir une tuile non sélectionnée ! ");
            	i--; //An iteration was wasted when you ask for on already selected tile so add an iteration 
            }
            else {
	            lastInput.add(tileRank);
	            actualPlayer.rack.swapTile((tileRank-1), bag);
            }
        }
        scanner.close();
	}
	
	public void playMove(Move move) {
        String word = move.getWord();
        WordDirection direction = move.getWordDirection();
        int row = move.getStartingPosY();
        int col = move.getStartingPosX();

        for (int i = 0; i < word.length(); i++) {
            char tileChar = word.charAt(i);
            Tiles tile = Tiles.charToTile(tileChar);

            if (direction == WordDirection.HORIZONTAL) {
                if (board.getSquare(row, col + i).getTile() == null) {
                    board.getSquare(row, col + i).setTile(tile);
                    move.getPlayer().getRack().removeTile(tile);
                }

            } else {
                if (board.getSquare(row + i, col).getTile() == null) {
                    board.getSquare(row + i, col).setTile(tile);
                    move.getPlayer().getRack().removeTile(tile);
                }
            }
        }
    }
	private void givePoint(Move move) { 
		 int score = move.calculateScore(); 
		 move.getPlayer().addScore(score); 
		 System.out.println("Score pour ce mouvement : " + score);	 
	} 
	 
	public Move askForAMove(Player player) { 
		WordDirection direction = null; 
		System.out.println("Saisir les tuiles que vous souhaitez créer : "); 
		String wordProposal = scanner.next(); 
		 
		while (direction == null ) { 
			direction = askDirection(); 
		} 
		int[] coordinates = null; 
		while (coordinates == null) { 
			coordinates = askCoordinate(); 
		} 
		return new Move(player, board, wordProposal, direction, coordinates[0], coordinates[1]); 
	} 
	 
	private WordDirection askDirection() { 
		WordDirection direction; 
		System.out.println("1 . Horizontal \n2 . Vertical "); 
		int directionChoice = scanner.nextInt(); 
		switch (directionChoice) { 
		case 1: { 
			direction = WordDirection.HORIZONTAL; 
			break; 
		} 
		case 2: { 
			direction = WordDirection.VERTICAL; 
			break; 
		} 
		default: 
			throw new IllegalArgumentException("Unexpected value: " + directionChoice); 
		} 
		return direction; 
	} 
	 
	private static int[] askCoordinate() { 
       Scanner scanner = new Scanner(System.in); 

       System.out.print("Veuillez saisir les coordonnées (exemple: A,1): "); 
       String input = scanner.nextLine(); 

       String[] parts = input.split(","); 

       if (parts.length == 2) { 
           String letterPart = parts[0].trim().toUpperCase();   
           String numberPart = parts[1].trim();           

           if (letterPart.length() == 1 && letterPart.charAt(0) >= 'A' && letterPart.charAt(0) <= 'O') { 
               int row = letterPart.charAt(0) - 'A'; 
               try { 
                   int column = Integer.parseInt(numberPart) - 1; 
                   if (column >= 0 && column <= 14) { 
                       return new int[]{row, column}; 
                   } else { 
                       System.out.println("Le numéro doit être un entier positif compris entre 1 et 15."); 
                   } 
               } catch (NumberFormatException e) { 
                   System.out.println("La partie nombre doit être un entier."); 
               } 
           } else { 
               System.out.println("La partie lettre doit être une seule lettre entre A et O."); 
           } 
       } else { 
           System.out.println("Entrée invalide. Veuillez utiliser le format A,1."); 
       } 
       return null; 
   } 
	
	public class ExchangeInvalidException extends Exception {
	    public ExchangeInvalidException(String message) {
	        super(message);
	    }
	}

}
