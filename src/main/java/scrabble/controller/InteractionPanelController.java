package scrabble.controller;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import scrabble.model.Game;
import scrabble.model.Move;
import scrabble.model.Position;
import scrabble.model.TileInstance;
import scrabble.model.Tiles;
import scrabble.model.WordDirection;
import scrabble.view.BoardView;
import scrabble.view.InteractionPanel;
import scrabble.view.RackView;




public class InteractionPanelController {
    private ComboBox<Character> letterComboBox;
    private ComboBox<WordDirection> directionComboBox;
    private TextField positionInputField;
    private Button addTileButton;
    private Button playMoveButton;
    private ArrayList<TileInstance> tilesList = new ArrayList<>();
    private ArrayList<Position> posList = new ArrayList<>();
    private Game game;
    private BoardView boardView;
    private Button cancelMoveButton;
    private RackView rackView;

    private InteractionPanel interactionPanel;
    
    public InteractionPanelController(ComboBox<WordDirection> directionComboBox,ComboBox<Character> letterComboBox, TextField positionInputField, Button addTileButton,
    		Button playMoveButton,Game game, BoardView boardView, RackView rackView,InteractionPanel interactionPanel,Button cancelMoveButton) {
        this.letterComboBox = letterComboBox;
        this.positionInputField = positionInputField;
        this.addTileButton = addTileButton;
        this.playMoveButton = playMoveButton;
        this.game = game;
        this.boardView = boardView;
        this.interactionPanel = interactionPanel;
        this.directionComboBox = directionComboBox;
        this.cancelMoveButton = cancelMoveButton;
        this.rackView = rackView;
        
        initialize();
    }

    private void initialize() {
        addTileButton.setOnAction(new AddTileHandler());
        playMoveButton.setOnAction(new PlayMoveHandler());
        cancelMoveButton.setOnAction(new CancelMoveHandler());
    }
    
    private class CancelMoveHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            posList.clear();
            tilesList.clear();
            interactionPanel.refreshPanel();
            boardView.refreshBoard();
        }
    }
    
   
    
    private class AddTileHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            String positionText = positionInputField.getText().trim();
            Character selectedTile = letterComboBox.getValue();
            TileInstance tile;
            interactionPanel.setErrorLabel("");
            if ((isValidPosition(positionText)) && (selectedTile != null)) {
                Position position = splitPosition(positionText);
                if (selectedTile=='j') {
                	tile = handleJoker();
                } else {
                	tile = new TileInstance(Tiles.charToTile(selectedTile));
                }
                
				tilesList.add(tile);
                posList.add(position);

                positionInputField.clear();
                letterComboBox.getItems().remove(selectedTile);
                
            } else {
            	interactionPanel.setErrorLabel("Position invalide: " + positionText);
                
            }
        }
    }

    private class PlayMoveHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Move move = new Move(tilesList, posList, game, game.getCurrentPlayer(),directionComboBox.getValue() , game.getBoard(), game.getTurn());
            if (move.canBePlaced()) {
                game.playMove(move);
                game.getGameMaster().givePoint(move);
                game.setJokerScoreTo0(move);
                game.getCurrentPlayer().getRack().addTile(game.getBag());
                game.setTurn(game.getTurn()+1);
                
            }
            interactionPanel.setErrorLabel("Mot invalide " );
            tilesList.clear();
            posList.clear();
            interactionPanel.refreshPanel();
            boardView.refreshBoard();
            rackView.refreshRackView();System.out.println("caca");
        }
    }
    
    
    
    private TileInstance handleJoker() {
        ArrayList<Character> choices = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            choices.add(c);
        }

        ChoiceDialog<Character> dialog = new ChoiceDialog<>('A', choices);
        dialog.setTitle("Joker");
        dialog.setHeaderText("Choix du Joker ");
        dialog.setContentText("Choisissez une lettre pour remplacer le joker:");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        Optional<Character> result = dialog.showAndWait();
        if (result.isPresent()) {
            Character newLetter = result.get();
            TileInstance jokerTile = new TileInstance(Tiles.charToTile(newLetter));
            jokerTile.setValue(Tiles.charToTile(newLetter).getValue());
            jokerTile.setWasAJoker(true);
            return jokerTile;
        } else {
            
            return null;
        }
    }
    
    
    private boolean isValidPosition(String positionText) {
    	String[] parts = positionText.split(","); 
		
	    if (parts.length == 2) {
	        String letterPart = parts[0].trim().toUpperCase();   
	        String numberPart = parts[1].trim();           
	
	        if (letterPart.length() == 1 && letterPart.charAt(0) >= 'A' && letterPart.charAt(0) <= 'O') { 
	             
	            try { 
	                int column = Integer.parseInt(numberPart) - 1; 
	                if (column >= 0 && column <= 14) { 
	             	   
	                   return true; 
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
        return false;
    }
    
    private Position splitPosition(String positionText) {
    	String[] parts = positionText.split(",");
    	String letterPart = parts[0].trim().toUpperCase();   
        String numberPart = parts[1].trim(); 
        
        int row = Integer.parseInt(numberPart) - 1;
        int column = letterPart.charAt(0) - 'A';
        
        return new Position(column,row);
        
    }
    
}
