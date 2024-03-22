import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StatsBinTest {

    @Test
    void testGetBinNames() {
        int[] binEdges = {1, 2, 4, 6, 8, 10, 12, 14};
        StatsBin statsBin = new StatsBin(binEdges);
        List<String> names = Arrays.asList("1", "2-3", "4-5", "6-7", "8-9", "10-11", "12-13", "14 or more");
        List<String> binNames = statsBin.getBinNames();
        assertTrue(IntStream.range(0, names.size()).allMatch(i -> binNames.get(i).equals(names.get(i))));
    }

    @Test
    void getBinGames() {
        Random r = new Random(0);
        int[] binEdges = {1, 2, 4, 6, 8, 10, 12, 14};
        StatsBin statsBin = new StatsBin(binEdges);
        MockGameStats gs = new MockGameStats();
        List<Integer> guessNumsReal = new ArrayList<>();
        int edgeIndex = 0;
        for(int i = 0; i < 15; i++) {
            if((edgeIndex < binEdges.length) && ((i + 1) >= binEdges[edgeIndex])) {
                edgeIndex++;
                guessNumsReal.add(0);
            }
            int j;
            for(j = 0; j < r.nextInt(5) + 1; j++) {
                gs.addGuess(i + 1);
            }
            int index = guessNumsReal.size() - 1;
            guessNumsReal.set(index, guessNumsReal.get(index) + j);
        }
        //using dependency injection
        List<Integer> guessNums = statsBin.getBinGames(gs);
        assertTrue(IntStream.range(0, guessNums.size()).allMatch(i ->
            guessNums.get(i) == guessNumsReal.get(i)));
    }
}