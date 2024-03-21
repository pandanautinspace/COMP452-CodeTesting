import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Data class to hold the result of a game
 * NOTE: You can refactor and edit this file if needed
 */
public class GameResult {
    public final boolean humanWasPlaying;
    private final int correctValue;
    private final int numGuesses;

    public GameResult(boolean humanWasPlaying, int correctValue, int numGuesses){
        this.humanWasPlaying = humanWasPlaying;
        this.correctValue = correctValue;
        this.numGuesses = numGuesses;
    }

    public String generateAnswerText() {
        return "The answer was " + correctValue + ".";
    }

    public String generateNumGuessesText() {
        if(numGuesses == 1){
            return (humanWasPlaying ? "You" : "I") + " guessed it on the first try!";
        }
        else {
            return "It took " + (humanWasPlaying ? "you" : "me") + " " + numGuesses + " guesses.";
        }
    }

    public int getNumGuesses() {
        return numGuesses;
    }
}
