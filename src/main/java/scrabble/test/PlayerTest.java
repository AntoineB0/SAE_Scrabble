package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.Player;
import scrabble.model.Rack;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Rack rack = new Rack();
        Player player = new Player("Alice", rack);
        
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(rack, player.getRack());
    }

    @Test
    public void testSetName() {
        Player player = new Player("Bob", new Rack());
        player.setName("Charlie");
        assertEquals("Charlie", player.getName());
    }

    @Test
    public void testSetScore() {
        Player player = new Player("Dave", new Rack());
        player.setScore(100);
        assertEquals(100, player.getScore());
    }

    @Test
    public void testSetRack() {
        Rack rack1 = new Rack();
        Rack rack2 = new Rack();
        Player player = new Player("Eve", rack1);
        player.setRack(rack2);
        assertEquals(rack2, player.getRack());
    }

    @Test
    public void testToString() {
        Player player = new Player("Frank", new Rack());
        assertEquals("Frank your score is 0 !", player.toString());
    }
}
