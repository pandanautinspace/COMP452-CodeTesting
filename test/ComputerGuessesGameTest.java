import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerGuessesGameTest {

    @Test
    void testFirstGuessBounds() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        int guess = g.firstGuess();
        assertTrue(guess <= 1000 && guess >= 1);
    }

    @Test
    void testFirstGuessNumber() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        int guess = g.firstGuess();
        assertEquals(g.getNumGuesses(), 0); //not sure if this should be zero or one
    }

    @Test
    void testBadBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComputerGuessesGame(1000,1);
        });
    }

    @Test
    void makeLowerGuess() {
    }

    @Test
    void makeHigherGuess() {
    }

    @Test
    void getLastGuess() {
    }
}