/**
 * Class for when the computer is guessing a number
 *
 * Generates the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 */
public class ComputerGuessesGame {
    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame() {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
    }

    public int firstGuess() {
        lastGuess = (lowerBound + upperBound + 1) / 2;
//        numGuesses += 1;
        return lastGuess;
    }
    public int lowerGuess() {
        upperBound = Math.min(upperBound, lastGuess);

        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
        return lastGuess;
    }

    public int higherGuess() {
        lowerBound = Math.max(lowerBound, lastGuess + 1);

        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
        return lastGuess;
    }

    public int getLastGuess() {
        return lastGuess;
    }

    public int getNumGuesses() {
        return numGuesses;
    }
}
