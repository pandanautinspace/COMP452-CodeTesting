/**
 * Class for when the computer is guessing a number
 *
 * Generates the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 */
public class ComputerGuessesGame extends GuessingGame {
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame(int lower, int upper) {
        numGuesses = 0;
        upperBound = upper;
        lowerBound = lower;
    }

    public int firstGuess() {
        lastGuess = (lowerBound + upperBound + 1) / 2;
//        numGuesses += 1;
        return lastGuess;
    }
    public int makeLowerGuess() {
        upperBound = Math.min(upperBound, lastGuess);
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
        return lastGuess;
    }

    public int makeHigherGuess() {
        lowerBound = Math.max(lowerBound, lastGuess + 1);
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
        return lastGuess;
    }

    public int getLastGuess() {
        return lastGuess;
    }

}
