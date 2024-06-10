package scrabble.view;



import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import scrabble.controller.InteractionPanelController;
import scrabble.model.Game;
import scrabble.model.WordDirection;

public class InteractionPanel {
    private Game game;
    private BoardView boardView;
    private RackView rackView;
    private Label playerNameLabel;
    private Label turnLabel;
    private Label scoreValue;
    private ComboBox<Character> letterComboBox;
    private ComboBox<WordDirection> directionComboBox;
    private Label errorLabel;
    StackPane scoreBackground;
    
    public InteractionPanel(Game game,BoardView boardView,RackView rackView) {
		this.game = game;
		this.boardView = boardView;
        this.rackView = rackView;
        
	}

	public VBox createInteractionPanel() {
		VBox interactionPanelBox = new VBox();
        
		interactionPanelBox.setAlignment(Pos.CENTER_RIGHT);
        Button swapTileButton = new Button("Echanger");
        Button quitButton = new Button("Quitter");
        letterComboBox = new ComboBox<>();
        TextField positionInputField = new TextField("Exemple : A,1");
        Label comboBoxLabel = new Label("Tuile : ");
        Label positionLabel = new Label("Position : ");
        Button addTileButton = new Button("Ajouter tuile");
        Button playMoveButton = new Button("Jouer coup");
        Button cancelMoveButton = new Button("Annuler l'action");
        errorLabel = new Label("");
        directionComboBox = new ComboBox<>();
        directionComboBox.setItems(FXCollections.observableArrayList(WordDirection.HORIZONTAL, WordDirection.VERTICAL));
        directionComboBox.setValue(WordDirection.HORIZONTAL);
        
        

        letterComboBox.setItems(FXCollections.observableArrayList(game.getCurrentPlayer().getRack().getTilesOnRackToChar()));

        VBox inputFields = new VBox(10);
        inputFields.getChildren().addAll(comboBoxLabel, letterComboBox, positionLabel, positionInputField,errorLabel,directionComboBox,addTileButton,cancelMoveButton);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setStyle(css_style_background);
        inputFields.setPrefSize(350, 500);
        
        
        
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(playMoveButton,swapTileButton, quitButton );
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle(css_style_background);
        buttons.setPrefSize(350, 70);
        quitButton.setOnAction(e -> Platform.exit());
        
        VBox gameDataBox = new VBox();
    	gameDataBox.setStyle(css_style_background);
    	gameDataBox.setMinSize(350, 400);
    	
    	InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0);
        innerShadow.setOffsetY(2.0);
        innerShadow.setColor(Color.GRAY);
        gameDataBox.setEffect(innerShadow);
        DropShadow dropShadow = new DropShadow();
        gameDataBox.setEffect(dropShadow);
        
        turnLabel = new Label("Tour : "+ String.valueOf(game.getTurn()));
        turnLabel.setFont(new Font("Arial", 32)); 
        
        
        playerNameLabel = new Label("Nom du joueur : " + game.getCurrentPlayer().getName());
        playerNameLabel.setFont(new Font("Arial", 24));
        
        gameDataBox.getChildren().addAll(turnLabel,playerNameLabel,createScoreBox());
        gameDataBox.setAlignment(Pos.CENTER);


        interactionPanelBox.getChildren().addAll(gameDataBox, inputFields, buttons);
        interactionPanelBox.setAlignment(Pos.CENTER);
        interactionPanelBox.setPadding(new Insets(0,0,0,80));
        interactionPanelBox.setSpacing(20);

        
		InteractionPanelController interactionController = new InteractionPanelController(swapTileButton,directionComboBox,letterComboBox, positionInputField, addTileButton, playMoveButton, game, boardView, rackView, this,cancelMoveButton);

        return interactionPanelBox;
	}
	
	public Game getGame() {
		return game;
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public RackView getRackView() {
		return rackView;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public void setRackView(RackView rackView) {
		this.rackView = rackView;
	}
	
	public void refreshPanel() {
		playerNameLabel.setText("Nom du joueur : " + game.getCurrentPlayer().getName());
	    turnLabel.setText("Tour : " + game.getTurn());
	    scoreValue.setText(String.valueOf(game.getCurrentPlayer().getScore()));
	    directionComboBox.setValue(WordDirection.HORIZONTAL);
	    letterComboBox.setItems(FXCollections.observableArrayList(game.getCurrentPlayer().getRack().getTilesOnRackToChar()));
	    scoreBackground.setStyle("-fx-border-color: black;"
        		+ " -fx-border-width: 4px;"
        		+  " -fx-background-color:"+(game.getTurn()%2==0?"red;":"blue;")
        		+ "-fx-border-radius: 8px;"
        		+ "-fx-background-radius:10px;"
        		+ "-fx-font-weight:bold;"
        		+ "-fx-font-size:32;");
	    scoreBackground.setMaxSize(120, 120);
    }
	
    private VBox createGameDataBox() {
    	VBox gameDataBox = new VBox();
    	gameDataBox.setStyle(css_style_background);
    	
    	gameDataBox.setMinSize(350, 400);
    	
    	InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0);
        innerShadow.setOffsetY(2.0);
        innerShadow.setColor(Color.GRAY);
        gameDataBox.setEffect(innerShadow);
        DropShadow dropShadow = new DropShadow();
        gameDataBox.setEffect(dropShadow);
        
        turnLabel = new Label("Tour : "+ String.valueOf(game.getTurn()));
        turnLabel.setFont(new Font("Arial", 32)); 
        
        
        playerNameLabel = new Label("Nom du joueur : " + game.getCurrentPlayer().getName());
        playerNameLabel.setFont(new Font("Arial", 24));
        
        gameDataBox.getChildren().addAll(turnLabel,playerNameLabel,createScoreBox());
        gameDataBox.setAlignment(Pos.CENTER);
    	return gameDataBox;
    }
	
	private VBox createScoreBox() {
		VBox score = new VBox(20);
	    Label scoreLabel = new Label("Score");
	    scoreValue = new Label(String.valueOf(game.getCurrentPlayer().getScore()));
	    scoreValue.setStyle("-fx-text-fill: white;");
	    scoreBackground = new StackPane();
	    scoreBackground.setStyle("-fx-border-color: black;"
        		+ " -fx-border-width: 4px;"
        		+ " -fx-background-color: blue;"
        		+ "-fx-border-radius: 8px;"
        		+ "-fx-background-radius:10px;"
        		+ "-fx-font-weight:bold;"
        		+ "-fx-font-size:32;");
	    scoreBackground.getChildren().add(scoreValue);
	    scoreBackground.setAlignment(Pos.CENTER);
	    
	    scoreBackground.setMaxSize(120, 120);
	    score.getChildren().addAll(scoreLabel,scoreBackground);
	    score.setAlignment(Pos.CENTER);
	    return score;
	}
	private static final String css_style_background = "-fx-border-color: grey;"
			+ " -fx-border-width: 3px;"
			+ " -fx-background-color: white;"
			+ "-fx-border-radius: 20 0 0 20 ;"
			+ "-fx-background-radius: 24 0 0 24;";
	public void setErrorLabel(String errorMessage) {
		errorLabel.setText(errorMessage);
	}
	
}
