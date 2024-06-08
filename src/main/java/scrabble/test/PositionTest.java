package scrabble.test;

import org.junit.jupiter.api.Test;

import scrabble.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testPositionInitialization() {
        Position position = new Position(3, 5);
        assertEquals(3, position.getX());
        assertEquals(5, position.getY());
    }


    

    @Test
    public void testToString() {
        Position position = new Position(2, 4);
        assertEquals("Position [x=2, y=4]", position.toString());
    }



    @Test
    public void testSetters() {
        Position position = new Position(0, 0);
        position.setX(7);
        position.setY(9);
        assertEquals(7, position.getX());
        assertEquals(9, position.getY());

        // Vérification après utilisation des setters
        position.setX(3);
        position.setY(6);
        assertEquals(3, position.getX());
        assertEquals(6, position.getY());
    }
}
