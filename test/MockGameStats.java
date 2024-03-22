import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class MockGameStats extends GameStats {
    private Map<Integer, Integer> statsMap;
    public MockGameStats() {
        statsMap = new HashMap<>();
    }

    public void addGuess(int guess) {
        int orig = statsMap.getOrDefault(guess, 0);
        statsMap.put(guess, orig + 1);
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses() {
        return statsMap.keySet().stream().max(Comparator.comparingInt(x -> x)).get();
    }
}