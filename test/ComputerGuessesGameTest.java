import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
    void testMakeLowerGuess() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        int guess = g.firstGuess();
        int lowerGuess = g.makeLowerGuess();
        assertTrue(lowerGuess < guess);
    }

    @Test
    void testFindsLowerBound() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess > 1) {
                guess = g.makeLowerGuess();
            }
            assertTrue(guess==1);
        });
    }

    @Test
    void testFindsUpperBound() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess < 1000) {
                guess = g.makeHigherGuess();
            }
            assertTrue(guess==1000);
        });

    }

    @Test
    void testFindsMidPoint() {
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess != 500) {
                if(guess < 500)
                    guess = g.makeHigherGuess();
                else guess = g.makeLowerGuess();
            }
            assertTrue(guess==500);
        });
    }

    @Test
    void testFindsRandomPoint() {
        Random r = new Random(100);
        int answer = r.nextInt(1000) + 1;
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess != answer) {
                if(guess < answer)
                    guess = g.makeHigherGuess();
                else guess = g.makeLowerGuess();
            }
            assertTrue(guess==answer);
        });
    }

    @Test
    void testGetLastGuess() {
        Random r = new Random(400);
        int answer = r.nextInt(1000) + 1;
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess != answer) {
                if(guess < answer)
                    guess = g.makeHigherGuess();
                else guess = g.makeLowerGuess();
            }
            assertTrue(guess==answer);
        });
        assertEquals(g.getLastGuess(), answer);
    }

    @Test
    void testGuessNumber() {
        Random r = new Random(300);
        int answer = r.nextInt(1000) + 1;
        AtomicInteger guessNo = new AtomicInteger();
        ComputerGuessesGame g = new ComputerGuessesGame(1,1000);
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int guess = g.firstGuess();
            while(guess != answer) {
                guessNo.getAndIncrement();
                if(guess < answer)
                    guess = g.makeHigherGuess();
                else guess = g.makeLowerGuess();
            }
            assertTrue(guess==answer);
        });
        assertEquals(g.getNumGuesses(), guessNo.get());
    }
}