package scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Move {
    private Player player ;
    private int score;
    private String word;
    private WordDirection wordDirection;
    private int startingPosX;
    private int startingPosY;
    private Board board;

    public Move(Player player,Board board, String word, WordDirection wordDirection, int startingPosX,int startingPosY) {
        this.player = player;
        this.board = board;
        this.word = word;
        this.score = 0;
        this.wordDirection = wordDirection;
        this.startingPosX = startingPosX;
        this.startingPosY = startingPosY;
    }
    
    public boolean hasRequiredTiles(List<Character> charList) {
        List<Tiles> rackLetterList = player.getRack().getTilesOnRack();


        for (Tiles tile : rackLetterList) {
            if ( charList.contains(tile.name().charAt(0)) ){
                charList.remove(tile.name().charAt(0));
            }
        }

        return (charList.size() == 0);
    }
    
    public boolean canBePlaced() {
        int x = startingPosX;
        int y = startingPosY;
        int length = word.length();
        List<Character> charList = new ArrayList<>();


        // Check if it fits in the board
        if (wordDirection == WordDirection.HORIZONTAL) {
            if (x + length > board.getColumns()) {
                return false;
            }
        } else {
            if (y + length > board.getRows()) {
                return false;
            }
        }

 

        return hasRequiredTiles(charList);
    }
}