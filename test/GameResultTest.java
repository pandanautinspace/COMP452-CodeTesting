import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    @Test
    void testGenerateAnswerText() {
        GameResult g = new GameResult(true, 100, 5);
        assertEquals(g.generateAnswerText(), "The answer was 100.");
    }

    @Test
    void testHumanPlayingNot1Guess() {
        GameResult g = new GameResult(true, 100, 5);
        assertEquals(g.generateNumGuessesText(), "It took you 5 guesses.");
    }

    @Test
    void testCPUPlayingNot1Guess() {
        GameResult g = new GameResult(false, 100, 5);
        assertEquals(g.generateNumGuessesText(), "It took me 5 guesses.");
    }

    @Test
    void testNumGuessesNot1Guess() {
        GameResult g = new GameResult(true, 100, 10);
        assertEquals(g.generateNumGuessesText(), "It took you 10 guesses.");
    }

    @Test
    void testHumanPlaying1Guess() {
        GameResult g = new GameResult(true, 100, 1);
        assertEquals(g.generateNumGuessesText(), "You guessed it on the first try!");
    }

    @Test
    void testCPUPlaying1Guess() {
        GameResult g = new GameResult(false, 100, 1);
        assertEquals(g.generateNumGuessesText(), "I guessed it on the first try!");
    }

    @Test
    void testGetNumGuesses() {
        GameResult g = new GameResult(true, 100, 5);
        assertEquals(g.getNumGuesses(), 5);

    }
}