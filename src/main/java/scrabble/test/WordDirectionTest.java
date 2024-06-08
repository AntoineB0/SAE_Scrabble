package scrabble.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import scrabble.model.WordDirection;

public class WordDirectionTest {

    @Test
    public void testWordDirectionValues() {
        // Vérifier que les valeurs de l'énumération sont correctes
        assertEquals(WordDirection.HORIZONTAL, WordDirection.valueOf("HORIZONTAL"));
        assertEquals(WordDirection.VERTICAL, WordDirection.valueOf("VERTICAL"));

    }

    @Test
    public void testWordDirectionCount() {
        // Vérifier que le nombre de valeurs de l'énumération est correct
        assertEquals(2, WordDirection.values().length);
    }

    @Test
    public void testWordDirectionNames() {
        // Vérifier les noms des valeurs de l'énumération
        String[] expectedNames = {"HORIZONTAL", "VERTICAL"};
        WordDirection[] directions = WordDirection.values();
        for (int i = 0; i < directions.length; i++) {
            assertEquals(expectedNames[i], directions[i].name());
        }
    }
}
