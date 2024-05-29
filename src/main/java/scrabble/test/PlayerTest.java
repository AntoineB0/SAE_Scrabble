package scrabble.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Player;
import scrabble.model.Rack;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Rack rack;

    @BeforeEach
    public void setUp() {
        rack = new Rack();
        player = new Player("John", rack);
    }

    @Test
    public void testConstructorWithScore() {
        Player playerWithScore = new Player("Alice", 10, rack);
        assertEquals("Alice", playerWithScore.getName());
        assertEquals(10, playerWithScore.getScore());
        assertEquals(rack, playerWithScore.getRack());
    }

    @Test
    public void testConstructorWithoutScore() {
        assertEquals("John", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(rack, player.getRack());
    }

    @Test
    public void testGetName() {
        assertEquals("John", player.getName());
    }

    @Test
    public void testSetName() {
        player.setName("Alice");
        assertEquals("Alice", player.getName());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
    }

    @Test
    public void testSetScore() {
        player.setScore(20);
        assertEquals(20, player.getScore());
    }

    @Test
    public void testGetRack() {
        assertEquals(rack, player.getRack());
    }

    @Test
    public void testSetRack() {
        Rack newRack = new Rack();
        player.setRack(newRack);
        assertEquals(newRack, player.getRack());
    }

    @Test
    public void testToString() {
        assertEquals("John your score is 0 !", player.toString());
    }

    @Test
    public void testAddScore() {
        player.addScore(15);
        assertEquals(15, player.getScore());
        player.addScore(10);
        assertEquals(25, player.getScore());
    }
}
