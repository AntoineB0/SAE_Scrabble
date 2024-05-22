package scrabble.model;

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
}