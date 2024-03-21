import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessingGameTest {

    @Test
    void testGetNumGuesses() {
        GuessingGame a = new GuessingGame() {
            @Override
            public int getNumGuesses() {
                return super.getNumGuesses();
            }
        };
        assertEquals(a.getNumGuesses(), 0);
    }
}