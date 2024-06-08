package scrabble.test;

import org.junit.jupiter.api.Test;
import scrabble.model.Multiplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplierTest {

    @Test
    public void testMultiplierEnum() {
        assertEquals(Multiplier.TRIPLE_LETTER, Multiplier.valueOf("TRIPLE_LETTER"));
        assertEquals(Multiplier.TRIPLE_WORD, Multiplier.valueOf("TRIPLE_WORD"));
        assertEquals(Multiplier.DOUBLE_LETTER, Multiplier.valueOf("DOUBLE_LETTER"));
        assertEquals(Multiplier.DOUBLE_WORD, Multiplier.valueOf("DOUBLE_WORD"));
    }
}
