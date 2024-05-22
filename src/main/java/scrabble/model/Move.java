package scrabble.model;

import java.util.List;
import java.util.stream.Collectors;

public class Move {
    private Player player ;
    private int score;
    private String word;
    private String wordDirection;
    private int startingPosX;
    private int startingPosY;
    private Board board;

    public Move(Player player,Board board, String word, String wordDirection, int startingPosX,int startingPosY) {
        this.player = player;
        this.board = board;
        this.word = word;
        this.score = 0;
        this.wordDirection = wordDirection;
        this.startingPosX = startingPosX;
        this.startingPosY = startingPosY;
    }
    
    public boolean hasRequiredTiles() {
        List<Character> charList = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Tiles> rackLetterList = player.getRack().getTilesOnRack();


        for (Tiles tile : rackLetterList) {
            if ( charList.contains(tile.name().charAt(0)) ){
                charList.remove(tile.name().charAt(0));
            }
        }

        return (charList.size() == 0);
    }
}