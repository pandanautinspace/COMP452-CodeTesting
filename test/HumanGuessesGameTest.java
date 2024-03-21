import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HumanGuessesGameTest {

    @Test
    void testHigherGuess() {
        Random r = new Random(0); //this will generate a value of 187
        HumanGuessesGame h = new HumanGuessesGame(r);
        assertEquals(h.makeGuess(200), GuessResult.HIGH);
    }

    @Test
    void testLowerGuess() {
        Random r = new Random(0); //this will generate a value of 187
        HumanGuessesGame h = new HumanGuessesGame(r);
        assertEquals(h.makeGuess(105), GuessResult.LOW);
    }

    @Test
    void testCorrectGuess() {
        Random r = new Random(0); //this will generate a value of 187
        HumanGuessesGame h = new HumanGuessesGame(r);
        assertEquals(h.makeGuess(187), GuessResult.CORRECT);
    }

    @Test
    void testMultiGuessCorrectness() {
        Random r = new Random(0); //this will generate a value of 187
        HumanGuessesGame h = new HumanGuessesGame(r);
        assertEquals(h.makeGuess(0), GuessResult.LOW);
        assertEquals(h.makeGuess(100), GuessResult.LOW);
        assertEquals(h.makeGuess(200), GuessResult.HIGH);
        assertEquals(h.makeGuess(300), GuessResult.HIGH);
        assertEquals(h.makeGuess(1300), GuessResult.HIGH);
        assertEquals(h.makeGuess(187), GuessResult.CORRECT);
    }

    @Test
    void testNoGuessNumber() {
        HumanGuessesGame h = new HumanGuessesGame();
        assertEquals(h.getNumGuesses(), 0);
    }

    @Test
    void testOneGuessNumber() {
        HumanGuessesGame h = new HumanGuessesGame();
        h.makeGuess(100);
        assertEquals(h.getNumGuesses(), 1);
    }

    @Test
    void testMultiGuessNumber() {
        Random r = new Random(0); //this will generate a value of 187
        HumanGuessesGame h = new HumanGuessesGame(r);
        h.makeGuess(0);
        h.makeGuess(100);
        h.makeGuess(200);
        assertEquals(h.getNumGuesses(), 3);
        h.makeGuess(300);
        h.makeGuess(1300);
        h.makeGuess(187);
        assertEquals(h.getNumGuesses(), 6);
    }
}