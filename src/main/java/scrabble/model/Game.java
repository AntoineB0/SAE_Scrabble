package scrabble.model;


import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private final Board board;
	private final Player player1;
	private final Player player2;
	private final GameMaster gameMaster;
	private Player actualPlayer;
	private Integer turn;
	private final Bag bag;
	private final Scanner scanner;
	
	
	
	public Game(Board board, Player player1, Player player2, GameMaster gameMaster, Bag bag) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.gameMaster = gameMaster;
		this.turn = 1;
		this.actualPlayer = getActualPlayer();
		this.bag = bag;
		this.scanner = new Scanner(System.in);
		initializeRack();
	}

	
	private void initializeRack() {
		this.player1.rack.addTile(bag);
		this.player2.rack.addTile(bag);
	}
	
	public void printGameStatus() {	
		System.out.println(this.actualPlayer.toString()+"\n"); 
		this.board.printBoard();
		this.getActualPlayer().getRack().printRack();
		System.out.println();
	}
	
	public int askAction(){
		
		System.out.println("\n Choisissez une action :");
        System.out.println(" 1 . Jouer un mot \n 2 . Échanger \n 3 . Passer son tour");

        return scanner.nextInt();
    }
	
	public void playAction() { 
		boolean actionDone = false;
		setActualPlayer();
		Move move;
		int choice = askAction(); 
		switch (choice) { 
		case 1: { 
			while (!actionDone) {
				move = askForAMove(actualPlayer);
				if (move.canBePlaced()) {
					playMove(move); 
					givePoint(move);
					setJokerScoreTo0(move);
					actualPlayer.getRack().addTile(bag);
					actionDone = true;
				}
			}
			
			break; 
		}
		case 2:{
			askSwap();
			printGameStatus();
			break;
		}
		case 3: 
			turn++;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		this.turn ++;
	}
	
	private void setJokerScoreTo0(Move move) {
		ArrayList<Position>PosList = move.getPosList();
		for (Position pos : PosList) {
			if (board.getSquare(pos.getX(), pos.getY()).getTile().getWasAJoker()) {
				board.getSquare(pos.getX(), pos.getY()).getTile().setValue(0);
			}
		}
		
		
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
            	System.out.println("Chiffre compris entre 1 et 7 UNIQUEMENT ");
            	i--;
            }
            else if (lastInput.contains(tileRank)) {
            	System.out.println("Veuillez choisir une tuile non sélectionnée ! ");
            	i--; //An iteration was wasted when you ask for on already selected tile so add an iteration 
            }
            else {
	            lastInput.add(tileRank);
	            actualPlayer.rack.swapTileOnRank((tileRank-1), bag);
            }
        }
	}
	
	public void playMove(Move move) {
        for (int i = 0; i < move.getPosList().size(); i++) {
        	Position pos = move.getPosList().get(i);
        	TileInstance tile = move.getTilesList().get(i);
        	
            board.getSquare(pos.getX(),pos.getY()).setTile(tile);
            if (tile.getWasAJoker()) {
                TileInstance jokerTile = move.getPlayer().getRack().findJoker(); 
                if (jokerTile != null) {
                    move.getPlayer().getRack().removeTile(jokerTile);
                }
            } else {
                move.getPlayer().getRack().removeTile(tile);
            }
        }
    }
	private void givePoint(Move move) { 
		 int score = gameMaster.calculateScore(move); 
		 move.getPlayer().addScore(score); 
		 System.out.println("Score pour ce mouvement : " + score);	 
	}
	
	public Move askForAMove(Player player) { 
		ArrayList<TileInstance> tilesList = new ArrayList<>();
		ArrayList<Position> posList = new ArrayList<>();
		WordDirection direction = null; 
		boolean wordEnded = false; 
		
		direction = askDirection(); 
		
		
		while (!wordEnded) { 
			posList.add(askCoordinate()); 
			tilesList.add(askTiles());
			wordEnded = !keepAskingForLetter();
		} 
		System.out.println(posList);
		return new Move(tilesList, posList, this, player, direction, board, turn); 
	}
	
	private boolean keepAskingForLetter() {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Souhaitez vous continuer à poser des tuiles : \n [espace] -> Continuer \n [autre] -> Quitter"); 
		String choice = scanner.nextLine();
		return " ".equals(choice);
	}


	private TileInstance askTiles() {
	    System.out.println("Saisir la tuile à placer : ");
	    Character letter = scanner.next().charAt(0);
	    if (Tiles.charToTile(letter) == Tiles.j){
	        return handleJoker();
	    }
	    return new TileInstance(Tiles.charToTile(letter));
	}

	private TileInstance handleJoker() {
	    System.out.println("Par quelle lettre voulez-vous remplacer ce joker ? :");
	    Character newLetter = Character.toUpperCase(scanner.next().charAt(0));
	    TileInstance jokerTile = new TileInstance(Tiles.charToTile(newLetter));
	    jokerTile.setValue(Tiles.charToTile(newLetter).getValue());
	    jokerTile.setWasAJoker(true);
	    return jokerTile;
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

	private static Position askCoordinate() {
		boolean validPos = false;
	    Scanner scanner = new Scanner(System.in); 
	    while (!validPos) {
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
		             	   validPos = true;
		                   return new Position(row,column); 
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
	     }
	     return null; 
     }
	
	public class ExchangeInvalidException extends Exception {
	    public ExchangeInvalidException(String message) {
	        super(message);
	    }
	}
	
	
	///
    ///     Getters And Setters 
    ///
	
	
	public Board getBoard() {
		return board;
	}

	public Bag getBag() {
		return bag;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setActualPlayer(Player actualPlayer) {
		this.actualPlayer = actualPlayer;
	}
	
	public Player getActualPlayer() {
		return turn % 2 == 0 ? player1 : player2;
	}
	
	public void setActualPlayer() {
		this.actualPlayer=turn % 2 == 0 ? player1 : player2;
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
	
	

}
