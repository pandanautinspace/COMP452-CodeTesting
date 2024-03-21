import java.util.Random;

/**
 * A game where a human guesses a number between 1 and UPPER_BOUND
 * Tracks the target, the number of guesses made, and if the number has been guessed
 *
 * NOTE: You can refactor and edit this file if needed
 */
public class HumanGuessesGame extends GuessingGame {
    public final static int UPPER_BOUND = 1000;

    private final int target;

    HumanGuessesGame(){
        Random randGen = new Random();
        this.target = randGen.nextInt(UPPER_BOUND) + 1;

        numGuesses = 0;
    }

    GuessResult makeGuess(int value){
        numGuesses += 1;

        if(value < target){
            return GuessResult.LOW;
        }
        if(value > target){
            return GuessResult.HIGH;
        }

        return GuessResult.CORRECT;
    }
}
